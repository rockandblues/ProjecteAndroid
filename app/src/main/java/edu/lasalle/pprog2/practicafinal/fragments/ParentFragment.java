package edu.lasalle.pprog2.practicafinal.fragments;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.activities.ActionBar3Activity;
import edu.lasalle.pprog2.practicafinal.model.Place;

/**
 * Created by MatiasJVH on 07/05/2017.
 */

public abstract class ParentFragment extends Fragment {


    public abstract void showResults(ArrayList<Place> aux);

    public abstract void showFilteredResults(ArrayList<Place> aux);

    public abstract void loadSpinner();

    /*public void loadSpinner(final ArrayList<Place> place, final ActionBar3Activity activity,
                            final ParentFragment pf) {
        int max = place.size();

        final ArrayList<String> types = new ArrayList<>();
        types.add(getString(R.string.filter_all));
        //Buscamos los tipos encontrados y los guardamos
        for(int i = 0; i < max; i++) {

            int typeMax = types.size();
            boolean exists = false;
            //Miramos si ya tenemos el tipo guardado, si no lo esta, lo guardamos
            for(int j = 0; j < typeMax; j++) {
                if(types.get(j).equals(place.get(i).getType())) exists = true;
            }
            if(!exists) types.add(place.get(i).getType());
        }

        //Creamos el adaptador
        ArrayAdapter spinner_adapter =
                new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, types);

        //Añadimos el layout para el menú y se lo damos al spinner
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        activity.getSpinner().setAdapter(spinner_adapter);

        //Adaptador para filtrar por el campo
        activity.getSpinner().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("PARENT FRAGMENT","item Selected");
                Log.d("PARENT FRAGMENT", types.get(position));
                ArrayList<Place> filter = new ArrayList<Place>();

                //Filtrar la lista
                if (!types.get(position).equals(getString(R.string.filter_all))) {

                    for (int i = 0; i < place.size(); i++) {
                        if (place.get(i).getType().equals(types.get(position))) {
                            filter.add(place.get(i));
                        }
                    }
                }else {
                    filter.addAll(place);
                }

                pf.showFilteredResults(filter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }*/


}
