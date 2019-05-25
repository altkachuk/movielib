package com.atproj.movielib.app.modules;

import com.atproj.movielib.domain.executor.InteractorExecutor;
import com.atproj.movielib.domain.executor.InteractorExecutorImpl;
import com.atproj.movielib.domain.executor.MainThreadExecutor;
import com.atproj.movielib.domain.executor.MainThreadExecutorImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class ExecutorModule {

    private final InteractorExecutor interactorExecutor;
    private final MainThreadExecutor mainThreadExecutor;

    public ExecutorModule() {
        interactorExecutor = new InteractorExecutorImpl();
        mainThreadExecutor = new MainThreadExecutorImpl();
    }

    @Provides
    InteractorExecutor interactorExecutor() {
        return interactorExecutor;
    }

    @Provides
    MainThreadExecutor mainThreadExecutor() {
        return mainThreadExecutor;
    }

}
