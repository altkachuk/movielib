package com.atproj.movielib.app.modules;

import com.atproj.movielib.domain.executor.MainThreadExecutor;
import com.atproj.movielib.domain.repository.MovieLibApiRepository;
import com.atproj.movielib.domain.repository.MovieLibRepository;
import com.atproj.movielib.domain.repository.MovieLibService;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    public RepositoryModule() {
        ;
    }

    @Provides
    MovieLibRepository movieLibRepository(MovieLibService api, MainThreadExecutor mainThreadExecutor) {
        return new MovieLibApiRepository(api, mainThreadExecutor);
    }

}
