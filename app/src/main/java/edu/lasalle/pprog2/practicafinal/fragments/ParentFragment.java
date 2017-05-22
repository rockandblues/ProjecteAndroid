package edu.lasalle.pprog2.practicafinal.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.activities.ActionBar3Activity;
import edu.lasalle.pprog2.practicafinal.adapters.PlaceListViewAdapter;
import edu.lasalle.pprog2.practicafinal.listener.SpinnerItemSelectedListener;
import edu.lasalle.pprog2.practicafinal.model.Place;

/**
 * Created by MatiasJVH on 07/05/2017.
 */

public abstract class ParentFragment extends Fragment {

    protected ArrayList<Place> searchResults;
    protected ArrayList<Place> filteredData;

    protected ListView listView;
    protected PlaceListViewAdapter adapter;
    protected ActionBar3Activity actionBar3Activity;
    protected View view;

    public abstract void updateLists(ArrayList<Place> aux);

    //Verifica si hay resultados o no.
    //Si no hay resultados se muestra un dialog informandole al usuario
    //Si hay resultados se muestran
    protected void showResults(ArrayList<Place> aux){

        if(aux.size() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

            builder.setTitle(getString(R.string.error))
                    .setMessage(R.string.non_results)
                    .setPositiveButton(getString(R.string.retry),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    getActivity().finish();
                                }
                            }).create();

            builder.show();
        } else {
            adapter.notifyDataSetChanged();
            loadSpinner();
        }
    }

    public void showFilteredResults(ArrayList<Place> aux){
        filteredData.clear();
        filteredData.addAll(aux);
        adapter.notifyDataSetChanged();
    }

    private void loadSpinner(){
        int filteredDataSize = filteredData.size();

        final ArrayList<String> types = new ArrayList<>();
        types.add(getString(R.string.filter_all));

        //Buscamos los tipos encontrados y los guardamos
        for(int i = 0; i < filteredDataSize; i++) {
            int typesSize = types.size();
            boolean exists = false;
            //Miramos si ya tenemos el tipo guardado, si no lo esta, lo guardamos
            for(int j = 0; j < typesSize; j++) {
                if(types.get(j).equals(filteredData.get(i).getType())) exists = true;
            }
            if(!exists) types.add(filteredData.get(i).getType());
        }

        //Creamos el adaptador
        ArrayAdapter spinner_adapter =
                new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, types);

        //Añadimos el layout para el menú y se lo damos al spinner
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        actionBar3Activity.getSpinner().setAdapter(spinner_adapter);

        //Adaptador para filtrar por el campo
        actionBar3Activity.getSpinner().setOnItemSelectedListener(
                new SpinnerItemSelectedListener(types,searchResults,this,actionBar3Activity));
    }


    public ArrayList<Place> getSearchResults() {
        return searchResults;
    }

    public ArrayList<Place> getFilteredData() {
        return filteredData;
    }

    public ActionBar3Activity getActionBar3Activity() {
        return actionBar3Activity;
    }

}
