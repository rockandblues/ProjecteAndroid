package edu.lasalle.pprog2.practicafinal.fragments;

import android.content.DialogInterface;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.model.Place;
/**
 * Created by joanfito on 21/4/17.
 */

public class OnlyOpenPlacesFragment extends ParentFragment {

    private Calendar c;

    public OnlyOpenPlacesFragment(){
        super();
    }

    @Override
    public void loadInitialResults(ArrayList<Place> aux){

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

        //cargar los datos a lista "original"
        searchResults.clear();
        searchResults.addAll(filteredData);

        //muestra los resultados de la busqueda
        showResults(aux);
    }

    @Override
    public void showFilteredResults(ArrayList<Place> aux){

        filteredData.clear();
        filteredData.addAll(aux);
        adapter.notifyDataSetChanged();

        //mostrar un dialog en caso de que la lista este vacia
        if (filteredData.size() == 0){
            android.support.v7.app.AlertDialog.Builder showInfo =
                            new android.support.v7.app.AlertDialog.Builder(actionBar3Activity);
            showInfo.setTitle(actionBar3Activity.getString(R.string.no_results_title));
            showInfo.setMessage(actionBar3Activity.getString(R.string.no_results_body));
            showInfo.setCancelable(false);
            showInfo.setPositiveButton(actionBar3Activity.getString(R.string.no_results_ok),
                    new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            showInfo.create();
            showInfo.show();
        }

    }

}
