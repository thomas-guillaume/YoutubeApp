package com.guillaume.thomas.project.View;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.guillaume.thomas.project.R;
import com.guillaume.thomas.project.model.Item;
import com.guillaume.thomas.project.model.Video;
import com.guillaume.thomas.project.api.ApiUtils;
import com.guillaume.thomas.project.model.VideoAdapter;
import com.guillaume.thomas.project.api.YoutubeApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResearchActivity extends AppCompatActivity
{
    private EditText mResearch;
    private Button mButton;
    private VideoAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private YoutubeApiService youtubeService;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research);


        // initialization
        mResearch = (EditText)findViewById(R.id.edit_research);
        mButton = (Button)findViewById(R.id.btn_ok);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_videos);
        youtubeService = ApiUtils.getYoutubeService();
        mAdapter = new VideoAdapter(this, new ArrayList<Item>(0), new VideoAdapter.PostItemListener() {
            @Override
            public void onPostClick(String id) {
                Intent videoPlayer = new Intent(ResearchActivity.this, VideoplayerActivity.class);
                videoPlayer.putExtra("video_id", id);
                startActivity(videoPlayer);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);


        // verify the connection
        ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected())
        {
            // fetch data
            mButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    loadVideos(v);
                }
            });
        }
        else
        {
            // display error
            System.out.println("Please, check your connection.");
        }
    }


    // fetch the videos
    public void loadVideos(View v){
        String query = mResearch.getText().toString();
        youtubeService.getVideos(query).enqueue(new Callback<Video>() {

            @Override
            public void onResponse(Call<Video> call, Response<Video> response) {

                if(response.isSuccessful()){
                    Video videos = response.body();
                    mAdapter.updateVideos(videos.getItems());
                    Log.d("ResearchActivity", "videos loaded from API");
                }else {
                    int statusCode = response.code();
                    Log.d("error", "couldn't load videos" + statusCode);
                }
            }

            @Override
            public void onFailure(Call<Video> call, Throwable throwable) {
                Log.d("ResearchActivity", "error loading from API ");
            }
        });
    }
}