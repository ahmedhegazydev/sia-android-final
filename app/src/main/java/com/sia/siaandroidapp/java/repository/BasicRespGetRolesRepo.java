package com.sia.siaandroidapp.java.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonArray;
import com.sia.siaandroidapp.java.model.Role;
import com.sia.siaandroidapp.java.model.responses.BasicResponse;
import com.sia.siaandroidapp.java.model.wrappers.StateLiveData;
import com.sia.siaandroidapp.java.ui.activities.MainActivity;
import com.sia.siaandroidapp.java.utils.volley.MyCustomRequest;
import com.sia.siaandroidapp.java.utils.volley.VolleySingleTone;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class BasicRespGetRolesRepo {
    private Application mContext;
    private static final String TAG = "BasicRespGetRolesRepo";
    private static class Role{
        int id;
        String  name;
    }
    private List<Role> roles;

    public BasicRespGetRolesRepo(Application mContext) {
        this.mContext = mContext;
    }

    public StateLiveData<BasicResponse> basicResponseGetRolesApi(String url) {
        StateLiveData<BasicResponse> getRolesResponseStateLiveData = new StateLiveData<>();



        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, url
                        ,
                        null,
                        response -> {
                            Log.e(TAG, "basicResponseGetRolesApi: success" + response);
//                            Type dataType = new TypeToken<BasicResponse>() {
//                            }.getType();
//                            BasicResponse data = new Gson().fromJson(response.toString(), dataType);
//                            getRolesResponseStateLiveData.postSuccess(data);
//                            signInResponseStateLiveData.postFail(data);

                        }, error -> {
                    Log.e(TAG, "volley_error basicResponseGetRolesApi" +
                            error.toString());
                    getRolesResponseStateLiveData.postError(error);
                    error.printStackTrace();
                });



//        MyCustomRequest jsonObjectRequest = new
//                MyCustomRequest(Request.Method.GET,
//                url, new JSONArray()< ,
//                new Response.Listener() {
//                    @Override
//                    public void onResponse(Object response) {
//                        Log.e(TAG, "onResponse: " + response );
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError response) {
//                        //Failure callback
//                        Toast.makeText(mContext,
//                                "Error Encountered",
//                                Toast.LENGTH_SHORT).show();
//                        Log.e(TAG, "onErrorResponse: " +
//                                response.getLocalizedMessage());
//
//                    }
//                });



                    //handle timeout error
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Add the request to the RequestQueue.
        VolleySingleTone.getInstance(mContext)
                .addToRequestQueue(jsonObjectRequest);


        return getRolesResponseStateLiveData;
    }


}