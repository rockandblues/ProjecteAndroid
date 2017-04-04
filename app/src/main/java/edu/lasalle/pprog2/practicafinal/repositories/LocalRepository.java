package edu.lasalle.pprog2.practicafinal.repositories;

/**
 * Created by MatiasJVH on 04/04/2017.
 */

public class LocalRepository implements  UserRepository{

    private final String name = "DPOO";
    private final String password = "DPOO";

    public LocalRepository(){

    }

    @Override
    public User getUserInfo(String username) {
        return null;
    }

    @Override
    public boolean registerUser(User user) {
        return false;
    }

    @Override
    public boolean loginCredentials(String username, String password) {
        return name.equals(username) && this.password.equals(password);
    }

}
