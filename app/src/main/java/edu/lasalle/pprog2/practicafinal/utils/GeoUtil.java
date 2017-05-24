package edu.lasalle.pprog2.practicafinal.utils;

import android.icu.text.NumberFormat;

import java.text.ParseException;
import java.util.Locale;

/**
 * Created by MatiasJVH on 05/05/2017.
 */

public class GeoUtil {

    /**
     * Devuelve los valores de las coordenadas en double
     * @param search
     * @return lat-lon-km
     */
    public static double[] getLatLonKm(String search){
        String[] tokens = search.split("/");
        double[] coordinates = new double[3];
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        Number [] number = new Number[3];
        try {
            number[0] = format.parse(tokens[0]);
            number[1] = format.parse(tokens[1]);
            number[2] = format.parse(tokens[2]);
            coordinates[0] = number[0].doubleValue();
            coordinates[1] = number[1].doubleValue();
            coordinates[2] = number[2].doubleValue();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return coordinates;
    }

    /**
     * Pasa los valores de las coordenadas a string
     * @param lat
     * @param lon
     * @param km
     */
    public static String  latLonKmToString(double lat, double lon, double km){
        return lat + "/" + lon + "/" + km;
    }
}
