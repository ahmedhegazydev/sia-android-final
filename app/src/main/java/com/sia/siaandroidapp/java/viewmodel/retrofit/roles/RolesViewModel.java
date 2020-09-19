package com.sia.siaandroidapp.java.viewmodel.retrofit.roles;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sia.siaandroidapp.java.model.RoleResponse;
import com.sia.siaandroidapp.java.rest.ApiClient;
import com.sia.siaandroidapp.java.utils.pref.SiaSharedPref;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class RolesViewModel extends AndroidViewModel {
    private static final String TAG = "RolesViewModel";
//    private MutableLiveData<ResponseBody> getAllRolesResponse;
private MutableLiveData<List<RoleResponse>> getAllRolesResponse;
    private MutableLiveData<Boolean> error;
    private MutableLiveData<Boolean> loading;
    private ApiClient client = new ApiClient();
    private CompositeDisposable disposable = new CompositeDisposable();
    private SiaSharedPref utility = new SiaSharedPref();
    private SharedPreferences.Editor editor;


    @Inject
    public RolesViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Boolean> getError() {
        return error;
    }

    public LiveData<Boolean> startLoading() {
        return loading;
    }


    public LiveData<List<RoleResponse>> getAllRoles() {
        getAllRolesRequest();
        return getAllRolesResponse;
    }

    private void getAllRolesRequest() {
        error = new MutableLiveData<>();
        loading = new MutableLiveData<>();
        getAllRolesResponse = new MutableLiveData<>();
        loading.setValue(true);
        disposable.add(
                client.getAllRoles()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<RoleResponse>>() {
                            @Override
                            public void onSuccess(List<RoleResponse> roles) {
                                Log.e(TAG, "onSuccess: " + roles.toString());
                                error.setValue(false);
                                loading.setValue(false);
                                getAllRolesResponse.setValue(roles);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, "onError: " + e.getLocalizedMessage());
                                loading.setValue(false);
                                error.setValue(true);
                            }
                        })
        );

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
