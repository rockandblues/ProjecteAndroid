package edu.lasalle.pprog2.practicafinal.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.adapters.PageAdapter;
import edu.lasalle.pprog2.practicafinal.model.Place;
import edu.lasalle.pprog2.practicafinal.repositories.imp.PersonDataBase;
import edu.lasalle.pprog2.practicafinal.utils.VolleyRequest;

import static edu.lasalle.pprog2.practicafinal.activities.SearchActivity.SEARCH_TYPE;
import static edu.lasalle.pprog2.practicafinal.activities.SearchActivity.TEXT;
import static edu.lasalle.pprog2.practicafinal.activities.SearchActivity.TYPE_FAV;
import static edu.lasalle.pprog2.practicafinal.activities.SearchActivity.TYPE_GEO;
import static edu.lasalle.pprog2.practicafinal.activities.SearchActivity.TYPE_NAME;
import static edu.lasalle.pprog2.practicafinal.utils.VolleyRequest.GEO_SEARCH;
import static edu.lasalle.pprog2.practicafinal.utils.VolleyRequest.PARAM_SEARCH;

/**
 * Created by miquelabellan on 31/3/17.
 */

public class ResultsActivity extends AppCompatActivity {


    private VolleyRequest volleyRequest;

    //private JsonSearcher jsonSearcher;
    private TabLayout tab;
    private PersonDataBase db;
    private ViewPager viewPager;
    private PageAdapter pageAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_layout);
        setTitle("");

        db = new PersonDataBase(this);
        //Obtenemos los elementos que necesitamos del layout
        tab = (TabLayout)findViewById(R.id.tabs);
        viewPager = (ViewPager)findViewById(R.id.webPager);

        //Variables para guardar los datos buscados
        pageAdapter = new PageAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(pageAdapter);
        tab.setupWithViewPager(viewPager);


        //VER PARAMETRO BUSQUEDA
        //volley
        volleyRequest = VolleyRequest.getInstance(this);
        String searchParam = getIntent().getStringExtra(TEXT);
        //Separamos los tipos de busqueda
        //buscar por nombre
        String s = getIntent().getStringExtra(SEARCH_TYPE);
        if(getIntent().getStringExtra(SEARCH_TYPE).equals(TYPE_NAME)) {
            Log.d("RESULTS", "volley");
            volleyRequest.getServerInfo(searchParam,this,PARAM_SEARCH);
        }else if (getIntent().getStringExtra(SEARCH_TYPE).equals(TYPE_GEO)){
            //buscar por geolocalizacion
            volleyRequest.getServerInfo(searchParam,this,GEO_SEARCH);

            //AsynTask
            //Buscar favourite place
            //TODO ASYNC
        }else if (getIntent().getStringExtra(SEARCH_TYPE).equals(TYPE_FAV)){
            Log.d("RESULTS", "fav");
            //ArrayList<Place> test = db.getAllFavPlaces(MainActivity.emailUser);
            new AsyncDBRequest(this).execute(MainActivity.emailUser);
        }
        Log.d("RESULTS", "no entra a ninguna");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Este metodo se llamara una vez durante la creacion de la Activity
        getMenuInflater().inflate(R.menu.action_bar3, menu);

        String[] aux = {"Hola", "hola", "hola2"};
        MenuItem item = menu.findItem(R.id.spinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_values, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        MenuItem heart = (MenuItem) menu.findItem(R.id.favorite_action_bar);
        if (getIntent().getStringExtra(SEARCH_TYPE).equals(TYPE_FAV)) {
            heart.setVisible(false);
        }else{
            heart.setVisible(true);
        }
        return true;
    }

    public void profileClick(MenuItem menuItem) {
        Intent intent = new Intent(this, PerfilActivity.class);
        startActivityForResult(intent, 2);

    }
    public void favClick(MenuItem menuItem) {
        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra(SEARCH_TYPE, TYPE_FAV);
        startActivityForResult(intent, 2);
    }

    public void showListView(ArrayList<Place> places){
        //Notify data changed en el fragment!
        pageAdapter.notifyDataSetChanged(places);
    }

    //buscar los datos en la bbdd
    private class AsyncDBRequest extends AsyncTask<String, Void, ArrayList<Place>>{

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
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return db.getAllFavPlaces(params[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Place> places) {
            super.onPostExecute(places);

            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            showListView(places);
        }
    }
}
