package com.atproj.movielib.presenter;

import android.content.Context;

import com.atproj.movielib.domain.interactors.MovieInteractor;
import com.atproj.movielib.domain.interactors.callback.PaginatedResourceRequestCallback;
import com.atproj.movielib.domain.interactors.callback.ResourceRequestCallback;
import com.atproj.movielib.domain.repository.exception.BaseException;
import com.atproj.movielib.model.Movie;
import com.atproj.movielib.view.MovieView;

import java.util.List;

import javax.inject.Inject;

public class MoviePresenter extends BasePresenter {

    @Inject
    MovieInteractor movieInteractor;

    private MovieView view;

    public MoviePresenter(Context context, MovieView view) {
        super(context);
        this.view = view;
    }

    public void test() {
        movieInteractor.searchMovie("pulp", new PaginatedResourceRequestCallback<Movie>() {
            @Override
            public void onSuccess(List<Movie> resource, int page, int totalResults, int totalPages) {

            }

            @Override
            public void onError(BaseException e) {

            }
        });


        /*movieInteractor.getMovieDetails("680", new ResourceRequestCallback<Movie>() {
            @Override
            public void onSuccess(Movie movie) {

            }

            @Override
            public void onError(BaseException e) {

            }
        });*/
    }
}
