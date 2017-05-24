package edu.lasalle.pprog2.practicafinal.fragments;


import java.util.ArrayList;
import edu.lasalle.pprog2.practicafinal.model.Place;

/**
 * Created by joanfito on 21/4/17.
 */

public class AllPlacesFragment extends ParentFragment {


    public AllPlacesFragment(){
        super();
    }

    @Override
    public void loadInitialResults(ArrayList<Place> aux){

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
