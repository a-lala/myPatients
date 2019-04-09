package com.ba.mypatient;


import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialogFragment extends DialogFragment {


    public MyDialogFragment() {
        // Required empty public constructor
    }

//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_my_dialog, container, false);
//    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        // Inflate the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.fragment_my_dialog, null);
        CalendarView cv = ((CalendarView)view.findViewById(R.id.calendar_view));
        HashSet<Date> events = new HashSet<>();

        cv.updateCalendar(events);

        // assign event handler
        cv.setEventHandler(new CalendarView.EventHandler()
        {
            @Override
            public void onDayLongPress(Date date)
            {
                // show returned day

                loadfragment(new ListDatesOfDayFragment());

                DateFormat df = SimpleDateFormat.getDateInstance();
                Toast.makeText(getContext(), df.format(date), Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        // Get your views by using view.findViewById() here and do your listeners.
        //...

        // Set the dialog layout
        builder.setView(view);

        return builder.create();
    }

    Boolean loadfragment(Fragment fragment){
        if (fragment!=null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.date_fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}

