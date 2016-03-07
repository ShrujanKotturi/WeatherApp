package com.example.shruj.weatherapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.util.List;

/**
 * Created by shruj on 03/03/2016.
 */
public class GetWeatherAsyncTask extends AsyncTask<Location, Integer, List<Forecast>> {

    Activity activity;
    ProgressDialog progressDialog;
    String location;

    public GetWeatherAsyncTask(Activity activity, String location) {
        this.activity = activity;
        this.location = location;
    }

    @Override
    protected List<Forecast> doInBackground(Location... params) {

        RequestParam requestParam = new RequestParam(Constants.GET_METHOD, Constants.URL);

        requestParam.addParam(Constants.API_KEY, Constants.API_KEY_VALUE);
        requestParam.addParam(Constants.STATE, params[0].getStateCode());
        requestParam.addParam(Constants.HOURLY, Constants.HOURLY_VALUE);
        requestParam.addParam(Constants.CITY, params[0].getCityName());
        try {
            HttpURLConnection httpURLConnection = requestParam.setUpConnection();
            int statusCode = httpURLConnection.getResponseCode();
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
        if (forecasts != null) {
            setHourlyDataActivityElements(forecasts);
        }

    }


    private void setHourlyDataActivityElements(final List<Forecast> forecasts) {
        WeatherAdapter weatherAdapter = new WeatherAdapter(activity, R.layout.listview_row_layout, forecasts);

        ListView listView = (ListView) activity.findViewById(R.id.listView);
        listView.setAdapter(weatherAdapter);

        weatherAdapter.setNotifyOnChange(Boolean.TRUE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(activity, DetailsActivity.class);
                intent.putExtra(Constants.LIST_OBJECT, (Serializable) forecasts);
                intent.putExtra(Constants.CURRENT_OBJECT, position);
                intent.putExtra(Constants.CURRENT_LOCATION, location);
                activity.startActivity(intent);
            }
        });
    }
}
