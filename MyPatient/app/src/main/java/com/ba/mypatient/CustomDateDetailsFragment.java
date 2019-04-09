package com.ba.mypatient;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CustomDateDetailsFragment extends Fragment {

    private String name;
    private String time;

    public CustomDateDetailsFragment() {
        // Required empty public constructor

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name = getArguments().getString("name");
        time = getArguments().getString("time");
        view.setBackgroundColor(Color.WHITE);
        ((TextView)view.findViewById(R.id.tv_name)).setText(name);
        ((TextView)view.findViewById(R.id.tv_time)).setText(time);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_custom_date_details, container, false);
    }

}
