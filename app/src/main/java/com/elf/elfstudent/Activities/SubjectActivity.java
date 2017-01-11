package com.elf.elfstudent.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.elf.elfstudent.Adapters.SubjectPagerAdapter;
import com.elf.elfstudent.Adapters.ViewPagerAdapters.SubjectImage;
import com.elf.elfstudent.CustomUI.titleTextview;
import com.elf.elfstudent.R;
import com.elf.elfstudent.Utils.BundleKey;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ELF on 08-01-2017.
 * The New top Layout of the new Feature
 */

public class SubjectActivity extends AppCompatActivity {

    @BindView(R.id.tab_layout_sac)
    TabLayout mTabLayout;
    @BindView(R.id.changebale_root_sa)
    RelativeLayout mViewroot;
    @BindView(R.id.subject_pager)
    ViewPager mPager;
    private String subjectId = null;
    private String studentId;





    /*
    //The Drop Down Icon
    @BindView(R.id.tool_bar_drop)
    ImageView mDropIcon;
//    @BindView(R.id.home_drawer_frame)
//    FrameLayout mdrawerLayout;
    @BindView(R.id.home_menu)
    CardView mHomeButton;
    @BindView(R.id.test_menu)
    CardView mTestButton;
    @BindView(R.id.report_menu)
    CardView mReportButton;
    @BindView(R.id.test_report_menu)
    CardView mTestReportButton;
    @BindView(R.id.payments_menu)
    CardView mPaymentsButton;

*/

    @BindView(R.id.title_text_toolbar)
    titleTextview titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subject_activity);
        ButterKnife.bind(this);


        //First Get teh Subject  you are gonna Write

        if (getIntent() != null) {
            subjectId = getIntent().getStringExtra(BundleKey.SUBJECT_ID);
            studentId = getIntent().getStringExtra(BundleKey.ARG_STUDENT_ID);
            prepareAdapter(subjectId,studentId);
            titleText.setText(SubjectImage.getSubjectName(subjectId) +" Overview ");

        }

        else{

            prepareAdapter("1","144");
        }

    }

    private void prepareAdapter(String subjectId, String studentId) {


        //First Prepate Adapter for View Pager

      SubjectPagerAdapter mAdapter = new SubjectPagerAdapter(getSupportFragmentManager(),subjectId,studentId);
        mPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mPager);
    }
}
