package com.elf.elfstudent.Fragments.New_Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elf.elfstudent.Adapters.TestListAdapter;
import com.elf.elfstudent.Fragments.ReportFragment;
import com.elf.elfstudent.R;
import com.elf.elfstudent.Utils.BundleKey;
import com.elf.elfstudent.model.AllTestModels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ELF on 08-01-2017.
 *
 *
 */
public class TakeTestFragment extends Fragment{

    private static TakeTestFragment mFragment;
    private Context mContext = null;


    @BindView(R.id.mTestListView)
    RecyclerView mListView;
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
        View v = LayoutInflater.from(mContext).inflate(R.layout.take_test,container,false);
        ButterKnife.bind(this,v);

        if (getArguments() != null){
            String subjectid = getArguments().getString(BundleKey.SUBJECT_ID);
            String studentId = getArguments().getString(BundleKey.ARG_STUDENT_ID);
            PrepareTests(subjectid,studentId);
        }





        return v;
    }

    private void PrepareTests(String subjectid, String studentId) {
        TestListAdapter mAdapter = new TestListAdapter(mContext,getData());
        mListView.setAdapter(mAdapter);
        mListView.setLayoutManager(new LinearLayoutManager(mContext));




    }

    private List<AllTestModels> getData() {
        List<AllTestModels> modelsList = new ArrayList<>(6);
        for (int i = 0 ; i < 5 ; i++){
            modelsList.add(new AllTestModels("sdsf","Test " + String.valueOf(i),"Sdfsadf","asdfds"));
        }
        return modelsList;
    }

    @Override
    public void onDetach() {
        super.onDetach();
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

    public static TakeTestFragment newInstance(String subejctId, String studentId) {
        if (mFragment == null){
            mFragment = new TakeTestFragment();
        }

        //Add Fragment Param' s here
        Bundle b =  new Bundle();
        b.putString(BundleKey.SUBJECT_ID,subejctId);
        b.putString(BundleKey.ARG_STUDENT_ID,studentId);
        mFragment.setArguments(b);


        return mFragment;
    }
}
