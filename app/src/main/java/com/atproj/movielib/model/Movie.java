package com.atproj.movielib.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
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
    int budget = 0;
    List<Genre> genres;
    String homepage;
    String imdb_id;
    List<Company> production_companies;
    List<Country> production_countries;
    int revenue = 0;
    int runtime = 0;
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

    public String getGenresStr() {
        return listToString(genres);
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

    public String getProductionCompaniesStr() {
        return listToString(production_companies);
    }

    public List<Country> getProductionCountries() {
        return production_countries;
    }

    public String getProductionCountriesStr() {
        return listToString(production_countries);
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

    public String getSpokenLanguagesStr() {
        return listToString(spoken_languages);
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOriginalTitle(String originalTitle) {
        this.original_title = originalTitle;
    }

    public void setPosterPath(String posterPath) {
        this.poster_path = posterPath;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setReleaseDate(String releaseDate) {
        this.release_date = releaseDate;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genre_ids = genreIds;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.original_language = original_language;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdrop_path = backdropPath;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public void setVoteCount(int voteCount) {
        this.vote_count = vote_count;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public void setVoteAverage(float voteAverage) {
        this.vote_average = vote_average;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setGenres(String str) {
        genres = new ArrayList<>();
        JSONArray jsonArray = stringToListArray(str);
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                genres.add(Genre.fromJson(jsonObject));
            } catch (Exception e){};
        }
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public void setImdbId(String imdbId) {
        this.imdb_id = imdbId;
    }

    public void setProductionCompanies(List<Company> productionCompanies) {
        this.production_companies = productionCompanies;
    }

    public void setProductionCompanies(String str) {
        production_companies = new ArrayList<>();
        JSONArray jsonArray = stringToListArray(str);
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                production_companies.add(Company.fromJson(jsonObject));
            } catch (Exception e){};
        }
    }


    public void setProductionCountries(List<Country> productionCountries) {
        this.production_countries = productionCountries;
    }

    public void setProductionCountries(String str) {
        production_countries = new ArrayList<>();
        JSONArray jsonArray = stringToListArray(str);
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                production_countries.add(Country.fromJson(jsonObject));
            } catch (Exception e){};
        }
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public void setSpokenLanguages(List<Language> spokenLanguages) {
        this.spoken_languages = spokenLanguages;
    }

    public void setSpokenLanguages(String str) {
        spoken_languages = new ArrayList<>();
        JSONArray jsonArray = stringToListArray(str);
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                spoken_languages.add(Language.fromJson(jsonObject));
            } catch (Exception e){};
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public <Item>String listToString(List<Item> items) {
        if (items == null || items.size() == 0) {
            return null;
        }
        String result = "[";
        result += items.get(0).toString();
        for (int i = 1; i < items.size(); i++) {
            result += ",";
            result += items.get(i).toString();
        }
        result += "]";
        return result;
    }

    public JSONArray stringToListArray(String str) {
        JSONArray jsonArray = new JSONArray();
        try {
            JSONObject object = new JSONObject("{'list': " + str + "}");
            jsonArray = object.getJSONArray("list");
        } catch (Exception e) {

        }

        return jsonArray;
    }

}
