package com.tonyblake.eventbuzz;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetEventsTask extends AsyncTask<String, Void, String>{

    private Context context;

    private String url_name;

    private BufferedReader reader;

    private String content;

    public GetEventsTask(Context context) {

        this.context = context;

        url_name = "http://shrouded-woodland-9458.herokuapp.com/events";
    }

    @Override
    protected String doInBackground(String... params) {

        URL url = null;

        try {

            url = new URL(url_name);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpURLConnection conn = null;

        try {

            conn = (HttpURLConnection) url.openConnection();

            conn.connect();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        InputStream is = null;

        try {

            is = conn.getInputStream();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        reader = null;

        try {

            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String data = "";
        content = "";

        try {

            while((data = reader.readLine()) != null){

                content += data + "\n";
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    private void showToastMessage(CharSequence text) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
