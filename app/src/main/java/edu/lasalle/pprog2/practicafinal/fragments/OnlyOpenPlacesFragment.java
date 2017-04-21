package edu.lasalle.pprog2.practicafinal.fragments;

import android.support.v4.app.Fragment;;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.lasalle.pprog2.practicafinal.R;

/**
 * Created by joanfito on 21/4/17.
 */

public class OnlyOpenPlacesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.only_open_layout, container, false);
    }
}
