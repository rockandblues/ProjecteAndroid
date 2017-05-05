package edu.lasalle.pprog2.practicafinal.model;

import java.io.Serializable;

/**
 * Created by miquelabellan on 18/4/17.
 */

public class Place implements Serializable {

    private String name;
    private String type;
    private Location location;
    private String address;
    private float review;

    public Place() {
    }

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

}
