package edu.lasalle.pprog2.practicafinal.model;

import java.io.Serializable;

/**
 * Created by miquelabellan on 18/4/17.
 */

public class Place implements Serializable {

    private String name;
    private String type;
    private Location location;
    private String description;
    private String address;
    private float review;
    private String openning;
    private String closing;
    private int favourite; /* 1 para que sea favorito, -1 para que no lo sea*/

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

    public String getOpenning() {
        return openning;
    }

    public void setOpenning(String openning) {
        this.openning = openning;
    }

    public String getClosing() {
        return closing;
    }

    public void setClosing(String closing) {
        this.closing = closing;
    }
}
