package alc.project.android.pomovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import alc.project.android.pomovies.adapter.MoviesAdapter;
import alc.project.android.pomovies.model.MovieList;
import alc.project.android.pomovies.rest.ApiClient;
import alc.project.android.pomovies.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rRecyclerView;
    private ApiClient apiClient;
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createViews();
        apiClient = new ApiClient();
        loadMovieData();
    }

    void createViews() {
        rRecyclerView = (RecyclerView) findViewById(R.id.user_recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rRecyclerView.setLayoutManager(gridLayoutManager);
        rRecyclerView.setHasFixedSize(true);
        rRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
    }

    void loadMovieData() {

        String API_KEY = "ENTER API_KEY HERE";
        ApiInterface apiInterface = apiClient.getService();
        Call<MovieList> movieListCall = apiInterface.getPopularMovies(API_KEY);
        movieListCall.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if (response.isSuccessful()) {
                    MovieList movieList = response.body();
                    moviesAdapter = new MoviesAdapter(movieList.getResults());
                    rRecyclerView.setAdapter(moviesAdapter);
                } else {
                    Toast.makeText(MainActivity.this, "Request not Sucessful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        "Request failed. Check your internet connection",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    void loadTopRatedData() {

        String API_KEY = "ENTER API_KEY HERE";
        ApiInterface apiInterface = apiClient.getService();
        Call<MovieList> movieListCall = apiInterface.getTopRatedMovies(API_KEY);
        movieListCall.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if (response.isSuccessful()) {
                    MovieList movieList = response.body();
                    moviesAdapter = new MoviesAdapter(movieList.getResults());
                    rRecyclerView.setAdapter(moviesAdapter);
                } else {
                    Toast.makeText(MainActivity.this, "Request not Sucessful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        "Request failed. Check your internet connection",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.sortA:
                loadMovieData();
                return true;
            case R.id.sortB:
                loadTopRatedData();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

}
