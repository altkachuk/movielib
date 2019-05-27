package com.atproj.movielib.database;

public class MovieLibDBSchema {

    public static final class MoviesTable {

        public static final String name = "movies";

        public static final class Cols {

            public static final String uuid = "id";
            public static final String title = "title";
            public static final String original_title = "original_title";
            public static final String poster_path = "poster_path";
            public static final String adult = "adult";
            public static final String overview = "overview";
            public static final String release_date = "release_date";
            public static final String genre_ids = "genre_ids";
            public static final String original_language = "original_language";
            public static final String backdrop_path = "backdrop_path";
            public static final String popularity = "popularity";
            public static final String vote_count = "vote_count";
            public static final String video = "video";
            public static final String vote_average = "vote_average";
            public static final String budget = "budget";
            public static final String genres = "genres";
            public static final String homepage = "homepage";
            public static final String imdb_id = "imdb_id";
            public static final String production_companies = "production_companies";
            public static final String production_countries = "production_countries";
            public static final String revenue = "revenue";
            public static final String runtime = "runtime";
            public static final String spoken_languages = "spoken_languages";
            public static final String status = "status";
            public static final String tagline = "tagline";
        }
    }

    public static final class VideosTable {

        public static final String name = "videos";

        public static final class Cols {

            public static final String uuid = "id";
            public static final String iso_639_1 = "iso_639_1";
            public static final String iso_3166_1 = "iso_3166_1";
            public static final String key = "key";
            public static final String name = "name";
            public static final String site = "site";
            public static final String size = "size";
            public static final String type = "type";
            public static final String movie_id = "movie_id";

        }

    }

}
