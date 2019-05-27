package com.atproj.movielib.model;

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
}
