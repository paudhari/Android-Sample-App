package com.hari.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "album")
public class AlbumResponse implements Parcelable {
    @PrimaryKey
    private int id;

    private int userId;
    private String title;

    public AlbumResponse(Parcel in) {
        this.id = in.readInt();
        this.userId = in.readInt();
        this.title = in.readString();
    }

    public AlbumResponse() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(userId);
        dest.writeString(title);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<AlbumResponse> CREATOR = new Parcelable.Creator<AlbumResponse>() {

        public AlbumResponse createFromParcel(Parcel in) {
            return new AlbumResponse(in);
        }

        public AlbumResponse[] newArray(int size) {
            return new AlbumResponse[size];
        }
    };
}
