package com.atproj.movielib.presenter;

import android.content.Context;

import com.atproj.movielib.domain.interactors.MovieInteractor;
import com.atproj.movielib.domain.interactors.callback.PaginatedResourceRequestCallback;
import com.atproj.movielib.domain.repository.exception.BaseException;
import com.atproj.movielib.model.Video;
import com.atproj.movielib.view.VideosView;

import java.util.List;

import javax.inject.Inject;

public class VideosPresenter extends BasePresenter {

    @Inject
    MovieInteractor movieInteractor;

    private VideosView view;

    public VideosPresenter(Context context, VideosView view) {
        super(context);
        this.view = view;
    }

    public void getVideos(int movieId, int page) {
        movieInteractor.getMovieVideos(movieId, page, new PaginatedResourceRequestCallback<Video>() {
            @Override
            public void onSuccess(List<Video> resource, int page, int totalResults, int totalPages) {
                view.onResourcesResponse(resource, page, totalResults, totalPages);
            }

            @Override
            public void onError(BaseException e) {
                view.onError();
            }
        });
    }
}
