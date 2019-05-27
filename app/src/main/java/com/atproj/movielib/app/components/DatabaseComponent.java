package com.atproj.movielib.app.components;

import com.atproj.movielib.app.modules.DatabaseModule;
import com.atproj.movielib.database.MovieLibDatabase;

import dagger.Component;

@Component(
        modules = {
                DatabaseModule.class
        },
        dependencies = {
                ApplicationComponent.class
        })
public interface DatabaseComponent {

    MovieLibDatabase movieLibDatabase();

}
