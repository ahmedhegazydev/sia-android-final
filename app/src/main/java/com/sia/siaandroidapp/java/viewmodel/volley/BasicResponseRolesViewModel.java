package com.sia.siaandroidapp.java.viewmodel.volley;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.sia.siaandroidapp.java.model.responses.BasicResponse;
import com.sia.siaandroidapp.java.model.wrappers.StateLiveData;
import com.sia.siaandroidapp.java.repository.BasicRespGetRolesRepo;
import com.sia.siaandroidapp.java.repository.BasicRespRegisterRepo;

import org.json.JSONObject;

public class BasicResponseRolesViewModel extends AndroidViewModel {
    private BasicRespGetRolesRepo repository;

    public BasicResponseRolesViewModel(@NonNull Application application) {
        super(application);
        repository = new BasicRespGetRolesRepo(application);
    }

    public StateLiveData<BasicResponse> basicResponseStateLiveData(String url) {
        return repository.basicResponseGetRolesApi(url);
    }


}