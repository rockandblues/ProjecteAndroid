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
