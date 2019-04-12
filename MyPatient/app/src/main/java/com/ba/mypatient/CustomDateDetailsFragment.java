package com.ba.mypatient;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name = getArguments().getString("name");
        time = getArguments().getString("time");
        view.setBackgroundColor(Color.WHITE);
        ((TextView)view.findViewById(R.id.tv_name)).setText(name);
        ((TextView)view.findViewById(R.id.tv_time)).setText(time);
        ((ImageView)view.findViewById(R.id.iv_files)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"hhhh",Toast.LENGTH_SHORT);
                loadfragment(new MedicalFilesFragment());
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_custom_date_details, container, false);
    }

//
Boolean loadfragment(Fragment fragment) {
    if (fragment != null) {
        getActivity().getFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
        return true;
    }
    return false;
}
}
