package com.example.testapiconsumerjava.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.testapiconsumerjava.model.AlbumModel;

import java.util.List;

/*
*Data Access Object Interface to define standard operation performed on the table
* */

@Dao
public interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<AlbumModel> albums);

    @Query("SELECT DISTINCT * FROM Album ORDER BY title ASC")
    LiveData<List<AlbumModel>> getAlbums();

    @Query("DELETE FROM Album")
    void deleteAll();
}
