package com.hari.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.hari.dao.AlbumDao;
import com.hari.dao.PhotoDao;
import com.hari.model.AlbumResponse;
import com.hari.model.PhotosResponse;

@Database(entities = {AlbumResponse.class, PhotosResponse.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PhotoDao photoDao();

    public abstract AlbumDao albumDao();
}