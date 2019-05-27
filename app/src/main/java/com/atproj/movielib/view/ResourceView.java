package com.atproj.movielib.view;

public interface ResourceView<Resource> extends View {

    void onResourceResponse(Resource resource);

}
