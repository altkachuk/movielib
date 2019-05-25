package com.atproj.movielib.app.components;

import com.atproj.movielib.app.modules.ExecutorModule;
import com.atproj.movielib.domain.executor.InteractorExecutor;
import com.atproj.movielib.domain.executor.MainThreadExecutor;

import dagger.Component;

@Component(
        modules = {
                ExecutorModule.class
        })
public interface ExecutorComponent {

    InteractorExecutor interactorExecutor();

    MainThreadExecutor mainThreadExecutor();

}
