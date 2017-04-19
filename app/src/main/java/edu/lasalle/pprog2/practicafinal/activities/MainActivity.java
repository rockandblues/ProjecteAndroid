package edu.lasalle.pprog2.practicafinal.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.model.User;
import edu.lasalle.pprog2.practicafinal.repositories.PersonDataBase;
import edu.lasalle.pprog2.practicafinal.repositories.LocalRepository;

public class MainActivity extends AppCompatActivity {

    private LocalRepository localRepository;
    private EditText email;
    private EditText password;
    private PersonDataBase personDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        personDB = new PersonDataBase(this);
        localRepository = new LocalRepository();
        email = (EditText) findViewById(R.id.emailMainActivity);
        password = (EditText) findViewById(R.id.passwordMainActivity);

    }

    public void enterActivity(View view) {

        if (correctCredentials(email.getText().toString(), password.getText().toString())) {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivityForResult(intent, 2);
        }else {
            //DIALOG WRONG Credentials
            //Ver en un futuro de especificar el error, si mail o contraseña no existen o los dos.

            AlertDialog.Builder showInfo = new AlertDialog.Builder(this);
            showInfo.setTitle(getString(R.string.error));
            showInfo.setMessage(getString(R.string.wrong_credentials));
            showInfo.setCancelable(true);
            showInfo.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            showInfo.create();
            showInfo.show();
        }

    }

    private boolean correctCredentials(String username, String password) {

        if (personDB.existUsername(username)){
            User user = personDB.getUser(username);
            Log.d("MAIN_ACTIVITY","email: " + user.getEmail());
            Log.d("MAIN_ACTIVITY","password: " + user.getPassword());
            //return user.getPassword().equals(password);
            return true;
        }
        return false;
    }

    public void registerActivity (View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivityForResult(intent, 2);
    }
}
