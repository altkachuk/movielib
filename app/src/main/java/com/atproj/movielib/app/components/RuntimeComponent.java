package com.atproj.movielib.app.components;

import com.atproj.movielib.app.modules.RuntimeModule;
import com.atproj.movielib.model.singleton.SearchMovieContext;

import dagger.Component;

@Component(
        modules = {
                RuntimeModule.class
        })
public interface RuntimeComponent {

    SearchMovieContext searchMovieContext();

}
