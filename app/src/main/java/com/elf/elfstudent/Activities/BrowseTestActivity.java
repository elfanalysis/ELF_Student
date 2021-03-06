package com.elf.elfstudent.Activities;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.elf.elfstudent.Adapters.ViewPagerAdapters.tenthTestPagerAdapter;
import com.elf.elfstudent.CustomUI.titleTextview;
import com.elf.elfstudent.DataStorage.DataStore;
import com.elf.elfstudent.Fragments.AllTestFragment;
import com.elf.elfstudent.R;
import com.elf.elfstudent.Utils.ScreenUtil;
import com.elf.elfstudent.Utils.Server_Values;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 18/10/16.
 * THe Activity that is used to Browse Tests
 * It has a view pager which has 3 fragments
 *   {
 *    {@link AllTestFragment}
 *    {@link com.elf.elfstudent.Fragments.PublicTestFragmet}
 *    {@link com.elf.elfstudent.Fragments.RecommendedTestFragment}
 *
 *    this is provided by {@link tenthTestPagerAdapter}
 *
 *
 *   }
 *
 *   supply each Fragment with Student iD or Data Store
 */

public class BrowseTestActivity extends AppCompatActivity {
    private static final String TAG = "BROWSE";


    //The singeleton class which provides student  details
   


    //The Views

    //The Tab Layout
    @BindView(R.id.test_tab)
    TabLayout mTab;

    //The View pager
    @BindView(R.id.test_pager)
    ViewPager mPager;


    @BindView(R.id.browse_content_root)
    LinearLayout mContentRoot;
    @BindView(R.id.browse_test_toolbar)
    Toolbar mToolbar;



    /*THe Drawer Related Atrributes*/
    //The Drop Down Icon
    @BindView(R.id.tool_bar_drop)
    ImageView mDropIcon;
    @BindView(R.id.browse_drawer_frame)
    FrameLayout mdrawerLayout;
    @BindView(R.id.home_menu)
    CardView mHomeButton;
    @BindView(R.id.test_menu) CardView mTestButton;
    @BindView(R.id.report_menu ) CardView mReportButton;
    @BindView(R.id.test_report_menu) CardView mTestReportButton;
    @BindView(R.id.payments_menu) CardView mPaymentsButton;

    //The Pager Adapter for View pager
    tenthTestPagerAdapter mAdapter;



    @BindView(R.id.title_text_toolbar)
    titleTextview titleText;
    DataStore mStore = null;
    private String classId;
    private boolean isDrawerShowing =false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_test_activity);
        ButterKnife.bind(this);
        mStore = DataStore.getStorageInstance(this);
        Log.d(TAG, "onCreate: ");

        titleText.setText("Write Tests");
        setPagerAdapter();
        setSupportActionBar(mToolbar);
        setUpCustomDrawer();

        mDropIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DropButtonClicked();
            }
        });

//        initDrawer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void setPagerAdapter() {

        if (mStore != null){
            classId = mStore.getStandard();
            Log.d(TAG, "Brwoset Test "+classId);

            if (classId != null){


                if (classId.equals(Server_Values.TENTH_CLASS_ID)){

                    //user is 10
                    Log.d(TAG, "setPagerAdapter: tenth standat");
                    mAdapter = new tenthTestPagerAdapter(getSupportFragmentManager(),true,0);
                    mPager.setAdapter(mAdapter);
                }
                else{
                    //he is 12 , get the group
                    String group = mStore.getStudentGroup();
                    Log.d(TAG, "setPagerAdapter: "+group);

                    if (group.equals("COMPUTER")){
                        Log.d(TAG, "Computer student");

                        mAdapter = new tenthTestPagerAdapter(getSupportFragmentManager(),false,1);
                        mPager.setAdapter(mAdapter);
                    }
                    else if (group.equals("BIOLOGY")){
                        Log.d(TAG, "setPagerAdapter: bio student");
                        mAdapter = new tenthTestPagerAdapter(getSupportFragmentManager(),false,2);
                        mPager.setAdapter(mAdapter);

                    }
                }
            }
        }

        mTab.setupWithViewPager(mPager);
    }

    private void setUpCustomDrawer() {

        //HOme Button Clicked
        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(i);
            }
        });
        //Report
        mReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent i = new Intent(getApplicationContext(),ReportActivity.class);
                startActivity(i);
            }
        });

        //Browse test Page
        mTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final  Intent i = new Intent(getApplicationContext(),BrowseTestActivity.class);
                startActivity(i);
            }
        });

        //Test Reports

        mTestReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent i = new Intent(getApplicationContext(),TestReportsActivity.class);
                startActivity(i);
            }
        });

        //Payments
        mPaymentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent i  = new Intent(getApplicationContext(),PaymentActivity.class);
                startActivity(i);
            }
        });
    }

    private void DropButtonClicked() {
//


        if (!isDrawerShowing){
            mdrawerLayout.setTranslationX(-ScreenUtil.getScreenWidth(this));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                mdrawerLayout.animate().translationX(0).setInterpolator(new DecelerateInterpolator(1.5f)).setDuration(600).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        mdrawerLayout.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                }).start();
            }
        }
        else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                mdrawerLayout.animate().setDuration(500)
                        .setInterpolator(new AccelerateDecelerateInterpolator())
                        .setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {

                            }

                            @Override
                            public void onAnimationCancel(Animator animator) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animator) {

                            }
                        }).translationX(-ScreenUtil.getScreenWidth(getApplicationContext())).start();
            }
        }



        isDrawerShowing = !isDrawerShowing;




    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
       final  Intent i = new Intent(this,HomeActivity.class);
        startActivity(i);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }


}
