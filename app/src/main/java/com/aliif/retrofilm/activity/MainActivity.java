package com.aliif.retrofilm.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.aliif.retrofilm.R;
import com.aliif.retrofilm.adapter.MainAdapter;
import com.aliif.retrofilm.model.MainModel;
import com.aliif.retrofilm.retrofit.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MainAdapter mainAdapter;
    private ArrayList<MainModel.Result> results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
        setupRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        showLoading(false);
        getDataFromApi();
    }

    private void setupView()
    {
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
    }

    private void setupRecyclerView()
    {
        mainAdapter = new MainAdapter(this, results, new MainAdapter.AdapterListener() {
            @Override
            public void onClick(MainModel.Result result)
            {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("title", result.getTitle());
                intent.putExtra("image", result.getImage());
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainAdapter);
    }

    private void getDataFromApi(){
        showLoading(true);
        ApiService.endpoint().getFilm()
                .enqueue(new Callback<MainModel>() {
                    @Override
                    public void onResponse(@NonNull Call<MainModel> call, @NonNull Response<MainModel> response) {
                        showLoading(false);
                        Log.d(TAG, results.toString());
                        if (response.isSuccessful())
                        {
                            assert response.body() != null;
                            List<MainModel.Result> results = response.body().getResults();
                            mainAdapter.setFilm(results);
                            Log.d(TAG, results.toString());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MainModel> call, @NonNull Throwable t) {
                        showLoading( false );
                        Log.d( TAG, t.toString());
                    }
                });
    }

    private void showLoading(Boolean loading)
    {
        if (loading)
        {
            progressBar.setVisibility(View.VISIBLE);
        }else
        {
            progressBar.setVisibility(View.GONE);
        }
    }
}