package com.example.shruj.weatherapp;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by shruj on 03/02/2016.
 */
public class RequestParam {
    String baseUrl;
    String method;
    HashMap<String, String> params = new HashMap<>();

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

    public String getEncodedUrl() {
        return this.baseUrl + getEncodedParams();
    }

    public HttpURLConnection setUpConnection() throws IOException {
        if (method.equals(Constants.GET_METHOD)) {
            URL url = new URL(getEncodedUrl());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(Constants.GET_METHOD);
            return httpURLConnection;
        } else {
            URL url = new URL(this.baseUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(Constants.POST_METHOD);
            httpURLConnection.setDoOutput(true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
            outputStreamWriter.write(getEncodedParams());
            outputStreamWriter.flush();
            return httpURLConnection;
        }
    }

}
