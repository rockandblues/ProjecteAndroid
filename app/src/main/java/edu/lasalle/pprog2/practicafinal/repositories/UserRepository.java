package edu.lasalle.pprog2.practicafinal.repositories;

/**
 * Created by MatiasJVH on 04/04/2017.
 */

public interface UserRepository  {

    public User getUserInfo(String username);

    public boolean registerUser(User user);

    public boolean loginCredentials(String username, String password);


}
