package edu.lasalle.pprog2.practicafinal.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

        //Inicializar la lista

        actionBar3Activity = (ActionBar3Activity)getActivity();

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

    /**
     * guarda los resultados iniciales de la busqueda  (servidor o base de datos)
     * @param aux lista de resultados
     */
    public abstract void loadInitialResults(ArrayList<Place> aux);


    /**
     * Muestra los resultados de la peticion (servidor o base de datos)
     * @param aux lista de resultados
     */
    protected void showResults(ArrayList<Place> aux){

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
        }
    }

    /**
     * Actualiza la lista de lugares filtrados (lugares a mostrar)
     * @param type tipo por el cual filtar
     */
    public void updateFilteredResults(String type){
        ArrayList<Place> filter = new ArrayList<Place>();

            //Filtrar la lista si el tipo NO es ALL, en caso contrario añadir todos los elementos
            if (!(type).equals(actionBar3Activity.getString(R.string.filter_all))) {

                for (int i = 0; i < searchResults.size(); i++) {
                    if (searchResults.get(i).getType().equals(type)) {
                        filter.add(searchResults.get(i));
                    }
                }
            } else {
                filter.addAll(searchResults);
            }
        //mostrar los resultados filtrados
        showFilteredResults(filter);
    }

    /**
     * Muestra los resultados filtrados
     * @param aux lista de resultados a mostrar
     */
    public void showFilteredResults(ArrayList<Place> aux){

        filteredData.clear();
        filteredData.addAll(aux);
        adapter.notifyDataSetChanged();
    }
}
