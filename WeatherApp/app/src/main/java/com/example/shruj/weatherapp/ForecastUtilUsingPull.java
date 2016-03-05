package com.example.shruj.weatherapp;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shruj on 03/04/2016.
 */
public class ForecastUtilUsingPull {
    public static class ForecastPullParser {
        static List<Forecast> parseForecast(InputStream inputStream) throws XmlPullParserException, IOException {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(inputStream, String.valueOf(Xml.Encoding.UTF_8));
            ArrayList<Forecast> forecastArrayList = new ArrayList<>();
            Forecast forecast = null;
            int event = parser.getEventType();

            while (event != XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equalsIgnoreCase(Constants.FORECAST)) {
                            forecast = new Forecast();
                        } else if (parser.getName().equalsIgnoreCase(Constants.FCTTIME_PRETTY)) {
                            forecast.setTime(parser.nextText().trim());
                            Log.d("demo", forecast.getTime());
                        } else if (parser.getName().equalsIgnoreCase(Constants.TEMP)) {
                            parser.nextTag();
                            if (parser.getName().equalsIgnoreCase(Constants.ENGLISH))
                                forecast.setTemperature(parser.nextText().trim());
                            Log.d("temp", "In Eng " + forecast.getTemperature());
                        } else if (parser.getName().equals(Constants.DEWPOINT)) {
                            parser.nextTag();
                            if (parser.getName().equalsIgnoreCase(Constants.ENGLISH))
                                forecast.setDewpoint(parser.nextText().trim());
                            Log.d("temp", "In Eng " + forecast.getDewpoint());
                        } else if (parser.getName().equals(Constants.CLOUDS)) {
                            forecast.setClouds(parser.nextText().toString());
                            Log.d("demo", forecast.getClouds());
                        } else if (parser.getName().equals(Constants.ICON_URL)) {
                            forecast.setIconUrl(parser.nextText().toString());
                            Log.d("demo", forecast.getIconUrl());
                        } else if (parser.getName().equals(Constants.WIND_SPEED)) {
                            parser.nextTag();
                            if (parser.getName().equalsIgnoreCase(Constants.ENGLISH))
                                forecast.setWindSpeed(parser.nextText().trim());
                            Log.d("temp", "In Eng " + forecast.getWindSpeed());
                        } else if (parser.getName().equals(Constants.WIND_DIRECTION)) {
                            parser.nextTag();
                            if (parser.getName().equalsIgnoreCase(Constants.WIND_DIRECTION_DIRECTION))
                                forecast.setWindDirection(parser.nextText().trim());
                            Log.d("temp", "In Eng " + forecast.getWindDirection());
                        } else if (parser.getName().equals(Constants.CLIMATE_TYPE)) {
                            forecast.setClimateType(parser.nextText().toString());
                            Log.d("demo", forecast.getClimateType());
                        } else if (parser.getName().equals(Constants.HUMIDITY)) {
                            forecast.setHumidity(parser.nextText().toString());
                            Log.d("demo", forecast.getHumidity());
                        } else if (parser.getName().equals(Constants.FEELS_LIKE)) {

                            parser.nextTag();
                            if (parser.getName().equalsIgnoreCase(Constants.ENGLISH))
                                forecast.setFeelsLike(parser.nextText().trim());
                            Log.d("temp", "In Eng " + forecast.getFeelsLike());

                        } else if (parser.getName().equals(Constants.PRESSURE)) {

                            parser.nextTag();
                            if (parser.getName().equalsIgnoreCase(Constants.ENGLISH))
                                forecast.setPressure(parser.nextText().trim());
                            Log.d("temp", "In Eng " + forecast.getPressure());

                        }

                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals(Constants.FORECAST)) {
                            forecastArrayList.add(forecast);
                            Log.d("forecast-list", forecast.toString());
                            forecast = null;
                        } else
                            break;
                    default:
                        break;

                }
                event = parser.next();
            }

            return forecastArrayList;
        }
    }
}
