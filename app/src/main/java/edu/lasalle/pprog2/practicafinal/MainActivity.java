package edu.lasalle.pprog2.practicafinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void enterActivity(View view) {

        Intent intent = new Intent(this, SearchActivity.class);
        startActivityForResult(intent, 2);

    }

    public void registerActivity (View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivityForResult(intent, 2);
    }
}
