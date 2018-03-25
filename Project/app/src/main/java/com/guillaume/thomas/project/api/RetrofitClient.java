package com.guillaume.thomas.project.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//To issue network requests to a REST API

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}