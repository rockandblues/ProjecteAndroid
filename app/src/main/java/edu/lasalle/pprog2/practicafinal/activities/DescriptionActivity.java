package edu.lasalle.pprog2.practicafinal.activities;

import android.os.Bundle;
import android.view.View;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.model.Place;

/**
 * Created by MatiasJVH on 04/04/2017.
 */

public class DescriptionActivity extends ParentActivity {

    private Place place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_layout);

        place = (Place)getIntent().getSerializableExtra("openedPlace");

        System.out.println("PLACE: "+place.getName());
        //TODO rellenar la informacion del layout con la informacion del place
    }


    public void onMapClicked(View view){

    }


    public void onSendClick(){

    }

    public void onFABClick(){

    }


}
