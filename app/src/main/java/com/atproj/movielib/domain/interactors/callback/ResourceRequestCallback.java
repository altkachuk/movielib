package com.atproj.movielib.domain.interactors.callback;

public interface ResourceRequestCallback<Resource> extends RequestCallback {

    void onSuccess(Resource resource);

}
