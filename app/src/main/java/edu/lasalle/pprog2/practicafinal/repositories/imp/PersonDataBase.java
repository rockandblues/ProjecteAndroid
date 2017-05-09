package edu.lasalle.pprog2.practicafinal.repositories.imp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.model.Person;
import edu.lasalle.pprog2.practicafinal.model.Place;
import edu.lasalle.pprog2.practicafinal.repositories.PersonsRepo;
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


    private static final String TABLE_PLACE = "fav_place";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_LAT = "lat";
    private static final String COLUMN_LON = "lon";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_OPENNING = "openning";
    private static final String COLUMN_CLOSING = "closing";
    private static final String COLUMN_REVIEW = "review";


    private static final String TABLE_RECENT_SEARCH = "recent_search";
    private static final String COLUMN_BUSQUEDA = "busqueda";
    private static final String COLUMN_ID_PERSON = "id_person";






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








    @Override
    public void addPlace(Place p, String email) {
        DataBaseHelper helper = DataBaseHelper.getInstance(context);

        ContentValues values = new ContentValues();


        values.put(COLUMN_NAME, p.getName());
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_LON, 0);
        values.put(COLUMN_LAT, 0);
        values.put(COLUMN_ADDRESS, p.getAddress());
        values.put(COLUMN_TYPE, p.getType());
        values.put(COLUMN_DESCRIPTION, p.getDescription());
        values.put(COLUMN_OPENNING, p.getOpenning());
        values.put(COLUMN_CLOSING, p.getClosing());
        values.put(COLUMN_REVIEW, p.getReview());

        helper.getWritableDatabase().insert(TABLE_PLACE, null, values);

    }


    @Override
    public void updatePlace(Place p, String email) {
        DataBaseHelper helper = DataBaseHelper.getInstance(context);

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, p.getName());
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_LON, 0);
        values.put(COLUMN_LAT, 0);
        values.put(COLUMN_ADDRESS, p.getAddress());
        values.put(COLUMN_TYPE, p.getType());
        values.put(COLUMN_DESCRIPTION, p.getDescription());
        values.put(COLUMN_OPENNING, p.getOpenning());
        values.put(COLUMN_CLOSING, p.getClosing());
        values.put(COLUMN_REVIEW, p.getReview());

        String whereClause = COLUMN_EMAIL + "=?" + COLUMN_ADDRESS + "=?";
        String[] whereArgs = {email, p.getAddress()};

        helper.getWritableDatabase().update(TABLE_PLACE, values, whereClause, whereArgs);
    }



    @Override
    public ArrayList<Place> getAllFavPlaces(String email) {
        ArrayList<Place> places = new ArrayList<>();
        DataBaseHelper helper = DataBaseHelper.getInstance(context);
        String[] selectColumns = null;

        String whereClause = COLUMN_EMAIL + "=?";
        String[] whereArgs = {email};

        Cursor cursor = helper.getReadableDatabase().query(TABLE_PLACE, selectColumns,
                whereClause, whereArgs, null, null, null);

        if(cursor != null) {
            if(cursor.moveToFirst()) {
                do{
                    String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                    String type = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE));
                    float lon = cursor.getFloat(cursor.getColumnIndex(COLUMN_LON));
                    float lat = cursor.getFloat(cursor.getColumnIndex(COLUMN_LAT));
                    String address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS));
                    String descritpion = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                    String openning = cursor.getString(cursor.getColumnIndex(COLUMN_OPENNING));
                    String closing = cursor.getString(cursor.getColumnIndex(COLUMN_CLOSING));
                    float review = cursor.getFloat(cursor.getColumnIndex(COLUMN_REVIEW));

                    Place place = new Place(name, type, lon, lat, address, descritpion, review,
                            openning,closing);
                    places.add(place);

                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return places;
    }

    @Override
    public Place getPlaceInfo(String email, Place p) {
        Place place = null;
        DataBaseHelper helper = DataBaseHelper.getInstance(context);
        String[] selectColumns = null;

        String whereClause = COLUMN_EMAIL + "=?" + COLUMN_ADDRESS + "=?";
        String[] whereArgs = {email, p.getAddress()};

        Cursor cursor = helper.getReadableDatabase().query(TABLE_PLACE, selectColumns,
                whereClause, whereArgs, null, null, null);

        if(cursor != null) {
            if(cursor.moveToFirst()) {
                do{
                    String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                    String type = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE));
                    float lon = cursor.getFloat(cursor.getColumnIndex(COLUMN_LON));
                    float lat = cursor.getFloat(cursor.getColumnIndex(COLUMN_LAT));
                    String address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS));
                    String descritpion = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                    String openning = cursor.getString(cursor.getColumnIndex(COLUMN_OPENNING));
                    String closing = cursor.getString(cursor.getColumnIndex(COLUMN_CLOSING));
                    float review = cursor.getFloat(cursor.getColumnIndex(COLUMN_REVIEW));

                    place = new Place(name, type, lon, lat, address, descritpion, review,
                            openning,closing);

                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return place;

    }

    @Override
    public void addRecentSearch(String email, String search) {
        DataBaseHelper helper = DataBaseHelper.getInstance(context);

        ContentValues values = new ContentValues();

        int idPerson = getIdFromEmail(email);
        values.put(COLUMN_BUSQUEDA, search);
        values.put(COLUMN_ID_PERSON, idPerson);

        helper.getWritableDatabase().insert(TABLE_RECENT_SEARCH, null, values);
    }

    private int getIdFromEmail(String email) {
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

    @Override
    public ArrayList<String> getAllRecentSearches(String email) {
        ArrayList<String> aux = new ArrayList<>();

        DataBaseHelper helper = DataBaseHelper.getInstance(context);
        String[] selectColumns = null;

        String idPerson = String.valueOf(getIdFromEmail(email));
        String whereClause = COLUMN_ID_PERSON + "=?";
        String[] whereArgs = {idPerson};

        Cursor cursor = helper.getReadableDatabase().query(TABLE_RECENT_SEARCH, selectColumns,
                whereClause, whereArgs, null, null, null);

        if(cursor != null) {
            if(cursor.moveToFirst()) {
                do{
                    String busqueda = cursor.getString(cursor.getColumnIndex(COLUMN_BUSQUEDA));
                    aux.add(busqueda);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return aux;
    }

    @Override
    public void deletePlace(String email, String direction) {
        DataBaseHelper helper = DataBaseHelper.getInstance(context);
        String whereClause = COLUMN_EMAIL + "=?" + COLUMN_ADDRESS + "=?";
        String[] whereArgs = {email, direction};

        helper.getWritableDatabase().delete(TABLE_PLACE,whereClause, whereArgs );
    }
}

