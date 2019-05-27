package com.atproj.movielib.model;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class Movie {

    public static final String API_RESOURCE_NAME = "movie";

    int id;
    String title;
    String original_title;
    String poster_path;
    boolean adult;
    String overview;
    String release_date;
    List<Integer> genre_ids;
    String original_language;
    String backdrop_path;
    float popularity;
    int vote_count;
    boolean video;
    float vote_average;
    //Object belongs_to_collection;
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

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return original_title;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public String getReleaseYear() {
        if (release_date != null && release_date.length() > 4) {
            return release_date.substring(0, 4);
        }

        return null;
    }

    public List<Integer> getGenreIds() {
        return genre_ids;
    }

    public String getOriginalLanguage() {
        return original_language;
    }

    public String getBackdropPath() {
        return backdrop_path;
    }

    public float getPopularity() {
        return popularity;
    }

    public int getVoteCount() {
        return vote_count;
    }

    public boolean isVideo() {
        return video;
    }

    public float getVoteAverage() {
        return vote_average;
    }

    /*public Object getBelongsToCollection() {
        return belongs_to_collection;
    }*/

    public int getBudget() {
        return budget;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getImdbId() {
        return imdb_id;
    }

    public List<Company> getProductionCompanies() {
        return production_companies;
    }

    public List<Country> getProductionCountries() {
        return production_countries;
    }

    public int getRevenue() {
        return revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public List<Language> getSpokenLanguages() {
        return spoken_languages;
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }
}
