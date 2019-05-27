package com.atproj.movielib.utils;

import android.widget.ImageView;

import com.atproj.movielib.R;
import com.atproj.movielib.model.Movie;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageUtil {

    public static void loadPosterImage(Picasso picasso, Movie movie, final ImageView imageView) {
        String url = ClientUtil.getImageHostUrl() + movie.getPosterPath();
        picasso.load(url).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                ;
            }

            @Override
            public void onError(Exception e) {
                imageView.setImageResource(R.drawable.no_image);
            }
        });
    }

    public static void loadPreviewVideo(Picasso picasso, String url, final ImageView imageView) {
            picasso.load(url).into(imageView, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    imageView.setImageResource(R.drawable.no_youtube);
                }
            });
    }

}
