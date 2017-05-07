package edu.lasalle.pprog2.practicafinal.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.fragments.AllPlacesFragment;
import edu.lasalle.pprog2.practicafinal.fragments.OnlyOpenPlacesFragment;
import edu.lasalle.pprog2.practicafinal.fragments.ParentFragment;
import edu.lasalle.pprog2.practicafinal.model.Place;

/**
 * Created by joanfito on 21/4/17.
 */

public class PageAdapter extends FragmentStatePagerAdapter {

    //constante para recuperar la lista en los fragments
    public static final String LIST = "list";

    private Context context;
    private ArrayList<Place> searchResults;
    private ParentFragment selected;

    public PageAdapter(FragmentManager fm, Context context, ArrayList<Place> searchResults) {
        super(fm);
        this.context = context;
        this.searchResults = searchResults;

    }

    @Override
    public Fragment getItem(int position) {
        selected = null;
        switch (position) {
            case 0:
                //ALL
                selected = new AllPlacesFragment();
                break;
            case 1:
                //ONLY OPEN
                selected = new OnlyOpenPlacesFragment();
                break;
        }
        //Crear un bundle con la infromacion que va a tener el fragment
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(LIST, searchResults);
        selected.setArguments(bundle);

        return selected;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String selected = null;
        switch (position) {
            case 0:
                //ALL
                selected = context.getString(R.string.all_tab);
                break;
            case 1:
                //ONLY OPEN
                selected = context.getString(R.string.only_open_tab);
                break;
        }
        return selected;
    }

    public void notifyDataSetChanged(ArrayList<Place> aux){
        Log.d("PAGE_ADAPTER", aux.toString());
       selected.notifyDataSetChanged(aux);
    }
}