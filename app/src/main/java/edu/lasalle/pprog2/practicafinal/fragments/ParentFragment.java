package edu.lasalle.pprog2.practicafinal.fragments;

import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.activities.ActionBar3Activity;
import edu.lasalle.pprog2.practicafinal.model.Place;

/**
 * Created by MatiasJVH on 07/05/2017.
 */

public abstract class ParentFragment extends Fragment {


    public void notifyDataSetChanged(ArrayList<Place> aux){}

    public void loadSpinner(ArrayList<Place> place, ActionBar3Activity activity) {
        int max = place.size();
        //Creamos el adapter
        ArrayList<String> types = new ArrayList<>();

        for(int i = 0; i < max; i++) {
            types.add(place.get(i).getType());
        }


        //TODO solo funciona bien el spinner la primera vez

        //Creamos el adaptador
        ArrayAdapter spinner_adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, types);
        //Añadimos el layout para el menú y se lo damos al spinner
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        activity.getSpinner().setAdapter(spinner_adapter);
    }
}
