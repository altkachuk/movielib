package com.atproj.movielib.domain.cache;

import com.atproj.movielib.domain.repository.request.ResourceRequest;
import com.atproj.movielib.domain.repository.response.PaginatedResourcesResponse;
import com.atproj.movielib.model.Movie;
import com.atproj.movielib.model.Video;

import java.util.List;

public interface MovieLibCache {

    void cacheMovies(List<Movie> movies);
    void cacheMovie(Movie movie);
    void cacheVideos(List<Video> videos, String movieId);

    PaginatedResourcesResponse<Movie> searchMovie(ResourceRequest request);
    Movie getMovieDetails(ResourceRequest request);
    PaginatedResourcesResponse<Video> getMovieVideos(ResourceRequest request);

}
