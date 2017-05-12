package edu.lasalle.pprog2.practicafinal.activities;

import android.app.FragmentTransaction;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.lasalle.pprog2.practicafinal.R;

/**
 * Created by MatiasVillarroel on 12/05/17.
 */

public class LocationActivity extends ActionBar1Activity implements OnMapReadyCallback {

    private static final String LAT_PLACE = "lat";
    private static final String LON_PLACE = "lon";
    private static final String NAME_PLACE = "name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa_layout);
        setTitle("");

        MapFragment mapFragment = MapFragment.newInstance();

        //FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, mapFragment);
        transaction.commit();

        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng longitude = new LatLng(getIntent().getDoubleExtra(LAT_PLACE, 0),
                getIntent().getDoubleExtra(LON_PLACE, 0));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(longitude, 15.0f);
        googleMap.moveCamera(cameraUpdate);

        MarkerOptions markerOptions = new MarkerOptions().position(longitude);
        markerOptions.title(getIntent().getStringExtra(NAME_PLACE));
        googleMap.addMarker(markerOptions);


    }




}
