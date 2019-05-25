package com.atproj.movielib.domain.interactors.callback;

import java.util.List;

public interface PaginatedResourceRequestCallback<Resource> extends RequestCallback {

    void onSuccess(List<Resource> resource, int page, int totalResults, int totalPages);

}
