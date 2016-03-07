package com.example.shruj.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

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
                    location.setCityName(editTextCityName.getText().toString());
                    location.setStateCode(editTextStateCode.getText().toString());
                    intent = new Intent();
                    intent.putExtra(Constants.LOCATION_OBJECT, location);
                    setResult(Constants.LOCATION_REQUEST_CODE, intent);
                    finish();
                } else if (editTextCityName.getText().toString().isEmpty()) {
                    editTextCityName.setError(Constants.TOAST_MANDATORY_FIELD);
                } else if (editTextStateCode.getText().toString().isEmpty()) {
                    editTextStateCode.setError(Constants.TOAST_MANDATORY_FIELD);
                }
            }
        });
    }
}
