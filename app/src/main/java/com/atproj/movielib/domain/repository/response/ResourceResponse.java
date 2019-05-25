package com.atproj.movielib.domain.repository.response;

public class ResourceResponse<Resource> extends BaseResponse {

    public Resource resource;

    public ResourceResponse(Resource resource) {
        this.resource = resource;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

}
