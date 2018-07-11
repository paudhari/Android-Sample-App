package com.hari.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.hari.model.AlbumResponse;
import com.hari.model.PhotosResponse;
import com.hari.service.ProjectRepository;

import java.util.List;

public class PhotosListViewModel extends AndroidViewModel{
    private final LiveData<List<PhotosResponse>> projectObservable;
    private final int albumID;

    public ObservableField<AlbumResponse> album = new ObservableField<>();

    public PhotosListViewModel(@NonNull Application application,
                            final int albumID) {
        super(application);
        this.albumID = albumID;

        projectObservable = ProjectRepository.getInstance().getPhotos(albumID);
    }

    public LiveData<List<PhotosResponse>> getObservablePhotos() {
        return projectObservable;
    }

    public void setAlbum(AlbumResponse album) {
        this.album.set(album);
    }

    /**
     * A creator is used to inject the project ID into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        private final int albumID;

        public Factory(@NonNull Application application, int albumID) {
            this.application = application;
            this.albumID = albumID;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new PhotosListViewModel(application, albumID);
        }
    }
}
