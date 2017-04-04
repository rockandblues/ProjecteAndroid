package edu.lasalle.pprog2.practicafinal.repositories;

/**
 * Created by MatiasJVH on 04/04/2017.
 */

public class User {

    private String name;
    private String surname;
    private String email;
    private String password;
    private String description;
    private String gender;
    //Algun atributo para la foto. No se cual es

    public User(String name, String surname, String email, String password, String description, String gender){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.description = description;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
