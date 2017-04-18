package edu.lasalle.pprog2.practicafinal.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.lasalle.pprog2.practicafinal.model.User;
import edu.lasalle.pprog2.practicafinal.utils.DataBaseHelper;


/**
 * Created by Miquel on 18/04/2017.
 */

public class DataBase implements PersonsRepo {

    // Contantes con los nombres de la tabla y de las columnas de la tabla.
    private static final String TABLE_NAME = "person";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SURNAME = "surname";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_GENDER = "gender";





    private Context context;

    public DataBase(Context context) {
        this.context = context;
    }

    @Override
    public void addPerson(User p) {
        DataBaseHelper helper = DataBaseHelper.getInstance(context);
        Log.d("DataBase", p.getName()  +" " + p.getEmail() + " " + p.getSurname() );
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
        values.put(COLUMN_PASSWORD, p.getEmail());
        values.put(COLUMN_DESCRIPTION, p.getDescription());
        values.put(COLUMN_GENDER, p.getGender());

        String whereClause = COLUMN_NAME + "=? and " + COLUMN_SURNAME + "=?";
        String[] whereArgs = {p.getName(), p.getSurname()};

        helper.getWritableDatabase().update(TABLE_NAME, values, whereClause, whereArgs);

    }

    @Override
    public boolean existsPerson(String name, String surname) {
        DataBaseHelper helper = DataBaseHelper.getInstance(context);


        String whereClause = COLUMN_NAME + "=? and " + COLUMN_SURNAME + "=?";
        String[] whereArgs = {name, surname};

        SQLiteDatabase db = helper.getReadableDatabase();

        long count = DatabaseUtils.queryNumEntries(db, TABLE_NAME, whereClause, whereArgs);

        return count > 0;
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

                    User p =new User(personName, personSurname, personEmail, personPassword,
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


                    User p =new User(personName, personSurname, personEmail, personPassword,
                            personDescription, personGender);
                    list.add(p);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return list;
    }
}

