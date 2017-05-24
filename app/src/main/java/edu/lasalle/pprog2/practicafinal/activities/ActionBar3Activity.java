package edu.lasalle.pprog2.practicafinal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import edu.lasalle.pprog2.practicafinal.listener.SpinnerItemSelectedListener;
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

    //Adapter y lista del spinner
    private ArrayAdapter arrayAdapter;
    protected ArrayList<String> types;
    protected ArrayList<Place> searchResults;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_layout);

        searchResults = new ArrayList<>();

        //Obtenemos los elementos que necesitamos del layout
        tab = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.webPager);

        //Variables para guardar los datos buscados
        pageAdapter = new PageAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(pageAdapter);
        tab.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // Este metodo se llamara una vez durante la creacion de la Activity
        getMenuInflater().inflate(R.menu.action_bar3, menu);
        setTitle("");

        MenuItem item = menu.findItem(R.id.types_spinner);
        Spinner spinner = (Spinner) item.getActionView();

        //Lista del menu
        types = new ArrayList<>();
        types.add(getString(R.string.filter_all));

        //crear el adapter para mostrar los tipos en el spinner
        arrayAdapter = new ArrayAdapter( this ,android.R.layout.simple_spinner_item, types);

        //Añadimos el layout para el menú y se lo damos al spinner
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Adaptador para filtrar por el campo
        SpinnerItemSelectedListener spinnerListener = new SpinnerItemSelectedListener(pageAdapter,types);

        //agregar el adapter y el listener
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(spinnerListener);

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

    /**
     * Muestra los resultados de la busqueda (Fragments)
     * Se Guarda una copia de la lista
     * @param places lista de resultados
     */
    public void showResults(ArrayList<Place> places){

        searchResults.clear();
        searchResults.addAll(places);
        loadSpinner();
        //Notify data changed en el fragment para que actualicen las listas
        pageAdapter.notifyDataSetChanged(places);
    }

    /**
     * Carga los valores del spinner
     */
    private void loadSpinner(){

        int filteredDataSize = searchResults.size();
        //limpiar la lista de types y agregar un ALL
        types.clear();
        types.add(getString(R.string.filter_all));

        //Buscamos los tipos encontrados y los guardamos
        for(int i = 0; i < filteredDataSize; i++) {
            int typesSize = types.size();
            boolean exists = false;
            //Miramos si ya tenemos el tipo guardado, si no lo esta, lo guardamos
            for(int j = 0; j < typesSize; j++) {
                if(types.get(j).equals(searchResults.get(i).getType())) exists = true;
            }
            if(!exists) types.add(searchResults.get(i).getType());
        }
        //notificar al adaptador
        arrayAdapter.notifyDataSetChanged();
    }

}
