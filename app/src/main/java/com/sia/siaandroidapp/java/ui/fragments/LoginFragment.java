package com.sia.siaandroidapp.java.ui.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.pixplicity.easyprefs.library.Prefs;
import com.sia.siaandroidapp.R;
import com.sia.siaandroidapp.java.constants.Constants;
import com.sia.siaandroidapp.java.model.errors.ErrorsMessages;
import com.sia.siaandroidapp.java.ui.activities.MainActivity;
import com.sia.siaandroidapp.java.ui.dialogs.DialogCreator;
import com.sia.siaandroidapp.java.ui.fragments.reset.ResetPassPhase1Fragment;
import com.sia.siaandroidapp.java.ui.snacks.Snacks;
import com.sia.siaandroidapp.java.utils.anim.AnimUtils;
import com.sia.siaandroidapp.java.viewmodel.retrofit.LoginViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerFragment;
import retrofit2.HttpException;

//public class LoginFragment extends Fragment {
public class LoginFragment extends DaggerFragment {

    private static final String TAG = "LoginFragment";
    @BindView(R.id.etEnterEmailOrPhoneNumber)
    TextInputEditText etEnterEmailOrPhoneNumber;
    @BindView(R.id.etEnterPassword)
    TextInputEditText etEnterPassword;
    private LoginViewModel viewModel;
    private Dialog loadingDialog;
    private Gson gson = new Gson();

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(LoginFragment.this, rootView);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this)
                .get(LoginViewModel.class);

        initializeLoadingDialog();


    }

    @OnClick(R.id.btnLogin)
    void onBtnLoginClicked(View v) {

        String username, password;

//        username = "ketaxok377@dffwer.com";
//        password = "123";

        username = etEnterEmailOrPhoneNumber.getText().toString().trim();
        password = etEnterPassword.getText().toString().trim();


        if (username.length() == 0) {
            Toast.makeText(getActivity(), "Enter username", Toast.LENGTH_SHORT).show();
            AnimUtils.shakeView(etEnterEmailOrPhoneNumber);
        } else {
            if (password.length() == 0) {
                Toast.makeText(getActivity(), "Enter password", Toast.LENGTH_SHORT).show();
                AnimUtils.shakeView(etEnterPassword);
            } else {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("username", username);
                    jsonObject.put("password", password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String token = "jwt " + Prefs.getPreferences()
                        .getString(Constants.REGISTER_TOKEN, "");
                observeLogin(jsonObject.toString(), token);
            }
        }

    }

    @OnClick(R.id.btnLoginWithFacebook)
    void onBtnLoginWithFBClicked(View v) {


    }

    private void observeLogin(String jsonObject, String token) {
        viewModel.getLogin(jsonObject, token)
                .observe(this, response -> {
                    Log.e(TAG, "observeLogin: " + response);
                    Snacks.showSuccess(getActivity(), "Logged success");


                });


        viewModel.getError().observe(this, error -> {
            if (error != null) {
                HttpException httpException = (HttpException) error;
                try {
                    String errorBody = httpException.response().errorBody().string();
                    Log.e(TAG, "observeLogin: " + errorBody);
                    ErrorsMessages errorsMessages = gson.fromJson(errorBody, ErrorsMessages.class);
                    Snacks.showError(getActivity(), errorsMessages.getErrorMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        viewModel.startLoading().observe(this, loading -> {
            if (loading) {
                loadingDialog.show();
            } else {
                loadingDialog.dismiss();
            }
        });


    }

    private void initializeLoadingDialog() {
        loadingDialog = DialogCreator.init(getActivity());
        loadingDialog.setCancelable(false);
    }

    @OnClick(R.id.tvBackToRoleChoice)
    public void tvBackToRoleChoice(View v) {
        ((MainActivity) getActivity()).pushFragment(new StartFragment());
    }

    @OnClick(R.id.tvForgetPassword)
    public void tvForgetPassword(View v) {
        ((MainActivity) getActivity()).pushFragment(new ResetPassPhase1Fragment());
    }


}
