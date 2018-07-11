package com.hari.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.hari.model.AlbumResponse;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;


@Dao
public interface AlbumDao {

    @Insert(onConflict = IGNORE)
    void insertAll(AlbumResponse... albumResponse);

    @Insert(onConflict = IGNORE)
    void insertAll(List<AlbumResponse> albumResponseList);

    @Query("Select * from album")
    List<AlbumResponse> findAll();
}
