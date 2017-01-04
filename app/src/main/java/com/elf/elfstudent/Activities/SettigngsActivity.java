package com.elf.elfstudent.Activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.elf.elfstudent.DataStorage.DataStore;
import com.elf.elfstudent.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 20/10/16.
 */
public class SettigngsActivity extends AppCompatActivity{


//    @BindView(R.id.settings_logout)
//    TextView mLogoutText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.settings_activity);
//        ButterKnife.bind(this);
//
//        mLogoutText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                logoutButtonClicked();
//            }
//        });

    }

    private void logoutButtonClicked() {
        DataStore mStore =  DataStore.getStorageInstance(this.getApplicationContext());

        if (mStore != null){
            mStore.logout();
        }
        else{
            throw new IllegalStateException("Cannot Log out");
        }
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
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
