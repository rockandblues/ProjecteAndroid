package edu.lasalle.pprog2.practicafinal.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.model.Place;

/**
 * Created by joanfito on 18/4/17.
 */

public class JsonParser {


    /**
     * Llegeix el json
     * @param context contexte de l'activitat
     * @return els llocs guardats en el fitxer
     * @throws IOException
     */
    public static ArrayList<Place> parseFile(Context context) throws IOException {

        //Creem un objecte tipus Gson
        final Gson gson = new GsonBuilder().create();

        //Llegim el fitxer i guardem la informacio
        ArrayList<Place> places = new ArrayList<>();
        places = gson.fromJson(new BufferedReader(new InputStreamReader
                        (context.getResources().openRawResource(R.raw.locations))),
                new TypeToken<ArrayList<Place>>(){}.getType());

        return places;
    }


    /**
     * lee un Jason String y lo pasa a ArrayList
     * @param json en formato string
     * @return un array list de Place
     */
    public static ArrayList<Place> parseServerResponse(String json){
        Gson gson = new Gson();
        ArrayList<Place> places = new ArrayList<>();
        places = gson.fromJson(json, new TypeToken<ArrayList<Place>>(){}.getType());
        return places;
    }
}
