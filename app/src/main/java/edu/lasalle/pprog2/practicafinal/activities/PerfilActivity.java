package edu.lasalle.pprog2.practicafinal.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.lasalle.pprog2.practicafinal.R;

/**
 * Created by miquelabellan on 31/3/17.
 */

public class PerfilActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextSurname;
    private boolean activa;

    private Button buttonTakePicture;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_layout);
        editTextName   = (EditText)findViewById(R.id.name_profile);
        editTextSurname   = (EditText)findViewById(R.id.surname_profile);

        buttonTakePicture = (Button) findViewById(R.id.take_picture_button);
        editTextName.setEnabled(false);
        editTextSurname.setEnabled(false);
        activa = false;


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Este metodo se llamara una vez durante la creacion de la Activity
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar2, menu);
        return true;
    }
    public void editProfile(MenuItem menuItem) {

        if(!activa) {
            editTextName.setEnabled(true);
            editTextSurname.setEnabled(true);
            buttonTakePicture.setVisibility(View.VISIBLE);
            activa = true;
        } else {
            editTextName.setEnabled(false);
            editTextSurname.setEnabled(false);
            buttonTakePicture.setVisibility(View.GONE);
            activa = false;
        }



    }
    public void finishActivity(MenuItem menuItem) {
        finishActivity(R.layout.perfil_layout);
    }


}
