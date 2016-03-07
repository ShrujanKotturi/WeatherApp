package com.example.shruj.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class HourlyDataActivity extends AppCompatActivity {

    Intent intent;
    Location locationDetails;
    String currentLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_data);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(Boolean.TRUE);
        actionBar.setIcon(R.drawable.ic_launcher);



        intent = getIntent();
        if (intent.getExtras() != null) {
            locationDetails = (Location) intent.getExtras().getSerializable(Constants.INTENT_INFO_LOCATION);
            currentLocation = Location.currentLocationFormatter(locationDetails);

            new GetWeatherAsyncTask(this, currentLocation).execute(locationDetails);
        }
    }
}
