package com.example.testapiconsumerjava.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapiconsumerjava.R;
import com.example.testapiconsumerjava.model.AlbumModel;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private Context context;
    private List<AlbumModel> albums;

    public AlbumAdapter(Context context, List<AlbumModel> albums) {
        this.context = context;
        this.albums = albums;
    }

    @NonNull
    @Override
    public AlbumAdapter.AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlbumViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.album_title,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        AlbumModel album = albums.get(position);
        holder.tvAlbumTitle.setText(album.getTitle());
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public void getAllData(List<AlbumModel> albums)
    {
        this.albums=albums;
    }


    public static class AlbumViewHolder extends RecyclerView.ViewHolder {
        public TextView tvAlbumTitle;
        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAlbumTitle = itemView.findViewById(R.id.tvItemTitle);
        }
    }
}
