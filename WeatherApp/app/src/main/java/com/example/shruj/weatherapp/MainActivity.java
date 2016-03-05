package com.example.shruj.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<Location> locationArrayList;
    TextView textViewMessage;
    ListView listView;
    Intent intent;
    ArrayAdapter<Location> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewMessage = (TextView) findViewById(R.id.textViewMessage);
        listView = (ListView) findViewById(R.id.listViewCities);

        if (locationArrayList != null) {
            textViewMessage.setVisibility(View.INVISIBLE);
            listView.setVisibility(View.VISIBLE);
        } else {
            textViewMessage.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        }

        //remove these
        setDefaultLocation();
        setListView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_favorite:
                intent = new Intent(MainActivity.this, AddCityActivity.class);
                startActivityForResult(intent, Constants.LOCATION_REQUEST_CODE);
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.LOCATION_REQUEST_CODE) {
            if (locationArrayList == null) {
                locationArrayList = new ArrayList<>();
            }
            locationArrayList.add((Location) data.getSerializableExtra(Constants.LOCATION_OBJECT));
            setListView();
        }
    }

    private void setListView() {
        arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, locationArrayList);
        listView.setAdapter(arrayAdapter);
        arrayAdapter.setNotifyOnChange(Boolean.TRUE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(MainActivity.this, HourlyDataActivity.class);
                intent.putExtra(Constants.INTENT_INFO_LOCATION, locationArrayList.get(position));
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                locationArrayList.remove(position);
                arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, locationArrayList);
                listView.setAdapter(arrayAdapter);
                arrayAdapter.setNotifyOnChange(Boolean.TRUE);
                return true;
            }
        });
        listView.setVisibility(View.VISIBLE);
        textViewMessage.setVisibility(View.INVISIBLE);
    }

    public void setDefaultLocation() {
        Location lo = new Location();
        lo.setCityName("Charlotte");
        lo.setStateCode("NC");
        if (locationArrayList == null) {
            locationArrayList = new ArrayList<>();
        }
        locationArrayList.add(lo);
    }
}
