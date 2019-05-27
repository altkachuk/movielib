package com.atproj.movielib.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.widget.EditText;
import android.widget.TextView;

import com.atproj.movielib.R;
import com.atproj.movielib.model.Movie;
import com.atproj.movielib.model.singleton.SearchMovieContext;
import com.atproj.movielib.presenter.SearchMoviePresenter;
import com.atproj.movielib.ui.adapter.MovieAdapter;
import com.atproj.movielib.ui.decorator.DividerItemDecoration;
import com.atproj.movielib.ui.listener.InfiniteScrollListener;
import com.atproj.movielib.utils.ActivityHelper;
import com.atproj.movielib.view.SearchMovieView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import jp.wasabeef.recyclerview.animators.FadeInAnimator;

public class MovieLibActivity extends BaseActivity implements SearchMovieView {

    @BindView(R.id.search_edit_text)
    EditText searchEditText;

    @BindView(R.id.result_text_view)
    TextView resultTextView;

    @BindView(R.id.movies_recycler_view)
    RecyclerView moviesRecyclerView;

    @Inject
    SearchMovieContext searchMovieContext;

    private SearchMoviePresenter searchMoviePresenter;
    private MovieAdapter movieAdapter;

    private InfiniteScrollListener infiniteScrollListener;

    private String currentQuery;
    private int currentPage;
    private int totalResults;

    private Timer searchThresholdTimer;

    //----------------------------------------------------------------------------------------------
    //                          Lifecycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        movieAdapter = new MovieAdapter();
        initRecyclerView();

        searchMoviePresenter = new SearchMoviePresenter(getApplicationContext(), this);

        getSearchInfos();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (currentQuery == null) {
            currentQuery = "";
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        saveSearchInfos();
    }

    //                          Lifecycle
    //----------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------
    //                          Method(s)

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        // optimization
        moviesRecyclerView.setHasFixedSize(true);
        // set layout
        moviesRecyclerView.setLayoutManager(layoutManager);
        // Add Animation
        moviesRecyclerView.setItemAnimator(new FadeInAnimator());

        moviesRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                R.drawable.divider_movie));
        moviesRecyclerView.setAdapter(movieAdapter);

        initInfiniteScrollListener(layoutManager);
    }

    private void initInfiniteScrollListener(LinearLayoutManager layoutManager) {
        infiniteScrollListener = new InfiniteScrollListener(layoutManager,
                new InfiniteScrollListener.OnInfiniteScrollListener() {
                    @Override
                    public void onLoadNext() {
                        if (movieAdapter.getItemCount() < totalResults) {
                            searchMoviePresenter.search(currentQuery, currentPage + 1);
                        }
                    }
                });
        moviesRecyclerView.addOnScrollListener(infiniteScrollListener);
    }

    private void updateResultTextView() {
        String strResult = movieAdapter.getItemCount() + " " + getString(R.string.of) + " " +
                this.totalResults;
        resultTextView.setText(strResult);
    }

    private void getSearchInfos() {
        currentQuery = searchMovieContext.getQuery();
        currentPage = searchMovieContext.getPage();
        totalResults = searchMovieContext.getTotalResults();
        int scrollPos = searchMovieContext.getScrollPos();

        movieAdapter.setItems(searchMovieContext.getMovies());
        moviesRecyclerView.scrollToPosition(scrollPos);
        searchEditText.setText(currentQuery);
        updateResultTextView();
    }

    private void saveSearchInfos() {
        searchMovieContext.setQuery(currentQuery);
        searchMovieContext.setPage(currentPage);
        searchMovieContext.setTotalResults(totalResults);
        searchMovieContext.setScrollPos(infiniteScrollListener.getFirstVisibleItem());
        searchMovieContext.setMovies(movieAdapter.getItems());
    }

    //                          Method(s)
    //----------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------
    //                          Action(s)

    @OnTextChanged(value = R.id.search_edit_text, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onAfterTextChangedSearchEditText(final Editable editable) {
        final String query = editable.toString();
        if (currentQuery != null && !currentQuery.equals(query)) {
            searchThresholdTimer = new Timer();
            searchThresholdTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (editable.length() > 0) {
                        currentPage = 1;
                        currentQuery = query;
                        searchMoviePresenter.search(currentQuery, currentPage);
                    }
                }
            }, 600);
        }
    }

    @OnTextChanged(value = R.id.search_edit_text, callback = OnTextChanged.Callback.TEXT_CHANGED)
    public void onTextChangedSearchEditText() {
        if (searchThresholdTimer != null) {
            searchThresholdTimer.cancel();
        }
    }

    @OnClick(R.id.clear_button)
    public void onClickClearButton() {
        searchEditText.setText("");
        ActivityHelper.hideKeyboard(this);
    }

    //                          Action(s)
    //----------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------
    //                          View Impl

    @Override
    public void onError() {
        infiniteScrollListener.clear();
    }

    @Override
    public void onResourcesResponse(List<Movie> movies, int page, int totalResults, int totalPages) {
        this.totalResults = totalResults;

        if (page == 1) {
            movieAdapter.setItems(movies);
            moviesRecyclerView.scrollToPosition(0);
            infiniteScrollListener.clear();
        } else if (page > currentPage) {
            movieAdapter.addRangeItems(movies);
        }
        currentPage = page;

        updateResultTextView();
    }

    //                          View Impl
    //----------------------------------------------------------------------------------------------

}
