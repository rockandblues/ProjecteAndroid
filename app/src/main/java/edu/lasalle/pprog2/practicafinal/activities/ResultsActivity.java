package edu.lasalle.pprog2.practicafinal.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;

import edu.lasalle.pprog2.practicafinal.R;
import edu.lasalle.pprog2.practicafinal.model.Place;
import edu.lasalle.pprog2.practicafinal.repositories.PersonDataBase;
import edu.lasalle.pprog2.practicafinal.utils.JsonSearcher;
import edu.lasalle.pprog2.practicafinal.utils.PageAdapter;

/**
 * Created by miquelabellan on 31/3/17.
 */

public class ResultsActivity extends ParentActivity {

    private ArrayList<Place> searchResults;
    private JsonSearcher jsonSearcher;
    private TabLayout tab;
    private PersonDataBase db;
    private ViewPager viewPager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_layout);
        setTitle("");

        db = new PersonDataBase(this);
        //Obtenemos los elementos que necesitamos del layout
        tab = (TabLayout)findViewById(R.id.tabs);
        viewPager = (ViewPager)findViewById(R.id.webPager);

        //Variables para guardar los datos buscados
        searchResults = new ArrayList<>();
        try {
            jsonSearcher = new JsonSearcher(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Separamos los tipos de busqueda
        if(getIntent().getStringExtra("searchType").equals("buscarPerNom")) {
            if (jsonSearcher != null){
                searchResults = jsonSearcher.searchByKeyWords
                        (getIntent().getStringExtra("searchText"));
            }
        }
        if(db.getIdFromUser(MainActivity.emailUser) != -1) {
            //db.addRecentPlace();
        }


//        if(getIntent().getStringExtra("searchType").equals("buscaPerLocalitzacio")) {
//            if(jsonSearcher != null) {
//                searchResults = jsonSearcher.searchByGeolocation();
//            }
//        }



        //Creem l'adaptador del Pager i el relacionem amb el viewPager
        final PageAdapter pageAdapter =
                new PageAdapter(getSupportFragmentManager(), this, searchResults);
        viewPager.setAdapter(pageAdapter);
        tab.setupWithViewPager(viewPager);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Este metodo se llamara una vez durante la creacion de la Activity
        getMenuInflater().inflate(R.menu.action_bar3, menu);

        MenuItem item = menu.findItem(R.id.spinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_values, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return true;
    }

    public void profileClick(MenuItem menuItem) {
        Intent intent = new Intent(this, PerfilActivity.class);
        startActivityForResult(intent, 2);

    }
    public void favClick(MenuItem menuItem) {
        Intent intent = new Intent(this, FavActivity.class);
        startActivityForResult(intent, 2);
    }

    private void doPetition() {
        String tag_json_obj = "json_obj_req";

        String url = "";

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        /*JsonObjectRequest jsonObjReq = new JsonObjectRequest(DownloadManager.Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            horaActualitzacio.setText(response.getJSONObject("time").getString("updated"));
                            JSONObject usd = response.getJSONObject("bpi").getJSONObject("USD");
                            JSONObject gbp = response.getJSONObject("bpi").getJSONObject("GBP");
                            JSONObject eur = response.getJSONObject("bpi").getJSONObject("EUR");

                            CanviMoneda cm = new CanviMoneda();
                            cm.setMoneda(usd.getString("code"));
                            cm.setDiners(usd.getDouble("rate_float"));

                            CanviMoneda cm2 = new CanviMoneda();
                            cm2.setMoneda(gbp.getString("code"));
                            cm2.setDiners(gbp.getDouble("rate_float"));

                            CanviMoneda cm3 = new CanviMoneda();
                            cm3.setMoneda(eur.getString("code"));
                            cm3.setDiners(eur.getDouble("rate_float"));

                            if(!primerCop) {
                                System.out.println("Entro per segon o + cops");
                                cm.setDiferencia(monedesArrayList.get(0).getDiners() -
                                        moneyDataBase.getCurrency(cm).getDiners());

                                cm2.setDiferencia(monedesArrayList.get(1).getDiners() -
                                        moneyDataBase.getCurrency(cm2).getDiners());

                                cm3.setDiferencia(monedesArrayList.get(2).getDiners() -
                                        moneyDataBase.getCurrency(cm3).getDiners());
                            } else {
                                cm.setDiferencia(0);
                                cm2.setDiferencia(0);
                                cm3.setDiferencia(0);

                                moneyDataBase.addCurrency(cm);
                                moneyDataBase.addCurrency(cm2);
                                moneyDataBase.addCurrency(cm3);
                                primerCop = false;
                            }



                            monedesArrayList.set(0,cm);
                            monedesArrayList.set(1,cm2);
                            monedesArrayList.set(2,cm3);


                            adapter.notifyDataSetChanged();


                            pDialog.hide();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("MainActivity", "Error: " + error.getMessage());
                pDialog.hide();
            }
        });

        addToRequestQueue(jsonObjReq, tag_json_obj);
    }
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? "MainActivity" : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }*/
    }






}
