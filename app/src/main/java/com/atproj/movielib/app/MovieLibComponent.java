package com.atproj.movielib.app;

import com.atproj.movielib.app.components.ApplicationComponent;
import com.atproj.movielib.app.components.InteractorComponent;
import com.atproj.movielib.app.components.PicassoComponent;
import com.atproj.movielib.app.components.RuntimeComponent;
import com.atproj.movielib.app.scopes.RuntimeScope;
import com.atproj.movielib.presenter.MoviePresenter;
import com.atproj.movielib.presenter.SearchMoviePresenter;
import com.atproj.movielib.presenter.VideosPresenter;
import com.atproj.movielib.ui.activities.MovieActivity;
import com.atproj.movielib.ui.activities.MovieLibActivity;
import com.atproj.movielib.ui.viewholder.MovieViewHolder;

import javax.inject.Singleton;

import dagger.Component;

@RuntimeScope
@Component(dependencies = {
        ApplicationComponent.class,
        InteractorComponent.class,
        PicassoComponent.class,
        RuntimeComponent.class
})

@Singleton
public interface MovieLibComponent {

    // activities
    void inject(MovieLibActivity movieLibActivity);
    void inject(MovieActivity movieActivity);

    // presenters
    void inject(SearchMoviePresenter searchMoviePresenter);
    void inject(MoviePresenter moviePresenter);
    void inject(VideosPresenter videosPresenter);

    // viewholders
    void inject(MovieViewHolder movieViewHolder);

}
