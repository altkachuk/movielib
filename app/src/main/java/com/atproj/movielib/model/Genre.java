package com.atproj.movielib.model;

import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Genre {

    int id;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "{'id': '" + id +
                "'; 'name': '" + name +
                "'}";
    }

    public static Genre fromJson(JSONObject json) {
        Genre genre = new Genre();
        try {
            genre.setId(json.getInt("id"));
            genre.setName(json.getString("name"));
        } catch (Exception e) {};

        return genre;
    }
}
