package com.atproj.movielib.database.cursor;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.atproj.movielib.database.MovieLibDBSchema;
import com.atproj.movielib.database.MovieLibDBSchema.MoviesTable;
import com.atproj.movielib.database.MovieLibDBSchema.VideosTable;
import com.atproj.movielib.model.Movie;
import com.atproj.movielib.model.Video;

public class VideoCursorWrapper extends CursorWrapper {

    public VideoCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Video getVideo() {
        Video video = new Video();

        video.setId(getString(getColumnIndex(VideosTable.Cols.id)));
        video.setSite(getString(getColumnIndex(VideosTable.Cols.site)));
        video.setKey(getString(getColumnIndex(VideosTable.Cols.key)));

        return video;
    }
}
