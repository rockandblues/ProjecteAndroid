package edu.lasalle.pprog2.practicafinal.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.adapters.RecentSearchListViewAdapter;
import edu.lasalle.pprog2.practicafinal.repositories.imp.PersonDataBase;
import edu.lasalle.pprog2.practicafinal.utils.GeoUtil;

/**
 * Created by miquelabellan on 31/3/17.
 */

public class SearchActivity extends ParentActivity{

    private SeekBar seekBar;
    private TextView radius;
    private EditText search;
    private ListView recentListView;
    private RecentSearchListViewAdapter adapter;
    private PersonDataBase db;

    private double lat;
    private double lon;
    private double km;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_busqueda);
        setTitle("");

        db = new PersonDataBase(this);
        //Atributos de la vista
        radius = (TextView)findViewById(R.id.searchRadius);
        seekBar = (SeekBar)findViewById(R.id.searchSeekBar);
        search = (EditText)findViewById(R.id.searchEditText);
        recentListView = (ListView)findViewById(R.id.last_places_listview);

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

        //TODO leer de la DB

        ArrayList<String> strings = new ArrayList<>();
        //EJEMPLO para ver si va
        for(int i = 0; i < 5; i++) {
            strings.add("Recent Search "+i);
        }
        //Creamos el adapter y lo vinculamos a la listview
        adapter = new RecentSearchListViewAdapter(this, strings);
        recentListView.setAdapter(adapter);
        recentListView.setOnItemClickListener(adapter);
    }

    public void buscarPerNom(View view) {
        if(search.getText().toString().equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle(getString(R.string.error))
                    .setMessage(R.string.empty_search)
                    .setPositiveButton(getString(R.string.ok),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //No fem res
                                }
                            }).create();

            builder.show();
        } else {
            db.addRecentSearch(MainActivity.emailUser, search.getText().toString());
            Intent intent = new Intent(this, ResultsActivity.class);
            intent.putExtra("searchType", "buscarPerNom");
            intent.putExtra("searchText", search.getText().toString());
            startActivityForResult(intent, 2);
        }

    }

    public void buscaPerLocalitzacio(View view) {
        Intent intent = new Intent(this, ResultsActivity.class);

        //TODO buscar los valores de lat/lon
        km = seekBar.getProgress()/10;
        String searchParam = GeoUtil.latLonKmToString(lat, lon, km);

        intent.putExtra("searchType", "buscaPerLocalitzacio");
        intent.putExtra("searchText", searchParam);
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

    public void netejaInfo(View view) {
        search.setText("");
    }


}
