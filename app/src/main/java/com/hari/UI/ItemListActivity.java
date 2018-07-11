package com.hari.UI;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hari.R;
import com.hari.adapter.albums.AlbumsAdapter;
import com.hari.callbacks.AlbumClickCallback;
import com.hari.databinding.ActivityAlbumBinding;
import com.hari.model.AlbumResponse;
import com.hari.utils.Constants;
import com.hari.viewmodel.AlbumListViewModel;

import java.util.List;


public class ItemListActivity extends BaseActivity{

    private AlbumsAdapter albumsAdapter;
    private ActivityAlbumBinding activityAlbumBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAlbumBinding = DataBindingUtil.setContentView(this, R.layout.activity_album);
        albumsAdapter = new AlbumsAdapter(albumClickCallback);
        activityAlbumBinding.albumsList.setAdapter(albumsAdapter);
        activityAlbumBinding.setIsLoading(true);
        final AlbumListViewModel viewModel = ViewModelProviders.of(this).get(AlbumListViewModel.class);
        observeViewModel(viewModel);
    }

    private void observeViewModel(AlbumListViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getAlbumListObservable().observe(this, new Observer<List<AlbumResponse>>() {
            @Override
            public void onChanged(@Nullable List<AlbumResponse> albums) {
                if (albums != null) {
                    activityAlbumBinding.setIsLoading(false);
                    albumsAdapter.setAlbumsList(albums);
                }
            }
        });
    }

    private final AlbumClickCallback albumClickCallback = new AlbumClickCallback() {
        @Override
        public void onClick(AlbumResponse albumResponse) {
            Intent intent = new Intent(ItemListActivity.this, PhotoListActivity.class);
            intent.putExtra(Constants.EXTRA_ALBUM_ID, albumResponse.getId());
            startActivity(intent);
        }
    };
}
