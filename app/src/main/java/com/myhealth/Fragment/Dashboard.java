package com.myhealth.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myhealth.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class Dashboard extends Fragment {

    private String dailyActivityJson;
    private String getWeightJson;
    private TextView weight;
    private TextView step;
    private TextView goalWeight;
    private TextView goalStep;
    private ArrayList<HashMap<String,String>> weightJson = new ArrayList<>();
    private ArrayList<HashMap<String,String >> dailyActivityList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_dashboard, container, false);
        weight=(TextView)v.findViewById(R.id.weight);
        step=(TextView)v.findViewById(R.id.step);
        goalWeight=(TextView)v.findViewById(R.id.goal_weight);
        goalStep=(TextView)v.findViewById(R.id.goal_step);
        dailyActivityJson=loadJSON("getDailyActivity.json");
        getWeightJson=loadJSON("getWeight.json");
        try{
            JSONObject weight = new JSONObject(getWeightJson);
            JSONArray weightArray = weight.getJSONArray("weight");
            HashMap<String,String> map = new HashMap<>();
            for(int i=0;i<weightArray.length();i++)
            {
                JSONObject inside = weightArray.getJSONObject(i);
                String wt = inside.getString("weight");
                map.put("weight",wt);
                weightJson.add(map);
            }
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
        try{
            JSONObject object = new JSONObject(dailyActivityJson);
            JSONArray activityArray = object.getJSONArray("activities");
            HashMap<String,String> map = new HashMap<>();
            for(int i=0;i<activityArray.length();i++)
            {
                JSONObject inside = activityArray.getJSONObject(i);
                String step = inside.getString("steps");
                map.put("steps",step);
                dailyActivityList.add(map);
            }
            JSONObject goals = object.getJSONObject("goals");
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
        weight.setText(weightJson.get(0).get("weight"));
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
