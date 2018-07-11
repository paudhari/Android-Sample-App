package com.hari.service;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.hari.application.BaseApplication;
import com.hari.database.AppDatabase;
import com.hari.model.AlbumResponse;
import com.hari.model.PhotosResponse;
import com.hari.utils.UpstackPrefernece;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectRepository {
    private APIInterface apiInterface;
    private static ProjectRepository projectRepository;
    private AppDatabase appDatabase;

    private ProjectRepository() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        appDatabase = BaseApplication.getAppDatabase(BaseApplication.mContext);

    }

    public synchronized static ProjectRepository getInstance() {
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
        return projectRepository;
    }

    public LiveData<List<AlbumResponse>> getAlbumsList() {
        final MutableLiveData<List<AlbumResponse>> data = new MutableLiveData<>();
        if (UpstackPrefernece.getInstance().getAlbums() != null) {
            data.setValue(UpstackPrefernece.getInstance().getAlbums());
        } else {
            apiInterface.getAlbums().enqueue(new Callback<List<AlbumResponse>>() {
                @Override
                public void onResponse(Call<List<AlbumResponse>> call, Response<List<AlbumResponse>> response) {
                    appDatabase.albumDao().insertAll(response.body());
                    UpstackPrefernece.getInstance().saveAlbums(response.body());
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<List<AlbumResponse>> call, Throwable t) {
                    data.setValue(null);
                }
            });
        }
        return data;
    }

    public LiveData<List<PhotosResponse>> getPhotos(int albumId) {
        final MutableLiveData<List<PhotosResponse>> data = new MutableLiveData<>();
        if (appDatabase.photoDao().fndAll(albumId) != null && appDatabase.photoDao().fndAll(albumId).size() > 0) {
            data.setValue(appDatabase.photoDao().fndAll(albumId));
        }else {
            apiInterface.getAlbumPhotos(albumId).enqueue(new Callback<List<PhotosResponse>>() {
                @Override
                public void onResponse(Call<List<PhotosResponse>> call, Response<List<PhotosResponse>> response) {
                    simulateDelay();
                    appDatabase.photoDao().insertAll(response.body());
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<List<PhotosResponse>> call, Throwable t) {
                    data.setValue(null);
                }
            });
        }
        return data;
    }

    private void simulateDelay() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
