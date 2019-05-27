package com.atproj.movielib.app.modules;

import com.atproj.movielib.database.MovieLibDatabase;
import com.atproj.movielib.domain.cache.MovieLibCache;
import com.atproj.movielib.domain.cache.MovieLibDBCache;

import dagger.Module;
import dagger.Provides;

@Module
public class CacheModule {

    @Provides
    MovieLibCache movieLibCache(MovieLibDatabase movieLibDatabase) {
        return new MovieLibDBCache(movieLibDatabase);
    }
}
