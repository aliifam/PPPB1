package com.aliif.retrofilm.retrofit;

import com.aliif.retrofilm.model.MainModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpoint {
    @GET("data.php") Call<MainModel> getFilm();
}
