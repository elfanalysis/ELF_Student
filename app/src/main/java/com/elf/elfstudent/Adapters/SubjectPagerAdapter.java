package com.elf.elfstudent.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.elf.elfstudent.Fragments.New_Fragments.PerFormanceFragment;
import com.elf.elfstudent.Fragments.New_Fragments.TakeTestFragment;
import com.elf.elfstudent.Fragments.ReportFragment;

/**
 * Created by ELF on 08-01-2017.
 *
 *This is the main Pager Adapter for Subejct Which is the new Root , get the student ID
 *
 * and Subejct Id
 * pass it down to Respectivge Fragments
 */
public class SubjectPagerAdapter  extends FragmentStatePagerAdapter{



    private String subejctId;
    private String studentId;
    public SubjectPagerAdapter(FragmentManager fm,String subjectId , String StudentId) {
        super(fm);
        this.subejctId = subjectId;
        this.studentId = StudentId;
    }



    @Override
    public Fragment getItem(int position) {

        switch (position){

            case  0 : ReportFragment mFragment  = ReportFragment.newInstance(subejctId,studentId);
                        return mFragment;
            case 1 : TakeTestFragment mTakeTestFragment = TakeTestFragment.newInstance(subejctId,studentId);
                return  mTakeTestFragment;
            case 2 : PerFormanceFragment mPerFFragment = PerFormanceFragment.newInstance(subejctId,studentId);
                return mPerFFragment;
        }

        return null;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "REPORT";
            case 1 : return "TEST'S";
            case 2 : return "PERFORMANCE";
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
