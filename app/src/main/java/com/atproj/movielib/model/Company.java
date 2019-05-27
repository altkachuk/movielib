package com.atproj.movielib.model;

import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Company {

    int id;
    String name;
    String logo_path;
    String origin_country;

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

    public String getLogoPath() {
        return logo_path;
    }

    public void setLogoPath(String logoPath) {
        this.logo_path = logo_path;
    }

    public String getOriginCountry() {
        return origin_country;
    }

    public void setOriginCountry(String originCountry) {
        this.origin_country = origin_country;
    }

    public String toString() {
        return "{'id': '" + id +
                "'; 'name': '" + name +
                "'; 'logo_path': '" + logo_path +
                "'; 'origin_country': '" + origin_country +
                "'}";
    }

    public static Company fromJson(JSONObject json) {
        Company company = new Company();
        try {
            company.setId(json.getInt("id"));
            company.setName(json.getString("name"));
            company.setLogoPath(json.getString("logo_path"));
            company.setOriginCountry(json.getString("origin_country"));
        } catch (Exception e) {};

        return company;
    }
}
