package com.hari.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.hari.model.PhotosResponse;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;


@Dao
public interface PhotoDao {

    @Insert(onConflict = IGNORE)
    void insertAll(PhotosResponse... photosResponse);

    @Insert(onConflict = IGNORE)
    void insertAll(List<PhotosResponse> photosResponseList);

    @Query("SELECT * FROM photo where albumId =:albumId")
    List<PhotosResponse> fndAll(int albumId);


}
