package com.atproj.movielib.presenter;

import android.content.Context;

import com.atproj.movielib.app.MovieLibApp;

public class BasePresenter {

    protected Context context;

    public BasePresenter(Context context) {
        MovieLibApp.get(context).inject(this);
        this.context = context;
    }

}
