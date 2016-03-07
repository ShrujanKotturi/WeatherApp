package com.example.shruj.weatherapp;

import java.io.Serializable;

/**
 * Created by shruj on 03/03/2016.
 */
public class Forecast implements Serializable {
    String time;
    String temperature;
    String dewpoint;
    String clouds;
    String iconUrl;
    String windSpeed;
    String windDirection;
    String climateType;
    String humidity;
    String feelsLike;
    String maximumTemp;
    String minimumTemp;
    String pressure;
    String error;


    @Override
    public String toString() {
        return "Forecast{" +
                "time='" + time + '\'' +
                ", temperature='" + temperature + '\'' +
                ", dewpoint='" + dewpoint + '\'' +
                ", clouds='" + clouds + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", windDirection='" + windDirection + '\'' +
                ", climateType='" + climateType + '\'' +
                ", humidity='" + humidity + '\'' +
                ", feelsLike='" + feelsLike + '\'' +
                ", maximumTemp='" + maximumTemp + '\'' +
                ", minimumTemp='" + minimumTemp + '\'' +
                ", pressure='" + pressure + '\'' +
                ", error='" + error + '\'' +
                ", windAngle='" + windAngle + '\'' +
                '}';
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getWindAngle() {
        return windAngle;
    }

    public void setWindAngle(String windAngle) {
        this.windAngle = windAngle;
    }

    String windAngle;

    public String getTime() {

        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getDewpoint() {
        return dewpoint;
    }

    public void setDewpoint(String dewpoint) {
        this.dewpoint = dewpoint;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getClimateType() {
        return climateType;
    }

    public void setClimateType(String climateType) {
        this.climateType = climateType;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }


    public String formatTime() {

        return time.substring(0, 8);
//        try {
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:m a z MMMM d, yyyy");
//            Date date = simpleDateFormat.parse(time.replace(Constants.ON, Constants.SPACE));
//
//            simpleDateFormat = new SimpleDateFormat("h:m a");
//            return simpleDateFormat.format(date);
//        } catch (ParseException e) {
//
//            e.printStackTrace();
//        }
//        return  null;
    }
}
