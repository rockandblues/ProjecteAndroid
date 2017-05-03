package edu.lasalle.pprog2.practicafinal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.model.Place;
import edu.lasalle.pprog2.practicafinal.repositories.PersonDataBase;
import edu.lasalle.pprog2.practicafinal.utils.JsonSearcher;
import edu.lasalle.pprog2.practicafinal.utils.PageAdapter;

/**
 * Created by miquelabellan on 31/3/17.
 */

public class ResultsActivity extends ParentActivity {

    private ArrayList<Place> searchResults;
    private JsonSearcher jsonSearcher;
    private TabLayout tab;
    private PersonDataBase db;
    private ViewPager viewPager;

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
        try {
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
        }
        if(db.getIdFromUser(MainActivity.emailUser) != -1) {
            //db.addRecentPlace();
        }


//        if(getIntent().getStringExtra("searchType").equals("buscaPerLocalitzacio")) {
//            if(jsonSearcher != null) {
//                searchResults = jsonSearcher.searchByGeolocation();
//            }
//        }



        //Creem l'adaptador del Pager i el relacionem amb el viewPager
        final PageAdapter pageAdapter =
                new PageAdapter(getSupportFragmentManager(), this, searchResults);
        viewPager.setAdapter(pageAdapter);
        tab.setupWithViewPager(viewPager);

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





}
