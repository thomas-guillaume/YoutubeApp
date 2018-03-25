package com.guillaume.thomas.project.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

// To display in a RecyclerView

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>
{
    private List<Item> mVideos;
    private Context mContext;
    private PostItemListener mVideoListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView title;
        PostItemListener mItemListener;

        public ViewHolder(View itemView, PostItemListener postItemListener){
            super(itemView);
            title = itemView.findViewById(android.R.id.text1);
            this.mItemListener = postItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Item item = getItem(getAdapterPosition());
            this.mItemListener.onPostClick(item.getId().getVideoId());

            notifyDataSetChanged();
        }
    }

    public VideoAdapter(Context context, List<Item> videos, PostItemListener videoListener) {
        mVideos = videos;
        mContext = context;
        mVideoListener = videoListener;
    }

    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(android.R.layout.simple_expandable_list_item_1, parent, false);

        ViewHolder viewHolder = new ViewHolder(postView, this.mVideoListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VideoAdapter.ViewHolder holder, int position){

        Item item = mVideos.get(position);
        TextView textView = holder.title;


        textView.setText(item.getSnippet().getTitle());
    }

    @Override
    public int getItemCount(){
        return mVideos.size();
    }

    public void updateVideos(List<Item> videos){
        mVideos = videos;
        notifyDataSetChanged();
    }

    private Item getItem(int adapterPosition){
        return mVideos.get(adapterPosition);
    }

    public interface PostItemListener {
        void onPostClick(String id);
    }
}
