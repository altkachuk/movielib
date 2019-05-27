package com.atproj.movielib.domain.interactors;


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

import java.lang.ref.WeakReference;

public class MovieInteractorImpl extends AbstractInteractor implements MovieInteractor {

    private MovieLibRepository repository;

    public MovieInteractorImpl(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor, MovieLibRepository repository) {
        super(interactorExecutor, mainThreadExecutor);
        this.repository = repository;
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
                        return repository.searchMovie(request.get());
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
                        return repository.getMovieDetails(request.get());
                    }
                });
            }
        });
    }


    @Override
    public void getMovieVideos(int movieId, final PaginatedResourceRequestCallback<Video> callback) {
        final WeakReference<ResourceRequest> request = new WeakReference<>(new ResourceRequest().id(String.valueOf(movieId)));
        getInteractorExecutor().run(new Interactor() {
            @Override
            public void run() {
                MovieInteractorImpl.super.doGetPaginatedResource(request.get(), callback, new PaginatedResourceGetter() {
                    @Override
                    public PaginatedResourcesResponse getPaginatedResource(ResourceRequest resourceRequest) {
                        return repository.getMovieVideos(request.get());
                    }
                });
            }
        });
    }
}
