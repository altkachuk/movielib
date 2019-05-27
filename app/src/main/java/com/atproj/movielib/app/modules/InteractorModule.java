package com.atproj.movielib.app.modules;

import android.content.Context;

import com.atproj.movielib.domain.cache.MovieLibCache;
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
    MovieInteractor movieInteractor(Context context, InteractorExecutor interactorExecutor,
                                    MainThreadExecutor mainThreadExecutor,
                                    MovieLibRepository repository, MovieLibCache cache) {
        return new MovieInteractorImpl(context, interactorExecutor, mainThreadExecutor, repository, cache);
    }

}
