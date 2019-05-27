package com.atproj.movielib.ui.viewholder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.atproj.movielib.R;
import com.atproj.movielib.model.Movie;
import com.atproj.movielib.ui.activities.IntentConstants;
import com.atproj.movielib.ui.activities.MovieActivity;
import com.atproj.movielib.utils.ImageUtil;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MovieViewHolder extends ItemViewHolder<Movie> {

    @Inject
    Picasso picasso;

    @BindView(R.id.poster_image_view)
    ImageView posterImageView;

    @BindView(R.id.title_text_view)
    TextView titleTextView;

    @BindView(R.id.release_year_text_view)
    TextView releaseYearTextView;

    @BindView(R.id.vote_average_text_view)
    TextView voteAverageTextView;

    @Nullable
    @BindView(R.id.overview_text_view)
    TextView overviewTextView;

    private Movie movie;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void setItem(Movie movie) {
        this.movie = movie;

        ImageUtil.loadPosterImage(picasso, movie, posterImageView);

        if (movie.getOriginalTitle() != null) {
            titleTextView.setText(movie.getTitle());
        }

        if (movie.getReleaseDate() != null) {
            releaseYearTextView.setText(movie.getReleaseYear());
        }

        voteAverageTextView.setText(String.valueOf(movie.getVoteAverage()));

        if (overviewTextView != null && movie.getOverview() != null) {
            overviewTextView.setText(movie.getOverview());
        }
    }

    @OnClick(R.id.movie_item_cell_content)
    public void onClickMovieItem() {
        Intent intent = new Intent(getContext(), MovieActivity.class);
        intent.putExtra(IntentConstants.EXTRA_MOVIE_ID, movie.getId());
        intent.putExtra(IntentConstants.EXTRA_MOVIE_TITLE, movie.getTitle());
        getContext().startActivity(intent);
    }
}
