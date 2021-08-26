package com.example.testapiconsumerjava.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.testapiconsumerjava.data.repository.AlbumRepository;
import com.example.testapiconsumerjava.model.AlbumModel;

import java.util.List;

public class AlbumViewModel extends AndroidViewModel {
    private AlbumRepository repository;
    public LiveData<List<AlbumModel>> getAllAlbums;

    public AlbumViewModel(@NonNull Application application) {
        super(application);
        repository = new AlbumRepository(application);
        getAllAlbums = repository.getAllAlbums();
    }

    public void insert(List<AlbumModel> albums){
        repository.insert(albums);
    }

    public LiveData<List<AlbumModel>> getAllAlbums() {
        return getAllAlbums;
    }
}
