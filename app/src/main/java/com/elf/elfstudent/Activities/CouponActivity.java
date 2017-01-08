package com.elf.elfstudent.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.elf.elfstudent.DataStorage.DataStore;
import com.elf.elfstudent.Network.AppRequestQueue;
import com.elf.elfstudent.Network.ErrorHandler;
import com.elf.elfstudent.Network.JsonProcessors.PaymentStatusProvider;
import com.elf.elfstudent.R;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 5/12/16.
 *
 */

public class CouponActivity extends AppCompatActivity implements Response.Listener<JSONArray>, Response.ErrorListener, PaymentStatusProvider.PayemntCallbacks, ErrorHandler.ErrorHandlerCallbacks {
    //// TODO: 5/12/16 addd Url
    private static final String COUPON_URL = " http://elfanalysis.net/elf_ws.svc/CheckCoupenCode";
    public static final String COUPUN_STATUS_URL = "";
    private static final String TAG = "COUPON";

    @BindView(R.id.textView2)
    TextView text;

    @BindView(R.id.editText)
    EditText coupBox;
    @BindView(R.id.button2)
    Button couponButton;
    AppRequestQueue mRequestQueue = null;
    JsonArrayRequest mCouponRequest = null;
    @BindView(R.id.coupon_toolbar)
    Toolbar couponToolbar;
    @BindView(R.id.coupon_root)
    LinearLayout couponRoot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupon_activity);
        ButterKnife.bind(this);
        setSupportActionBar(couponToolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
            ab.setTitle("Power Up");
        }

        CheckifCOdeApplied();
        mRequestQueue = AppRequestQueue.getInstance(this.getApplicationContext());
        couponButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coupOnButtonCLicked();
            }
        });

    }

    private void CheckifCOdeApplied() {

        JSONObject moJsonObject = new JSONObject();
        try {
            DataStore mStore = DataStore.getStorageInstance(this);
            moJsonObject.put("StudentId", mStore.getStudentId());
        } catch (Exception e) {
            Log.d(TAG, "Exception in Checkin Coipuon");
        }

        PaymentStatusProvider mProvider = new PaymentStatusProvider(this);
        ErrorHandler mErrorHandler = new ErrorHandler(this);
        JsonArrayRequest mRequest = new JsonArrayRequest(Request.Method.POST, COUPUN_STATUS_URL, moJsonObject,mProvider,mErrorHandler);


        if (mRequestQueue != null){
            mRequestQueue.addToRequestQue(mRequest);
        }
    }

    private void coupOnButtonCLicked() {
        String coupon_text = coupBox.getEditableText().toString();
        Log.d("value", coupon_text);

        if (!TextUtils.isEmpty(coupon_text)) {

            sendCoupondCode(coupon_text);
        }
    }

    private void sendCoupondCode(String coupon_text) {
        Log.d("sdfdsf", "sending code " + coupon_text);
        DataStore mStore = DataStore.getStorageInstance(this.getApplicationContext());
        JSONObject mObject = new JSONObject();
        try {
            if (mStore == null) {
                mStore = DataStore.getStorageInstance(this);
            }
            mObject.put("StudentId", mStore.getStudentId());
            mObject.put("CoupenCode", coupon_text);

        } catch (Exception e) {
            Log.d("Coupon Code", "sendCoupondCode: ");

        }
        try {

            mCouponRequest = new JsonArrayRequest(Request.Method.POST, COUPON_URL, mObject, this, this);

        } catch (Exception e) {
            Toast.makeText(this, "coupon error", Toast.LENGTH_SHORT).show();
        }
        if (mRequestQueue != null) {
            Log.d(TAG, "sendCoupondCode: adding to Reuqest Queue");
            mRequestQueue.addToRequestQue(mCouponRequest);
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

    @Override
    public void onResponse(JSONArray response) {

        Log.d(TAG, "onResponse: " + response.toString());


        try {

            if (response != null) {

                JSONObject mObject = response.getJSONObject(0);
                if (mObject.getString("StatusCode").equals("9996")) {

                    final Intent in = new Intent(this, CouponAcceptedActivity.class);
                    startActivity(in);
                } else {
                    Animation an = AnimationUtils.loadAnimation(this, R.anim.shake);
                    coupBox.startAnimation(an);
                    Toast.makeText(this, "Please Enter Valid Code ", Toast.LENGTH_LONG).show();
                    coupBox.setText("");
                }
            }

        } catch (Exception e) {
            Log.d("Exception ", "onResponse: " + e.getLocalizedMessage());

            Animation an = AnimationUtils.loadAnimation(this, R.anim.shake);
            coupBox.startAnimation(an);
            Toast.makeText(this, "Coupon Not Applied  , Please Contact Support ", Toast.LENGTH_LONG).show();
            final Intent in1 = new Intent(this, HomeActivity.class);
            startActivity(in1);


        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                } else {
                    finish();
                }
        }

        return true;
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d(TAG, "onErrorResponse: ");
        Animation an = AnimationUtils.loadAnimation(this, R.anim.shake);
        coupBox.startAnimation(an);
        Toast.makeText(this, "Coupon Not Applied  , Please Contact Support ", Toast.LENGTH_LONG).show();
        final Intent in1 = new Intent(this, HomeActivity.class);
    }

    @Override
    public void paymentStatus(boolean Status) {
        if (Status) {
            //Payment has been done do not show Standard Layout

            try {
                couponRoot.removeAllViews();
                View v = View.inflate(this,R.layout.coupon_activated,couponRoot);
            }
            catch (Exception e ){
                Log.d(TAG, "paymentStatus: "+Status);
            }



        }
        else {
            //No Payment has been done ,  Do nothing
        }
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
