package edu.lasalle.pprog2.practicafinal.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.model.Place;
import edu.lasalle.pprog2.practicafinal.repositories.imp.PersonDataBase;

/**
 * Created by MatiasVillarroel on 12/05/17.
 */

public class FavouritePlacesActivity extends ActionBar3Activity {

    protected PersonDataBase db;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        db = new PersonDataBase(this);

        MenuItem heart = (MenuItem) menu.findItem(R.id.favorite_action_bar);
        heart.setVisible(false);

        //Peticion a la bbdd
        new AsyncDBRequest(this).execute(MainActivity.emailUser);

        return true;
    }


    //buscar los datos en la bbdd
    private class AsyncDBRequest extends AsyncTask<String, Void, ArrayList<Place>> {

        private ProgressDialog progressDialog;

        protected AsyncDBRequest(Context context) {
            progressDialog = new ProgressDialog(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage(getString(R.string.searching_message));
            progressDialog.show();
        }

        @Override
        protected ArrayList<Place> doInBackground(String... params) {

            return db.getAllFavPlaces(params[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Place> places) {
            super.onPostExecute(places);

            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            showResults(places);
        }
    }



}
