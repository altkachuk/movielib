package com.atproj.movielib.domain.repository;

import com.atproj.movielib.domain.executor.MainThreadExecutor;
import com.atproj.movielib.domain.repository.exception.AuthorizationException;
import com.atproj.movielib.domain.repository.exception.BaseException;
import com.atproj.movielib.domain.repository.exception.NotFountException;
import com.atproj.movielib.domain.repository.request.ResourceRequest;
import com.atproj.movielib.domain.repository.response.BaseResponse;
import com.atproj.movielib.domain.repository.response.PaginatedResourcesResponse;
import com.atproj.movielib.domain.repository.response.ResourceResponse;
import com.atproj.movielib.model.Movie;
import com.atproj.movielib.model.Video;

import retrofit2.Call;
import retrofit2.Response;

public class MovieLibApiRepository implements MovieLibRepository {

    private MovieLibService movieLibApi;
    private MainThreadExecutor mainThreadExecutor;

    public MovieLibApiRepository(MovieLibService movieLibApi, MainThreadExecutor mainThreadExecutor) {
        this.movieLibApi = movieLibApi;
        this.mainThreadExecutor = mainThreadExecutor;
    }

    @Override
    public PaginatedResourcesResponse<Movie> searchMovie(ResourceRequest request) throws BaseException {
        return this.<PaginatedResourcesResponse<Movie>>call(movieLibApi.searchMovie(request.getParams()));
    }

    public Movie getMovieDetails(final ResourceRequest request) throws BaseException {
        return this.call(movieLibApi.getMovieDetails(request.getId()));
    }

    @Override
    public PaginatedResourcesResponse<Video> getMovieVideos(String movieId) throws BaseException {
        return null;
    }

    public <T> T call(Call<T> apiResourceCall) {
        try {
            Response<T> response = apiResourceCall.execute();
            checkResponseCode(response.code());
            return response.body();
        } catch (Exception e) {
            throw new BaseException(e.getMessage());
        }
    }

    private void checkResponseCode(int code) throws BaseException {
        if (code == 401) {
            throw new AuthorizationException("AuthorizationException");
        }

        if (code == 404) {
            throw new NotFountException("NotFountException");
        }
    }
}
