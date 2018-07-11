package com.hari.adapter.photos;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hari.R;
import com.hari.callbacks.PhotosClickCallback;
import com.hari.databinding.ListItemPhotosBinding;
import com.hari.model.PhotosResponse;

import java.util.List;
import java.util.Objects;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder> {

    List<? extends PhotosResponse> photoList;

    @Nullable
    private final PhotosClickCallback photoClickCallback;

    public PhotosAdapter(@Nullable PhotosClickCallback photoClickCallback) {
        this.photoClickCallback = photoClickCallback;
    }

    public void setAlbumsList(final List<? extends PhotosResponse> photoList) {
        if (this.photoList == null) {
            this.photoList = photoList;
            notifyItemRangeInserted(0, photoList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return PhotosAdapter.this.photoList.size();
                }

                @Override
                public int getNewListSize() {
                    return photoList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return PhotosAdapter.this.photoList.get(oldItemPosition).getId() ==
                            photoList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    PhotosResponse project = photoList.get(newItemPosition);
                    PhotosResponse old = photoList.get(oldItemPosition);
                    return project.getId() == old.getId()
                            && Objects.equals(project.getTitle(), old.getTitle());
                }
            });
            this.photoList = photoList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemPhotosBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_photos,
                        parent, false);

        binding.setCallback(photoClickCallback);

        return new PhotoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        holder.binding.setSinglePhoto(photoList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return photoList == null ? 0 : photoList.size();
    }

    static class PhotoViewHolder extends RecyclerView.ViewHolder {

        final ListItemPhotosBinding binding;

        public PhotoViewHolder(ListItemPhotosBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}