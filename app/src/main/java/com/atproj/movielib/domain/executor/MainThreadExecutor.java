package com.atproj.movielib.domain.executor;

public interface MainThreadExecutor {

    void execute(Runnable runnable);

}
