package com.sia.siaandroidapp.java.viewmodel.volley;


import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;


import com.sia.siaandroidapp.java.model.responses.BasicResponse;
import com.sia.siaandroidapp.java.model.wrappers.StateLiveData;
import com.sia.siaandroidapp.java.repository.BasicRespRegisterRepo;

import org.json.JSONObject;

public class BasicResponseRegisterViewModel extends AndroidViewModel {
    private BasicRespRegisterRepo repository;

    public BasicResponseRegisterViewModel(@NonNull Application application) {
        super(application);
        repository = new BasicRespRegisterRepo(application);
    }

    public StateLiveData<BasicResponse> basicResponseStateLiveData(String url, JSONObject jsonBody) {
        Log.e(TAG, "basicResponseStateLiveData: " + url );
        return repository.basicResponsePostApi(url, jsonBody);
    }

    private static final String TAG = "BasicResponseRegisterVi";


}