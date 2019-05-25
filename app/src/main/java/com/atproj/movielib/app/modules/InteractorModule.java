package com.atproj.movielib.app.modules;

import com.atproj.movielib.domain.executor.InteractorExecutor;
import com.atproj.movielib.domain.executor.MainThreadExecutor;
import com.atproj.movielib.domain.interactors.MovieInteractor;
import com.atproj.movielib.domain.interactors.MovieInteractorImpl;
import com.atproj.movielib.domain.repository.MovieLibRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class InteractorModule {

    public InteractorModule() {
        ;
    }

    @Provides
    MovieInteractor movieInteractor(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor, MovieLibRepository repository) {
        return new MovieInteractorImpl(interactorExecutor, mainThreadExecutor, repository);
    }

}
