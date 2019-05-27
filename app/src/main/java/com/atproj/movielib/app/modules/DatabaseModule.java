package com.atproj.movielib.app.modules;

import android.content.Context;

import com.atproj.movielib.database.MovieLibDatabase;
import com.atproj.movielib.database.MovieLibDatabaseSQLite;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    MovieLibDatabase movieLibDatabase(Context context) {
        return new MovieLibDatabaseSQLite(context);
    }
}
