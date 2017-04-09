package com.myhealth.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myhealth.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class Dashboard extends Fragment {
    private String dailyActivityJson;
    private String getWeightJson;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_dashboard, container, false);
        dailyActivityJson=loadJSON("getDailyActivity.json");
        getWeightJson=loadJSON("getWeight.json");
        return v;
    }

    private String loadJSON(String filename)
    {
        String temp="";
        try{
            InputStream inputStream = getActivity().getAssets().open(filename);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
             temp= new String(buffer,"UTF-8");

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return temp;
    }

}
