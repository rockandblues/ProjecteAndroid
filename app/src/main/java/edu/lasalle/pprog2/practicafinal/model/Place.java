package edu.lasalle.pprog2.practicafinal.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by miquelabellan on 18/4/17.
 */

public class Place implements Parcelable {

    private String name;
    private String type;
    private Location location;
    private String description;
    private String address;
    private float review;
    private String opening;
    private String closing;
    private int favourite; /* -1 para favorito*/


    public Place (String name, String type, float lat, float lon, String description, String address,
                  float review, String opening, String closing) {
        this.name = name;
        this.type = type;
        location = new Location();
        location.setLat(lat);
        location.setLng(lon);
        this.description = description;
        this.address = address;
        this.review = review;
        this.opening = opening;
        this.closing = closing;
    }

    public Place() {
    }

    protected Place(Parcel in) {
        name = in.readString();
        type = in.readString();
        location = in.readParcelable(Location.class.getClassLoader());
        description = in.readString();
        address = in.readString();
        review = in.readFloat();
        opening = in.readString();
        closing = in.readString();
        favourite = in.readInt();
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getReview() {
        return review;
    }

    public void setReview(float review) {
        this.review = review;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFavourite() {
        return favourite;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }

    public String getOpening() {
        return opening;
    }

    public void setOpening(String opening) {
        this.opening = opening;
    }

    public String getClosing() {
        return closing;
    }

    public void setClosing(String closing) {
        this.closing = closing;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(type);
        dest.writeParcelable(location, flags);
        //dest.writeString(location.toString());
        dest.writeString(description);
        dest.writeString(address);
        dest.writeFloat(review);
        dest.writeString(opening);
        dest.writeString(closing);
        dest.writeInt(favourite);
    }
}
