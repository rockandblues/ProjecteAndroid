package edu.lasalle.pprog2.practicafinal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import edu.lasalle.pprog2.practicafinal.R;

/**
 * Created by miquelabellan on 31/3/17.
 */

public class SearchActivity extends ParentActivity{


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_busqueda);
    }

    public void buscarPerNom(View view) {
        Intent intent = new Intent(this, ResultsActivity.class);
        startActivityForResult(intent, 2);
    }

    public void buscaPerLocalitzacio(View view) {
        Intent intent = new Intent(this, ResultsActivity.class);
        startActivityForResult(intent, 2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Este metodo se llamara una vez durante la creacion de la Activity
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar1, menu);
        return true;
    }
    public void profileClick(MenuItem menuItem) {
        Intent intent = new Intent(this, PerfilActivity.class);
        startActivityForResult(intent, 2);

    }


}
