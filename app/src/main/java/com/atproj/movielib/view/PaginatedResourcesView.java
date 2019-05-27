package com.atproj.movielib.view;

import java.util.List;

public interface PaginatedResourcesView<Resource> extends View {

    void onResourcesResponse(List<Resource> resources, int page, int totalResults, int totalPages);

}
