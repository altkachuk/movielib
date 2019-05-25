package com.atproj.movielib.domain.interactors;

import com.atproj.movielib.domain.executor.InteractorExecutor;
import com.atproj.movielib.domain.executor.MainThreadExecutor;
import com.atproj.movielib.domain.interactors.callback.PaginatedResourceRequestCallback;
import com.atproj.movielib.domain.interactors.callback.ResourceRequestCallback;
import com.atproj.movielib.domain.interactors.resource.PaginatedResourceGetter;
import com.atproj.movielib.domain.interactors.resource.ResourceGetter;
import com.atproj.movielib.domain.repository.exception.BaseException;
import com.atproj.movielib.domain.repository.request.ResourceRequest;
import com.atproj.movielib.domain.repository.response.PaginatedResourcesResponse;
import com.atproj.movielib.domain.repository.response.ResourceResponse;

public abstract class AbstractInteractor {

    private InteractorExecutor interactorExecutor;
    private MainThreadExecutor mainThreadExecutor;

    public AbstractInteractor(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor) {
        this.interactorExecutor = interactorExecutor;
        this.mainThreadExecutor = mainThreadExecutor;
    }

    public InteractorExecutor getInteractorExecutor() {
        return interactorExecutor;
    }

    public <Resource> void doGetResource(ResourceRequest resourceRequest,
                              final ResourceRequestCallback resourceRequestCallback,
                              ResourceGetter<Resource> resourceGetter) {
        try {
            final Resource resource = resourceGetter.getResource(resourceRequest);

            mainThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    resourceRequestCallback.onSuccess(resource);
                }
            });
        } catch (final BaseException e) {
            mainThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    resourceRequestCallback.onError(e);
                }
            });
        }
    }

    public void doGetPaginatedResource(ResourceRequest resourceRequest,
                                       final PaginatedResourceRequestCallback paginatedResourceRequestCallback,
                                       PaginatedResourceGetter paginatedResourceGetter) {
        try {
            final PaginatedResourcesResponse paginatedResourcesResponse = paginatedResourceGetter.getPaginatedResource(resourceRequest);
            mainThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    paginatedResourceRequestCallback.onSuccess(paginatedResourcesResponse.getResults(),
                            paginatedResourcesResponse.getPage(),
                            paginatedResourcesResponse.getTotalResults(),
                            paginatedResourcesResponse.getTotalPages());
                }
            });
        } catch (final BaseException e) {
            mainThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    paginatedResourceRequestCallback.onError(e);
                }
            });
        }
    }
}
