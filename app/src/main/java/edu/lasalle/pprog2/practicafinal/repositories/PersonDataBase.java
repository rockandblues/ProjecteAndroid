package edu.lasalle.pprog2.practicafinal.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.model.Comment;
import edu.lasalle.pprog2.practicafinal.model.Person;
import edu.lasalle.pprog2.practicafinal.model.Place;
import edu.lasalle.pprog2.practicafinal.utils.DataBaseHelper;


/**
 * Created by Miquel on 18/04/2017.
 */

public class PersonDataBase implements PersonsRepo {


    private static final String TABLE_PERSON = "person";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SURNAME = "surname";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_FEMALE = "female";


    private static final String TABLE_PLACE_PERSON = "place_person";
    private static final String COLUMN_ID_PERSON = "id_person";
    private static final String COLUMN_ID_PLACE = "id_place";
    private static final String COLUMN_IS_FAVOURITE = "isFavourite";
    private static final String COLUMN_COMMENT = "comment";


    private static final String TABLE_PLACE = "place";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_LAT = "lat";
    private static final String COLUMN_LON = "lon";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_OPENNING = "openning";
    private static final String COLUMN_CLOSING = "closing";
    private static final String COLUMN_REVIEW = "review";







    private Context context;

    public PersonDataBase(Context context){
        this.context = context;
    }


    @Override
    public void addPerson(Person p) {
        DataBaseHelper helper = DataBaseHelper.getInstance(context);
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, p.getName());
        values.put(COLUMN_SURNAME, p.getSurname());
        values.put(COLUMN_EMAIL, p.getEmail());
        values.put(COLUMN_PASSWORD, p.getPassword());
        values.put(COLUMN_DESCRIPTION, p.getDescription());
        values.put(COLUMN_FEMALE, p.getFemale());

        helper.getWritableDatabase().insert(TABLE_PERSON, null, values);
    }

    @Override
    public boolean existUser(String email) {
        DataBaseHelper helper = DataBaseHelper.getInstance(context);
        String whereClause = COLUMN_EMAIL + "=?";
        String[] whereArgs = {email};
        SQLiteDatabase db = helper.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_PERSON, whereClause, whereArgs);
        return count > 0;
    }

    @Override
    public void updatePerson(Person p) {

        DataBaseHelper helper = DataBaseHelper.getInstance(context);

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, p.getName());
        values.put(COLUMN_SURNAME, p.getSurname());
        values.put(COLUMN_EMAIL, p.getEmail());
        values.put(COLUMN_PASSWORD, p.getPassword());
        values.put(COLUMN_DESCRIPTION, p.getDescription());
        values.put(COLUMN_FEMALE, p.getFemale());

        String whereClause = COLUMN_EMAIL + "=?";
        String[] whereArgs = {p.getEmail()};

        helper.getWritableDatabase().update(TABLE_PERSON, values, whereClause, whereArgs);

    }

    @Override
    public Person getPerson(String email) {
        Person person = null;

        DataBaseHelper helper = DataBaseHelper.getInstance(context);
        String[] selectColumns = null;

        String whereClause = COLUMN_EMAIL + "=?";
        String[] whereArgs = {email};

        Cursor cursor = helper.getReadableDatabase().query(TABLE_PERSON, selectColumns,
                whereClause, whereArgs, null, null, null);

        if(cursor != null) {
            if(cursor.moveToFirst()) {
                do{
                    String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                    String surname = cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME));
                    String pEmail = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                    String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
                    String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                    int female = cursor.getInt(cursor.getColumnIndex(COLUMN_FEMALE));
                    person = new Person(name, surname, pEmail, password, description, female);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return person;
    }

    private int getPersonId(String email) {
        int id = -1;
        DataBaseHelper helper = DataBaseHelper.getInstance(context);
        String[] selectColumns = null;

        String whereClause = COLUMN_EMAIL + "=?";
        String[] whereArgs = {email};

        Cursor cursor = helper.getReadableDatabase().query(TABLE_PERSON, selectColumns,
                whereClause, whereArgs, null, null, null);

        if(cursor != null) {
            if(cursor.moveToFirst()) {
                do{
                    id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));

                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return id;
    }


    private int getPlaceId(String direction) {
        int id = -1;
        DataBaseHelper helper = DataBaseHelper.getInstance(context);
        String[] selectColumns = null;

        String whereClause = COLUMN_ADDRESS + "=?";
        String[] whereArgs = {direction};

        Cursor cursor = helper.getReadableDatabase().query(TABLE_PLACE, selectColumns,
                whereClause, whereArgs, null, null, null);

        if(cursor != null) {
            if(cursor.moveToFirst()) {
                do{
                    id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));

                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return id;
    }




    @Override
    public void addPlace(Place p, String email, String direction, Comment c) {
        DataBaseHelper helper = DataBaseHelper.getInstance(context);

        ContentValues values = new ContentValues();


        values.put(COLUMN_NAME, p.getName());
        values.put(COLUMN_TYPE, p.getType());
        values.put(COLUMN_LON, p.getLocation().getLng());
        values.put(COLUMN_LAT, p.getLocation().getLat());
        values.put(COLUMN_ADDRESS, p.getAddress());
        values.put(COLUMN_DESCRIPTION, p.getDescription());
        values.put(COLUMN_OPENNING, p.getOpenning());
        values.put(COLUMN_CLOSING, p.getClosing());
        values.put(COLUMN_REVIEW, p.getReview());

        helper.getWritableDatabase().insert(TABLE_PLACE, null, values);
        int idPerson = getPersonId(email);
        int idPlace = getPlaceId(direction);

        values.put(COLUMN_ID_PERSON, idPerson);
        values.put(COLUMN_ID_PLACE, idPlace);
        values.put(COLUMN_IS_FAVOURITE, p.getFavourite());
        values.put(COLUMN_COMMENT, c.getComment());

        String whereClause = COLUMN_ID_PERSON + "=?" + COLUMN_ID_PLACE + "+?";
        String[] whereArgs = {};

            helper.getWritableDatabase().update(TABLE_PLACE_PERSON, values, whereClause, whereArgs);

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

