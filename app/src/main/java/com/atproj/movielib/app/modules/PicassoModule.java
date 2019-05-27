package com.atproj.movielib.app.modules;

import android.content.Context;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

@Module
public class PicassoModule {

    @Provides
    Picasso picasso(Context context) {
        return new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(context, Integer.MAX_VALUE))
                .build();
    }
}
