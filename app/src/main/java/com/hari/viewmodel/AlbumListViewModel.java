package com.hari.viewmodel;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.hari.service.ProjectRepository;

import java.util.List;

import com.hari.model.AlbumResponse;

public class AlbumListViewModel extends AndroidViewModel {
    private final LiveData<List<AlbumResponse>> albumListObservable;

    public AlbumListViewModel(Application application) {
        super(application);
        albumListObservable = ProjectRepository.getInstance().getAlbumsList();
    }

    public LiveData<List<AlbumResponse>> getAlbumListObservable() {
        return albumListObservable;
    }
}
