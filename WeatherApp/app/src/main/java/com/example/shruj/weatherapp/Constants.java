package com.example.shruj.weatherapp;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by shruj on 03/02/2016.
 */
public class Constants {

    //API related
    public static String API_KEY_VALUE = "95977d2310997675";
    public static String GET_METHOD = "GET";
    public static String POST_METHOD = "POST";
    public static String API_KEY = "api-key";
    public static String STATE = "state";
    public static String CITY = "city";
    public static String HOURLY = "hourly";
    public static String HOURLY_VALUE = "hourly/q";
    public static String XML_EXTENSION = ".xml";
    public static String URL = "http://api.wunderground.com/api/";
    public static String SPACE = " ";
    public static String UNDERSCORE = "_";
    public static String COMMA = ", ";

    //Intent info
    public static String INTENT_INFO_LOCATION = "LocationObject";
    public static String LIST_OBJECT = "ForecastObject";
    public static String CURRENT_OBJECT = "CurrentPosition";
    public static String CURRENT_LOCATION = "CurrentLocation";

    //Progress Dialog messages
    public static String PROGRESS_DIALOG_MESSAGE = "Loading Hourly Data..";

    //Location details
    public static int LOCATION_REQUEST_CODE = 100;
    public static String LOCATION_OBJECT = "locationObject";

    //Toast messages Constants
    public static String TOAST_MANDATORY_FIELD = "Mandatory field";

    //Toast messages
    public static void ToastMessages(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    //XML constants
    public static String FORECAST = "forecast";
    public static String FCTTIME_PRETTY = "pretty";
    public static String TEMP = "temp";
    public static String DEWPOINT = "dewpoint";
    public static String CLOUDS = "condition";
    public static String ICON_URL = "icon_url";
    public static String WIND_SPEED = "wspd";
    public static String WIND_DIRECTION = "wdir";
    public static String WIND_ANGLE = "degrees";
    public static String CLIMATE_TYPE = "wx";
    public static String HUMIDITY = "humidity";
    public static String FEELS_LIKE = "feelslike";
    public static String PRESSURE = "mslp";
    public static String THUMBNAIL = "Standara Thumbnail";
    public static String NORMAL_IMAGE = "format: \"Normal\"";
    public static String ENGLISH = "english";
    public static String WIND_DIRECTION_DIRECTION = "dir";
    public static String FAHRENHEIT = "\u2109.";
    public static String DEGREE = "\u00B0";
    public static String HUMIDITY_PRECENTAGE = " %";
    public static String FAHRENHEIT_REPRESENTATION = " Fahrenheit";
    public static String PRESSURE_REPRESENTATION = " hPa";
    public static String WINDS_SCALE = " mph,";


}
