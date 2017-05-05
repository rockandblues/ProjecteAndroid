package edu.lasalle.pprog2.practicafinal.fragments;

import android.support.v4.app.Fragment;;
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

/**
 * Created by joanfito on 21/4/17.
 */

public class OnlyOpenPlacesFragment extends Fragment {

    private ArrayList<Place> searchResults;
    private ListView listView;
    private PlaceListViewAdapter adapter;

    public OnlyOpenPlacesFragment(ArrayList<Place> searchResults) {
        this.searchResults = searchResults;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {

        //TODO Buscamos los establecimientos abiertos
        ArrayList<Place> opened = new ArrayList<>();

        View view =  inflater.inflate(R.layout.all_layout, container, false);

        listView = (ListView)view.findViewById(R.id.all_listview);

        //Creem l'adapter i el vinculem a la listview
        adapter = new PlaceListViewAdapter(getContext(), searchResults);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(adapter);
        return view;
    }
}
