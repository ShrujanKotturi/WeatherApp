package com.example.shruj.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by shruj on 03/05/2016.
 */
public class WeatherAdapter extends ArrayAdapter<Forecast> {
    List<Forecast> mData;
    Context mContext;
    int mResource;

    public WeatherAdapter(Context context, int resource, List<Forecast> objects) {
        super(context, resource, objects);
        mData = objects;
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);

            holder = new ViewHolder();
            holder.textViewTime = (TextView) convertView.findViewById(R.id.textViewTime);
            holder.textViewCondition = (TextView) convertView.findViewById(R.id.textViewCondition);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.textViewTemperature = (TextView) convertView.findViewById(R.id.textViewTemperature);
            convertView.setTag(holder);
        }

        Forecast forecast = mData.get(position);

        holder = (ViewHolder) convertView.getTag();
        TextView textViewTime = holder.textViewTime;
        TextView textViewCondition = holder.textViewCondition;
        TextView textViewTemperature = holder.textViewTemperature;
        ImageView imageView = holder.imageView;

        textViewTime.setText(forecast.formatTime());
        textViewTemperature.setText(forecast.getTemperature() + Constants.FAHRENHEIT);
        textViewCondition.setText(forecast.getClouds());

        if (forecast.getIconUrl() != null) {
            Picasso.with(mContext)
                    .load(forecast.getIconUrl())
                    .fit()
                    .centerInside()
                    .into(imageView);
        }
        return convertView;

    }

    static class ViewHolder {
        TextView textViewTime;
        TextView textViewCondition;
        ImageView imageView;
        TextView textViewTemperature;
    }
}
