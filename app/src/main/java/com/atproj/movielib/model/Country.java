package com.atproj.movielib.model;

import org.parceler.Parcel;

@Parcel
public class Country {

    String iso_3166_1;
    String name;

    public String getIso31661() {
        return iso_3166_1;
    }

    public void setIso31661(String iso31661) {
        this.iso_3166_1 = iso_3166_1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
