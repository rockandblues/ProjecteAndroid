package edu.lasalle.pprog2.practicafinal.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import edu.lasalle.pprog2.practicafinal.R;

import static edu.lasalle.pprog2.practicafinal.activities.SearchActivity.SEARCH_TYPE;
import static edu.lasalle.pprog2.practicafinal.activities.SearchActivity.TYPE_FAV;

/**
 * Created by MatiasJVH on 04/04/2017.
 */

public class ActionBar1Activity extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Este metodo se llamara una vez durante la creacion de la Activity
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar1, menu);
        return true;
    }

    /**
     * On click del icono de Perfil del menu
     * Lleva al usuario a la actividad de perfil
     * @param menuItem
     */
    public void profileClick(MenuItem menuItem) {
        Intent intent = new Intent(this, PerfilActivity.class);
        startActivityForResult(intent, 2);

    }

    /**
     * On click del icono de Corazon del menu
     * Lleva al usuario a la actividad de lugares favoritos
     * @param menuItem
     */
    public void favClick(MenuItem menuItem) {
        Intent intent = new Intent(this, FavouritePlacesActivity.class);
        intent.putExtra(SEARCH_TYPE, TYPE_FAV);
        startActivityForResult(intent, 2);
    }

}
