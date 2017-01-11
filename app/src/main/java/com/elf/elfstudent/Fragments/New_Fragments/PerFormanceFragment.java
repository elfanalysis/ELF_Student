package com.elf.elfstudent.Fragments.New_Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.elf.elfstudent.Activities.BrowseTestActivity;
import com.elf.elfstudent.Adapters.TopicPerfromingAdapter;
import com.elf.elfstudent.Network.AppRequestQueue;
import com.elf.elfstudent.Network.ErrorHandler;
import com.elf.elfstudent.Network.JsonProcessors.AverageProvider;
import com.elf.elfstudent.R;
import com.elf.elfstudent.Utils.BundleKey;
import com.elf.elfstudent.Utils.SubjectBigImage;
import com.elf.elfstudent.model.Topic;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ELF on 08-01-2017.
 *
 */
public class PerFormanceFragment extends Fragment  implements AverageProvider.avgCallback {

    private static final String TAG = "Performance";
    private static PerFormanceFragment mFragment;
    private Context mContext;

    @BindView(R.id.sub_image)
    ImageView mBackImage;


    @BindView(R.id.perf_root)
    FrameLayout mChangableRoot;

    private static final String GET_LESSON_URL = "http://elfanalysis.net/elf_ws.svc/GetTopicWisePerformance";
    private Object studentId;
    private AverageProvider mLessonProvider;
    private String subjectID;
    private JsonArrayRequest mLessonListRequestor;
    private AppRequestQueue mRequestQueue;
    private ErrorHandler errorHandler;


    List<Topic> averageList = null;
    List<Topic> goodList = null;
    List<Topic> badList = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.perf_frag,container,false);
        ButterKnife.bind(this,v);


         mRequestQueue = AppRequestQueue.getInstance(mContext.getApplicationContext());


