package edu.lasalle.pprog2.practicafinal.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.model.Place;

/**
 * Created by MatiasJVH on 04/04/2017.
 */

public class DescriptionActivity extends ParentActivity {

    private Place place;
    private RatingBar restaurantRating;
    private TextView restaurantName;
    private TextView restaurantDescription;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_layout);

        place = (Place)getIntent().getSerializableExtra("openedPlace");
        restaurantRating = (RatingBar) findViewById(R.id.rating_description_activity);
        restaurantName = (TextView) findViewById(R.id.restaurant_name_description_activity);
        restaurantDescription = (TextView) findViewById(R.id.restaurant_description);
        imageView = (ImageView) findViewById(R.id.imageDescriptionActivity);

        imageView.setImageResource(R.drawable.restaurante3);
        restaurantName.setText(place.getName());
        restaurantRating.setRating(place.getReview());
        restaurantDescription.setText(getText(R.string.restaurant_description) + " " + place.getType());

    }


    public void onMapClicked(View view){

    }


    public void onSendClick(View view){

    }

    public void onFABClick(View view){

    }


}
