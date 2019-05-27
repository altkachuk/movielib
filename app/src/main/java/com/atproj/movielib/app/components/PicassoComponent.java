package com.atproj.movielib.app.components;

import com.atproj.movielib.app.modules.PicassoModule;
import com.squareup.picasso.Picasso;

import dagger.Component;

@Component(
        modules = {
                PicassoModule.class
        },
        dependencies = {
                ApplicationComponent.class
        })
public interface PicassoComponent {

    Picasso picasso();

}
