package edu.lasalle.pprog2.practicafinal.repositories;

import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.model.Location;
import edu.lasalle.pprog2.practicafinal.model.Place;

/**
 * Created by MatiasJVH on 18/04/2017.
 */

public interface Searcher {


    public ArrayList<Place> searchByKeyWords(String keywords);

    public ArrayList<Place> searchByGeolocation(Location location);

    public ArrayList<Place> searchByRestaurantType(String type);

}
