package com.droidrank.sample.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASEURL="https://simplifiedcoding.net/demos/";

    @GET("marvel")
    Call<List<Heros>> getHeros();
}
