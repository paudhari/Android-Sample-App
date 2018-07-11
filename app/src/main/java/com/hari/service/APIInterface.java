package com.hari.service;

import com.hari.model.AlbumResponse;
import com.hari.model.PhotosResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("/albums")
    Call<List<AlbumResponse>> getAlbums();

    @GET("/albums/{albumId}/photos")
    Call<List<PhotosResponse>> getAlbumPhotos(@Path("albumId") int albumId);

}