package com.atproj.movielib.model.singleton;

import android.content.Context;

import com.atproj.movielib.model.Movie;

import java.util.List;

public class SearchMovieContext {

    private String query = "";
    private int page = 1;
    private int totalResults = 0;
    private int scrollPos = 0;
    private List<Movie> movies;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
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

    public int getScrollPos() {
        return scrollPos;
    }

    public void setScrollPos(int scrollPos) {
        this.scrollPos = scrollPos;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
