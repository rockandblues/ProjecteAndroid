package edu.lasalle.pprog2.practicafinal.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.model.Person;
import edu.lasalle.pprog2.practicafinal.repositories.PersonDataBase;

//TODO pswrd == null es fb

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private LoginButton fbButton;
    private ProfileTracker profileTracker;
    private Profile userProfile;
    private CallbackManager callbackManager;
    public static String emailUser;
    private PersonDataBase personDB;
    private Person fbUser;
    private static MainActivity instance = null;

    public static MainActivity getInstance() {
        if(instance == null) {
            instance = new MainActivity();
        }
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Si hay un usuario logeado al iniciar sesion, la cerramos

        setContentView(R.layout.activity_main);
        setTitle("");
        personDB = new PersonDataBase(this);
        email = (EditText)findViewById(R.id.emailMainActivity);
        password = (EditText)findViewById(R.id.passwordMainActivity);
        fbButton = (LoginButton)findViewById(R.id.login_fb_button);


        //Creamos el inicio de sesion con facebook
        callbackManager = CallbackManager.Factory.create();

        fbButton.setReadPermissions("email", "public_profile", "user_about_me");
        fbButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                //Leemos la informaacion que nos proporciona facebook
                if(Profile.getCurrentProfile() == null) {
                    profileTracker = new ProfileTracker() {
                        @Override
                        protected void onCurrentProfileChanged(Profile profile, Profile profile2) {
                            // profile2 is the new profile
                            userProfile = profile2;
                            profileTracker.stopTracking();
                            readFBInformation(loginResult);
                        }
                    };
                }
                else {
                    userProfile = Profile.getCurrentProfile();
                    readFBInformation(loginResult);
                }


                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivityForResult(intent, 2);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                showError(getString(R.string.error));
            }
        });
    }

    public void enterActivity(View view) {

        if (correctCredentials(email.getText().toString(), password.getText().toString())) {
            emailUser = email.getText().toString();

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

    private boolean correctCredentials(String email, String password) {

        if (personDB.existUser(email)){
            Person user = personDB.getPerson(email);
            System.out.println("EXISTO!");

            //Si el usuario esta registrado con facebook, las credenciales no seran correctas
            if(user.getPassword() == null) return false;
            return user.getPassword().equals(password);
        }
        return false;
    }

    public void registerActivity (View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivityForResult(intent, 2);
    }

    private void showError(String message) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (callbackManager.onActivityResult(requestCode, resultCode, data)) {
            return;
        }
    }

    private void readFBInformation(final LoginResult loginResult) {
        fbUser = new Person();
        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            //Creamos un nuevo usuario
                            if(userProfile != null) {
                                String email = object.getString("email");

                                //Indicamos que este mail es el usuario activo
                                emailUser = email;
                                System.out.println("EMAILUSER - "+emailUser);
                                //Añadimos la informacion de facebook al nuevo usuario
                                fbUser.setName(userProfile.getFirstName());
                                fbUser.setSurname(userProfile.getLastName());
                                //fbUser.setGender(object.getString("gender"));
                                fbUser.setEmail(email);

                                //Si el password val null vol dir que es usuari de fb
                                fbUser.setPassword(null);
                                fbUser.setDescription("Hola");
                                System.out.println("A: "+fbUser.getName());
                                System.out.println("B: "+fbUser.getSurname());
                                System.out.println("C: "+fbUser.getEmail());
                                //System.out.println("D: "+fbUser.getGender());
                                //System.out.println("ffff:  "+object.getString("about"));

                                //Añadimos el usuario a la DB
                                addFBUser();
                                //TODO get description
                            } else {
                                System.out.println("NOP");
                            }
                        } catch (JSONException e) {
                            System.out.println("JSONExecption en MainActivity");
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "email,gender,about");
        request.setParameters(parameters);
        request.executeAsync();
    }


    public static void setEmailUser(String emailUser) {
        MainActivity.emailUser = emailUser;
    }

    private void addFBUser() {
        if (personDB.existUser(fbUser.getEmail())) {
            //Si ya existe no hacemos nada
        } else {
            //Si no existe, la registramos en la base de datos;
            personDB.addPerson(fbUser);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //TODO no se ejecuta el on destroy
        //Cerramos la session de fb
        LoginManager.getInstance().logOut();
    }
}
