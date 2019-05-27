package com.atproj.movielib.ui.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.atproj.movielib.app.MovieLibApp;

import butterknife.ButterKnife;

public abstract class ItemViewHolder<Item> extends RecyclerView.ViewHolder {

    private Context context;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        MovieLibApp.get(context).inject(this);
        ButterKnife.bind(this, itemView);
    }

    public Context getContext() {
        return context;
    }

    public abstract void setItem(Item item);
}
