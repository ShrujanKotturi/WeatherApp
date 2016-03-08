package com.example.shruj.weatherapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class AddCityActivity extends AppCompatActivity {

    EditText editTextCityName, editTextStateCode;
    Location location;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(Boolean.TRUE);
        actionBar.setIcon(R.drawable.ic_launcher);

        editTextCityName = (EditText) findViewById(R.id.editTextCityName);
        editTextStateCode = (EditText) findViewById(R.id.editTextStateCode);

        findViewById(R.id.buttonSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextCityName.getText().toString().isEmpty() && !editTextStateCode.getText().toString().isEmpty()) {
                    location = new Location();
                    location.setCityName(editTextCityName.getText().toString().trim());
                    location.setStateCode(editTextStateCode.getText().toString().toUpperCase());
                    new ValidateCityStatePair().execute(location);
                } else if (editTextCityName.getText().toString().isEmpty()) {
                    editTextCityName.setError(Constants.TOAST_MANDATORY_FIELD);
                } else if (editTextStateCode.getText().toString().isEmpty()) {
                    editTextStateCode.setError(Constants.TOAST_MANDATORY_FIELD);
                }
            }
        });
    }

    private class ValidateCityStatePair extends AsyncTask<Location, Void, String> {

        @Override
        protected String doInBackground(Location... params) {

            RequestParam requestParam = new RequestParam(Constants.GET_METHOD_STATE_CITY, Constants.URL_CITY_VALIDATION);

            try {
                requestParam.addParam(Constants.CITY, URLEncoder.encode(params[0].getCityName(), "UTF-8"));
                requestParam.addParam(Constants.STATE, params[0].getStateCode());

                try {
                    HttpURLConnection httpURLConnection = requestParam.setUpConnection();
                    int statusCode = httpURLConnection.getResponseCode();
                    if (statusCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                        StringBuilder stringBuilder = new StringBuilder();
                        String line = bufferedReader.readLine();
                        while (line != null) {
                            stringBuilder.append(line);
                            line = bufferedReader.readLine();
                        }
                        return stringBuilder.toString();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                if (!s.isEmpty() && s.length() > 2) {
                    intent = new Intent();
                    intent.putExtra(Constants.LOCATION_OBJECT, location);
                    setResult(Constants.LOCATION_REQUEST_CODE, intent);
                    finish();
                } else {
                    Constants.ToastMessages(AddCityActivity.this, Constants.TOAST_ENTER_PROPER_CITY);
                }
            } else {
                Constants.ToastMessages(AddCityActivity.this, Constants.TOAST_ENTER_PROPER_CITY);
            }
        }
    }
}
