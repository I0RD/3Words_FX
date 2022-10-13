package main.java.pl.programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Connection {
    private URL url;

    public Connection(String url) throws MalformedURLException {
            this.url=new URL(url);
    }
    public String returnConnection() throws IOException {
        HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        if(httpURLConnection.getResponseCode()!=200){
            throw new RuntimeException("Failed : HTTP error code : " + httpURLConnection.getResponseCode());
        }
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        return bufferedReader.readLine();
    }
}