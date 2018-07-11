package com.hari.adapter.albums;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hari.R;
import com.hari.callbacks.AlbumClickCallback;
import com.hari.databinding.ListItemAlbumBinding;
import com.hari.model.AlbumResponse;

import java.util.List;
import java.util.Objects;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder> {

    List<AlbumResponse> albumList;

    @Nullable
    private final AlbumClickCallback albumClickCallback;

    public AlbumsAdapter(@Nullable AlbumClickCallback albumClickCallback) {
        this.albumClickCallback = albumClickCallback;
    }

    public void setAlbumsList(final List<AlbumResponse> albumList) {
        if (this.albumList == null) {
            this.albumList = albumList;
            notifyItemRangeInserted(0, albumList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return AlbumsAdapter.this.albumList.size();
                }

                @Override
                public int getNewListSize() {
                    return albumList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AlbumsAdapter.this.albumList.get(oldItemPosition).getId() ==
                            albumList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    AlbumResponse project = albumList.get(newItemPosition);
                    AlbumResponse old = albumList.get(oldItemPosition);
                    return project.getId() == old.getId()
                            && Objects.equals(project.getTitle(), old.getTitle());
                }
            });
            this.albumList = albumList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemAlbumBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_album,
                        parent, false);

        binding.setCallback(albumClickCallback);

        return new AlbumViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        holder.binding.setAlbum(albumList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return albumList == null ? 0 : albumList.size();
    }

    static class AlbumViewHolder extends RecyclerView.ViewHolder {

        final ListItemAlbumBinding binding;

        public AlbumViewHolder(ListItemAlbumBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}