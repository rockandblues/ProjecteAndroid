package edu.lasalle.pprog2.practicafinal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.adapters.PageAdapter;
import edu.lasalle.pprog2.practicafinal.fragments.ParentFragment;
import edu.lasalle.pprog2.practicafinal.listener.SpinnerItemSelectedListener;
import edu.lasalle.pprog2.practicafinal.model.Place;

import static edu.lasalle.pprog2.practicafinal.activities.SearchActivity.SEARCH_TYPE;
import static edu.lasalle.pprog2.practicafinal.activities.SearchActivity.TYPE_FAV;

/**
 * Created by MatiasVillarroel on 12/05/17.
 */

public class ActionBar3Activity extends AppCompatActivity {

    protected ParentFragment pf;
    protected TabLayout tab;
    protected ViewPager viewPager;
    protected PageAdapter pageAdapter;
    protected TabLayout.OnTabSelectedListener tabSelectedListener;

    //Spinner, adapter y lista del spinner
    private Spinner spinner;
    private ArrayAdapter arrayAdapter;
    protected ArrayList<String> types;
    private SpinnerItemSelectedListener spinnerListener;

    protected ArrayList<Place> searchResults;
    protected ArrayList<Place> filteredResults;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_layout);

        searchResults = new ArrayList<>();
        filteredResults = new ArrayList<>();

        //Obtenemos los elementos que necesitamos del layout
        tab = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.webPager);

        //Variables para guardar los datos buscados
        pageAdapter = new PageAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(pageAdapter);
        tab.setupWithViewPager(viewPager);

        tabSelectedListener = createTabListener();
        tab.addOnTabSelectedListener(tabSelectedListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // Este metodo se llamara una vez durante la creacion de la Activity
        getMenuInflater().inflate(R.menu.action_bar3, menu);
        setTitle("");

        MenuItem item = menu.findItem(R.id.types_spinner);
        spinner = (Spinner) item.getActionView();

        //Lista del menu
        types = new ArrayList<>();
        types.add(getString(R.string.filter_all));

        //crear el adapter para mostrar los tipos en el spinner
        arrayAdapter = new ArrayAdapter( this ,android.R.layout.simple_spinner_item, types);

        //Añadimos el layout para el menú y se lo damos al spinner
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Adaptador para filtrar por el campo
        spinnerListener = new SpinnerItemSelectedListener(searchResults,types,this);

        //agregar el adapter y el listener
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(spinnerListener);

        return true;
    }

    public void profileClick(MenuItem menuItem) {
        Intent intent = new Intent(this, PerfilActivity.class);
        startActivityForResult(intent, 2);

    }
    public void favClick(MenuItem menuItem) {
        Intent intent = new Intent(this, FavouritePlacesActivity.class);
        intent.putExtra(SEARCH_TYPE, TYPE_FAV);
        startActivityForResult(intent, 2);
    }

    public void showResults(ArrayList<Place> places){
        //Notify data changed en el fragment
        searchResults.clear();
        searchResults.addAll(places);
        pageAdapter.notifyDataSetChanged(places);
    }

    public Spinner getSpinner() {
        return spinner;
    }

    public void updateTypes(ArrayList<String> aux){
        types.clear();
        types.add(getString(R.string.filter_all));
        types.addAll(aux);
        arrayAdapter.notifyDataSetChanged();
    }

    public void updateSearchResults(ArrayList<Place> aux){
        searchResults.clear();
        searchResults.addAll(aux);
    }

    public void updateFilteredResults(ArrayList<Place> aux){
        filteredResults.clear();
        filteredResults.addAll(aux);
    }

    public TabLayout.OnTabSelectedListener createTabListener(){
        TabLayout.OnTabSelectedListener tabSelectedListener =  new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d(getCallingActivity().getClassName(),"tab: " + tab.getPosition());
                pf = (ParentFragment) pageAdapter.getItem(tab.getPosition());
                pf.loadSpinner();
                pf.showFilteredResults(filteredResults);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {


            }
        };

        return tabSelectedListener;
    }

}
