package edu.lasalle.pprog2.practicafinal.repositories;

import java.util.List;

/**
 * Created by miquelabellan on 18/4/17.
 */

public interface PersonsRepo {

    void addPerson(User p);
    void removePerson(User p);
    void updatePerson(User p);
    boolean existsPerson(String name, String surname);
    List<User> getPerson(String name, String surname);
    List<User> getAllPersons();

}
