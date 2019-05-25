package com.atproj.movielib.ui.activities;

import android.os.Bundle;
import android.widget.Button;

import com.atproj.movielib.R;
import com.atproj.movielib.presenter.MoviePresenter;
import com.atproj.movielib.view.MovieView;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MovieView {

    @BindView(R.id.test_button)
    Button testButton;

    private MoviePresenter moviePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviePresenter = new MoviePresenter(getApplicationContext(), this);
    }

    @OnClick(R.id.test_button)
    public void onClickTestButton() {
        moviePresenter.test();
    }
}
