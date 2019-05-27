package com.atproj.movielib.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.atproj.movielib.R;

import butterknife.BindView;
import butterknife.OnClick;

public class ResourceBaseActivity extends BaseActivity {

    private static final String LOADING_STATE = "LOADING_STATE";
    private static final String LOADED_STATE = "LOADED_STATE";
    private static final String ERROR_STATE = "ERROR_STATE";

    @BindView(R.id.loading_view)
    ViewGroup loadingView;

    @BindView(R.id.preloader)
    ProgressBar preloader;

    @BindView(R.id.loaded_error_view)
    LinearLayout loadedErrorView;

    private boolean needUpdateState = false;
    private boolean started = false;

    private String currentState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLoadedState();
    }

    @Override
    protected void onStart() {
        super.onStart();
        started = true;
        if (needUpdateState) {
            if (currentState == LOADING_STATE) {
                setLoadingState();
            }
            if (currentState == LOADED_STATE) {
                setLoadedState();
            }
            if (currentState == ERROR_STATE) {
                setErrorState();
            }
            needUpdateState = false;
        }
    }

    public void setLoadingState() {
        if (started) {
            loadingView.setVisibility(View.VISIBLE);
            preloader.setVisibility(View.VISIBLE);
            loadedErrorView.setVisibility(View.GONE);
        } else {
            currentState = LOADING_STATE;
            needUpdateState = true;
        }
    }

    public void setLoadedState() {
        if (started) {
            loadingView.setVisibility(View.GONE);
            preloader.setVisibility(View.GONE);
            loadedErrorView.setVisibility(View.GONE);
        } else {
            currentState = LOADED_STATE;
            needUpdateState = true;
        }
    }

    public void setErrorState() {
        if (started) {
            loadingView.setVisibility(View.VISIBLE);
            preloader.setVisibility(View.GONE);
            loadedErrorView.setVisibility(View.VISIBLE);
        } else {
            currentState = ERROR_STATE;
            needUpdateState = true;
        }
    }

    @OnClick(R.id.reload_button)
    public void onReloadButton() {
        onReload();
    }

    protected void onReload() {
        ;
    }

}
