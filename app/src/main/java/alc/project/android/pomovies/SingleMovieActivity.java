package alc.project.android.pomovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

public class SingleMovieActivity extends AppCompatActivity {

    private static final String TAG = "SingleMovieActivity";
    TextView overview, release_date, movieTitle, user_voting;
    String moviePath;
    ProgressBar progressBar;
    ImageView moviePoster, closeActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_movie);
        setUpViews();
    }

    void setUpViews() {
        moviePoster = (ImageView) findViewById(R.id.moviePoster);
        movieTitle = (TextView) findViewById(R.id.movie_titles);
        overview = (TextView) findViewById(R.id.overview);
        release_date = (TextView) findViewById(R.id.release_date);
        user_voting = (TextView) findViewById(R.id.user_rating);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        closeActivity = (ImageView) findViewById(R.id.closeActivity);

        closeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    void loadData() {
        Intent getPassedData = getIntent();
        moviePath = "http://image.tmdb.org/t/p/w342";
        progressBar.setVisibility(View.INVISIBLE);
        movieTitle.setText(getPassedData.getStringExtra("TITLE"));
        overview.setText(getPassedData.getStringExtra("OVERVIEW"));
        release_date.setText(getPassedData.getStringExtra("RELEASE_DATE"));
        user_voting.setText(getPassedData.getStringExtra("VOTE_AVERAGE"));

        Glide.with(this).load(moviePath + getPassedData.getStringExtra("POSTER")).into(moviePoster);

    }

}
