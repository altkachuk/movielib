package com.atproj.movielib.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.atproj.movielib.database.MovieLibDBSchema.MoviesTable;
import com.atproj.movielib.database.MovieLibDBSchema.VideosTable;

public class MovieLibBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATBASE_NAME = "movieLib.db";

    public MovieLibBaseHelper(Context context) {
        super(context, DATBASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + MoviesTable.name + "(" +
                "_id" + " integer primary key autoincrement, " +
                MoviesTable.Cols.id + " INTEGER, " +
                MoviesTable.Cols.title + ", " +
                MoviesTable.Cols.original_title + ", " +
                MoviesTable.Cols.poster_path + ", " +
                MoviesTable.Cols.adult + ", " +
                MoviesTable.Cols.overview + ", " +
                MoviesTable.Cols.release_date + ", " +
                MoviesTable.Cols.genre_ids + ", " +
                MoviesTable.Cols.original_language + ", " +
                MoviesTable.Cols.backdrop_path + ", " +
                MoviesTable.Cols.popularity + ", " +
                MoviesTable.Cols.vote_count + ", " +
                MoviesTable.Cols.video + ", " +
                MoviesTable.Cols.vote_average + ", " +
                MoviesTable.Cols.budget + ", " +
                MoviesTable.Cols.genres + ", " +
                MoviesTable.Cols.homepage + ", " +
                MoviesTable.Cols.imdb_id + ", " +
                MoviesTable.Cols.production_companies + ", " +
                MoviesTable.Cols.production_countries + ", " +
                MoviesTable.Cols.revenue + ", " +
                MoviesTable.Cols.runtime + ", " +
                MoviesTable.Cols.spoken_languages + ", " +
                MoviesTable.Cols.status + ", " +
                MoviesTable.Cols.tagline +
                ")"
        );

        db.execSQL("create table " + VideosTable.name + "(" +
                "_id" + " integer primary key autoincrement, " +
                VideosTable.Cols.movie_id + ", " +
                VideosTable.Cols.id + ", " +
                VideosTable.Cols.iso_639_1 + ", " +
                VideosTable.Cols.iso_3166_1 + ", " +
                VideosTable.Cols.key + ", " +
                VideosTable.Cols.name + ", " +
                VideosTable.Cols.site + ", " +
                VideosTable.Cols.size + ", " +
                VideosTable.Cols.type +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
