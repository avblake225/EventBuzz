package com.tonyblake.eventbuzz;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class DeleteEventTask extends AsyncTask<String, Void, String>{

    private Context context;

    public DeleteEventTask(Context context) {

        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        String result = "";

        URL url = null;

        String charset = "UTF-8";
        String name = params[0];
        String start = params[1];
        String end = params[2];

        String query = "";

        try {
            query = String.format("param1=%s&param2=%s",
                    URLEncoder.encode(name, charset),
                    URLEncoder.encode(start, charset),
                    URLEncoder.encode(end, charset));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {

            url = new URL(context.getString(R.string.url_name));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpURLConnection conn = null;

        try {

            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("DELETE");

            conn.setRequestProperty("Accept-Charset", "UTF-8");

            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);

            conn.connect();

        } catch (IOException e) {
            e.printStackTrace();

            showToastMessage(context.getString(R.string.server_cant_be_reached));
        }

        OutputStream os;

        try {

            os = conn.getOutputStream();

            os.write(query.getBytes(charset));

            result = "success";

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private void showToastMessage(CharSequence text) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
