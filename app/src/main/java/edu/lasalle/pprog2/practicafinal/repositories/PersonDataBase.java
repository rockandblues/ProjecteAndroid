package edu.lasalle.pprog2.practicafinal.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.model.Comment;
import edu.lasalle.pprog2.practicafinal.model.Place;
import edu.lasalle.pprog2.practicafinal.model.User;
import edu.lasalle.pprog2.practicafinal.utils.DataBaseHelper;


/**
 * Created by Miquel on 18/04/2017.
 */

public class PersonDataBase implements PersonsRepo {


    private static final String TABLE_NAME = "person";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SURNAME = "surname";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_FEMALE = "female";
    private static final String COLUMN_MALE = "male";


    private Context context;

      public PersonDataBase(Context context) { 
        this.context = context; 
    }

    @Override
    public void addPerson(User u) {
        DataBaseHelper helper = DataBaseHelper.getInstance(context); 
        ContentValues values = new ContentValues(); 
        values.put(COLUMN_NAME, u.getName()); 
        values.put(COLUMN_SURNAME, u.getSurname()); 
        values.put(COLUMN_EMAIL, u.getEmail()); 
        values.put(COLUMN_PASSWORD, u.getPassword()); 
        values.put(COLUMN_DESCRIPTION, u.getDescription()); 
        values.put(COLUMN_FEMALE, u.isFemale());  
        values.put(COLUMN_MALE, u.isMale());  

        helper.getWritableDatabase().insert(TABLE_NAME, null, values);
    }

    @Override
    public boolean existUser(String email) {
        DataBaseHelper helper = DataBaseHelper.getInstance(context);  
        String whereClause = COLUMN_EMAIL + "=?"; 
        String[] whereArgs = {email};  
        SQLiteDatabase db = helper.getReadableDatabase();  
        long count = DatabaseUtils.queryNumEntries(db, TABLE_NAME, whereClause, whereArgs);  
        return count > 0; 
    }

    @Override
    public void updatePerson(User u) {

    }

    @Override
    public User getPerson(String email) {
        return null;
    }

    @Override
    public void addPlace(Place p, String email) {

    }

    @Override
    public void updatePlaceComment(Place p, String comment, String email) {

    }

    @Override
    public void updateFavPlace(Place p, String email) {

    }

    @Override
    public ArrayList<Comment> getCommentsFromPlace(Place p) {
        return null;
    }

    @Override
    public ArrayList<Place> getAllFavPlaces(String email) {
        return null;
    }

    @Override
    public void addRecentSearch(String email, String search) {

    }

    @Override
    public ArrayList<String> getAllRecentSearches(String email) {
        return null;
    }
}

