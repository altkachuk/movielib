package com.atproj.movielib.app.components;

import com.atproj.movielib.app.modules.RepositoryModule;
import com.atproj.movielib.domain.repository.MovieLibRepository;

import dagger.Component;

@Component(
        modules = {
                RepositoryModule.class
        },
        dependencies = {
                NetworkComponent.class,
                ExecutorComponent.class
        })
public interface RepositoryComponent {

    MovieLibRepository movieLibRepository();

}
