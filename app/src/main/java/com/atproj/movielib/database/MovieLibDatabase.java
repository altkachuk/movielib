package com.atproj.movielib.database;

import com.atproj.movielib.domain.repository.response.PaginatedResourcesResponse;
import com.atproj.movielib.model.Movie;
import com.atproj.movielib.model.Video;

import java.util.List;

public interface MovieLibDatabase {

    void addMovie(Movie movie);
    void updateMovie(Movie movie);
    boolean existMovie(Movie movie);
    Movie getMovie(String id);
    PaginatedResourcesResponse<Movie> searchMovie(String query, int page, int offset);

    void addVideo(Video video, String movieId);
    void updateVideo(Video video, String movieId);
    boolean existVideo(Video video);
    PaginatedResourcesResponse<Video> getVideosByMovie(String movieId, int page, int offset);

}
