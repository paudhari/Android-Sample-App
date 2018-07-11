package com.hari.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hari.application.BaseApplication;

import java.util.ArrayList;
import java.util.List;

import com.hari.model.AlbumResponse;

public class UpstackPrefernece {

    private static SharedPreferences mPreferences;
    private static UpstackPrefernece mInstance;
    private static Editor mEditor;

    private static String ALBUMS = "albums";


    private UpstackPrefernece() {
    }


    public static UpstackPrefernece getInstance() {
        synchronized (UpstackPrefernece.class) {
            if (mInstance == null) {
                Context context = BaseApplication.mContext;
                mInstance = new UpstackPrefernece();
                mPreferences = context.getSharedPreferences(Constants.UPSTACK, Context.MODE_PRIVATE);
                mEditor = mPreferences.edit();
            }
        }
        return mInstance;
    }


    public void saveAlbums(List<AlbumResponse> value) {
        mEditor.putString(ALBUMS, new Gson().toJson(value)).apply();
    }

    public ArrayList<AlbumResponse> getAlbums() {
        Gson gson = new Gson();
        String contactListString = mPreferences.getString(ALBUMS, null);
        if (!StringUtils.isNullOrEmpty(contactListString)) {
            ArrayList<AlbumResponse> albumResponseList = gson.fromJson(contactListString, new TypeToken<List<AlbumResponse>>() {
            }.getType());
            return albumResponseList;
        }
        return null;
    }

}