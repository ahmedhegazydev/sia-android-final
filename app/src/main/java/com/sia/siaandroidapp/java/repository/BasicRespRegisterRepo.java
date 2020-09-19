package com.sia.siaandroidapp.java.repository;

import android.app.Application;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sia.siaandroidapp.java.model.responses.BasicResponse;
import com.sia.siaandroidapp.java.model.wrappers.StateLiveData;
import com.sia.siaandroidapp.java.utils.volley.VolleySingleTone;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

public class BasicRespRegisterRepo {
    private static final String TAG = "BasicResponseRepository";
    private Application mContext;
    private Gson gson = new Gson();

    public BasicRespRegisterRepo(Application mContext) {
        this.mContext = mContext;
    }

    public StateLiveData<BasicResponse> basicResponsePostApi(String url, JSONObject jsonBody) {
        String stringJson = jsonBody.toString();
        StateLiveData<BasicResponse> signInResponseStateLiveData = new StateLiveData<>();
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.POST, url
                        ,
                        null,
                        response -> {
                            Log.e(TAG, "basicResponsePostApi: success" + response);
                            Type dataType = new TypeToken<BasicResponse>() {
                            }.getType();
                            BasicResponse data = new Gson().fromJson(response.toString(), dataType);
                            signInResponseStateLiveData.postSuccess(data);
//                            signInResponseStateLiveData.postFail(data);

                        }, error -> {
                    Log.e("volley_error", error.toString());
                    signInResponseStateLiveData.postError(error);
                    error.printStackTrace();
                }) {
//                    @Override
//                    public Map<String, String> getHeaders() throws AuthFailureError {
//                        Map<String, String> headers = new HashMap<>();
//                        headers.put("Accept", "application/json");
//                        headers.put("Content-Type", "application/json");
////                        headers.put("charset", "utf-8");
//                        return headers;
//                    }

//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//                        return params;
//                    }

                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8";
                    }

                    @Override
                    public byte[] getBody() {
                        try {
                            return stringJson == null ? null :
                                    stringJson
                                            .getBytes("utf-8");
                        } catch (UnsupportedEncodingException uee) {
//                            VolleyLog.wtf("Unsupported Encoding " +
//                                    "while trying to get the " +
//                                    "bytes of %s using %s", main.toString(),
//                                    "utf-8");
                            return null;
                        }
                    }


                };

        //handle timeout error
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Add the request to the RequestQueue.
        VolleySingleTone.getInstance(mContext)
                .addToRequestQueue(jsonObjectRequest);


        return signInResponseStateLiveData;
    }


}