package com.example.roy.newapp;

/**
 * Created by Roy on 06/01/2017.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


/*this is the class which representing the quote*/
public class QuoteMachine {
    private Quote myq;
    private static Quote temp=null;


    public QuoteMachine() {
        myq=null;
        ;

    }

    public Quote getQoute() throws Exception {
        initquoate();
        myq=temp;
        return myq;
    }



    private void initquoate() throws Exception {
        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost();
        request.setURI(new URI("https://andruxnet-random-famous-quotes.p.mashape.com/?cat=famous"));
        request.addHeader("X-Mashape-Key", "QRjYQMvC4BmshfyofqNlnYpzY4BCp15Hw1ajsnY2tDWq2v5XS2");
        request.addHeader("Content-Type", "application/x-www-form-urlencoded");
        request.addHeader("Accept", "application/json");
        HttpResponse response = client.execute(request);
        responseProcess(response);

    }

    private static void responseProcess(HttpResponse response) throws UnsupportedOperationException, IOException {

        BufferedReader inBuff=null;
        String s=null;

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        Gson gson = new Gson();
        temp=gson.fromJson(result.toString(), Quote.class);



    }
}










