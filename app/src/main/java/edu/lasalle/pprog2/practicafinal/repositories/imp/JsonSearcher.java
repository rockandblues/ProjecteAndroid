package edu.lasalle.pprog2.practicafinal.repositories.imp;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.model.Location;
import edu.lasalle.pprog2.practicafinal.model.Place;
import edu.lasalle.pprog2.practicafinal.repositories.Searcher;
import edu.lasalle.pprog2.practicafinal.utils.JsonParser;

/**
 * Created by MatiasJVH on 18/04/2017.
 */

public class JsonSearcher implements Searcher {


    private ArrayList<Place> placesList;

    public JsonSearcher(Context context) throws IOException {
        placesList = JsonParser.parseFile(context);
    }

    @Override
    public ArrayList<Place> searchByKeyWords(String keywords) {
        ArrayList<Place> results = new ArrayList<Place>();

        for (int i = 0; i<placesList.size(); i++){

            if (placesList.get(i).getName().toLowerCase().contains(keywords.toLowerCase())){
                results.add(placesList.get(i));
            }
            if (placesList.get(i).getType().toLowerCase().contains(keywords.toLowerCase())){
                results.add(placesList.get(i));
            }
        }
        return results;
    }

    @Override
    public ArrayList<Place> searchByGeolocation(Location location) {
        return null;
    }

    @Override
    public ArrayList<Place> searchByRestaurantType(String type) {

        ArrayList<Place> results = new ArrayList<Place>();

        for (int i = 0; i<placesList.size(); i++){
            if (placesList.get(i).getType().toLowerCase().contains(type.toLowerCase())){
                results.add(placesList.get(i));
            }
        }
        return results;
    }
}
