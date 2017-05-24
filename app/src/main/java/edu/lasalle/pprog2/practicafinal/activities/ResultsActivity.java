package edu.lasalle.pprog2.practicafinal.activities;


import android.util.Log;
import android.view.Menu;

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
            volleyRequest.getServerInfo(searchParam,this,PARAM_SEARCH);
        }else if (getIntent().getStringExtra(SEARCH_TYPE).equals(TYPE_GEO)) {
            //buscar por geolocalizacion
            volleyRequest.getServerInfo(searchParam, this, GEO_SEARCH);
        }
        return true;
    }
}
