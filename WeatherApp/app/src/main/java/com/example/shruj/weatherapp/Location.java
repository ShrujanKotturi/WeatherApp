package com.example.shruj.weatherapp;

import java.io.Serializable;

/**
 * Created by shruj on 03/02/2016.
 */
public class Location implements Serializable {
    String cityName, stateCode;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    @Override
    public String toString() {
        return this.cityName + ", " + this.stateCode;
    }

    public static Location formatter(Location location) {
        if (location.getCityName().contains(Constants.SPACE)) {
            location.setCityName(location.getCityName().replaceAll(Constants.SPACE, Constants.UNDERSCORE));
        }
        return location;
    }

    public static String currentLocationFormatter(Location location) {
        return location.getCityName() + Constants.COMMA + location.getStateCode();
    }

}
