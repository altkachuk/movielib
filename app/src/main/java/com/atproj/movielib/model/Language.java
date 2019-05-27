package com.atproj.movielib.model;

import com.google.gson.JsonObject;

import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Language {

    String iso_639_1;
    String name;

    public String getIso6391() {
        return iso_639_1;
    }

    public void setIso6391(String iso6391) {
        this.iso_639_1 = iso_639_1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "{'iso_639_1': '" + iso_639_1 +
                "'; 'name': '" + name +
                "'}";
    }

    public static Language fromJson(JSONObject json) {
        Language language = new Language();
        try {
            language.setIso6391(json.getString("iso_639_1"));
            language.setName(json.getString("name"));
        } catch (Exception e) {};

        return language;
    }
}
