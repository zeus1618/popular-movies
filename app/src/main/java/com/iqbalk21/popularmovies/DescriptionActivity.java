package com.iqbalk21.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.iqbalk21.popularmovies.dataset.Movie;
import com.squareup.picasso.Picasso;

public class DescriptionActivity extends AppCompatActivity {

    private static final String TAG = "DescriptionActivity";

    private ImageView posterIV;
    private TextView titleTV, userRatingTV, releaseDateTV, synopsisTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Movie movie = getIntent().getParcelableExtra("moviedata");
        Log.d(TAG,movie.toString());

        posterIV = (ImageView) findViewById(R.id.poster_thumb);
        titleTV = (TextView) findViewById(R.id.title);
        userRatingTV = (TextView) findViewById(R.id.user_rating);
        releaseDateTV = (TextView) findViewById(R.id.release_date);
        synopsisTV = (TextView) findViewById(R.id.synopsis);

        Picasso.with(DescriptionActivity.this).load(movie.getImagePath()).into(posterIV);
        titleTV.setText(movie.getOriginalTitle());
        userRatingTV.setText(movie.getUserRating());
        releaseDateTV.setText(movie.getReleaseDate());
        synopsisTV.setText(movie.getPlotSynopsis());

    }
}
