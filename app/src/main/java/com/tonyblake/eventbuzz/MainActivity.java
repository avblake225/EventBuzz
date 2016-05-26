package com.tonyblake.eventbuzz;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private Context context;

    private MainActivity mainActivity;

    private Toolbar actionBar;

    private DrawerLayout dLayout;
    private ListView dList;
    private ArrayAdapter<String> drawerAdapter;

    private ListView list;
    //private EventAdapter adapter;

    //private ArrayList<Event> eventsToDisplay;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Show Status Bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_VISIBLE;
        decorView.setSystemUiVisibility(uiOptions);

        context = this;

        // Set up Action Bar
        actionBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(actionBar);
        actionBar.setNavigationIcon(context.getResources().getDrawable(R.drawable.ic_menu_white_24dp));
        actionBar.setTitle(context.getString(R.string.app_name));
        actionBar.setTitleTextColor(context.getResources().getColor(R.color.white));

        // Set up Navigation Drawer
        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        dList = (ListView) findViewById(R.id.left_drawer);
        drawerAdapter = new ArrayAdapter<String>(this,R.layout.drawer_item_layout,context.getResources().getStringArray(R.array.menu_items));
        dList.setAdapter(drawerAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        actionBar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dLayout.openDrawer(dList);
            }
        });

        dList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FragmentManager fm = getSupportFragmentManager();

                switch (position) {

                    // Get All Events
                    case 0:

                        dLayout.closeDrawer(dList);

                        // getAllEvents();

                        break;

                    // Add Event
                    case 1:

                        dLayout.closeDrawer(dList);

                        //addEventDialog = new AddEventDialog();
                        //addEventDialog.show(fm, "add_event_dialog_fragment");

                        break;

                    // Delete Event
                    case 2:

                        dLayout.closeDrawer(dList);

                        //deleteEventDialog = new DeleteEventDialog();
                        //deleteEventDialog.show(fm, "add_event_dialog_fragment");

                        break;
                }
            }
        });
    }

    private void getAllEvents(){

//        eventsToDisplay = new ArrayList<>();
//
//        new GetEventsTask(context) {
//
//            @Override
//            protected void onPreExecute() {
//
//                progressDialog = new ProgressDialog(context);
//                progressDialog.setMessage(context.getString(R.string.getting_events));
//                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                progressDialog.setIndeterminate(true);
//                progressDialog.show();
//            }
//
//            @Override
//            protected void onPostExecute(ArrayList<Event> events_returned) {
//
//                if (events_returned != null) {
//
//                    eventsToDisplay = events_returned;
//
//                    progressDialog.dismiss();
//
//                    displayEvents();
//
//                }
//            }
//
//        }.execute();
    }

    private void displayEvents(){

        Resources res = getResources();

        list= ( ListView )findViewById(R.id.event_list);

//        adapter = new EventAdapter( mainActivity, eventsToDisplay, res);
//
//        list.setAdapter(adapter);
    }

    private void showToastMessage(CharSequence text) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

//    @Override
//    public void onAddEventDialogAddClick(DialogFragment dialog) {
//
//        showToastMessage(context.getString(R.string.event_added));
//    }
//
//    @Override
//    public void onDeleteEventDialogDeleteClick(DialogFragment dialog) {
//
//        showToastMessage(context.getString(R.string.event_deleted));
//    }
}
