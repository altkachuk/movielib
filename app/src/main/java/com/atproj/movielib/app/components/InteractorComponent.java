package com.atproj.movielib.app.components;

import com.atproj.movielib.app.modules.InteractorModule;
import com.atproj.movielib.domain.interactors.MovieInteractor;

import dagger.Component;

@Component(
        modules = {
                InteractorModule.class
        },
        dependencies = {
                RepositoryComponent.class,
                ExecutorComponent.class
        }
)
public interface InteractorComponent {

    MovieInteractor movieInteractor();

}
