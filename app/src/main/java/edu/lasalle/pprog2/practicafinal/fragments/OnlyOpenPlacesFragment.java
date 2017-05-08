package edu.lasalle.pprog2.practicafinal.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.model.Place;
import edu.lasalle.pprog2.practicafinal.adapters.PlaceListViewAdapter;

import static edu.lasalle.pprog2.practicafinal.adapters.PageAdapter.LIST;

/**
 * Created by joanfito on 21/4/17.
 */

public class OnlyOpenPlacesFragment extends ParentFragment {

    private ArrayList<Place> searchResults;
    ArrayList<Place> open;

    private ListView listView;
    private PlaceListViewAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {

        open  = new ArrayList<>();
        searchResults = new ArrayList<>();

        Bundle bundle = this.getArguments();
        if (bundle != null){
            //Copia el contenido a la lista
            ArrayList<Place> aux = bundle.getParcelableArrayList(LIST);
            searchResults.clear();
            searchResults.addAll(aux);
        }


        //IR haciendo adds en open

        View view =  inflater.inflate(R.layout.all_layout, container, false);

        listView = (ListView)view.findViewById(R.id.all_listview);

        //Creem l'adapter i el vinculem a la listview
        adapter = new PlaceListViewAdapter(getContext(), open);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(adapter);
        return view;
    }

    @Override
    public void notifyDataSetChanged(ArrayList<Place> aux){
        //TODO Buscamos los establecimientos abiertos
        //Cambiar los datos de la lista
        open.clear();
        open.addAll(aux);
        adapter.notifyDataSetChanged();
    }
}
