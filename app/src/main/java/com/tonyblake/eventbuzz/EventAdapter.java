package com.tonyblake.eventbuzz;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EventAdapter extends BaseAdapter{

    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater = null;
    public Resources res;
    Event event = null;
    int i=0;

    public EventAdapter(Activity a, ArrayList d,Resources resLocal) {

        activity = a;
        data=d;
        res = resLocal;

        inflater = ( LayoutInflater )activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {

        if(data.size()<=0) return 1;

        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder{

        public TextView event_name;
        public TextView event_start;
        public TextView event_end;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        ViewHolder holder;

        if(convertView==null){

            v = inflater.inflate(R.layout.event_item_layout, null);

            holder = new ViewHolder();
            holder.event_name = (TextView) v.findViewById(R.id.event_name);
            holder.event_start=(TextView)v.findViewById(R.id.event_start);
            holder.event_end=(TextView)v.findViewById(R.id.event_end);

            v.setTag( holder );
        }
        else
            holder=(ViewHolder)v.getTag();

        if(data.size()<=0)
        {
            holder.event_name.setText("No Data");
            holder.event_start.setText("No Data");
            holder.event_end.setText("No Data");
        }
        else
        {
            event = ( Event ) data.get( position );

            holder.event_name.setText(event.name);
            holder.event_start.setText(event.start);
            holder.event_end.setText(event.end);

        }
        return v;
    }
}