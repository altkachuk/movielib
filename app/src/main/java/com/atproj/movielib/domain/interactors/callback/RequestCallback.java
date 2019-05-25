package com.atproj.movielib.domain.interactors.callback;

import com.atproj.movielib.domain.repository.exception.BaseException;

public interface RequestCallback {

    void onError(BaseException e);

}
