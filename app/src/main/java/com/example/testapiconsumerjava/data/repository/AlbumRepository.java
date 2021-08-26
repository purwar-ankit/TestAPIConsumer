package com.example.testapiconsumerjava.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.testapiconsumerjava.model.AlbumModel;
import com.example.testapiconsumerjava.data.db.AlbumDao;
import com.example.testapiconsumerjava.data.db.AlbumRoomDatabase;

import java.util.List;

public class AlbumRepository {
    public AlbumDao albumDao;
    public LiveData<List<AlbumModel>> allAlbumsList;
    private AlbumRoomDatabase database;

    public AlbumRepository(Application application){
        database = AlbumRoomDatabase.getInstance(application);
        albumDao = database.albumDao();
        allAlbumsList = albumDao.getAlbums();
    }

    public void insert(List<AlbumModel> albums){
        new InsetAsyncTask(albumDao).execute(albums);
    }

    public LiveData<List<AlbumModel>> getAllAlbums(){
        return allAlbumsList;
    }

    private static class InsetAsyncTask extends AsyncTask<List<AlbumModel>, Void, Void> {
        private AlbumDao albumDao;
        public InsetAsyncTask(AlbumDao albumDao) {
            this.albumDao = albumDao;
        }

        @Override
        protected Void doInBackground(List<AlbumModel>... lists) {
            albumDao.insert(lists[0]);
            return null;
        }
    }
}
