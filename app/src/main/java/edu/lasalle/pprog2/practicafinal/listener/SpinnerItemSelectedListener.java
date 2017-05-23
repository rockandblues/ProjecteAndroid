package edu.lasalle.pprog2.practicafinal.listener;

import android.view.View;
import android.widget.AdapterView;
import java.util.ArrayList;
import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.activities.ActionBar3Activity;
import edu.lasalle.pprog2.practicafinal.fragments.ParentFragment;
import edu.lasalle.pprog2.practicafinal.model.Place;

/**
 * Created by MatiasVillarroel on 22/05/17.
 */

public class SpinnerItemSelectedListener implements AdapterView.OnItemSelectedListener {

    private ArrayList<String> types;
    private ArrayList<Place> searchResults;
    private ParentFragment fragment;
    private ActionBar3Activity actionBar3Activity;
    private boolean selectItem;


    public SpinnerItemSelectedListener(ArrayList<Place> searchResults,ArrayList<String> types, ActionBar3Activity actionBar3Activity){
        //quiero que apunten a la lista de la actividad, que iran cambiando de valor acorde a los fragments
        this.searchResults = searchResults;
        this.types = types;
        this.actionBar3Activity = actionBar3Activity;
        selectItem = false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        ArrayList<Place> filter = new ArrayList<Place>();

        if (selectItem) {
            //Filtrar la lista si el tipo NO es ALL, en caso contrario añadir todos los elementos
            if (!types.get(position).equals(actionBar3Activity.getString(R.string.filter_all))) {

                for (int i = 0; i < searchResults.size(); i++) {
                    if (searchResults.get(i).getType().equals(types.get(position))) {
                        filter.add(searchResults.get(i));
                    }
                }
            } else {
                filter.addAll(searchResults);
                //mostrar los resultados filtrados
            }
            actionBar3Activity.updateFilteredResults(filter);
        }else {
            selectItem = true;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
