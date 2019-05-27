package com.atproj.movielib.database.cursor;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.atproj.movielib.database.MovieLibDBSchema.MoviesTable;
import com.atproj.movielib.model.Movie;

public class MovieCursorWrapper extends CursorWrapper {

    public MovieCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Movie getMovie() {
        Movie movie = new Movie();

        movie.setId(getInt(getColumnIndex(MoviesTable.Cols.id)));
        movie.setTitle(getString(getColumnIndex(MoviesTable.Cols.title)));
        movie.setOriginalTitle(getString(getColumnIndex(MoviesTable.Cols.original_title)));
        movie.setPosterPath(getString(getColumnIndex(MoviesTable.Cols.poster_path)));
        movie.setAdult(getInt(getColumnIndex(MoviesTable.Cols.adult)) == 1 ? true : false);
        movie.setOverview(getString(getColumnIndex(MoviesTable.Cols.overview)));
        movie.setReleaseDate(getString(getColumnIndex(MoviesTable.Cols.release_date)));
        //movie.setGenreIds(getInt(getColumnIndex(MoviesTable.Cols.genre_ids)));
        movie.setOriginalLanguage(getString(getColumnIndex(MoviesTable.Cols.original_language)));
        movie.setBackdropPath(getString(getColumnIndex(MoviesTable.Cols.backdrop_path)));
        movie.setPopularity(getFloat(getColumnIndex(MoviesTable.Cols.popularity)));
        movie.setVoteCount(getInt(getColumnIndex(MoviesTable.Cols.vote_count)));
        movie.setVideo(getInt(getColumnIndex(MoviesTable.Cols.video)) == 1 ? true : false);
        movie.setVoteAverage(getFloat(getColumnIndex(MoviesTable.Cols.vote_average)));
        movie.setBudget(getInt(getColumnIndex(MoviesTable.Cols.budget)));
        movie.setGenres(getString(getColumnIndex(MoviesTable.Cols.genres)));
        movie.setHomepage(getString(getColumnIndex(MoviesTable.Cols.homepage)));
        movie.setImdbId(getString(getColumnIndex(MoviesTable.Cols.imdb_id)));
        movie.setProductionCompanies(getString(getColumnIndex(MoviesTable.Cols.production_companies)));
        movie.setProductionCountries(getString(getColumnIndex(MoviesTable.Cols.production_countries)));
        movie.setRevenue(getInt(getColumnIndex(MoviesTable.Cols.revenue)));
        movie.setRuntime(getInt(getColumnIndex(MoviesTable.Cols.runtime)));
        movie.setSpokenLanguages(getString(getColumnIndex(MoviesTable.Cols.spoken_languages)));
        movie.setStatus(getString(getColumnIndex(MoviesTable.Cols.status)));
        movie.setTagline(getString(getColumnIndex(MoviesTable.Cols.tagline)));

        return movie;
    }
}
