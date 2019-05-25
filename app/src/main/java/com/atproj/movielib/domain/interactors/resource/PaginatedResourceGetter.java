package com.atproj.movielib.domain.interactors.resource;

import com.atproj.movielib.domain.repository.request.ResourceRequest;
import com.atproj.movielib.domain.repository.response.PaginatedResourcesResponse;

public interface PaginatedResourceGetter<AbstractResource> {

    PaginatedResourcesResponse<AbstractResource> getPaginatedResource(ResourceRequest resourceRequest);

}
