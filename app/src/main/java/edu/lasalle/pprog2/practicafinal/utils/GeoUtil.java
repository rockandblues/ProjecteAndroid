package edu.lasalle.pprog2.practicafinal.utils;

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
        String[] tokens = search.split("-");
        double[] coordinates = new double[3];
        coordinates[0] = Double.valueOf(tokens[0]);
        coordinates[1] = Double.valueOf(tokens[1]);
        coordinates[2] = Double.valueOf(tokens[2]);
        return coordinates;
    }

    /**
     * Pasa los valores de las coordenadas a string
     * @param lat
     * @param lon
     * @param km
     */
    public static String  latLonKmToString(double lat, double lon, double km){
        return lat + "-" + lon + "-" + km;
    }
}
