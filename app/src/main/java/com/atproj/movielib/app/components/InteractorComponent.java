package com.atproj.movielib.app.components;

import com.atproj.movielib.app.modules.InteractorModule;
import com.atproj.movielib.domain.interactors.MovieInteractor;

import dagger.Component;

@Component(
        modules = {
                InteractorModule.class
        },
        dependencies = {
                ApplicationComponent.class,
                RepositoryComponent.class,
                ExecutorComponent.class,
                CacheComponent.class
        }
)
public interface InteractorComponent {

    MovieInteractor movieInteractor();

}
