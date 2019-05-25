package com.atproj.movielib.app;

import com.atproj.movielib.app.components.ApplicationComponent;
import com.atproj.movielib.app.components.DaggerApplicationComponent;
import com.atproj.movielib.app.components.DaggerExecutorComponent;
import com.atproj.movielib.app.components.DaggerInteractorComponent;
import com.atproj.movielib.app.components.DaggerMovieLibComponent;
import com.atproj.movielib.app.components.DaggerNetworkComponent;
import com.atproj.movielib.app.components.DaggerRepositoryComponent;
import com.atproj.movielib.app.components.ExecutorComponent;
import com.atproj.movielib.app.components.InteractorComponent;
import com.atproj.movielib.app.components.MovieLibComponent;
import com.atproj.movielib.app.components.NetworkComponent;
import com.atproj.movielib.app.components.RepositoryComponent;
import com.atproj.movielib.app.modules.ApplicationModule;
import com.atproj.movielib.app.modules.ExecutorModule;
import com.atproj.movielib.app.modules.InteractorModule;
import com.atproj.movielib.app.modules.NetworkModule;
import com.atproj.movielib.app.modules.RepositoryModule;
import com.atproj.movielib.utils.ClientUtil;

public class Injector {

    private static Injector instance;

    public static Injector getInstance() {
        if (instance == null) {
            instance = new Injector();
        }
        return instance;
    }

    MovieLibComponent movieLibComponent;

    private Injector() {
        ;
    }

    public void initializeComponents(MovieLibApp app) {
        ApplicationComponent applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(app))
                .build();

        NetworkComponent networkComponent = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(ClientUtil.getClientUrl(), ClientUtil.getApiKey()))
                .applicationComponent(applicationComponent)
                .build();

        ExecutorComponent executorComponent = DaggerExecutorComponent.builder()
                .executorModule(new ExecutorModule())
                .build();

        RepositoryComponent repositoryComponent = DaggerRepositoryComponent.builder()
                .repositoryModule(new RepositoryModule())
                .executorComponent(executorComponent)
                .networkComponent(networkComponent)
                .build();

        InteractorComponent interactorComponent = DaggerInteractorComponent.builder()
                .interactorModule(new InteractorModule())
                .executorComponent(executorComponent)
                .repositoryComponent(repositoryComponent)
                .build();

        movieLibComponent = DaggerMovieLibComponent.builder()
                .applicationComponent(applicationComponent)
                .interactorComponent(interactorComponent)
                .build();
    }

    public MovieLibComponent getMovieLibComponent() {
        return movieLibComponent;
    }
}
