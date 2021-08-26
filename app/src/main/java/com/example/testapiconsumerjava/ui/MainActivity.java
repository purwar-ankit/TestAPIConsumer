package com.example.testapiconsumerjava.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.testapiconsumerjava.data.network.AlbumApi;
import com.example.testapiconsumerjava.model.AlbumModel;
import com.example.testapiconsumerjava.data.repository.AlbumRepository;
import com.example.testapiconsumerjava.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private AlbumViewModel albumViewModel;
    private List<AlbumModel> allAlbumsList;
    private AlbumAdapter albumAdapter;
    private RecyclerView rvAlbums;
    private AlbumRepository albumRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        albumRepository = new AlbumRepository(getApplication());
        allAlbumsList = new ArrayList<>();
        rvAlbums = findViewById(R.id.rvAlbums);
        rvAlbums.setLayoutManager(new LinearLayoutManager(this));
        rvAlbums.setHasFixedSize(true);

        albumViewModel = new ViewModelProvider(this).get(AlbumViewModel.class);

        albumAdapter = new AlbumAdapter(this, allAlbumsList);
        makeRequest();
        albumViewModel.getAllAlbums().observe(this, new Observer<List<AlbumModel>>() {
            @Override
            public void onChanged(List<AlbumModel> albumModels) {
                rvAlbums.setAdapter(albumAdapter);
                albumAdapter.getAllData(albumModels);
                Log.d("MainAct", "OnChanged: " + albumModels);
            }
        });

    }

    private void makeRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AlbumApi api = retrofit.create(AlbumApi.class);
        Call<List<AlbumModel>> call = api.getAlbums();
        call.enqueue(new retrofit2.Callback<List<AlbumModel>>() {
            @Override
            public void onResponse(Call<List<AlbumModel>> call, Response<List<AlbumModel>> response) {
                if (response.isSuccessful()) {
                    Log.d("MainAct","response; - "+response.raw().request().url());
                    albumRepository.insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<AlbumModel>> call, Throwable t) {
                Log.d("MainAct", "OnFailure: " + t.getMessage());
                Toast.makeText(getApplicationContext(),"Device Offline",Toast.LENGTH_SHORT).show();

            }
        });

    }
}