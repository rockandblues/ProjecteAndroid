package edu.lasalle.pprog2.practicafinal.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.fragments.AllPlacesFragment;
import edu.lasalle.pprog2.practicafinal.fragments.OnlyOpenPlacesFragment;
import edu.lasalle.pprog2.practicafinal.model.Place;

/**
 * Created by joanfito on 21/4/17.
 */

public class PageAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private ArrayList<Place> searchResults;

    public PageAdapter(FragmentManager fm, Context context, ArrayList<Place> searchResults) {
        super(fm);
        this.context = context;
        this.searchResults = searchResults;

    }

    @Override
    public Fragment getItem(int position) {
        Fragment selected = null;
        switch (position) {
            case 0:
                //ALL
                selected = new AllPlacesFragment(searchResults);
                break;
            case 1:
                //ONLY OPEN
                selected = new OnlyOpenPlacesFragment(searchResults);
                break;
        }
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
}
