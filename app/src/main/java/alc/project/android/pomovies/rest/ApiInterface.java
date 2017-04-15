package alc.project.android.pomovies.rest;

import alc.project.android.pomovies.model.MovieList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/3/movie/popular")
    Call<MovieList> getPopularMovies(@Query("api_key") String filters);

    @GET("/3/movie/top_rated")
    Call<MovieList> getTopRatedMovies(@Query("api_key") String filters);

}
