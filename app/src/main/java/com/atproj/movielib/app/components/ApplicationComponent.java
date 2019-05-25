package com.atproj.movielib.app.components;

import android.app.Application;
import android.content.Context;

import com.atproj.movielib.app.modules.ApplicationModule;

import dagger.Component;

@Component(
        modules = {
                ApplicationModule.class
        })
public interface ApplicationComponent {
    Application application();
    Context context();
}
