package com.example.shruj.weatherapp;

import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;

/**
 * Created by shruj on 03/02/2016.
 */
public class RequestParam {
    String baseUrl;
    String method;
    LinkedHashMap<String, String> params = new LinkedHashMap<>();

    public RequestParam(String method, String baseUrl) {
        super();
        this.method = method;
        this.baseUrl = baseUrl;
    }

    public void addParam(String key, String value) {
        params.put(key, value);
    }

    public String getEncodedParams() {
        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet()) {
            String value = params.get(key);
            if (sb.length() > 0) {
                sb.append("/");
            }
            sb.append(value);
        }
        sb.append(Constants.XML_EXTENSION);
        return sb.toString();
    }

    public String getEncodedParamsForStateCityValidation() {
        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet()) {
            String value = params.get(key);
            if (sb.length() > 0) {
                sb.append("/");
            }
            sb.append(value);
        }
        sb.append(Constants.JSON_EXTENSION);
        return sb.toString();
    }


    public String getEncodedUrl() {
        Log.d("demo", this.baseUrl + getEncodedParams());
        return this.baseUrl + getEncodedParams();
    }

    private String getEncodedCityStateUrl() {
        Log.d("demo", this.baseUrl + getEncodedParamsForStateCityValidation());
        return this.baseUrl + getEncodedParamsForStateCityValidation();
    }

    public HttpURLConnection setUpConnection() throws IOException {
        if (method.equals(Constants.GET_METHOD)) {
            URL url = new URL(getEncodedUrl());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(Constants.GET_METHOD);
            return httpURLConnection;
        } else {
            URL url = new URL(getEncodedCityStateUrl());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(Constants.GET_METHOD);
            return httpURLConnection;
        }
    }

}
