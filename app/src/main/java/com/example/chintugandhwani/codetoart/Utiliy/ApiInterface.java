package com.example.chintugandhwani.codetoart.Utiliy;

import com.example.chintugandhwani.codetoart.Pojoclasses.MovieImages.MovieImages;
import com.example.chintugandhwani.codetoart.Pojoclasses.Moviedetail.Moviedetail;
import com.example.chintugandhwani.codetoart.Pojoclasses.MoviesList.Movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {

    @GET("upcoming")
    Call<Movies> getUpcomingMovies(@Query("api_key") String apiKey);

    @GET("{id}")
    Call<Moviedetail> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("{id}/images")
    Call<MovieImages> getMovieImages(@Path("id") int id, @Query("api_key") String apiKey);


}