package com.teste.provastudiosol;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class HttpConnection extends AsyncTask<String, Void, String > {
    private static final String urlString = "https://us-central1-ss-devops.cloudfunctions.net/rand?min=1&max=300";

    protected String doInBackground(String... urls) {
        URL url = null;
        try {
            url = new URL( "https://us-central1-ss-devops.cloudfunctions.net/rand?min=1&max=300");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return  null;
        }
        HttpURLConnection httpConn = null;
        try {
            httpConn =  (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }
        try {
            httpConn.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
            return  null;
        }
        InputStream inputStream = null;
        try {

            inputStream = httpConn.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = null;
        try {
            line = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }
        finally {
            httpConn.disconnect();
        }
        return line;
    }

}

