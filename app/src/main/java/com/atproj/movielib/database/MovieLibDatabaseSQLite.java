package com.atproj.movielib.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.atproj.movielib.database.MovieLibDBSchema.MoviesTable;
import com.atproj.movielib.database.MovieLibDBSchema.VideosTable;
import com.atproj.movielib.database.cursor.MovieCursorWrapper;
import com.atproj.movielib.database.cursor.VideoCursorWrapper;
import com.atproj.movielib.domain.repository.response.PaginatedResourcesResponse;
import com.atproj.movielib.model.Movie;
import com.atproj.movielib.model.Video;

import java.util.ArrayList;
import java.util.List;

public class MovieLibDatabaseSQLite implements MovieLibDatabase {

    private Context context;
    private SQLiteDatabase database;

    public MovieLibDatabaseSQLite(Context context) {
        this.context = context;
        this.database = new MovieLibBaseHelper(context).getWritableDatabase();
    }

    @Override
    public void addMovie(Movie movie) {
        ContentValues values = getMovieContentValues(movie);

        database.insert(MoviesTable.name, null, values);
    }

    @Override
    public void updateMovie(Movie movie) {
        String uuid = String.valueOf(movie.getId());
        ContentValues values = getMovieContentValues(movie);

        database.update(MoviesTable.name, values,
                MoviesTable.Cols.id + " = ?",
                new String[]{uuid});
    }

    @Override
    public boolean existMovie(Movie movie) {
        String whereClause = MoviesTable.Cols.id + " = ?";
        String[] whereArgs = new String[]{String.valueOf(movie.getId())};

        MovieCursorWrapper cursor = queryMovies(whereClause, whereArgs);

        return cursor.getCount() > 0;
    }

