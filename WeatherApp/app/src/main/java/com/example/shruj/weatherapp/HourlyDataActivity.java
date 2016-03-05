package com.example.shruj.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class HourlyDataActivity extends AppCompatActivity {

    Intent intent;
    Location locationDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_data);

        intent = getIntent();
        if (intent.getExtras() != null) {
            locationDetails = (Location) intent.getExtras().getSerializable(Constants.INTENT_INFO_LOCATION);
            locationDetails = Location.formatter(locationDetails);
            new GetWeatherAsyncTask(this).execute(locationDetails);
        }
    }
}
