package edu.lasalle.pprog2.practicafinal.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.activities.ActionBar3Activity;
import edu.lasalle.pprog2.practicafinal.adapters.PlaceListViewAdapter;
import edu.lasalle.pprog2.practicafinal.listener.SpinnerItemSelectedListener;
import edu.lasalle.pprog2.practicafinal.model.Place;

import static edu.lasalle.pprog2.practicafinal.adapters.PageAdapter.LIST;

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

    public ParentFragment(){
        searchResults = new ArrayList<>();
        filteredData = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {

        actionBar3Activity = (ActionBar3Activity)getActivity();
        //Inicializar la lista
        //searchResults = new ArrayList<>();
        //filteredData = new ArrayList<>();
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

    public void loadSpinner(){

        int filteredDataSize = filteredData.size();
        ArrayList<String> types = new ArrayList<>();

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

        actionBar3Activity.updateTypes(types);
    }

    public ArrayList<Place> getFilteredData() {
        return filteredData;
    }

    public ActionBar3Activity getActionBar3Activity() {
        return actionBar3Activity;
    }

}