    @Override
    public Movie getMovie(String id) {
        String whereClause = MoviesTable.Cols.id + " = ?";
        String[] whereArgs = new String[]{id};

        MovieCursorWrapper cursor = queryMovies(whereClause, whereArgs);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                return cursor.getMovie();
            }
        } finally {
            cursor.close();
        }

        return null;
    }

    @Override
    public PaginatedResourcesResponse<Movie> searchMovie(String query, int page, int offset) {
        PaginatedResourcesResponse<Movie> response = new PaginatedResourcesResponse<>();

        String whereClause = MoviesTable.Cols.title + " LIKE ?";
        String[] whereArgs = new String[]{"%" + query + "%"};

        MovieCursorWrapper cursor = queryMovies(whereClause, whereArgs);

        List<Movie> movies = new ArrayList<>();
        try {
            int startPos = (page - 1) * offset;
            cursor.moveToPosition(startPos);

            int i = 0;
            while (!cursor.isAfterLast() && i < offset) {
                movies.add(cursor.getMovie());
                cursor.moveToNext();
                i++;
            }
        } finally {
            cursor.close();
        }

        response.results = movies;
        response.page = page;
        response.totalResults = cursor.getCount();
        response.totalPages = response.totalResults / offset;

        return response;
    }

    @Override
    public void addVideo(Video video, String movieId) {
        ContentValues values = getVideoContentValues(video, movieId);

        database.insert(VideosTable.name, null, values);
    }

    @Override
    public void updateVideo(Video video, String movieId) {
        String uuid = video.getId();
        ContentValues values = getVideoContentValues(video, movieId);

        database.update(VideosTable.name, values,
                VideosTable.Cols.id + " = ?",
                new String[]{uuid});
    }

    @Override
    public boolean existVideo(Video video) {
        String whereClause = VideosTable.Cols.id + " = ?";
        String[] whereArgs = new String[]{String.valueOf(video.getId())};

        VideoCursorWrapper cursor = queryVideos(whereClause, whereArgs);

        return cursor.getCount() > 0;
    }

    @Override
    public PaginatedResourcesResponse<Video> getVideosByMovie(String movieId, int page, int offset) {
        PaginatedResourcesResponse<Video> response = new PaginatedResourcesResponse<>();

        String whereClause = VideosTable.Cols.movie_id + " = ?";
        String[] whereArgs = new String[]{movieId};

        VideoCursorWrapper cursor = queryVideos(whereClause, whereArgs);

        List<Video> videos = new ArrayList<>();
        try {
            int startPos = (page - 1) * offset;
            cursor.moveToPosition(startPos);

            int i = 0;
            while (!cursor.isAfterLast() && i < offset) {
                videos.add(cursor.getVideo());
                cursor.moveToNext();
                i++;
            }
        } finally {
            cursor.close();
        }

        response.results = videos;
        response.page = page;
        response.totalResults = cursor.getCount();
        response.totalPages = response.totalResults / offset;

        return response;
    }

    private ContentValues getMovieContentValues(Movie movie) {
        ContentValues values = new ContentValues();
        values.put(MoviesTable.Cols.id, movie.getId());
        values.put(MoviesTable.Cols.title, movie.getTitle());
        values.put(MoviesTable.Cols.original_title, movie.getOriginalTitle());
        values.put(MoviesTable.Cols.poster_path, movie.getPosterPath());
        values.put(MoviesTable.Cols.adult, movie.isAdult() ? 1 : 0);
        values.put(MoviesTable.Cols.overview, movie.getOverview());
        values.put(MoviesTable.Cols.release_date, movie.getReleaseDate());
        //values.put(MoviesTable.Cols.genre_ids, movie.getId());
        values.put(MoviesTable.Cols.original_language, movie.getOriginalLanguage());
        values.put(MoviesTable.Cols.backdrop_path, movie.getBackdropPath());
        values.put(MoviesTable.Cols.popularity, movie.getPopularity());
        values.put(MoviesTable.Cols.vote_count, movie.getVoteCount());
        values.put(MoviesTable.Cols.video, movie.isVideo() ? 1 : 0);
        values.put(MoviesTable.Cols.vote_average, movie.getVoteAverage());
        if (movie.getBudget() > 0) {
            values.put(MoviesTable.Cols.budget, movie.getBudget());
        }
        if (movie.getGenres() != null) {
            values.put(MoviesTable.Cols.genres, movie.getGenresStr());
        }
        if (movie.getHomepage() != null) {
            values.put(MoviesTable.Cols.homepage, movie.getHomepage());
        }
        if (movie.getImdbId() != null) {
            values.put(MoviesTable.Cols.imdb_id, movie.getImdbId());
        }
        if (movie.getProductionCompanies() != null) {
            values.put(MoviesTable.Cols.production_companies, movie.getProductionCompaniesStr());
        }
        if (movie.getProductionCountries() != null) {
            values.put(MoviesTable.Cols.production_countries, movie.getProductionCountriesStr());
        }
        if (movie.getRevenue() > 0) {
            values.put(MoviesTable.Cols.revenue, movie.getRevenue());
        }
        if (movie.getRuntime() > 0) {
            values.put(MoviesTable.Cols.runtime, movie.getRuntime());
        }
        if (movie.getSpokenLanguages() != null) {
            values.put(MoviesTable.Cols.spoken_languages, movie.getSpokenLanguagesStr());
        }
        if (movie.getStatus() != null) {
            values.put(MoviesTable.Cols.status, movie.getStatus());
        }
        if (movie.getTagline() != null) {
            values.put(MoviesTable.Cols.tagline, movie.getTagline());
        }

        return values;
    }

    private ContentValues getVideoContentValues(Video video, String movieId) {
        ContentValues values = new ContentValues();
        values.put(VideosTable.Cols.id, video.getId());
        values.put(VideosTable.Cols.iso_639_1, video.getIso6391());
        values.put(VideosTable.Cols.iso_3166_1, video.getIso31661());
        values.put(VideosTable.Cols.key, video.getKey());
        values.put(VideosTable.Cols.name, video.getName());
        values.put(VideosTable.Cols.site, video.getSite());
        values.put(VideosTable.Cols.size, video.getSize());
        values.put(VideosTable.Cols.type, video.getType());
        values.put(VideosTable.Cols.movie_id, movieId);

        return values;
    }

    private MovieCursorWrapper queryMovies(String whereClause, String[] whereArgs) {
        Cursor cursor = database.query(
                MoviesTable.name,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null,
                null);

        return new MovieCursorWrapper(cursor);
    }

    private VideoCursorWrapper queryVideos(String whereClause, String[] whereArgs) {
        Cursor cursor = database.query(
                VideosTable.name,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null,
                null);

        return new VideoCursorWrapper(cursor);
    }
}
