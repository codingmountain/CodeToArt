package com.example.chintugandhwani.codetoart.Activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.chintugandhwani.codetoart.Constant.StringConstant;
import com.example.chintugandhwani.codetoart.Pojoclasses.MovieImages.MovieImages;
import com.example.chintugandhwani.codetoart.Pojoclasses.Moviedetail.Moviedetail;
import com.example.chintugandhwani.codetoart.R;
import com.example.chintugandhwani.codetoart.Utiliy.ApiClient;
import com.example.chintugandhwani.codetoart.Utiliy.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;

public class MoviedetailActivity extends AppCompatActivity {

    Moviedetail moviedetails;
    private final static String API_KEY = "b7cd3340a794e5a2f35e3abb820b497f";
    int id;
    TextView movietitletv, movieoverviewtv;
    RatingBar Movierating;
    BannerSlider bannerSlider;
    List<Banner> banners;
    MovieImages movieImages;
    String moviename;
    ProgressBar infoloaderanim;
    LinearLayout infoviewlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviedetail);
        id = getIntent().getIntExtra(StringConstant.MOVIE_ID, 0);
        getSupportActionBar().setTitle(getIntent().getStringExtra(StringConstant.MOVIE_NAME));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initviews();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Moviedetail> moviedetailCall = apiInterface.getMovieDetails(id, API_KEY);
        Call<MovieImages> movieImagesCall = apiInterface.getMovieImages(id, API_KEY);
        infoloaderanim.setVisibility(View.VISIBLE);
        infoviewlayout.setVisibility(View.GONE);
        moviedetailCall.enqueue(new Callback<Moviedetail>() {
            @Override
            public void onResponse(Call<Moviedetail> call, Response<Moviedetail> response) {
                moviedetails = response.body();
                infoloaderanim.setVisibility(View.GONE);
                infoviewlayout.setVisibility(View.VISIBLE);
                movietitletv.setText(moviedetails.getOriginal_title());
                movieoverviewtv.setText(moviedetails.getOverview());
                Movierating.setMax(5);
                Movierating.setRating(Float.parseFloat(moviedetails.getPopularity()) / 100);
            }

            @Override
            public void onFailure(Call<Moviedetail> call, Throwable t) {
                infoloaderanim.setVisibility(View.GONE);
                infoviewlayout.setVisibility(View.VISIBLE);

            }
        });

        movieImagesCall.enqueue(new Callback<MovieImages>() {
            @Override
            public void onResponse(Call<MovieImages> call, Response<MovieImages> response) {
                movieImages = response.body();
                banners = new ArrayList<>();
                bannerSlider.setBackgroundResource(R.drawable.emptyimage);
                if (movieImages.getPosters().size() >= 5) {
                    for (int i = 0; i < 5; i++) {
                        banners.add(new RemoteBanner(StringConstant.BASE_URL_IMG + movieImages.getPosters().get(i).getFile_path().toString()));
                    }
                } else {
                    for (int i = 0; i < movieImages.getPosters().size(); i++) {
                        banners.add(new RemoteBanner(StringConstant.BASE_URL_IMG + movieImages.getPosters().get(i).getFile_path().toString()));
                    }
                }

                bannerSlider.setBanners(banners);

            }

            @Override
            public void onFailure(Call<MovieImages> call, Throwable t) {

            }
        });

    }

    public void initviews() {
        Movierating = (RatingBar) findViewById(R.id.movierating);
        movietitletv = (TextView) findViewById(R.id.movieorignaltitle);
        movieoverviewtv = (TextView) findViewById(R.id.movievoerview);
        bannerSlider = (BannerSlider) findViewById(R.id.banner_slider);
        infoloaderanim = (ProgressBar) findViewById(R.id.infoloader);
        infoviewlayout = (LinearLayout) findViewById(R.id.infolayout);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
