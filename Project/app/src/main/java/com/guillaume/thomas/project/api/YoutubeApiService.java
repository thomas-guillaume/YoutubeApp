package com.guillaume.thomas.project.api;

import com.guillaume.thomas.project.model.Video;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// To execute the GET request

public interface YoutubeApiService
{
    @GET("search?part=snippet&maxResults=25&type=video&key=AIzaSyA3SKObYfjSgbl3xB4L32m5DIEKd2xz_Fc")
    Call<Video> getVideos(@Query("q") String query);
}