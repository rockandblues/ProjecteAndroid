package edu.lasalle.pprog2.practicafinal.model;

import android.graphics.Bitmap;

/**
 * Created by MatiasJVH on 04/04/2017.
 */

public class Person {

    private Bitmap photo;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String description;
    private int female; /* > 0 es mujer*/
    //Algun atributo para la foto. No se cual es (ES BLOB :))

    public Person(){

    }


    public Person(Bitmap photo, String name, String surname, String email,
                  String password, String description, int female){
        this.photo = photo;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.description = description;
        this.female = female;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
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



    public int getFemale() {
        return female;
    }

    public void setFemale(int female) {
        this.female = female;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
