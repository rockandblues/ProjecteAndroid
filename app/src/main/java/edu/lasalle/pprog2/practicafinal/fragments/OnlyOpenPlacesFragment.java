package edu.lasalle.pprog2.practicafinal.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.activities.ActionBar3Activity;
import edu.lasalle.pprog2.practicafinal.model.Place;
import edu.lasalle.pprog2.practicafinal.adapters.PlaceListViewAdapter;

import static edu.lasalle.pprog2.practicafinal.adapters.PageAdapter.LIST;

/**
 * Created by joanfito on 21/4/17.
 */

public class OnlyOpenPlacesFragment extends ParentFragment {

    private ArrayList<Place> searchResults;
    private ArrayList<Place> filteredData;

    private ListView listView;
    private PlaceListViewAdapter adapter;
    private ActionBar3Activity actionBar3Activity;

    private Calendar c;
    private View view;

    public OnlyOpenPlacesFragment(){
        searchResults = new ArrayList<>();
        filteredData = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {


        actionBar3Activity = (ActionBar3Activity)getActivity();

        searchResults = new ArrayList<>();
        filteredData = new ArrayList<>();

        Bundle bundle = this.getArguments();
        if (bundle != null){
            //Copia el contenido a la lista
            ArrayList<Place> aux = bundle.getParcelableArrayList(LIST);
            filteredData.clear();
            filteredData.addAll(aux);

            searchResults.clear();
            searchResults.addAll(aux);
        }


        //IR haciendo adds en open
        view =  inflater.inflate(R.layout.all_layout, container, false);

        listView = (ListView)view.findViewById(R.id.all_listview);

        //Creem l'adapter i el vinculem a la listview
        adapter = new PlaceListViewAdapter(getContext(), filteredData);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(adapter);
        return view;
    }

    @Override
    public void showResults(ArrayList<Place> aux){
        //Dejar la lista vacia
        filteredData.clear();
        //conseguir el tiempo
        c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        String formattedDate = df.format(c.getTime());

        for (int i  = 0; i < aux.size(); i++){
            if (!aux.get(i).getOpening().equals("null") || !aux.get(i).getClosing().equals("null")){
                if (aux.get(i).getOpening().compareTo(formattedDate) < 0 &&
                                            aux.get(i).getClosing().compareTo(formattedDate) > 0)

                filteredData.add(aux.get(i));
            }
        }

        //cargar la lista original
        searchResults.clear();
        searchResults.addAll(filteredData);

        //loadSpinner(searchResults, actionBar3Activity, this);

        //Cambiar los datos de la lista
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

    @Override
    public void showFilteredResults(ArrayList<Place> aux){

        filteredData.clear();
        filteredData.addAll(aux);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadSpinner() {



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
