package edu.lasalle.pprog2.practicafinal.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.adapters.PageAdapter;
import edu.lasalle.pprog2.practicafinal.model.Place;

import static edu.lasalle.pprog2.practicafinal.activities.SearchActivity.SEARCH_TYPE;
import static edu.lasalle.pprog2.practicafinal.activities.SearchActivity.TYPE_FAV;

/**
 * Created by MatiasVillarroel on 12/05/17.
 */

public class ActionBar3Activity extends AppCompatActivity {

    protected TabLayout tab;
    protected ViewPager viewPager;
    protected PageAdapter pageAdapter;

    private Spinner spinner;
    private ArrayAdapter arrayAdapter;
    protected ArrayList<Place> searchResults;
    protected ArrayList<String> types;


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

        arrayAdapter =
                new ArrayAdapter( this ,android.R.layout.simple_spinner_item, types);

        //Añadimos el layout para el menú y se lo damos al spinner
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Adaptador para filtrar por el campo
        //spinner.setOnItemSelectedListener(
          //      new SpinnerItemSelectedListener(types,searchResults,this,this);

        spinner.setAdapter(arrayAdapter);

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
}
