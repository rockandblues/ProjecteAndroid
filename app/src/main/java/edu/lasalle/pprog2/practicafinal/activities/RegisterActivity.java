package edu.lasalle.pprog2.practicafinal.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.model.Person;
import edu.lasalle.pprog2.practicafinal.repositories.imp.PersonDataBase;
import edu.lasalle.pprog2.practicafinal.repositories.PersonsRepo;

/**
 * Created by miquelabellan on 31/3/17.
 */

public class RegisterActivity extends AppCompatActivity{

    private static final String TAG = "RegisterActivity";
    private static final String EMAIL_PATTERN = "[a-z0-9]+\\.*\\w*@[a-z]+\\.[a-z]{2,3}$";
    private EditText name;
    private EditText surname;
    private EditText email;
    private EditText password;
    private EditText cPassword;
    private RadioButton male;
    private RadioButton female;
    private EditText description;
    private CheckBox confirm;
    private ImageView image;
    private PersonsRepo personsRepo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);
        setTitle("");

        name = (EditText)findViewById(R.id.registerName);
        surname = (EditText)findViewById(R.id.registerSurname);
        email = (EditText)findViewById(R.id.registerEmail);
        password = (EditText)findViewById(R.id.registerPassword);
        cPassword = (EditText)findViewById(R.id.registerCPassword);
        male = (RadioButton)findViewById(R.id.registerMale);
        female = (RadioButton)findViewById(R.id.registerFemale);
        description = (EditText)findViewById(R.id.registerDescription);
        confirm = (CheckBox)findViewById(R.id.registerConfirm);
        image = (ImageView) findViewById(R.id.register_image);
        personsRepo = new PersonDataBase(this);

    }

    /**
     * Onclick de take a picture
     * Toma una foto del usuario
     * @param view
     */
    public void takeAPictureOnClick (View view) {
        // Creamos un intent implícito que llame a alguna aplicación capaz de tomar fotos.
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //TODO guardar imagen en la DB

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
                    Bitmap photo = (Bitmap) bundle.get("data");

                    if (image != null) {
                        image.setImageBitmap(photo);
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * Registra a usuario en la aplicacion
     * @param view
     */
    public void register(View view) {
        if(confirm.isChecked()) {
            if(checkInfo()) {
                if(!existUser(email.getText().toString())) {
                    Person u = null;

                    //Obtenemos la imagen guardada en el image view
                    BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
                    Bitmap userImage = drawable.getBitmap();

                    if (male.isChecked()) {

                        u = new Person(userImage,
                                name.getText().toString(), surname.getText().toString(),
                                email.getText().toString(), password.getText().toString(),
                                description.getText().toString(), -1);

                    } else if (female.isChecked()) {
                        u = new Person(userImage,
                                name.getText().toString(), surname.getText().toString(),
                                email.getText().toString(), password.getText().toString(),
                                description.getText().toString(),1);
                    }
                    personsRepo.addPerson(u);

                    //Cambiamos el correo del MainActivity que indica el usuari actual
                    MainActivity.setEmailUser(email.getText().toString());

                    //Notificar que se registro
                    Toast.makeText(this, getText(R.string.successful_registration), Toast.LENGTH_LONG)
                            .show();
                    //Ir a la siguiente pantalla
                    Intent intent = new Intent(this, SearchActivity.class);
                    startActivityForResult(intent, 2);
                } else {
                    Toast.makeText(this, getString(R.string.used_email), Toast.LENGTH_LONG)
                            .show();
                }
            }
            else showError(getString(R.string.wrong_information));
        } else {
            showError(getString(R.string.accept_terms));
        }
    }

    /**
     * Verifica que los datos introducidos sean correctos
     * muestra mensajes de error para el campo erroneo
     * @return true si los datos son correctos o false si no
     */
    private boolean checkInfo() {
        boolean ok = true;
        if(name.getText().toString().isEmpty()) {
            name.requestFocus();
            name.setError(getString(R.string.empty_field));
            ok = false;
        }
        if(surname.getText().toString().isEmpty()) {
            surname.requestFocus();
            surname.setError(getString(R.string.empty_field));
            ok = false;
        }
        if(password.getText().toString().isEmpty()) {
            password.requestFocus();
            password.setError(getString(R.string.empty_field));
            ok = false;
        }
        if(cPassword.getText().toString().isEmpty()) {
            cPassword.requestFocus();
            cPassword.setError(getString(R.string.empty_field));
            ok = false;
        } else {
            //Comprovamos que las contraseñas coincidan
            if(!password.getText().toString().isEmpty()) {
                if(!password.getText().toString().equals(cPassword.getText().toString())) {
                    password.requestFocus();
                    cPassword.requestFocus();
                    password.setError(getString(R.string.wrong_password));
                    cPassword.setError(getString(R.string.wrong_password));
                    ok = false;
                }
            }
        }
        if(email.getText().toString().isEmpty()) {
            email.requestFocus();
            email.setError(getString(R.string.empty_field));
            ok = false;
        } else {
            //Comprovamos que el email tenga el formato correcto
            if(!checkEmail(email.getText().toString())) {
                email.requestFocus();
                email.setError(getString(R.string.unvalid_email));
                ok = false;
            }
        }
        if(description.getText().toString().isEmpty()) {
            description.getText().toString().isEmpty();
            ok = false;
        }

        return ok;

    }

    /**
     * Muestra un mensaje de error
     * @param message
     */
    private void showError(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(getString(R.string.error))
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //No fem res
                            }
                        }).create();

        builder.show();
    }

    /**
     * Verifica que el email cumpla con los requerimientos
     * @param email email a verificar
     * @return
     */
    private boolean checkEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Mira si el usuario existe en la base de datos
     * @param email email que se esta registrando
     * @return
     */
    public boolean existUser(String email) {
        return personsRepo.existUser(email);
    }
}
