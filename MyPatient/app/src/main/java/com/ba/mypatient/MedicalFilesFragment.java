package com.ba.mypatient;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MedicalFilesFragment extends Fragment {


    public MedicalFilesFragment() {
        // Required empty public constructor
    }
    String[] gridViewString = {
            "قلبية","الأسنان","الكلى","فحوصات عامة",

    } ;
    int[] gridViewImageId = {
            R.drawable.ic_image_black_24dp, R.drawable.next_icon, R.drawable.next_icon, R.drawable.next_icon, R.drawable.next_icon, R.drawable.next_icon,
            R.drawable.next_icon, R.drawable.next_icon, R.drawable.next_icon, R.drawable.next_icon, R.drawable.next_icon, R.drawable.next_icon,
            R.drawable.next_icon, R.drawable.next_icon, R.drawable.next_icon, R.drawable.next_icon, R.drawable.next_icon, R.drawable.next_icon,
            R.drawable.next_icon, R.drawable.next_icon, R.drawable.next_icon, R.drawable.next_icon, R.drawable.next_icon, R.drawable.next_icon,

    };
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GridView grid = (GridView)view.findViewById(R.id.gv_grid);
        grid.setAdapter(new CustomGridViewAdapter(getActivity(),gridViewString,gridViewImageId));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medical_files, container, false);
    }

}
