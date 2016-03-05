package com.example.shruj.weatherapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;

/**
 * Created by shruj on 03/03/2016.
 */
public class GetWeatherAsyncTask extends AsyncTask<Location, Integer, List<Forecast>> {

    Activity activity;
    ProgressDialog progressDialog;

    public GetWeatherAsyncTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected List<Forecast> doInBackground(Location... params) {

        RequestParam requestParam = new RequestParam(Constants.GET_METHOD, Constants.URL);

        requestParam.addParam(Constants.API_KEY, Constants.API_KEY_VALUE);
        requestParam.addParam(Constants.STATE, params[0].getStateCode());
        requestParam.addParam(Constants.HOURLY, Constants.HOURLY_VALUE);
        requestParam.addParam(Constants.CITY, params[0].getCityName());

        Log.d("demo", "request" + requestParam.getEncodedUrl());
        try {
            HttpURLConnection httpURLConnection = requestParam.setUpConnection();
            int statusCode = httpURLConnection.getResponseCode();
            Log.d("demo", "statusCode" + statusCode);
            if (statusCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpURLConnection.getInputStream();
                return ForecastUtilUsingPull.ForecastPullParser.parseForecast(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(Constants.PROGRESS_DIALOG_MESSAGE);
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(Boolean.FALSE);
        progressDialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressDialog.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(List<Forecast> forecasts) {
        super.onPostExecute(forecasts);
        progressDialog.dismiss();
        Log.d("demo", "onPostExecute");
        if (forecasts != null) {
            for (Forecast forecast : forecasts) {
                Log.d("forecast", forecast.toString());
            }
        } else Log.d("forecast", "null");

    }
}
