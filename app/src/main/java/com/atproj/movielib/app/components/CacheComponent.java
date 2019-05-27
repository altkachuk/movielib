package com.atproj.movielib.app.components;

import com.atproj.movielib.app.modules.CacheModule;
import com.atproj.movielib.domain.cache.MovieLibCache;

import dagger.Component;

@Component(
        modules = {
                CacheModule.class
        },
        dependencies = {
                DatabaseComponent.class
        })
public interface CacheComponent {

    MovieLibCache movieLibCache();

}
