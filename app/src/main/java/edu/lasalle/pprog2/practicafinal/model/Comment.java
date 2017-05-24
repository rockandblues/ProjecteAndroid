package edu.lasalle.pprog2.practicafinal.model;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by MatiasJVH on 04/04/2017.
 */

public class Comment implements Parcelable {

    private String username;
    private String comment;

    public Comment() {
    }

    protected Comment(Parcel in) {
        this.username = in.readString();
        this.comment = in.readString();
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel source) {
            return new Comment(source);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(comment);

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
