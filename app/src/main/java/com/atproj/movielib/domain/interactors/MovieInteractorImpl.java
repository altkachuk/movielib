package com.atproj.movielib.domain.interactors;


import android.content.Context;

import com.atproj.movielib.domain.cache.MovieLibCache;
import com.atproj.movielib.domain.executor.InteractorExecutor;
import com.atproj.movielib.domain.executor.MainThreadExecutor;
import com.atproj.movielib.domain.interactors.callback.PaginatedResourceRequestCallback;
import com.atproj.movielib.domain.interactors.callback.ResourceRequestCallback;
import com.atproj.movielib.domain.interactors.resource.PaginatedResourceGetter;
import com.atproj.movielib.domain.interactors.resource.ResourceGetter;
import com.atproj.movielib.domain.repository.MovieLibRepository;
import com.atproj.movielib.domain.repository.request.ResourceRequest;
import com.atproj.movielib.domain.repository.response.PaginatedResourcesResponse;
import com.atproj.movielib.domain.repository.response.ResourceResponse;
import com.atproj.movielib.model.Movie;
import com.atproj.movielib.model.Video;
import com.atproj.movielib.utils.ConnectionUtil;

import java.lang.ref.WeakReference;

public class MovieInteractorImpl extends AbstractInteractor implements MovieInteractor {

    private Context context;
    private MovieLibRepository repository;
    private MovieLibCache cache;

    public MovieInteractorImpl(Context context, InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor, MovieLibRepository repository, MovieLibCache cache) {
        super(interactorExecutor, mainThreadExecutor);
        this.context = context;
        this.repository = repository;
        this.cache = cache;
    }

    @Override
    public void searchMovie(String query, int page, final PaginatedResourceRequestCallback<Movie> callback) {
        final WeakReference<ResourceRequest> request = new WeakReference<>(new ResourceRequest()
                .query(query).page(page));
        getInteractorExecutor().run(new Interactor() {
            @Override
            public void run() {
                MovieInteractorImpl.super.doGetPaginatedResource(request.get(), callback, new PaginatedResourceGetter() {
                    @Override
                    public PaginatedResourcesResponse getPaginatedResource(ResourceRequest resourceRequest) {
                        if (ConnectionUtil.isConnectedToInternet(context)) {
                            PaginatedResourcesResponse<Movie> response = repository.searchMovie(request.get());
                            cache.cacheMovies(response.results);
                            return response;
                        }
                        return cache.searchMovie(request.get());
                    }
                });
            }
        });

    }

    public void getMovieDetails(int id, final ResourceRequestCallback<Movie> callback) {
        final WeakReference<ResourceRequest> request = new WeakReference<>(new ResourceRequest().id(String.valueOf(id)));
        getInteractorExecutor().run(new Interactor() {
            @Override
            public void run() {
                MovieInteractorImpl.super.doGetResource(request.get(), callback, new ResourceGetter() {
                    @Override
                    public Movie getResource(ResourceRequest resourceRequest) {
                        if (ConnectionUtil.isConnectedToInternet(context)) {
                            Movie movie = repository.getMovieDetails(request.get());
                            cache.cacheMovie(movie);
                            return movie;
                        }
                        return cache.getMovieDetails(request.get());
                    }
                });
            }
        });
    }


    @Override
    public void getMovieVideos(int movieId, int page, final PaginatedResourceRequestCallback<Video> callback) {
        final WeakReference<ResourceRequest> request = new WeakReference<>(new ResourceRequest()
                .id(String.valueOf(movieId)).page(page));
        getInteractorExecutor().run(new Interactor() {
            @Override
            public void run() {
                MovieInteractorImpl.super.doGetPaginatedResource(request.get(), callback, new PaginatedResourceGetter() {
                    @Override
                    public PaginatedResourcesResponse getPaginatedResource(ResourceRequest resourceRequest) {
                        if (ConnectionUtil.isConnectedToInternet(context)) {
                            PaginatedResourcesResponse<Video> response = repository.getMovieVideos(request.get());
                            cache.cacheVideos(response.results, request.get().getId());
                            return response;
                        }
                        return cache.getMovieVideos(request.get());
                    }
                });
            }
        });
    }
}
