package com.atproj.movielib.domain.repository;

import com.atproj.movielib.domain.repository.response.PaginatedResourcesResponse;
import com.atproj.movielib.domain.repository.response.ResourceResponse;
import com.atproj.movielib.model.Movie;
import com.atproj.movielib.model.Video;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface MovieLibService {

    String VERSION = "3";

    @GET(VERSION + "/search/" + Movie.API_RESOURCE_NAME)
    Call<PaginatedResourcesResponse<Movie>> searchMovie(
            @QueryMap Map<String, String> params
    );

    @GET(VERSION + "/" + Movie.API_RESOURCE_NAME + "/{movie_id}")
    Call<Movie> getMovieDetails(
            @Path("movie_id") String movieId
    );

    @GET(VERSION + "/" + Movie.API_RESOURCE_NAME + "/{movie_id}/" + Video.API_RESOURCE_NAME)
    PaginatedResourcesResponse<Video> getMovieVideos(
            @Path("movie_id") String movieId
    );
}
