package edu.lasalle.pprog2.practicafinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

/**
 * Created by miquelabellan on 31/3/17.
 */

public class SearchActivity extends AppCompatActivity{


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_busqueda);
    }

    public void buscarPerNom(View view) {
        Intent intent = new Intent(this, Results.class);
        startActivityForResult(intent, 2);
    }

    public void buscaPerLocalitzacio(View view) {
        Intent intent = new Intent(this, Results.class);
        startActivityForResult(intent, 2);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Este metodo se llamara una vez durante la creacion de la Activity
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar3, menu);
        return true;
    }

}
