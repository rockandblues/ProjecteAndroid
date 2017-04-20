package edu.lasalle.pprog2.practicafinal.repositories;

import java.util.List;

import edu.lasalle.pprog2.practicafinal.model.Place;
import edu.lasalle.pprog2.practicafinal.model.User;

/**
 * Created by miquelabellan on 18/4/17.
 */

public interface PersonsRepo {

    void addPerson(User p);
    void removePerson(User p);
    void updatePerson(User p);
    boolean existsPerson(String name, String surname);
    boolean existUsername(String email);
    User getUser(String email);
    List<User> getPerson(String name, String surname);
    List<User> getAllPersons();

    void addFavPlace(Place p, String email);
    Place getFavPlace(String name, String email);

    void addRecentPlace(Place p, String email);
    Place getRecentPlace(Place p, String email);

}
