package com.atproj.movielib.app.modules;

import com.atproj.movielib.model.singleton.SearchMovieContext;

import dagger.Module;
import dagger.Provides;

@Module
public class RuntimeModule {

    private final SearchMovieContext searchMovieContext;

    public RuntimeModule() {
        searchMovieContext = new SearchMovieContext();
    }

    @Provides
    SearchMovieContext searchMovieContext() {
        return searchMovieContext;
    }

}
