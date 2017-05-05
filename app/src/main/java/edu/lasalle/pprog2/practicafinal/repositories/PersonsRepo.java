package edu.lasalle.pprog2.practicafinal.repositories;

import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.model.Comment;
import edu.lasalle.pprog2.practicafinal.model.Place;
import edu.lasalle.pprog2.practicafinal.model.User;

/**
 * Created by miquelabellan on 18/4/17.
 */

public interface PersonsRepo {

    void addPerson(User u);
    boolean existUser(String email);
    void updatePerson(User u);
    User getPerson(String email);

    void addPlace(Place p, String email);
    void updatePlaceComment(Place p, String comment, String email);
    void updateFavPlace(Place p, String email);
    ArrayList<Comment> getCommentsFromPlace(Place p);
    ArrayList<Place> getAllFavPlaces(String email);

    void addRecentSearch(String email, String search);
    ArrayList<String>  getAllRecentSearches(String email);

}
