package edu.lasalle.pprog2.practicafinal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.model.Place;

import edu.lasalle.pprog2.practicafinal.adapters.PageAdapter;
import edu.lasalle.pprog2.practicafinal.repositories.PersonDataBase;
import edu.lasalle.pprog2.practicafinal.repositories.imp.JsonSearcher;
import edu.lasalle.pprog2.practicafinal.utils.VolleyRequest;

import static edu.lasalle.pprog2.practicafinal.utils.VolleyRequest.GEO_SEARCH;
import static edu.lasalle.pprog2.practicafinal.utils.VolleyRequest.PARAM_SEARCH;

/**
 * Created by miquelabellan on 31/3/17.
 */

public class ResultsActivity extends ParentActivity {


    private VolleyRequest volleyRequest;

    private JsonSearcher jsonSearcher;
    private ArrayList<Place> searchResults;
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
        searchResults = new ArrayList<>();



        //TODO cambiar por volley
        /*try {
            jsonSearcher = new JsonSearcher(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Separamos los tipos de busqueda
        if(getIntent().getStringExtra("searchType").equals("buscarPerNom")) {
            if (jsonSearcher != null){
                searchResults = jsonSearcher.searchByKeyWords
                        (getIntent().getStringExtra("searchText"));
            }
        }else {
            //TODO buscar por geolocalizacion
        }*/

        /*pageAdapter = new PageAdapter(getSupportFragmentManager(), this, searchResults);
        viewPager.setAdapter(pageAdapter);
        tab.setupWithViewPager(viewPager);*/

        //VOLLEY
        volleyRequest = VolleyRequest.getInstance(this);
        String searchParam = getIntent().getStringExtra("searchText");
        //Separamos los tipos de busqueda
            //buscar por nombre
        if(getIntent().getStringExtra("searchType").equals("buscarPerNom")) {
            volleyRequest.getServerInfo(searchParam,this,PARAM_SEARCH);
        }else {
            //buscar por geolocalizacion
            volleyRequest.getServerInfo(searchParam,this,GEO_SEARCH);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Este metodo se llamara una vez durante la creacion de la Activity
        getMenuInflater().inflate(R.menu.action_bar3, menu);

        MenuItem item = menu.findItem(R.id.spinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_values, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return true;
    }

    public void profileClick(MenuItem menuItem) {
        Intent intent = new Intent(this, PerfilActivity.class);
        startActivityForResult(intent, 2);

    }
    public void favClick(MenuItem menuItem) {
        Intent intent = new Intent(this, FavActivity.class);
        startActivityForResult(intent, 2);
    }

    public void showListView(ArrayList<Place> places){
        //Notify data changed en el fragment!
        Log.d("RESULTS", places.toString());
        //pageAdapter.notifyDataSetChanged(places);
        searchResults.clear();
        searchResults.addAll(places);
        pageAdapter = new PageAdapter(getSupportFragmentManager(), this, searchResults);
        viewPager.setAdapter(pageAdapter);
        tab.setupWithViewPager(viewPager);
    }


}
