package com.atproj.movielib.app.components;

import com.atproj.movielib.app.scopes.RuntimeScope;
import com.atproj.movielib.presenter.MoviePresenter;
import com.atproj.movielib.ui.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@RuntimeScope
@Component(dependencies = {
        ApplicationComponent.class,
        InteractorComponent.class
})

@Singleton
public interface MovieLibComponent {

    // activities
    void inject(MainActivity mainActivity);

    // presenters
    void inject(MoviePresenter moviePresenter);

}
