package com.example.testapiconsumerjava.data.network;

import com.example.testapiconsumerjava.model.AlbumModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AlbumApi {

    @GET("albums")
    Call<List<AlbumModel>> getAlbums();
}
