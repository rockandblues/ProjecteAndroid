package edu.lasalle.pprog2.practicafinal.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.model.User;
import edu.lasalle.pprog2.practicafinal.repositories.PersonDataBase;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private LoginButton fbButton;
    private ProfileTracker profileTracker;
    private Profile userProfile;
    private CallbackManager callbackManager;
    public static String emailUser;
    private PersonDataBase personDB;
    private static User innerUser;

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
        //TODO a veces no se ejecuta ni el logout ni el profileTracker
//        LoginManager.getInstance().logOut();

        setContentView(R.layout.activity_main);
        setTitle("");
        personDB = new PersonDataBase(this);
        email = (EditText) findViewById(R.id.emailMainActivity);
        password = (EditText) findViewById(R.id.passwordMainActivity);
        fbButton = (LoginButton)findViewById(R.id.login_fb_button);

//        //Creamos el inicio de sesion con facebook
//        callbackManager = CallbackManager.Factory.create();
//
//        fbButton.setReadPermissions("email", "public_profile", "user_about_me");
//        fbButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                //Leemos la informaacion que nos proporciona facebook
//                if(Profile.getCurrentProfile() == null) {
//                    profileTracker = new ProfileTracker() {
//                        @Override
//                        protected void onCurrentProfileChanged(Profile profile, Profile profile2) {
//                            // profile2 is the new profile
//                            userProfile = profile2;
//                            profileTracker.stopTracking();
//                        }
//                    };
//                }
//                else {
//                    userProfile = Profile.getCurrentProfile();
//                }
//
//                User fbUser = readFBInformation(loginResult);
//                System.out.println("www: "+fbUser.getEmail());
////                if (personDB.existUsername(fbUser.getEmail())) {
////                    //Si ya existe no hacemos nada
////                } else {
////                    //Si no existe, la registramos en la base de datos;
////                    personDB.addPerson(fbUser);
////
////                }
//
//                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
//                startActivityForResult(intent, 2);
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                showError(getString(R.string.error));
//            }
//        });
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

    private boolean correctCredentials(String username, String password) {

        if (personDB.existUsername(username)){
            User user = personDB.getUser(username);
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
//        if (callbackManager.onActivityResult(requestCode, resultCode, data)) {
//            return;
//        }
    }

    private User readFBInformation(final LoginResult loginResult) {
        innerUser = new User();
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

                                //Añadimos la informacion de facebook al nuevo usuario
                                innerUser.setName(userProfile.getFirstName());
                                innerUser.setSurname(userProfile.getLastName());
                                innerUser.setGender(object.getString("gender"));
                                innerUser.setEmail(email);
                                innerUser.setPassword("fb_password");
                                innerUser.setDescription("Hola");
                                //TODO user.setPassword(??????);
                                System.out.println("A: "+innerUser.getName());
                                System.out.println("B: "+innerUser.getSurname());
                                System.out.println("C: "+innerUser.getEmail());
                                System.out.println("D: "+innerUser.getGender());
                                System.out.println("ffff:  "+object.getString("about"));
                                //TODO get description
                                //TODO crear un usuario nuevo con esa info y registrarlo
                            } else {
                                System.out.println("NOP");
                            }
                        } catch (JSONException e) {
                            System.out.println("fijejviojiei");
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "email,gender,about");
        request.setParameters(parameters);
        request.executeAsync();
        System.out.println("YYY: "+innerUser.getEmail());
        return innerUser;
    }


    public static void setEmailUser(String emailUser) {
        MainActivity.emailUser = emailUser;
    }
}
