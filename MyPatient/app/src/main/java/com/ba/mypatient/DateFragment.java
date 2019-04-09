package com.ba.mypatient;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class DateFragment extends Fragment {


    public DateFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        DialogFragment dialogFragment = new MyDialogFragment();
//
//        dialogFragment.show(getActivity().getSupportFragmentManager(),"dialog");
        final CalendarView cv = (CalendarView) view.findViewById(R.id.cv_fragment_date);
        final View v = view;
        view.setBackgroundColor(Color.WHITE);
        ((ImageButton)(view.findViewById(R.id.img_btn_back))).
                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        view.setVisibility(View.INVISIBLE);
                        cv.setVisibility(View.VISIBLE);
                        ((LinearLayout)v.findViewById(R.id.date_fragment_container)).removeAllViews();
                    }
                });
        cv.setEventHandler(new CalendarView.EventHandler()
        {
            @Override
            public void onDayLongPress(Date date)
            {
                // show returned day
                cv.setVisibility(View.GONE);
                loadfragment(new ListDatesOfDayFragment());

                ((ImageButton)(v.findViewById(R.id.img_btn_back))).setVisibility(View.VISIBLE);

                DateFormat df = SimpleDateFormat.getDateInstance();
                Toast.makeText(getContext(), df.format(date), Toast.LENGTH_SHORT).show();
//                dismiss();
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_date, container, false);
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
