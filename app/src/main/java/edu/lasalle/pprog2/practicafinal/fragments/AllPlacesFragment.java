package edu.lasalle.pprog2.practicafinal.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

    private ArrayList<Place> searchResults;
    private ArrayList<Place> filteredData;

    private ListView listView;
    private PlaceListViewAdapter adapter;
    private ActionBar3Activity actionBar3Activity;
    private View view;

    public AllPlacesFragment(){
        searchResults = new ArrayList<>();
        filteredData = new ArrayList<>();
    }

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
    public void showResults(ArrayList<Place> aux){
        filteredData.clear();
        filteredData.addAll(aux);

        searchResults.clear();
        searchResults.addAll(aux);

        //Muestra los datos del spinner
       // loadSpinner(searchResults, actionBar3Activity, this);

        //Verifica si hay resultados o no.
        //Si no hay resultados se muestra un dialog informandole al usuario
        //Si hay resultados se muestran
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



    @Override
    public void showFilteredResults(ArrayList<Place> aux){

        filteredData.clear();
        filteredData.addAll(aux);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void loadSpinner() {

        int max = filteredData.size();

        final ArrayList<String> types = new ArrayList<>();
        types.add(getString(R.string.filter_all));
        //Buscamos los tipos encontrados y los guardamos
        for(int i = 0; i < max; i++) {

            int typeMax = types.size();
            boolean exists = false;
            //Miramos si ya tenemos el tipo guardado, si no lo esta, lo guardamos
            for(int j = 0; j < typeMax; j++) {
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
        actionBar3Activity.getSpinner().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("PARENT FRAGMENT","item Selected");
                Log.d("PARENT FRAGMENT", types.get(position));
                ArrayList<Place> filter = new ArrayList<Place>();

                //Filtrar la lista
               /* if (!types.get(position).equals(getString(R.string.filter_all))) {

                    for (int i = 0; i < place.size(); i++) {
                        if (place.get(i).getType().equals(types.get(position))) {
                            filter.add(place.get(i));
                        }
                    }
                }else {
                    filter.addAll(place);
                }*/

                //showFilteredResults(filter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
