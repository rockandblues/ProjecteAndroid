package edu.lasalle.pprog2.practicafinal.activities;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.adapters.PageAdapter;
import edu.lasalle.pprog2.practicafinal.model.Place;
import edu.lasalle.pprog2.practicafinal.utils.JsonParser;
import edu.lasalle.pprog2.practicafinal.utils.VolleyRequest;

import static edu.lasalle.pprog2.practicafinal.activities.SearchActivity.SEARCH_TYPE;
import static edu.lasalle.pprog2.practicafinal.activities.SearchActivity.TEXT;
import static edu.lasalle.pprog2.practicafinal.activities.SearchActivity.TYPE_GEO;
import static edu.lasalle.pprog2.practicafinal.activities.SearchActivity.TYPE_NAME;
import static edu.lasalle.pprog2.practicafinal.utils.VolleyRequest.GEO_SEARCH;
import static edu.lasalle.pprog2.practicafinal.utils.VolleyRequest.PARAM_SEARCH;

/**
 * Created by miquelabellan on 31/3/17.
 */

public class ResultsActivity extends ActionBar3Activity {


    private VolleyRequest volleyRequest;
    //private JsonSearcher jsonSearcher;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_layout);

        searchResults = new ArrayList<>();

        //obetenemos el actionBar
        final ActionBar actionBar = getActionBar();

        //Obtenemos los elementos que necesitamos del layout
        tab = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.webPager);

        //Variables para guardar los datos buscados
        pageAdapter = new PageAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(pageAdapter);
        tab.setupWithViewPager(viewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        //volley
        volleyRequest = VolleyRequest.getInstance(this);
        String searchParam = getIntent().getStringExtra(TEXT);
        //Separamos los tipos de busqueda
        //buscar por nombre
        String s = getIntent().getStringExtra(SEARCH_TYPE);
        if(getIntent().getStringExtra(SEARCH_TYPE).equals(TYPE_NAME)) {
            Log.d("RESULTS", "volley");
            volleyRequest.getServerInfo(searchParam,this,PARAM_SEARCH);
        }else if (getIntent().getStringExtra(SEARCH_TYPE).equals(TYPE_GEO)) {
            //buscar por geolocalizacion
            volleyRequest.getServerInfo(searchParam, this, GEO_SEARCH);
        }
        return true;
    }
}
