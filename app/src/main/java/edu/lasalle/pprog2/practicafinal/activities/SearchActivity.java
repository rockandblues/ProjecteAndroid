package edu.lasalle.pprog2.practicafinal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.model.Place;
import edu.lasalle.pprog2.practicafinal.utils.JsonSearcher;

/**
 * Created by miquelabellan on 31/3/17.
 */

public class SearchActivity extends ParentActivity{

    private SeekBar seekBar;
    private TextView radius;
    private EditText search;
    private ArrayList<Place> searchResults;
    private JsonSearcher jsonSearcher;      //Variable q vamos a cambiar en un futuro


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_busqueda);

        //Variables para guardar los datos buscados
        searchResults = new ArrayList<>();
        try {
            jsonSearcher = new JsonSearcher(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Atributos de la vista
        radius = (TextView)findViewById(R.id.searchRadius);
        seekBar = (SeekBar)findViewById(R.id.searchSeekBar);
        search = (EditText) findViewById(R.id.searchEditText);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String kms = String.valueOf(seekBar.getProgress()/10);
                radius.setText(kms + " km");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void buscarPerNom(View view) {

        if (jsonSearcher != null){
            searchResults = jsonSearcher.searchByKeyWords(search.getText().toString());
        }

        //mostrar que esta pasando
        for (int i = 0; i < searchResults.size(); i++){
            Log.d("SEARCH_ACTIVITY", "restaurantName: " + searchResults.get(i).getName());
        }
        //TODO pasar la lista en el intent

        Intent intent = new Intent(this, ResultsActivity.class);
        startActivityForResult(intent, 2);

    }

    public void buscaPerLocalitzacio(View view) {


        //TODO pasar la lista en el intent


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

    public void favClick(MenuItem menuItem) {
        Intent intent = new Intent(this, FavActivity.class);
        startActivityForResult(intent, 2);
    }


}
