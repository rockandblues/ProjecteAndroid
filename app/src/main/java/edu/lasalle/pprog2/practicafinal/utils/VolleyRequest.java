package edu.lasalle.pprog2.practicafinal.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;
import edu.lasalle.pprog2.practicafinal.activities.ResultsActivity;
import edu.lasalle.pprog2.practicafinal.model.Place;
import edu.lasalle.pprog2.practicafinal.repositories.PersonDataBase;

/**
 * Created by MatiasJVH on 05/05/2017.
 */

public class VolleyRequest {

    //Constante
    private static final String BASE_URL = "http://testapi-pprog2.azurewebsites.net/api/locations.php?method=getLocations";
    private static final String PARAM_URL = "http://testapi-pprog2.azurewebsites.net/api/locations.php?method=getLocations&s=";
    private static final String GEO_URL = "http://testapi-pprog2.azurewebsites.net/api/locations.php?method=getLocations&lat=";
    public static final int ALL_SEARCH = 1;
    public static final int PARAM_SEARCH = 2;
    public static final int GEO_SEARCH = 3;

    private static VolleyRequest instance = null;
    private static RequestQueue queue = null;

    private PersonDataBase personDataBase;

    private VolleyRequest(){}

    public static VolleyRequest getInstance(Context context){
        if (instance == null){
            instance = new VolleyRequest();
            queue = Volley.newRequestQueue(context);
        }
        return instance;
    }

    /**
     *
     * @param searchParam parametros de busqueda
     * @param activity   actividad desde donde se hace la peticion
     * @param searchType 1-ALL_SEARCH    busca la totalidad de la infromacion del server
     *                   2-PARAM_SEARCH  busca segun parametros de busqueda
     *                   3-GEO_SEARCH    buca segun parametros de geolocalizacion
     */
    public void getServerInfo(String searchParam, final ResultsActivity activity, int searchType){
        personDataBase =  new PersonDataBase(activity.getApplicationContext());
        String url = BASE_URL;
        switch (searchType){
            case 1:
                //no hacemos nada porque dejamos la busqueda con los parametros por defecto
                break;

            case 2:
                url = PARAM_URL + searchParam;
                break;

            case 3:
                double[] param = GeoUtil.getLatLonKm(searchParam);
                url = GEO_URL + param[0] + "&lon=" + param[1] + "&dist=" + param[2];
                Log.d("VOLLEY", url);
                break;
        }



        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<Place> placeResults = JsonParser.parseServerResponse(response.toString());
                        for (int i = 0; i < placeResults.size(); i++){

                            Log.d("VOLLEY", placeResults.get(i).getName());

                        }
                        activity.showListView(placeResults);
                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //TODO activity dialog de no poder encontrar resultados
                        Log.d("VOLLEY", "Error.Response");
                        Log.d("VOLLEY", error.getMessage());
                    }
                }

                );

        // add it to the RequestQueue
        queue.add(getRequest);

    }

}
