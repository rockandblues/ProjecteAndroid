package edu.lasalle.pprog2.practicafinal.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.adapters.RecentSearchListViewAdapter;
import edu.lasalle.pprog2.practicafinal.repositories.imp.PersonDataBase;
import edu.lasalle.pprog2.practicafinal.service.LocationService;
import edu.lasalle.pprog2.practicafinal.utils.GeoUtil;

/**
 * Created by miquelabellan on 31/3/17.
 */

public class SearchActivity extends ActionBar1Activity {

    public static final String SEARCH_TYPE = "searchType";
    public static final String TYPE_FAV =    "searchFav";
    public static final String TYPE_NAME =   "searchName";
    public static final String TYPE_GEO =    "searchGeo";
    public static final String TEXT =        "searchText";

    private SeekBar seekBar;
    private TextView radius;
    private EditText search;

    private ListView recentListView;
    private RecentSearchListViewAdapter recentSearchListViewAdapter;
    private ArrayList<String> recentSearchesList;
    private PersonDataBase db;

    private double lat;
    private double lon;
    private double km;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_busqueda);
        setTitle("");

        LocationService.getInstance(getApplicationContext());

        db = new PersonDataBase(this);

        //Atributos de la vista
        radius = (TextView)findViewById(R.id.searchRadius);
        seekBar = (SeekBar)findViewById(R.id.searchSeekBar);
        search = (EditText)findViewById(R.id.searchEditText);
        recentListView = (ListView)findViewById(R.id.last_places_listview);

        //List view resent searches
        recentSearchesList = new ArrayList<>();
        recentSearchListViewAdapter = new RecentSearchListViewAdapter(this, recentSearchesList);
        recentListView.setAdapter(recentSearchListViewAdapter);
        recentListView.setOnItemClickListener(recentSearchListViewAdapter);

        new AsyncDBRequest().execute(MainActivity.emailUser);

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

    /**
     * Busca restaurantes segun lo que escribe el usuario
     * @param view
     */
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
            intent.putExtra(SEARCH_TYPE, TYPE_NAME);
            intent.putExtra(TEXT, search.getText().toString());
            startActivityForResult(intent, 2);
        }

    }

    /**
     * Busca restaurantes segun la localizacion del usuario
     * @param view
     */
    public void buscaPerLocalitzacio(View view) {
        Intent intent = new Intent(this, ResultsActivity.class);
        Location location = LocationService.getInstance(getApplicationContext()).getLocation();
        if (location != null) {
            lat = location.getLatitude();
            lon = location.getLongitude();
        }

        km = seekBar.getProgress()/10;
        String searchParam = GeoUtil.latLonKmToString(lat, lon, km);

        db.addRecentSearch(MainActivity.emailUser, searchParam);

        intent.putExtra(SEARCH_TYPE, TYPE_GEO);
        intent.putExtra(TEXT, searchParam);
        startActivityForResult(intent, 2);
    }

    /**
     * Limpia el campo de busqueda
     * @param view
     */
    public void netejaInfo(View view) {
        search.setText("");
    }


    /**
     * Actualiza la lista de lugares buscados recientemente
     * @param aux
     */
    public void updateRecentSearches(ArrayList<String> aux){
        recentSearchesList.clear();
        recentSearchesList.addAll(aux);
        recentSearchListViewAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //mostrar los cambios de la lista al volver a la actividad
        new AsyncDBRequest().execute(MainActivity.emailUser);
    }

    //Clase usada para buscar los datos de la lista de busquedas recientes en la base de datos
    private class AsyncDBRequest extends AsyncTask<String, Void, ArrayList<String>> {


        @Override
        protected ArrayList<String> doInBackground(String... params) {
            //TODO arreglar esto
            //se usa para darle tiempo a los fragments de crearse
            return db.getAllRecentSearches(params[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<String> resentPlaces) {
            super.onPostExecute(resentPlaces);

            updateRecentSearches(resentPlaces);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        LocationService locationService = LocationService.getInstance(getApplicationContext());
        locationService.registerListeners(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocationService locationService = LocationService.getInstance(getApplicationContext());
        locationService.unregisterListeners();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LocationService.MY_PERMISSIONS_REQUEST_LOCATION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    LocationService.getInstance(this).registerListeners(this);
                }
                break;
        }
    }

}
