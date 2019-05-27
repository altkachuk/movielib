package com.atproj.movielib.domain.cache;

import com.atproj.movielib.database.MovieLibDatabase;
import com.atproj.movielib.domain.repository.request.ResourceRequest;
import com.atproj.movielib.domain.repository.response.PaginatedResourcesResponse;
import com.atproj.movielib.model.Movie;
import com.atproj.movielib.model.Video;

import java.util.List;

public class MovieLibDBCache implements MovieLibCache {

    private MovieLibDatabase database;

    public MovieLibDBCache(MovieLibDatabase database) {
        this.database = database;
    }

    @Override
    public void cacheMovies(List<Movie> movies) {
        for (Movie movie : movies) {
            if (database.existMovie(movie)) {
                database.updateMovie(movie);
            } else {
                database.addMovie(movie);
            }
        }
    }

    @Override
    public void cacheMovie(Movie movie) {
        if (database.existMovie(movie)) {
            database.updateMovie(movie);
        } else {
            database.addMovie(movie);
        }
    }

    @Override
    public void cacheVideos(List<Video> videos, String movieId) {
        for (Video video : videos) {
            if (database.existVideo(video)) {
                database.updateVideo(video, movieId);
            } else {
                database.addVideo(video, movieId);
            }
        }
    }

    @Override
    public PaginatedResourcesResponse<Movie> searchMovie(ResourceRequest request) {
        int page = Integer.valueOf(request.getParams().get("page").toString());
        String query = request.getParams().get("query").toString();
        return database.searchMovie(query, page, 20);
    }

    @Override
    public Movie getMovieDetails(ResourceRequest request) {
        return database.getMovie(request.getId());
    }

    @Override
    public PaginatedResourcesResponse<Video> getMovieVideos(ResourceRequest request) {
        int page = Integer.valueOf(request.getParams().get("page").toString());
        return database.getVideosByMovie(request.getId(), page, 20);
    }
}
