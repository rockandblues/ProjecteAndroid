package edu.lasalle.pprog2.practicafinal.repositories;

import edu.lasalle.pprog2.practicafinal.model.User;

/**
 * Created by MatiasJVH on 04/04/2017.
 */

public interface UserRepository  {

    public User getUserInfo(String username);

    public boolean registerUser(User user);

    public boolean loginCredentials(String username, String password);


}
