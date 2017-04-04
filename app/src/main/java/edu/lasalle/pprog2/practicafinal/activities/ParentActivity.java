package edu.lasalle.pprog2.practicafinal.activities;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;

import edu.lasalle.pprog2.practicafinal.R;

/**
 * Created by MatiasJVH on 04/04/2017.
 */

public class ParentActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Este metodo se llamara una vez durante la creacion de la Activity
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar3, menu);
        return true;
    }

    //implementar los metodos de los iconos

}
