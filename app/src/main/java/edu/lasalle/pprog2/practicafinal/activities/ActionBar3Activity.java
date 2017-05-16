package edu.lasalle.pprog2.practicafinal.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.adapters.PageAdapter;
import edu.lasalle.pprog2.practicafinal.model.Place;
import edu.lasalle.pprog2.practicafinal.repositories.imp.PersonDataBase;

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // Este metodo se llamara una vez durante la creacion de la Activity
        getMenuInflater().inflate(R.menu.action_bar3, menu);
        setTitle("");

        MenuItem item = menu.findItem(R.id.types_spinner);
        spinner = (Spinner) item.getActionView();

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
        pageAdapter.notifyDataSetChanged(places);
    }

    public Spinner getSpinner() {
        return spinner;
    }

}
