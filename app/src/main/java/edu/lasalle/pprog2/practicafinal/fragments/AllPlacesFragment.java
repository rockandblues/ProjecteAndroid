package edu.lasalle.pprog2.practicafinal.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.activities.ActionBar3Activity;
import edu.lasalle.pprog2.practicafinal.activities.RegisterActivity;
import edu.lasalle.pprog2.practicafinal.model.Place;
import edu.lasalle.pprog2.practicafinal.adapters.PlaceListViewAdapter;

import static edu.lasalle.pprog2.practicafinal.adapters.PageAdapter.LIST;

/**
 * Created by joanfito on 21/4/17.
 */

public class AllPlacesFragment extends ParentFragment {

    private ArrayList<Place> searchResults;
    private ListView listView;
    private PlaceListViewAdapter adapter;
    private ActionBar3Activity actionBar3Activity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {

        actionBar3Activity = (ActionBar3Activity)getActivity();
        //Inicializar la lista
        searchResults = new ArrayList<>();
        //Buscar los datos del bundle
        Bundle bundle = this.getArguments();
        if (bundle != null){
            //Copia el contenido a la lista
            ArrayList<Place> aux = bundle.getParcelableArrayList(LIST);
            searchResults.clear();
            searchResults.addAll(aux);
        }

        //Creem l'adapter
        adapter = new PlaceListViewAdapter(getContext(), searchResults);

        //Creamos la vista para poder acceder a los recursos
        View view =  inflater.inflate(R.layout.all_layout, container, false);
        listView = (ListView)view.findViewById(R.id.all_listview);
        //Vinculamos el adpatador a la lista
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(adapter);

        return view;
    }

    @Override
    public void notifyDataSetChanged(ArrayList<Place> aux){
        searchResults.clear();
        searchResults.addAll(aux);

        loadSpinner(aux, actionBar3Activity);

        adapter.notifyDataSetChanged();
    }

}
