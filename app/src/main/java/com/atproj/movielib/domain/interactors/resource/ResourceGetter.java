package com.atproj.movielib.domain.interactors.resource;

import com.atproj.movielib.domain.repository.request.ResourceRequest;
import com.atproj.movielib.domain.repository.response.ResourceResponse;

public interface ResourceGetter<AbstractResource> {

    AbstractResource getResource(ResourceRequest resourceRequest);

}
