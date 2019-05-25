package com.atproj.movielib.app.components;

import com.atproj.movielib.app.modules.NetworkModule;
import com.atproj.movielib.domain.repository.MovieLibService;

import dagger.Component;
import retrofit2.Retrofit;

@Component(
        modules = {
                NetworkModule.class
        },
        dependencies = {
                ApplicationComponent.class
        }
)
public interface NetworkComponent {

    MovieLibService movieLibApi();

}
