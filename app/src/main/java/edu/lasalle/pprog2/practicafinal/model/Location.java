package edu.lasalle.pprog2.practicafinal.model;

import java.io.Serializable;

/**
 * Created by joanfito on 18/4/17.
 */

public class Location implements Serializable {

    private double lat;
    private double lng;

    public Location() {
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
