package com.hari.UI;

import android.app.ActivityOptions;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.hari.R;
import com.hari.adapter.photos.PhotosAdapter;
import com.hari.callbacks.PhotosClickCallback;
import com.hari.databinding.ActivityPhotoBinding;
import com.hari.model.PhotosResponse;
import com.hari.utils.Constants;
import com.hari.utils.GridSpacingItemDecoration;
import com.hari.viewmodel.PhotosListViewModel;

import java.util.List;


public class PhotoListActivity extends BaseActivity {

    private PhotosAdapter photosAdapter;
    private ActivityPhotoBinding activityPhotoBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPhotoBinding = DataBindingUtil.setContentView(this, R.layout.activity_photo);
        activityPhotoBinding.photosList.setLayoutManager(new GridLayoutManager(this, 2));
        activityPhotoBinding.photosList.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(5), false));
        photosAdapter = new PhotosAdapter(photosClickCallback);
        activityPhotoBinding.photosList.setAdapter(photosAdapter);
        activityPhotoBinding.setIsLoading(true);
        PhotosListViewModel.Factory factory = new PhotosListViewModel.Factory(getApplication(), getIntent().getIntExtra(Constants.EXTRA_ALBUM_ID, 0));

        final PhotosListViewModel viewModel = ViewModelProviders.of(this, factory)
                .get(PhotosListViewModel.class);
        observeViewModel(viewModel);
    }

    private void observeViewModel(PhotosListViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getObservablePhotos().observe(this, new Observer<List<PhotosResponse>>() {
            @Override
            public void onChanged(@Nullable List<PhotosResponse> albums) {
                if (albums != null) {
                    activityPhotoBinding.setIsLoading(false);
                    photosAdapter.setAlbumsList(albums);
                }
            }
        });
    }

    private final PhotosClickCallback photosClickCallback = new PhotosClickCallback() {
        @Override
        public void onClick(PhotosResponse albumResponse, View sharedView) {
            handlePhotoClick(albumResponse, sharedView);
        }
    };

    void handlePhotoClick(PhotosResponse photoResponse, View sharedView) {
        Intent intent = new Intent(this, ImageViewActivity.class);
        intent.putExtra(Constants.EXTRA_PHOTO_URL, photoResponse.getUrl());
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this, sharedView, "photo");
        startActivity(intent, activityOptions.toBundle());
    }
}
