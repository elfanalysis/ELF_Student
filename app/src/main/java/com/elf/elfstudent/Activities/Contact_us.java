package com.elf.elfstudent.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.elf.elfstudent.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kri on 23-12-2016.
 *
 */

public class Contact_us extends AppCompatActivity {

    TextView elf_link;
    Button shareit;



    @BindView(R.id.contct_us_tlb)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us);
        ButterKnife.bind(this);



        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null){
            ab.setTitle("Go Back");
            ab.setDisplayShowHomeEnabled(true);
            ab.setDisplayHomeAsUpEnabled(true);
        }
        elf_link = (TextView) findViewById(R.id.hyperlink);
        elf_link.setClickable(true);
        elf_link.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='http://www.elfanalysis.com'> www.elfanalysis.com </a>";
        elf_link.setText(Html.fromHtml(text));

        shareit = (Button)findViewById(R.id.shareit);
//        mToolbar = (Toolbar)findViewById(R.id.conta)
//
        shareit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareit();
            }
        });
//
    }

    private void shareit() {

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Elf for Students");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, "ELF - For Students – Android Apps on Google PlayTest and Analyze In-depth Subject Knowledge and Areas Of Improvementplay.google.comhttps://play.google.com/store/apps/details?id=com.elf.elfstudent ");
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                }
                else{
                    finish();
                }
        }

        return true;
    }
}
