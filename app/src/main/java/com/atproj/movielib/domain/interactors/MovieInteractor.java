package com.atproj.movielib.domain.interactors;

import com.atproj.movielib.domain.interactors.callback.PaginatedResourceRequestCallback;
import com.atproj.movielib.domain.interactors.callback.ResourceRequestCallback;
import com.atproj.movielib.model.Movie;
import com.atproj.movielib.model.Video;

public interface MovieInteractor {

    void searchMovie(String query, int page, final PaginatedResourceRequestCallback<Movie> callback);

    void getMovieDetails(int id, final ResourceRequestCallback<Movie> callback);

    void getMovieVideos(int movieId, int page, final PaginatedResourceRequestCallback<Video> callback);

}
