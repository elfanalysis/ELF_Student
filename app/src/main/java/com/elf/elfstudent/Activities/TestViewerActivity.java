package com.elf.elfstudent.Activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.elf.elfstudent.Adapters.TesteViewerAdapter;
import com.elf.elfstudent.Network.AppRequestQueue;
import com.elf.elfstudent.Network.ErrorHandler;
import com.elf.elfstudent.Network.JsonProcessors.TestListProvider;
import com.elf.elfstudent.R;
import com.elf.elfstudent.Utils.BundleKey;
import com.elf.elfstudent.Utils.RequestParameterKey;
import com.elf.elfstudent.model.AllTestModels;

import junit.framework.Test;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ELF on 07-01-2017.
 *
 *
 */


public class TestViewerActivity extends AppCompatActivity implements TestListProvider.TestProviderCallback, ErrorHandler.ErrorHandlerCallbacks {
    private static final String TAG = "TESTVIEWACTIVITY";

//    // TODO: 07-01-2017 add Test View URL Here
    private static final String TEST_VIEW = "";
    @BindView(R.id.textView38)
    TextView textView38;
    @BindView(R.id.test_view_recycler_view)
    RecyclerView mList;
    @BindView(R.id.test_viewer_root)
    RelativeLayout testViewerRoot;
    TesteViewerAdapter mAdapter = null;
    private Object data;

    AppRequestQueue mRequestQueue = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_viewer_activity);
        ButterKnife.bind(this);



        if (getIntent() != null){
            String subId = getIntent().getStringExtra(BundleKey.SUBJECT_ID);
            String lessonid = getIntent().getStringExtra(BundleKey.LESSON_ID);

            if (subId != null && lessonid != null){
                pullTestQuestions(subId,lessonid);
            }
        }


        mAdapter = new TesteViewerAdapter(this,getData());
        mList.setLayoutManager(new LinearLayoutManager(this));
        mList.setAdapter(mAdapter);


    }

    private void pullTestQuestions(String subId, String lessonid) {
        JSONObject mObject = null;
        try{
            mObject = new JSONObject();
            mObject.put(RequestParameterKey.SUBJECT_ID,subId);
            mObject.put(RequestParameterKey.LESSON_ID,lessonid);

        }
        catch (Exception e ){
            Log.d(TAG, "pullTestQuestions: ");
        }



        if (mObject != null){
            TestListProvider mProvider = new TestListProvider(this);
            ErrorHandler mHandler = new ErrorHandler(this);

            JsonArrayRequest mObjectReq = new JsonArrayRequest(Request.Method.POST,TEST_VIEW,mObject,mProvider,mHandler);
            if (mRequestQueue == null){
                mRequestQueue = AppRequestQueue.getInstance(this);
            }
//            mRequestQueue.addToRequestQue(mObjectReq);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public List<AllTestModels> getData() {
        List<AllTestModels> mModelsList = new ArrayList<>(22);
               for (int i = 0; i < 22 ; i++){
                   mModelsList.add(new AllTestModels("1",String.valueOf("Chapter "+i),"Physics","11"));
               }
        return mModelsList;

    }

    @Override
    public void setTestListData(List<AllTestModels> mTests) {


        mAdapter  = new TesteViewerAdapter(this,mTests);
        mList.setLayoutManager(new LinearLayoutManager(this));
        mList.setAdapter(mAdapter);


    }

    @Override
    public void NoTestListData() {

    }

    @Override
    public void TimeoutError() {

    }

    @Override
    public void NetworkError() {

    }

    @Override
    public void ServerError() {

    }
}
