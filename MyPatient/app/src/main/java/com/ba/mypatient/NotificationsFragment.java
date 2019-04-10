package com.ba.mypatient;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsFragment extends Fragment {


    public NotificationsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(Color.WHITE);
        List<String> date = readFromFile(getContext(), "dates.txt");
        if(date.size()>0) {
            MyCustomDate vi = (MyCustomDate) LayoutInflater.from(getContext()).inflate(R.layout.custom_date, null);
            //vi.tv_name.setText("");
            vi.setDateText(date.get(0) + " " + date.get(1));
            LinearLayout list = (LinearLayout) view.findViewById(R.id.list);
            list.addView(vi);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    private void writeToFile(Context context, String fileName, String data) {


//        Log.e("Root path",Environment.getDataDirectory().toString());
//        File directory = new File(Environment.getRootDirectory()+"/myfiles");
//        directory.mkdirs();
//        File myfile = new File(Environment.getRootDirectory()+"/myfiles/"+fileName);
//        try {
//            myfile.createNewFile();
//        }catch (Exception e){
//
//        }
        String saveFolder = "/storage";
        File directory = new File(saveFolder + "/myfiles");
        if (directory.mkdirs()) {
            Log.e(TAG, "writeToFile:create folder");
        }

        String path_file = saveFolder + "/myfiles/" + fileName;
        File myfile = new File(path_file);

        try {
            myfile.createNewFile();
            Log.e("File", "created");
            Writer out = new FileWriter(path_file);
            BufferedWriter br = new BufferedWriter(out);
            br.write(data + "\n");
            br.close();

        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private List<String> readFromFile(Context context, String fileName) {

        List<String> stringBuilder = new ArrayList<String>();
        String saveFolder =Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();

//        File directory = new File(saveFolder + "/myfiles");
//        if(directory.mkdirs()){
//            Log.e(TAG, "writeToFile:create folder" );;
//        }
        String path_file = saveFolder + "/myfiles/" + fileName;
        File myfile = new File(path_file);

        try {
            //InputStream inputStream = context.openFileInput(fileName);
            Reader inputStreamReader = new FileReader(path_file);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String receiveString = "";

            if (inputStreamReader != null) {


                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.add(receiveString);
                }

                inputStreamReader.close();
                //ret = stringBuilder.toString();
                Toast.makeText(context,"Done!!!",Toast.LENGTH_SHORT);
            }
        } catch (FileNotFoundException e) {
            Log.e("activity", "File not found: " + e.toString());
            Toast.makeText(context,"caN't make it:" + e.toString(),Toast.LENGTH_SHORT);
        } catch (IOException e) {
            Log.e("activity", "Can not read file: " + e.toString());
            Toast.makeText(context,"caN't make it:" + e.toString(),Toast.LENGTH_SHORT);
        }

        return stringBuilder;
    }

}
