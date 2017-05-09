package edu.lasalle.pprog2.practicafinal.repositories;

import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.model.Person;
import edu.lasalle.pprog2.practicafinal.model.Place;

/**
 * Created by miquelabellan on 18/4/17.
 */

public interface PersonsRepo {

    void addPerson(Person u);
    boolean existUser(String email);
    void updatePerson(Person p);
    Person getPerson(String email);

    void addPlace(Place p, String email, String c, int isFav);
    ArrayList<Place> getAllFavPlaces(String email);
    Place getPlaceInfo(String email, Place p);
    ArrayList<String> getCommentsFromPlace(Place p);
    void updatePlace(Place p, String email, String comment, int isFav);
    void addRecentSearch(String email, String search);
    ArrayList<String>  getAllRecentSearches(String email);

}
