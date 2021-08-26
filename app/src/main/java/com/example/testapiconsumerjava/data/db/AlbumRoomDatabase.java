package com.example.testapiconsumerjava.data.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.testapiconsumerjava.model.AlbumModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {AlbumModel.class}, version = 1, exportSchema = false)
public abstract class AlbumRoomDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "album_database";
    public abstract AlbumDao albumDao();

    private static volatile AlbumRoomDatabase INSTANCE;
    //private static final int NUMBER_OF_THREAD = 4;
   // static final ExecutorService dbWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREAD);

    //Creating a n INSTANCE of the database
    public static AlbumRoomDatabase getInstance(final Context context){
        if (INSTANCE == null){
            synchronized (AlbumRoomDatabase.class){
                if (INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AlbumRoomDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    //callback to start a background thread task which deletes the old database whenever a new database is created
    public static Callback callback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE);
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void,Void,Void> {
        private AlbumDao albumDao;
        public PopulateDbAsync(AlbumRoomDatabase dbInstance) {
            albumDao = dbInstance.albumDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            albumDao.deleteAll();
            return null;
        }
    }
}
