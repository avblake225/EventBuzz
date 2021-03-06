package com.tonyblake.eventbuzz;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class AddEventDialog extends DialogFragment{

    private Context context;

    private EditText et_name;
    private EditText et_start;
    private EditText et_end;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        context = getActivity();

        et_name = new EditText(context);
        et_name.setHint(context.getString(R.string.name));

        et_start = new EditText(context);
        et_start.setHint(context.getString(R.string.start));

        et_end = new EditText(context);
        et_end.setHint(context.getString(R.string.end));

        LinearLayout ll=new LinearLayout(context);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(et_name);
        ll.addView(et_start);
        ll.addView(et_end);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.add_event)

                .setView(ll)

                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        String name_entered = et_name.getText().toString();
                        String start_entered = et_start.getText().toString();
                        String end_entered = et_end.getText().toString();

                        mListener.onAddEventDialogAddClick(AddEventDialog.this, name_entered, start_entered, end_entered);

                    }
                })

                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        dismiss();
                    }
                });

        final AlertDialog dialog = builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(final DialogInterface dialog) {

                Button cancelButton = ((AlertDialog)dialog).getButton(DialogInterface.BUTTON_NEGATIVE);
                Button searchButton = ((AlertDialog)dialog).getButton(DialogInterface.BUTTON_POSITIVE);

                cancelButton.setTextColor(Color.BLACK);
                searchButton.setTextColor(Color.BLACK);

                final Drawable cancelButtonBackground = getResources().getDrawable(R.drawable.background_color);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    cancelButton.setBackground(cancelButtonBackground);
                }

                final Drawable searchButtonBackground = getResources().getDrawable(R.drawable.background_color);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    searchButton.setBackground(searchButtonBackground);
                }
            }
        });

        return dialog;
    }

    public interface AddEventDialogListener {

        void onAddEventDialogAddClick(DialogFragment dialog, String name_entered, String start_entered, String end_entered);
    }

    AddEventDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (AddEventDialogListener) activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement NoticeDialogListener");
        }
    }
}