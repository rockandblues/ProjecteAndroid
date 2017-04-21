package edu.lasalle.pprog2.practicafinal.utils;

import android.content.Context;
import android.media.Rating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.model.Place;

/**
 * Created by joanfito on 21/4/17.
 */

public class PlaceListViewAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

    private Context context;
    private ArrayList<Place> places;

    public PlaceListViewAdapter(Context context, ArrayList<Place> places) {
        this.context = context;
        this.places = places;

        for(Place p: places) System.out.println("PlaceListViewAdapter - "+p.getName());
    }

    @Override
    public int getCount() {
        return places.size();
    }

    @Override
    public Object getItem(int position) {
        return places.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View placeView = inflater.inflate(R.layout.restaurants_listview, parent, false);

        Place place = places.get(position);

        //Llenamos la vista
        ImageView imageView = (ImageView)placeView.findViewById(R.id.listview_cell_image);

        TextView name = (TextView)placeView.findViewById(R.id.listview_restaurant_name);
        name.setText(place.getName());

        RatingBar rating = (RatingBar)placeView.findViewById(R.id.listview_cell_rating);
        rating.setProgress(50); //TODO dependiendo de la valoracion poner las estrellas

        TextView address = (TextView)placeView.findViewById(R.id.listview_cell_address);
        address.setText(place.getAddress());

        return placeView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //TODO abrir descripcion
    }
}
