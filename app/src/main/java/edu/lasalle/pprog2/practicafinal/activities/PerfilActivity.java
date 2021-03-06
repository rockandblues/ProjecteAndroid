package edu.lasalle.pprog2.practicafinal.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.model.Person;
import edu.lasalle.pprog2.practicafinal.repositories.imp.PersonDataBase;

import static edu.lasalle.pprog2.practicafinal.activities.SearchActivity.SEARCH_TYPE;
import static edu.lasalle.pprog2.practicafinal.activities.SearchActivity.TYPE_FAV;

/**
 * Created by miquelabellan on 31/3/17.
 */

public class PerfilActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextSurname;
    private RadioButton male;
    private RadioButton female;
    private EditText editTextDescription;
    private boolean activa;
    private Button buttonTakePicture;
    private Button buttonUpdateProfile;
    private PersonDataBase personDataBase;
    private ImageView image;
    //private MainActivity mainActivity;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_layout);
        setTitle("");
        image = (ImageView)findViewById(R.id.image_profile);
        editTextName   = (EditText)findViewById(R.id.name_profile);
        editTextSurname   = (EditText)findViewById(R.id.surname_profile);
        male = (RadioButton)findViewById(R.id.male_profile);
        female = (RadioButton)findViewById(R.id.female_profile);
        editTextDescription = (EditText)findViewById(R.id.description_profile);
        buttonTakePicture = (Button) findViewById(R.id.take_picture_button);
        buttonUpdateProfile = (Button) findViewById(R.id.update_profile);


        //Cargar la informacion del usuario

        //mainActivity = MainActivity.getInstance();
        personDataBase = new PersonDataBase(this);

        image.setImageBitmap(personDataBase.getPerson(MainActivity.emailUser).getPhoto());
        editTextName.setText(personDataBase.getPerson(MainActivity.emailUser).getName());
        editTextSurname.setText(personDataBase.getPerson(MainActivity.emailUser).getSurname());
        editTextDescription.setText(personDataBase.getPerson(MainActivity.emailUser).getDescription());
        //TODO hacer que esto funcione
        if(personDataBase.getPerson(MainActivity.emailUser).getFemale() > 0) {
            female.setChecked(true);
        } else if (personDataBase.getPerson(MainActivity.emailUser).getFemale() < 0){
            male.setChecked(true);
        }
        buttonUpdateProfile.setVisibility(View.GONE);
        editTextName.setEnabled(false);


        editTextSurname.setEnabled(false);
        editTextDescription.setEnabled(false);
        male.setEnabled(false);
        female.setEnabled(false);
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
            editTextDescription.setEnabled(true);
            male.setEnabled(true);
            female.setEnabled(true);
            buttonTakePicture.setVisibility(View.VISIBLE);
            buttonUpdateProfile.setVisibility(View.VISIBLE);
            activa = true;

        } else {
            editTextName.setEnabled(false);
            editTextSurname.setEnabled(false);
            editTextDescription.setEnabled(false);
            male.setEnabled(false);
            female.setEnabled(false);
            buttonTakePicture.setVisibility(View.GONE);
            buttonUpdateProfile.setVisibility(View.GONE);
            activa = false;
            //Guardar informacion
        }

    }

    public void onUpDateProfile(View view){

        Person u = new Person();

        //Obtenemos la imagen guardada en el image view
        BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        u.setPhoto(bitmap);
        u.setName(editTextName.getText().toString());
        u.setSurname(editTextSurname.getText().toString());
        u.setEmail(personDataBase.getPerson(MainActivity.emailUser).getEmail());
        u.setDescription(editTextDescription.getText().toString());
        u.setPassword(personDataBase.getPerson(MainActivity.emailUser).getPassword());
        if(female.isChecked()) {
            u.setFemale(1);
        }
        else {
            u.setFemale(-1);
        }
        personDataBase.updatePerson(u);
        Toast.makeText(this, getText(R.string.successful_actualization), Toast.LENGTH_LONG)
                .show();

    }

    public void takeAPictureOnClick (View view) {
        // Creamos un intent implícito que llame a alguna aplicación capaz de tomar fotos.
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Lanzamos el intent para que nos devuelva un resultado y configuramos el requestCode
        // para poder reconocer el valor de retorno.
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Filtramos los resultados de las distintas actividades según el requestCode.
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    Bitmap image = (Bitmap) bundle.get("data");

                    ImageView imageView = (ImageView) findViewById(R.id.image_profile);
                    if (imageView != null) {
                        imageView.setImageBitmap(image);
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * On click del icono de Corazon del menu
     * Lleva al usuario a la actividad de lugares favoritos
     * @param menuItem
     */
    public void onClickFavPlace(MenuItem menuItem) {

        Intent intent = new Intent(this, FavouritePlacesActivity.class);
        intent.putExtra(SEARCH_TYPE, TYPE_FAV);
        startActivityForResult(intent, 2);
    }


}
