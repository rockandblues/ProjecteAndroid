package edu.lasalle.pprog2.practicafinal.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.model.Place;

/**
 * Created by MatiasVillarroel on 12/05/17.
 */

public class FavouritePlacesActivity extends ActionBar3Activity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //crear atributos e inicializar atributos


        //Peticion a la pag web

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuItem heart = (MenuItem) menu.findItem(R.id.favorite_action_bar);
        heart.setVisible(false);
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
            //TODO arreglar esto
            //se usa para darle tiempo a los fragments de crearse

            //return db.getAllFavPlaces(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Place> places) {
            super.onPostExecute(places);

            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            //showListView(places);
        }
    }
}
