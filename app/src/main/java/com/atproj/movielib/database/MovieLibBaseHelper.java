package com.atproj.movielib.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.atproj.movielib.database.MovieLibDBSchema.MoviesTable;

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
                MoviesTable.Cols.uuid + "integer, " +
                MoviesTable.Cols.title + ", " +
                /*MoviesTable.Cols. + ", " +
                MoviesTable.Cols. + ", " +
                MoviesTable.Cols. + ", " +
                MoviesTable.Cols. + ", " +
                MoviesTable.Cols. + ", " +
                MoviesTable.Cols. + ", " +
                MoviesTable.Cols. + ", " +
                MoviesTable.Cols. + ", " +
                MoviesTable.Cols. + ", " +
                MoviesTable.Cols. + ", " +
                MoviesTable.Cols. + ", " +
                MoviesTable.Cols. + ", " +
                MoviesTable.Cols. + ", " +
                MoviesTable.Cols. + ", " +
                MoviesTable.Cols. + ", " +
                MoviesTable.Cols. + ", " +
                MoviesTable.Cols. + ", " +
                MoviesTable.Cols. + ", " +
                MoviesTable.Cols. + ", " +
                MoviesTable.Cols. + ", " +
                MoviesTable.Cols. + ", " +
                MoviesTable.Cols. + ", " +
                MoviesTable.Cols. + ", " +
                MoviesTable.Cols. + ", " +*/
                MoviesTable.Cols.tagline +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
