package com.hari.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BindingAdapter;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

@Entity(tableName = "photo")
public class PhotosResponse implements Parcelable {
    @PrimaryKey
    private int id;
    private int albumId;
    private String title;
    private String url;
    private String thumbnailUrl;

    public PhotosResponse(Parcel in) {
        this.id = in.readInt();
        this.albumId = in.readInt();
        this.title = in.readString();
        this.url = in.readString();
        this.thumbnailUrl = in.readString();
    }

    public PhotosResponse() {

    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

    public String getImageUrl(){
        return this.thumbnailUrl;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(albumId);
        dest.writeString(title);
        dest.writeString(url);
        dest.writeString(thumbnailUrl);
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<PhotosResponse> CREATOR = new Parcelable.Creator<PhotosResponse>() {

        public PhotosResponse createFromParcel(Parcel in) {
            return new PhotosResponse(in);
        }

        public PhotosResponse[] newArray(int size) {
            return new PhotosResponse[size];
        }
    };
}