//        setLessonList(getLessonList());

        if (getArguments() != null){


            //Get the Percentage & Subject iD
//            String percent = getArguments().getString(BundleKey.PERCENTAGE);
            subjectID = getArguments().getString(BundleKey.SUBJECT_ID);

            //set The Background image
            Picasso.with(mContext).load(SubjectBigImage.getBIgSubjectImage(subjectID))
                    .into(mBackImage);


            //Get The Student Id
            studentId = getArguments().getString(BundleKey.ARG_STUDENT_ID);


            //Get The Transitions Name
//            String percent_trans_name  = getArguments().getString(BundleKey.HOME_PERCENT_TRANS_NAME);
//            String img_transName = getArguments().getString(BundleKey.HOME_SUBJECT_IMAGE_TRANS_NAME);

            //set the Transition Names
//            ViewCompat.setTransitionName(mPercentage,percent_trans_name);
//            ViewCompat.setTransitionName(mSubjectViewImage,img_transName);

            //Set the View values
//            if (percent != null){
//                mPercentage.setText(percent);
//            }





//            mSubjectId = getIntent().getStringExtra(BundleKey.SUBJECT_ID);
        }
        else{
            throw new NullPointerException("Intent Cannot be null");
        }

        if(subjectID != null && studentId != null){
            getLessonListFromServer();
        }
        else{
            throw  new NullPointerException("Subject ID cannot be null");
        }




    return v;
    }



    private void getLessonListFromServer(){


        //Networks Related Code
        try {
            JSONObject mObject = new JSONObject();
            mObject.put("StudentId", studentId);
            mObject.put("SubjectId", subjectID);
            mLessonListRequestor = new JsonArrayRequest(Request.Method.POST,
                    GET_LESSON_URL, mObject, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONArray average = null;
                        JSONArray good = null;
                        JSONArray bad = null;

                        JSONObject mObject = response.getJSONObject(0);
                        if (mObject == null) {
                            noProvider();
                        } else {

                            //Resposne Exists


                            //First Try to get Average
                            try {
                                average = mObject.getJSONArray("Average");

                            } catch (Exception e) {
                                //there is exception here , average is likely to be object here
                                String averageObject = mObject.getString("Average");
                                averageList = new ArrayList<>(1);
                                averageList.add(new Topic("Write More Test To improve your Performance"));
                            }
                            if (average != null) {
                                Log.d(TAG, "onResponse: after if");

                                averageList = new ArrayList<>(average.length());
                                for (int i = 0; i < average.length(); i++) {
                                    JSONObject obj = average.getJSONObject(i);
                                    averageList.add(new Topic(obj.getString("Topic")));
                                }
                            } else {

                                averageList = new ArrayList<>(1);
                            }


                            //Parse Good similartly
                            try {
                                good = mObject.getJSONArray("Good");

                            } catch (Exception e) {
                                //there is exception here , average is likely to be object here
                                String goodobject = mObject.getString("Average");
                                goodList = new ArrayList<>(1);
                                goodList.add(new Topic("Write More Test To improve your Performance"));
                            }

                            if (good != null) {

                                goodList = new ArrayList<>(good.length());
                                for (int i = 0; i < good.length(); i++) {
                                    JSONObject obj = good.getJSONObject(i);
                                    goodList.add(new Topic(obj.getString("Topic")));
                                }
                            } else {
                                String goodobject = mObject.getString("Good");
                                goodList = new ArrayList<>(1);
                                goodList.add(new Topic("Write More Test To improve your Performance"));
                            }


                            //Parse For Bad..
                            try {
                                bad = mObject.getJSONArray("Bad");

                            } catch (Exception e) {
                                //there is exception here , average is likely to be object here
                                String badObj = mObject.getString("Bad");
                                goodList = new ArrayList<>(1);
                                goodList.add(new Topic("Write More Test To improve your Performance"));
                            }

                            if (bad != null) {

                                badList = new ArrayList<>(bad.length());

                                for (int i = 0; i < bad.length(); i++) {
                                    JSONObject obj = bad.getJSONObject(i);
                                    badList.add(new Topic(obj.getString("Topic")));
                                }
                            }

                            avgProvider(averageList, goodList, badList);
                        }
                    } catch (JSONException e) {
                        Log.d("Average", "onResponse: " + e.getLocalizedMessage());
                        noProvider();
                    }
                }
            }
                    , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "onErrorResponse: errr");
                }
            });
        }
        catch (Exception e ){
            Log.d(TAG, "getLessonListFromServer: exception "+e.getLocalizedMessage());
        }

        //Add to Request Que

        if(mLessonListRequestor != null){

            mRequestQueue.addToRequestQue(mLessonListRequestor);
        }
        else{
            //no Request Obejct
            TimeoutError();
        }

    }

    private void TimeoutError() {

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public static PerFormanceFragment newInstance(String subejctId, String studentId) {
        if (mFragment == null) {
            mFragment = new PerFormanceFragment();
        }

        //Add Fragment Param' s here
        Bundle b = new Bundle();
        b.putString(BundleKey.SUBJECT_ID, subejctId);
        b.putString(BundleKey.ARG_STUDENT_ID, studentId);
        mFragment.setArguments(b);


        return mFragment;
    }

    @Override
    public void avgProvider(List<Topic> average, List<Topic> good, List<Topic> bad) {

        try{

            mChangableRoot.removeAllViews();
            View recommendation = LayoutInflater.from(mContext).inflate(R.layout.recommendation,mChangableRoot,true);

            //Find Recycler views
            RecyclerView mGoodlistview = (RecyclerView) recommendation.findViewById(R.id.good_list);
            RecyclerView mAvgList = (RecyclerView) recommendation.findViewById(R.id.average_list);
            RecyclerView mBadList = (RecyclerView) recommendation.findViewById(R.id.bad_list);
            //setting Layout Managers
            mGoodlistview.setLayoutManager(new LinearLayoutManager(mContext));
            mAvgList.setLayoutManager(new LinearLayoutManager(mContext));
            mBadList.setLayoutManager(new LinearLayoutManager(mContext));
            //set Adapter
            mGoodlistview.setAdapter(new TopicPerfromingAdapter(mContext,good));
            mBadList.setAdapter(new TopicPerfromingAdapter(mContext,bad));
            mAvgList.setAdapter(new TopicPerfromingAdapter(mContext,average));



        }
        catch (Exception e ){
            Log.d(TAG, "avgProvider: nexception");
            Log.d(TAG, "avgProvider: in ");
            mChangableRoot.removeAllViews();
            View no_data  = View.inflate(mContext,R.layout.no_data,mChangableRoot);

            Button mButton = (Button) no_data.findViewById(R.id.view_test);
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowtestWritePage();
                }
            });
        }
    }


   /*After parsing From Recoomendation Service  ,  There is no data since no Test were Written
   *  SO Show A layout that tells to wirte mOre test
   *
   *
   * */

    @Override
    public void noProvider() {

        Log.d(TAG, "noProvider: ");
        try {
            mChangableRoot.removeAllViews();
            View noList = View.inflate(mContext,R.layout.no_data,mChangableRoot);
            Button mButton = (Button) noList.findViewById(R.id.view_test);
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowtestWritePage();
                }
            });

        }
        catch (Exception e ){
            Log.d(TAG, "noProvider: exception");
        }
    }

    private void ShowtestWritePage() {
        final Intent   i = new Intent(mContext,BrowseTestActivity.class);

        startActivity(i);
    }
}


