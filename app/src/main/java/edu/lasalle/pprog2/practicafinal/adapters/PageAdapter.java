package edu.lasalle.pprog2.practicafinal.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
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

public class PageAdapter extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener {

    //constante para recuperar la lista en los fragments
    public static final String LIST = "list";

    private Context context;
    //private ArrayList<Place> searchResults;
    private ParentFragment allPlacesFragemnt;
    private ParentFragment onlyOpenFragment;


    public PageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        allPlacesFragemnt = new AllPlacesFragment();
        onlyOpenFragment = new OnlyOpenPlacesFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                //ALL
                Log.d("PAGEADAPTER", "all places");
                return allPlacesFragemnt;
            case 1:
                //ONLY OPEN
                Log.d("PAGEADAPTER", "only open");
                return onlyOpenFragment;
        }
        return null;
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
        allPlacesFragemnt.updateLists(aux);
        onlyOpenFragment.updateLists(aux);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d("PAGE_ADAPTER", "scroll --> " + position);
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("PAGE_ADAPTER", "page selected --> " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.d("PAGE_ADAPTER", "state --> ");
    }
}
