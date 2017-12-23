package com.example.chintugandhwani.codetoart.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chintugandhwani.codetoart.Constant.StringConstant;
import com.example.chintugandhwani.codetoart.Pojoclasses.MoviesList.Movies;
import com.example.chintugandhwani.codetoart.Pojoclasses.MoviesList.Results;
import com.example.chintugandhwani.codetoart.R;
import com.example.chintugandhwani.codetoart.Utiliy.ApiClient;
import com.example.chintugandhwani.codetoart.Utiliy.ApiInterface;
import com.example.chintugandhwani.codetoart.Utiliy.NetworkConnectivity;
import com.example.chintugandhwani.codetoart.adapter.RecyclerViewAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private final static String API_KEY = "b7cd3340a794e5a2f35e3abb820b497f";
    List<Results> moviesList;
    private SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    RecyclerViewAdapter mAdapter;
    RelativeLayout loaderlayout;
    ApiInterface apiInterface;
    Call<Movies> moviesCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Upcoming Movies");
        initviews();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        if (NetworkConnectivity.isConnected(MainActivity.this)) {
            fetchdata();
        } else {
            showerror();
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (NetworkConnectivity.isConnected(MainActivity.this)) {
                    fetchdata();
                } else {
                    showerror();
                }
            }
        });
    }

    public void initviews() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        loaderlayout = (RelativeLayout) findViewById(R.id.loader);
    }

    public void setRecyclerViewdata() {
        mAdapter = new RecyclerViewAdapter(moviesList, MainActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    public void showmoviedetail(String id, String moviename) {
        Intent in = new Intent(MainActivity.this, MoviedetailActivity.class);
        in.putExtra(StringConstant.MOVIE_ID, Integer.parseInt(id));
        in.putExtra(StringConstant.MOVIE_NAME, moviename);
        startActivity(in);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_info:
                startActivity(new Intent(MainActivity.this, Developerinfo.class));
                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void fetchdata() {
        loaderlayout.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        moviesCall = apiInterface.getUpcomingMovies(API_KEY);
        moviesCall.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                moviesList = response.body().getResults();
                setRecyclerViewdata();
                loaderlayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

                loaderlayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                swipeRefreshLayout.setRefreshing(false);

            }
        });

    }

    public void showerror() {
        swipeRefreshLayout.setRefreshing(false);
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.coordinator), "Please check Internet Connection !!", Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
