package edu.lasalle.pprog2.practicafinal.fragments;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.activities.ActionBar3Activity;
import edu.lasalle.pprog2.practicafinal.adapters.PlaceListViewAdapter;
import edu.lasalle.pprog2.practicafinal.model.Place;

import static edu.lasalle.pprog2.practicafinal.adapters.PageAdapter.LIST;

/**
 * Created by joanfito on 21/4/17.
 */

public class AllPlacesFragment extends ParentFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {

        actionBar3Activity = (ActionBar3Activity)getActivity();
        //Inicializar la lista
        searchResults = new ArrayList<>();
        filteredData = new ArrayList<>();
        //Buscar los datos del bundle
        Bundle bundle = this.getArguments();

        if (bundle != null){
            //Copia el contenido a la lista
            ArrayList<Place> aux = bundle.getParcelableArrayList(LIST);

            filteredData.clear();
            filteredData.addAll(aux);
            searchResults.clear();
            searchResults.addAll(aux);
        }

        //Creem l'adapter
        adapter = new PlaceListViewAdapter(getContext(), filteredData);

        //Creamos la vista para poder acceder a los recursos
        view = inflater.inflate(R.layout.all_layout, container, false);
        listView = (ListView) view.findViewById(R.id.all_listview);
        //Vinculamos el adpatador a la lista
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(adapter);

        return view;
    }

    @Override
    public void updateLists(ArrayList<Place> aux){

        //cargar los datos a lista usada para los filtros
        filteredData.clear();
        filteredData.addAll(aux);

        //cargar los datos a lista "original"
        searchResults.clear();
        searchResults.addAll(aux);

        //muestra los resultados de la busqueda
        showResults(aux);
    }

}
