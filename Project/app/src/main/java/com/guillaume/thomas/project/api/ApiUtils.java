package com.guillaume.thomas.project.api;

// To have the base URL as a static variable and to provide the YoutubeApiService interface to our application through the getYoutubeService() static method.

public class ApiUtils
{
    public static final String BASE_URL = "https://www.googleapis.com/youtube/v3/";

    public static YoutubeApiService getYoutubeService() {
        return RetrofitClient.getClient(BASE_URL).create(YoutubeApiService.class);
    }
}