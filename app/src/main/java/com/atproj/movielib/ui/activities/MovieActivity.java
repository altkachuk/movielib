package com.atproj.movielib.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.atproj.movielib.R;
import com.atproj.movielib.model.Movie;
import com.atproj.movielib.model.Video;
import com.atproj.movielib.presenter.MoviePresenter;
import com.atproj.movielib.presenter.VideosPresenter;
import com.atproj.movielib.utils.ImageUtil;
import com.atproj.movielib.utils.VideoUtil;
import com.atproj.movielib.view.MovieView;
import com.atproj.movielib.view.VideosView;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MovieActivity extends ResourceBaseActivity implements MovieView, VideosView {

    private static final String KEY_MOVIE = "movie";
    private static final String KEY_YOUTUBE = "youtube";

    @BindView(R.id.title_text_view)
    TextView titleTextView;

    @BindView(R.id.release_year_text_view)
    TextView releaseYearTextView;

    @BindView(R.id.vote_average_text_view)
    TextView voteAverageTextView;

    @BindView(R.id.vote_count_text_view)
    TextView voteCountTextView;

    @BindView(R.id.runtime_text_view)
    TextView runtimeTextTiew;

    @BindView(R.id.release_date_text_view)
    TextView releaseDateTextView;

    @BindView(R.id.production_country_text_view)
    TextView productionCountryTextView;

    @BindView(R.id.poster_image_view)
    ImageView posterImageView;

    @BindView(R.id.genres_text_view)
    TextView genresTextView;

    @BindView(R.id.overview_text_view)
    TextView overviewTextView;

    @BindView(R.id.youtube_image_view)
    ImageView youtubeImageView;

    @BindView(R.id.play_button_image_view)
    ImageView playButtonImageView;

    @Inject
    Picasso picasso;

    private MoviePresenter moviePresenter;
    private VideosPresenter videosPresenter;

    private int movieId = 0;
    private Movie movie;
    private String youtubeKey;
    private boolean needLoad = false;

    //----------------------------------------------------------------------------------------------
    //                          Lifecycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        moviePresenter = new MoviePresenter(getApplicationContext(), this);
        videosPresenter = new VideosPresenter(getApplicationContext(), this);

        if (getIntent().hasExtra(IntentConstants.EXTRA_MOVIE_TITLE)) {
            String title = getIntent().getStringExtra(IntentConstants.EXTRA_MOVIE_TITLE);
            setTitle(title);
        }

        if (getIntent().hasExtra(IntentConstants.EXTRA_MOVIE_ID)) {
            movieId = getIntent().getIntExtra(IntentConstants.EXTRA_MOVIE_ID, 0);
            needLoad = true;
        }

        playButtonImageView.setVisibility(View.GONE);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        needLoad = false;
        getMovieInfoFromBundle(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (needLoad) {
            needLoad = false;
            setLoadingState();
            moviePresenter.getMovie(movieId);
            videosPresenter.getVideos(movieId);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        saveMovieInfo(savedInstanceState);
    }

    //                          Lifecycle
    //----------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------
    //                          Method(s)

    private void getMovieInfoFromBundle(Bundle bundle) {
        if (bundle.containsKey(KEY_MOVIE)) {
            movie = Parcels.unwrap(bundle.getParcelable(KEY_MOVIE));
            updateContent(movie);
        }
        if (bundle.containsKey(KEY_YOUTUBE)) {
            youtubeKey = bundle.getString(KEY_YOUTUBE);
            updateTrailerContent();
        }
    }

    private void saveMovieInfo(Bundle bundle) {
        if (movie != null) {
            bundle.putParcelable(KEY_MOVIE, Parcels.wrap(movie));
        }
        if (youtubeKey != null) {
            bundle.putString(KEY_YOUTUBE, youtubeKey);
        }
    }

    private void updateContent(Movie movie) {
        titleTextView.setText(movie.getTitle());
        releaseYearTextView.setText(movie.getReleaseYear());
        voteAverageTextView.setText(String.valueOf(movie.getVoteAverage()));
        voteCountTextView.setText(String.valueOf(movie.getVoteCount()));
        runtimeTextTiew.setText(movie.getRuntime() + getString(R.string.minutes));
        releaseDateTextView.setText(movie.getReleaseDate());

        if (movie.getProductionCountries() != null && movie.getProductionCountries().size() > 0) {
            productionCountryTextView.setText(
                    "(" + movie.getProductionCountries().get(0).getIso31661() + ")");
        }

        ImageUtil.loadPosterImage(picasso, movie, posterImageView);

        if (movie.getGenres() != null) {
            if (movie.getGenres().size() > 0) {
                String genres = movie.getGenres().get(0).getName();
                for (int i = 1; i < movie.getGenres().size(); i++) {
                    genres += ", " + movie.getGenres().get(i).getName();
                }
                genresTextView.setText(genres);
            }
        }

        overviewTextView.setText(movie.getOverview());
    }

    private void updateTrailerContent() {
        if (youtubeKey != null) {
            ImageUtil.loadPreviewVideo(picasso, VideoUtil.buildYoutubePreview(youtubeKey),
                    youtubeImageView);
            playButtonImageView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onReload() {
        setLoadingState();
        moviePresenter.getMovie(movieId);
        videosPresenter.getVideos(movieId);
    }

    //                          Method(s)
    //----------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------
    //                          Action(s)


    @OnClick(R.id.play_button_image_view)
    public void onClickPlayButton() {
        if (youtubeKey != null) {
            Uri uri = Uri.parse(VideoUtil.buildYoutubeUrl(youtubeKey));
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

    //                          Action(s)
    //----------------------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------------------
    //                          View Impl

    @Override
    public void onError() {
        setErrorState();
    }

    @Override
    public void onResourceResponse(Movie movie) {
        this.movie = movie;
        updateContent(movie);
    }

    @Override
    public void onResourcesResponse(List<Video> videos, int page, int totalResults, int totalPages) {
        setLoadedState();

        Video youtubeVideo = VideoUtil.getYoutubeVideo(videos);
        if (youtubeVideo != null) {
            youtubeKey = youtubeVideo.getKey();
            updateTrailerContent();
        }
    }

    //                          View Impl
    //----------------------------------------------------------------------------------------------

}
