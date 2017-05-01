package edu.lasalle.pprog2.practicafinal.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.lasalle.pprog2.practicafinal.model.Place;
import edu.lasalle.pprog2.practicafinal.model.User;
import edu.lasalle.pprog2.practicafinal.utils.DataBaseHelper;


/**
 * Created by Miquel on 18/04/2017.
 */

public class PersonDataBase implements PersonsRepo {

    // Contantes con los nombres de la tabla y de las columnas de la tabla.
    private static final String TABLE_NAME = "person";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SURNAME = "surname";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_GENDER = "gender";

    private static final String TABLE_FAV_PLACES = "place_fav";
    private static final String COLUMN_FAV_PLACES_ID_REC = "id_rec";
    private static final String COLUMN_FAV_PLACES_ID_PERS = "id_pers";


    private static final String TABLE_RECENT_PLACES = "place_recent";
    private static final String COLUMN_RECENT_NAME = "name";
    private static final String COLUMN_RECENT_TYPE = "type";
    private static final String COLUMN_RECENT_LAT = "lat";
    private static final String COLUMN_RECENT_LONG = "long";
    private static final String COLUMN_RECENT_ADDRS = "address";

    private static final String COLUMN_RECENT_REVIEW = "review";




    private Context context;

    public PersonDataBase(Context context) {
        this.context = context;
    }

    @Override
    public void addPerson(User p) {
        DataBaseHelper helper = DataBaseHelper.getInstance(context);
        Log.d("PersonDataBase", p.getName() + " " + p.getEmail() + " " + p.getSurname());
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, p.getName());
        values.put(COLUMN_SURNAME, p.getSurname());
        values.put(COLUMN_EMAIL, p.getEmail());
        values.put(COLUMN_PASSWORD, p.getPassword());
        values.put(COLUMN_DESCRIPTION, p.getDescription());
        values.put(COLUMN_GENDER, p.getGender());

        helper.getWritableDatabase().insert(TABLE_NAME, null, values);

    }

    @Override
    public void removePerson(User p) {
        DataBaseHelper helper = DataBaseHelper.getInstance(context);


        String whereClause = COLUMN_NAME + "=? and " + COLUMN_SURNAME + "=?";
        String[] whereArgs = {p.getName(), p.getSurname()};

        helper.getWritableDatabase().delete(TABLE_NAME, whereClause, whereArgs);

    }

    @Override
    public void updatePerson(User p) {
        DataBaseHelper helper = DataBaseHelper.getInstance(context);

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, p.getName());
        values.put(COLUMN_SURNAME, p.getSurname());
        values.put(COLUMN_EMAIL, p.getEmail());
        values.put(COLUMN_PASSWORD, p.getPassword());
        values.put(COLUMN_DESCRIPTION, p.getDescription());
        values.put(COLUMN_GENDER, p.getGender());

        String whereClause = COLUMN_EMAIL+ "=?";
        String[] whereArgs = {p.getEmail()};

        helper.getWritableDatabase().update(TABLE_NAME, values, whereClause, whereArgs);

    }

    @Override
    public boolean existsPerson(String email) {
        DataBaseHelper helper = DataBaseHelper.getInstance(context);

        String whereClause = COLUMN_EMAIL + "=?";
        String[] whereArgs = {email};

        SQLiteDatabase db = helper.getReadableDatabase();

        long count = DatabaseUtils.queryNumEntries(db, TABLE_NAME, whereClause, whereArgs);

        return count > 0;
    }

    @Override
    public boolean existUsername(String email) {
        DataBaseHelper helper = DataBaseHelper.getInstance(context);

        String whereClause = COLUMN_EMAIL + "=?";
        String[] whereArgs = {email};

        SQLiteDatabase db = helper.getReadableDatabase();

        long count = DatabaseUtils.queryNumEntries(db, TABLE_NAME, whereClause, whereArgs);

        return count > 0;
    }

    @Override
    public User getUser(String email) {
        User user = new User();
        DataBaseHelper helper = DataBaseHelper.getInstance(context);
        String[] selectColumns = null;
        String whereClause = COLUMN_EMAIL + "=?";
        String[] whereArgs = {email};

        Cursor cursor = helper.getReadableDatabase().
                query(TABLE_NAME, selectColumns, whereClause, whereArgs, null, null, null);


        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String personName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                user.setName(personName);
                String personSurname = cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME));
                user.setSurname(personSurname);
                String personDescription = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                user.setDescription(personDescription);
                String personPassword = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
                user.setPassword(personPassword);
                String personEmail = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                user.setEmail(personEmail);
                String personGender = cursor.getString(cursor.getColumnIndex(COLUMN_GENDER));
                user.setGender(personGender);

            }
            //Cerramos el cursor al terminar.
            cursor.close();
        }

        return user;
    }

    @Override
    public List<User> getPerson(String name, String surname) {
        List<User> list = new ArrayList<>();
        DataBaseHelper helper = DataBaseHelper.getInstance(context);
        String[] selectColumns = null;
        String whereClause = COLUMN_NAME + "=? and " + COLUMN_SURNAME + "=?";
        String[] whereArgs = {name, surname};

        Cursor cursor = helper.getReadableDatabase().
                query(TABLE_NAME, selectColumns, whereClause, whereArgs, null, null, null);


        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String personName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                    String personSurname = cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME));
                    String personDescription = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                    String personPassword = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
                    String personEmail = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                    String personGender = cursor.getString(cursor.getColumnIndex(COLUMN_GENDER));

                    User p = new User(personName, personSurname, personEmail, personPassword,
                            personDescription, personGender);
                    list.add(p);
                } while (cursor.moveToNext());
            }
            //Cerramos el cursor al terminar.
            cursor.close();
        }

        return list;
    }

    @Override
    public List<User> getAllPersons() {
        List<User> list = new ArrayList<>();

        DataBaseHelper helper = DataBaseHelper.getInstance(context);

        String[] selectColumns = null;

        Cursor cursor = helper.getReadableDatabase().
                query(TABLE_NAME, selectColumns, null, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {

                    String personName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                    String personSurname = cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME));
                    String personDescription = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                    String personPassword = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
                    String personEmail = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                    String personGender = cursor.getString(cursor.getColumnIndex(COLUMN_GENDER));


                    User p = new User(personName, personSurname, personEmail, personPassword,
                            personDescription, personGender);
                    list.add(p);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return list;
    }

    @Override
    public void addFavPlace(Place p, String email) {
        //TODO
    }

    @Override
    public ArrayList<Place> getFavPlace(String name, String email) {
        //TODO


        return null;
    }

    @Override
    public void addRecentPlace(Place p, String email) {
        DataBaseHelper helper = DataBaseHelper.getInstance(context);

        ContentValues values = new ContentValues();
        values.put(COLUMN_RECENT_NAME, p.getName());
        values.put(COLUMN_RECENT_TYPE, p.getType());
        values.put(COLUMN_RECENT_LAT, p.getLocation().getLat());
        values.put(COLUMN_RECENT_LONG, p.getLocation().getLng());
        values.put(COLUMN_RECENT_ADDRS, p.getAddress());
        values.put(COLUMN_RECENT_REVIEW, p.getReview());

        //TODO JOAN
        //String whereClause = COLUMN_RECENT_NAME + "=? and " +  + "=?";
        //String[] whereArgs = {p.getName(), p.getSurname()};

        //helper.getWritableDatabase().update(TABLE_NAME, values, whereClause, whereArgs);

    }

    @Override
    public ArrayList<Place> getRecentPlace(Place p, String email) {
        //TODO JOAN



        return null;
    }
}

