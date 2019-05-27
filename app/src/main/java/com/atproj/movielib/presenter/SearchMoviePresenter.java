package com.atproj.movielib.presenter;

import android.content.Context;

import com.atproj.movielib.domain.interactors.MovieInteractor;
import com.atproj.movielib.domain.interactors.callback.PaginatedResourceRequestCallback;
import com.atproj.movielib.domain.repository.exception.BaseException;
import com.atproj.movielib.model.Movie;
import com.atproj.movielib.view.SearchMovieView;

import java.util.List;

import javax.inject.Inject;

public class SearchMoviePresenter extends BasePresenter {

    @Inject
    MovieInteractor movieInteractor;

    private SearchMovieView view;

    public SearchMoviePresenter(Context context, SearchMovieView view) {
        super(context);
        this.view = view;
    }

    public void search(String query, int page) {
        movieInteractor.searchMovie(query, page, new PaginatedResourceRequestCallback<Movie>() {
            @Override
            public void onSuccess(List<Movie> resource, int page, int totalResults, int totalPages) {
                view.onResourcesResponse(resource, page, totalResults, totalPages);
            }

            @Override
            public void onError(BaseException e) {

            }
        });
    }
}
