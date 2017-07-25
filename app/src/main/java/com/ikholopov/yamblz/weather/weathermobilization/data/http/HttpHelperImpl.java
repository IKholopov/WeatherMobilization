package com.ikholopov.yamblz.weather.weathermobilization.data.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by turist on 25.07.2017.
 */

public class HttpHelperImpl implements HttpHelper {

    @Override
    public String get(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setUseCaches(false);
        urlConnection.connect();

        InputStream inputStream = urlConnection.getInputStream();
        StringBuilder builder = new StringBuilder();
        if(inputStream == null) {
            return null;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while((line = reader.readLine()) != null) {
            builder.append(line);
        }

        if(builder.length() == 0) {
            return null;
        }

        return builder.toString();
    }
}
