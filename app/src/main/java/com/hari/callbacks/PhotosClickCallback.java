package com.hari.callbacks;

import android.view.View;

import com.hari.model.PhotosResponse;

public interface PhotosClickCallback {
    void onClick(PhotosResponse photosResponse, View sharedView);
}