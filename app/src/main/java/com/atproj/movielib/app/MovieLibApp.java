package com.atproj.movielib.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.atproj.movielib.BuildConfig;
import com.atproj.movielib.app.components.ApplicationComponent;;
import com.atproj.movielib.app.components.MovieLibComponent;
import com.atproj.movielib.utils.ClientUtil;

import java.lang.reflect.Method;

public class MovieLibApp extends Application {

    private boolean daggerInitialized = false;

    @Override
    public void onCreate() {
        super.onCreate();

        ClientUtil.setClientUrl(BuildConfig.BUILD_TYPE, BuildConfig.HOST);
        ClientUtil.setApiKey(BuildConfig.BUILD_TYPE, BuildConfig.API_KEY);

        initializeDependencies();
    }

    private void initializeDependencies() {
        if (!daggerInitialized) {
            Injector.getInstance().initializeComponents(this);

            //inject(this);
            daggerInitialized = true;
        }
    }

    public void inject(Object object) {
        try {
            MovieLibComponent component = Injector.getInstance().getMovieLibComponent();
            Method method = component.getClass().getMethod("inject",
                    object.getClass());
            method.invoke(component, object);
        } catch (Exception e) {
            // If method not found no injection will be made
            Log.e("MovieLibApp", e.getMessage(), e);
        }
    }

    public static MovieLibApp get(Context context) {
        return (MovieLibApp) context.getApplicationContext();
    }

}
