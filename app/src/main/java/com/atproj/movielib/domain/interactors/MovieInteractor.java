package com.atproj.movielib.domain.interactors;

import com.atproj.movielib.domain.interactors.callback.PaginatedResourceRequestCallback;
import com.atproj.movielib.domain.interactors.callback.ResourceRequestCallback;
import com.atproj.movielib.model.Movie;
import com.atproj.movielib.model.Video;

public interface MovieInteractor {

    void searchMovie(String query, final PaginatedResourceRequestCallback<Movie> callback);

    void getMovieDetails(String id, final ResourceRequestCallback<Movie> callback);

    void getMovieVideos(String movieId, final PaginatedResourceRequestCallback<Video> callback);

}
