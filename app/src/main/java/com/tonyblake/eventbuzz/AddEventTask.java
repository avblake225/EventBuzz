package com.tonyblake.eventbuzz;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AddEventTask extends AsyncTask<String, Void, Void> {

    private Context context;

    private URL url;

    private HttpURLConnection connection;

    private DataOutputStream dStream;

    private String urlParameters;

    public AddEventTask(Context context){

        this.context = context;

        try {

            url = new URL("http://shrouded-woodland-9458.herokuapp.com/events");
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {

            connection = (HttpURLConnection)url.openConnection();

            connection.setRequestMethod("POST");

            connection.setDoOutput(true);

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {

            dStream = new DataOutputStream(connection.getOutputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Void doInBackground(String... params) {

        urlParameters = "fizz=buzz";

        try {

            dStream.writeBytes(urlParameters);

            showToastMessage(context.getString(R.string.event_added));

            dStream.flush();

            dStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void showToastMessage(CharSequence text) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
