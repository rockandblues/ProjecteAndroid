package edu.lasalle.pprog2.practicafinal.fragments;

import android.support.v4.app.Fragment;

import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.model.Place;

/**
 * Created by MatiasJVH on 07/05/2017.
 */

public abstract class ParentFragment extends Fragment {

    public void notifyDataSetChanged(ArrayList<Place> aux){}
}
