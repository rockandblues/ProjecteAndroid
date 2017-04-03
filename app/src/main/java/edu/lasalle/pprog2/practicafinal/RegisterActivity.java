package edu.lasalle.pprog2.practicafinal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by miquelabellan on 31/3/17.
 */

public class RegisterActivity extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);
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

                    ImageView imageView = (ImageView) findViewById(R.id.imageView2);
                    if (imageView != null) {
                        imageView.setImageBitmap(image);
                    }
                }
                break;
            default:
                break;
        }
    }

}
