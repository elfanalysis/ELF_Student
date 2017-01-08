package com.elf.elfstudent.Network.JsonProcessors;

import android.util.Log;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by ELF on 07-01-2017.
 *
 */
public class PaymentStatusProvider implements Response.Listener<JSONArray> {


    private static final String TAG = "PAYMETN STAUTS";
    PayemntCallbacks mCallbacks = null;
    public PaymentStatusProvider(PayemntCallbacks mCallbacks) {
        this.mCallbacks  = mCallbacks;
    }

    @Override
    public void onResponse(JSONArray response) {


        try {

            JSONObject mObj = response.getJSONObject(0);

            if (mObj.getString("StatusCode").equals("1000")){
                mCallbacks.paymentStatus(true);
            }
            else{
                mCallbacks.paymentStatus(false);
            }
        }
        catch (Exception e ){
            Log.d(TAG, "onResponse: ");
        }
    }

    public interface  PayemntCallbacks{
        void paymentStatus(boolean Status);
    }
}
