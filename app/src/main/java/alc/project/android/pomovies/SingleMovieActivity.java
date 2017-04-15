package alc.project.android.pomovies;

import alc.project.android.pomovies.model.Movie;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class SingleMovieActivity extends AppCompatActivity {

    private static final String TAG = "SingleMovieActivity";
    TextView overview, release_date, movieTitle, user_voting;
    String moviePath;

    ImageView moviePoster, closeActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_movie);
        setUpViews();
        loadData();
    }

    void setUpViews() {
        moviePoster = (ImageView) findViewById(R.id.moviePoster);
        movieTitle = (TextView) findViewById(R.id.movie_titles);
        overview = (TextView) findViewById(R.id.overview);
        release_date = (TextView) findViewById(R.id.release_date);
        user_voting = (TextView) findViewById(R.id.user_rating);

        closeActivity = (ImageView) findViewById(R.id.closeActivity);

        closeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleMovieActivity.super.onBackPressed();
            }
        });
    }

    void loadData() {

        Movie movie = getIntent().getParcelableExtra("movie");

        moviePath = "http://image.tmdb.org/t/p/w342";

        movieTitle.setText(movie.getOriginal_title());
        overview.setText(movie.getOverview());
        release_date.setText(movie.getRelease_date());

        String vote = String.valueOf(movie.getVote_average());
        user_voting.setText(vote);

        Glide.with(this).load(moviePath + movie.getBackdrop_path()).into(moviePoster);
    }

}
