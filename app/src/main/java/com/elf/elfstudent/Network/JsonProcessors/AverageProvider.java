package com.elf.elfstudent.Network.JsonProcessors;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.elf.elfstudent.Fragments.New_Fragments.PerFormanceFragment;
import com.elf.elfstudent.model.Topic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nandhu on 12/12/16.
 *
 */
public class AverageProvider implements Response.Listener<JSONArray> {

    private static final String TAG = "Average Provider";
    List<Topic> averageList = null;
    List<Topic> goodList = null;
    List<Topic> badList = null;
        private avgCallback mCallback = null;

    public AverageProvider(Context context) {
        this.mCallback  = (avgCallback) context;
    }




    @Override
    public void onResponse(JSONArray response) {

        Log.d("reposme", "onResponse: "+response.toString());


        try {
            JSONArray average = null;
            JSONArray good = null;
            JSONArray bad = null;

            JSONObject mObject = response.getJSONObject(0);
            if (mObject == null) {
                    mCallback.noProvider();
            } else {

                //Resposne Exists


                //First Try to get Average
                try {
                    average  = mObject.getJSONArray("Average");

                }
                catch (Exception  e){
                    //there is exception here , average is likely to be object here
                    String averageObject = mObject.getString("Average");
                    averageList = new ArrayList<>(1);
                    averageList.add(new Topic("Write More Test To improve your Performance"));
                }
            if (average != null){
                Log.d(TAG, "onResponse: after if");

                averageList = new ArrayList<>(average.length());
                for (int i = 0; i < average.length(); i++) {
                    JSONObject obj = average.getJSONObject(i);
                    averageList.add(new Topic(obj.getString("Topic")));
                }
            }
                else{

                averageList = new ArrayList<>(1);
            }


                //Parse Good similartly
                try {
                    good  = mObject.getJSONArray("Good");

                }
                catch (Exception  e){
                    //there is exception here , average is likely to be object here
                    String goodobject = mObject.getString("Average");
                    goodList = new ArrayList<>(1);
                    goodList.add(new Topic("Write More Test To improve your Performance"));
                }

                if (good != null  ){

                    goodList = new ArrayList<>(good.length());
                    for (int i = 0; i < good.length(); i++) {
                        JSONObject obj = good.getJSONObject(i);
                        goodList.add(new Topic(obj.getString("Topic")));
                    }
                }
                else{
                    String goodobject = mObject.getString("Good");
                    goodList = new ArrayList<>(1);
                    goodList.add(new Topic("Write More Test To improve your Performance"));
                }


                //Parse For Bad..
                try {
                    bad  = mObject.getJSONArray("Bad");

                }
                catch (Exception  e){
                    //there is exception here , average is likely to be object here
                    String badObj = mObject.getString("Bad");
                    goodList = new ArrayList<>(1);
                    goodList.add(new Topic("Write More Test To improve your Performance"));
                }

                if (bad != null){

                    badList = new ArrayList<>(bad.length());

                    for (int i = 0; i < bad.length(); i++) {
                        JSONObject obj = bad.getJSONObject(i);
                        badList.add(new Topic(obj.getString("Topic")));
                    }
                }


                mCallback.avgProvider(averageList, goodList, badList);
            }
        }catch (JSONException e) {
            Log.d("Average", "onResponse: "+e.getLocalizedMessage());
            mCallback.noProvider();
        }
    }


    public interface avgCallback{
        void avgProvider(List<Topic> average, List<Topic> good, List<Topic> bad);
        void noProvider();

    }

}
