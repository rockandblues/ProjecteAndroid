package edu.lasalle.pprog2.practicafinal.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.activities.DescriptionActivity;
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
        //Cargamos la imagenes segun los datos que nos llegan del servidor
        ImageView imageView = (ImageView)placeView.findViewById(R.id.listview_cell_image);
        if(place.getType().equals("Oriental")){
            InputStream is = context.getResources().openRawResource(R.raw.oriental);
            imageView.setImageBitmap(BitmapFactory.decodeStream(is));
        } else if(place.getType().equals("Take Away")) {
            InputStream is = context.getResources().openRawResource(R.raw.takeaway);
            imageView.setImageBitmap(BitmapFactory.decodeStream(is));
        } else if(place.getType().equals("Italiano")) {
            InputStream is = context.getResources().openRawResource(R.raw.italian);
            imageView.setImageBitmap(BitmapFactory.decodeStream(is));
        } else if(place.getType().equals("Hamburgueseria")) {
            InputStream is = context.getResources().openRawResource(R.raw.hamburg);
            imageView.setImageBitmap(BitmapFactory.decodeStream(is));
        } else if(place.getType().equals("Restaurante")) {
            InputStream is = context.getResources().openRawResource(R.raw.restaurant);
            imageView.setImageBitmap(BitmapFactory.decodeStream(is));
        } else if(place.getType().equals("Nepali")) {
            InputStream is = context.getResources().openRawResource(R.raw.nepali);
            imageView.setImageBitmap(BitmapFactory.decodeStream(is));
        } else if(place.getType().equals("Frankfurt")){
            InputStream is = context.getResources().openRawResource(R.raw.frankfurt);
            imageView.setImageBitmap(BitmapFactory.decodeStream(is));
        } else if(place.getType().equals("Cafe")) {
            InputStream is = context.getResources().openRawResource(R.raw.cafe);
            imageView.setImageBitmap(BitmapFactory.decodeStream(is));
        } else if(place.getType().equals("Braseria")) {
            InputStream is = context.getResources().openRawResource(R.raw.braseria);
            imageView.setImageBitmap(BitmapFactory.decodeStream(is));
        } else {
            InputStream is = context.getResources().openRawResource(R.raw.tapas);
            imageView.setImageBitmap(BitmapFactory.decodeStream(is));
        }

        TextView name = (TextView)placeView.findViewById(R.id.listview_restaurant_name);
        name.setText(place.getName());

        RatingBar rating = (RatingBar)placeView.findViewById(R.id.listview_cell_rating);
        rating.setRating(place.getReview());

        TextView address = (TextView)placeView.findViewById(R.id.listview_cell_address);
        address.setText(place.getAddress());

        return placeView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(context, DescriptionActivity.class);
        intent.putExtra("openedPlace", places.get(position));

        context.startActivity(intent);
    }
}
