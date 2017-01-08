package com.elf.elfstudent.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.elf.elfstudent.Adapters.FeedAdapter;
import com.elf.elfstudent.R;
import com.elf.elfstudent.model.Feed;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ELF on 05-01-2017.
 */

public class FeedActivity extends AppCompatActivity {


    @BindView(R.id.feed_toolbar)
    Toolbar feedToolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.feed_root)
    FrameLayout feedRoot;
    @BindView(R.id.feed_list)
    RecyclerView feedList;

    FeedAdapter mAdapter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_activity);
        ButterKnife.bind(this);

        feedList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdapter  = new FeedAdapter(getData(),this);
        feedList.setAdapter(mAdapter);

    }

    private List<Feed> getData(){
        ArrayList<Feed> feedList = new ArrayList<>(5);
        feedList.add(new Feed(true,"Image","Image Text"));
        feedList.add(new Feed(false,"Image","Image Text"));
        feedList.add(new Feed(true,"Image","Image Text"));
        feedList.add(new Feed(false,"Image","Image Text"));
        feedList.add(new Feed(false,"Image","Image Text"));
        return feedList;

    }

}
