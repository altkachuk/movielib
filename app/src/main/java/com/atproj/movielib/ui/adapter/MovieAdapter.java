package com.atproj.movielib.ui.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atproj.movielib.R;
import com.atproj.movielib.model.Movie;
import com.atproj.movielib.ui.viewholder.MovieViewHolder;

public class MovieAdapter extends ItemAdapter<Movie, MovieViewHolder> {

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_movie, parent,
                false);
        return new MovieViewHolder(view);
    }
}
