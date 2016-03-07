package com.example.shruj.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    Intent intent;
    List<Forecast> forecastList;
    int currentObjectPosition;
    TextView textViewTemperature, textViewCondition, textViewMaxTemperature, textViewMinTemperature, textViewCurrentLocation,
            textViewFeelsLike, textViewHumidity, textViewDewpoint, textViewPressure, textViewClouds, textViewWinds;
    ImageView imageView;
    Forecast currentForecast;
    float maxTemp = 0;
    float minTemp = 0;
    String currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        intent = getIntent();

        if (intent.getExtras() != null) {
            forecastList = (List<Forecast>) intent.getExtras().getSerializable(Constants.LIST_OBJECT);
            currentObjectPosition = intent.getExtras().getInt(Constants.CURRENT_OBJECT);
            currentLocation = intent.getExtras().getString(Constants.CURRENT_LOCATION);
        }

        textViewCurrentLocation = (TextView) findViewById(R.id.textViewCV);
        textViewTemperature = (TextView) findViewById(R.id.textViewTemperature);
        textViewCondition = (TextView) findViewById(R.id.textViewCondition);
        textViewMaxTemperature = (TextView) findViewById(R.id.textViewMaxTemperatureValue);
        textViewMinTemperature = (TextView) findViewById(R.id.textViewMinTemperatureValue);
        textViewFeelsLike = (TextView) findViewById(R.id.textViewFeelsLikeValue);
        textViewHumidity = (TextView) findViewById(R.id.textViewHumidityValue);
        textViewDewpoint = (TextView) findViewById(R.id.textViewDewpointValue);
        textViewPressure = (TextView) findViewById(R.id.textViewPressureValue);
        textViewClouds = (TextView) findViewById(R.id.textViewCloudsValue);
        textViewWinds = (TextView) findViewById(R.id.textViewWindsValue);

        imageView = (ImageView) findViewById(R.id.imageViewClimate);


        findViewById(R.id.imageViewLeft).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentObjectPosition--;
                setUI();
            }
        });
        findViewById(R.id.imageViewRight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentObjectPosition++;
                setUI();
            }
        });

        setUI();
    }

    private void setUI() {
        if (!forecastList.isEmpty()) {
            if (currentObjectPosition + 1 > forecastList.size()) {
                currentObjectPosition = 0;
            } else if (currentObjectPosition < 0) {
                currentObjectPosition = forecastList.size() - 1;
            }
            currentForecast = forecastList.get(currentObjectPosition);
            MaxMinTemperature();

            textViewCurrentLocation.setText(currentLocation + " (" + currentForecast.formatTime() + ")");
            textViewMinTemperature.setText(minTemp + Constants.FAHRENHEIT_REPRESENTATION);
            textViewMaxTemperature.setText(maxTemp + Constants.FAHRENHEIT_REPRESENTATION);
            textViewTemperature.setText(currentForecast.getTemperature() + Constants.FAHRENHEIT);
            textViewCondition.setText(currentForecast.getClimateType());
            textViewFeelsLike.setText(currentForecast.getFeelsLike() + Constants.FAHRENHEIT_REPRESENTATION);
            textViewHumidity.setText(currentForecast.getHumidity() + Constants.HUMIDITY_PRECENTAGE);
            textViewDewpoint.setText(currentForecast.getDewpoint() + Constants.FAHRENHEIT_REPRESENTATION);
            textViewPressure.setText(currentForecast.getPressure() + Constants.PRESSURE_REPRESENTATION);
            textViewClouds.setText(currentForecast.getClouds());
            textViewWinds.setText(currentForecast.getWindSpeed() + Constants.WINDS_SCALE + currentForecast.getWindAngle() + Constants.DEGREE + currentForecast.getWindDirection());
            if (currentForecast.getIconUrl() != null) {
                Picasso.with(DetailsActivity.this)
                        .load(currentForecast.getIconUrl())
                        .into(imageView);
            }

        }
    }

    private void MaxMinTemperature() {
        if (!forecastList.isEmpty()) {
            minTemp = Float.parseFloat(forecastList.get(0).getTemperature());
            for (Forecast forecast : forecastList) {
                float currentTemp = Float.parseFloat(forecast.getTemperature());
                if (maxTemp <= currentTemp) {
                    maxTemp = currentTemp;
                }
                if (minTemp >= currentTemp) {
                    minTemp = currentTemp;
                }
            }

        }
    }


}
