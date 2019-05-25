package com.atproj.movielib.domain.repository;

import com.atproj.movielib.domain.repository.exception.BaseException;
import com.atproj.movielib.domain.repository.request.ResourceRequest;
import com.atproj.movielib.domain.repository.response.PaginatedResourcesResponse;
import com.atproj.movielib.domain.repository.response.ResourceResponse;
import com.atproj.movielib.model.Movie;
import com.atproj.movielib.model.Video;

public interface MovieLibRepository {

    PaginatedResourcesResponse<Movie> searchMovie(ResourceRequest request) throws BaseException;

    Movie getMovieDetails(ResourceRequest request) throws BaseException;

    PaginatedResourcesResponse<Video> getMovieVideos(String movieId) throws BaseException;

}
