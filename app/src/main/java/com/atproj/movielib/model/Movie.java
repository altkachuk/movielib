package com.atproj.movielib.model;

import java.util.List;

public class Movie {

    public static final String API_RESOURCE_NAME = "movie";

    String poster_path;
    boolean adult;
    String overview;
    String release_date;
    List<Integer> genre_ids;
    int id;
    String original_title;
    String original_language;
    String title;
    String backdrop_path;
    float popularity;
    int vote_count;
    boolean video;
    float vote_average;
    Object belongs_to_collection;
    int budget;
    List<Genre> genres;
    String homepage;
    String imdb_id;
    List<Company> production_companies;
    List<Country> production_countries;
    int revenue;
    int runtime;
    List<Language> spoken_languages;
    String status;
    String tagline;


}
