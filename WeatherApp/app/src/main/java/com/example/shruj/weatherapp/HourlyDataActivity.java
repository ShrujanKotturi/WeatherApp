package com.example.shruj.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class HourlyDataActivity extends AppCompatActivity {

    Intent intent;
    Location locationDetails;
    String currentLocation;
    TextView textViewLocationValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_data);

        textViewLocationValue = (TextView) findViewById(R.id.textViewLocationValue);

        intent = getIntent();
        if (intent.getExtras() != null) {
            locationDetails = (Location) intent.getExtras().getSerializable(Constants.INTENT_INFO_LOCATION);
            currentLocation = Location.currentLocationFormatter(locationDetails);
            textViewLocationValue.setText(currentLocation);
            new GetWeatherAsyncTask(this, currentLocation).execute(locationDetails);
        }
    }
}
