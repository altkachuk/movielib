package com.atproj.movielib.domain.repository.response;

import java.util.List;

public class PaginatedResourcesResponse<Resource> extends BaseResponse {

    public List<Resource> results;
    public int page;
    public int totalResults;
    public int totalPages;

    public List<Resource> getResults() {
        return results;
    }

    public void setResults(List<Resource> results) {
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

}
