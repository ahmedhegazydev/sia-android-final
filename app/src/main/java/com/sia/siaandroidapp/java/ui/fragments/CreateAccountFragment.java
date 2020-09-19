package com.sia.siaandroidapp.java.ui.fragments;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.pixplicity.easyprefs.library.Prefs;
import com.sia.siaandroidapp.R;
import com.sia.siaandroidapp.java.constants.Constants;
import com.sia.siaandroidapp.java.model.Role;
import com.sia.siaandroidapp.java.model.RoleResponse;
import com.sia.siaandroidapp.java.model.errors.ErrorsMessages;
import com.sia.siaandroidapp.java.model.responses.BasicResponse;
import com.sia.siaandroidapp.java.model.wrappers.StateData;
import com.sia.siaandroidapp.java.ui.activities.MainActivity;
import com.sia.siaandroidapp.java.ui.dialogs.DialogCreator;
import com.sia.siaandroidapp.java.ui.snacks.Snacks;
import com.sia.siaandroidapp.java.utils.SiaUtils;
import com.sia.siaandroidapp.java.viewmodel.retrofit.RegisterViewModel;
import com.sia.siaandroidapp.java.viewmodel.retrofit.VerifyTokenViewModel;
import com.sia.siaandroidapp.java.viewmodel.retrofit.roles.RolesViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;
import es.dmoral.toasty.Toasty;
import retrofit2.HttpException;

public class CreateAccountFragment extends DaggerFragment {

    private static final String TAG =
            CreateAccountFragment.class.getSimpleName();
    private static Gson mGson = new Gson();
    //    @BindView(android.R.id.content)
    @BindView(R.id.container)
    View container;
    @BindView(R.id.etEnterUserName)
    TextInputEditText etEnterUserName;
    @BindView(R.id.etEnterEmailOrPhone)
    TextInputEditText etEnterEmailOrPhone;
    @BindView(R.id.etEnterPassword)
    TextInputEditText etEnterPassword;
    @BindView(R.id.tvRegisterAs)
    TextView tvRegisterAs;
    @BindView(R.id.tilEmaiPhone)
    TextInputLayout tilEmaiPhone;
    @BindView(R.id.tilName)
    TextInputLayout tilName;
    @BindView(R.id.tilPassword)
    TextInputLayout tilPassword;
    private Unbinder mUnbinder;
    private Role signUpAsRole = null;
    private RegisterViewModel registerVMRetrofit;
    private VerifyTokenViewModel verifyTokenViewModel;
    private Dialog mLoadingDialog;
    //    private View mRootView;
    private RolesViewModel rolesViewModel;

    public static CreateAccountFragment newInstance(Bundle args) {
        CreateAccountFragment fragment = new CreateAccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static CreateAccountFragment newInstance() {
        Bundle args = new Bundle();
        CreateAccountFragment fragment = new CreateAccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_register, container, false);
        mUnbinder = ButterKnife.bind(CreateAccountFragment.this, mRootView);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initializeLoadingDialog();

        initViewModelProviders();

        getThePassedRegisterAsRole();

        setErrorEnabledDefault(false);

        changeStrokeAndHintClrOnFocus(null, etEnterPassword, tilPassword);
        changeStrokeAndHintClrOnFocus(null, etEnterEmailOrPhone, tilEmaiPhone);
        changeStrokeAndHintClrOnFocus(null, etEnterUserName, tilName);

        addShowHidePassTextWatcher(etEnterPassword);
        addShowHidePassTextWatcher(etEnterUserName);
        addShowHidePassTextWatcher(etEnterEmailOrPhone);


    }

    private void setErrorEnabledDefault(boolean b) {
        tilName.setErrorEnabled(false);
        tilEmaiPhone.setErrorEnabled(false);
        tilPassword.setErrorEnabled(b);
    }

    private void addShowHidePassTextWatcher(TextInputEditText et) {
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                ivShowHidePass.setVisibility(s.toString().length() != 0
//                        ? ImageView.VISIBLE : ImageView.INVISIBLE);

                if (tilName.isErrorEnabled()) {

                }
                tilName.setErrorEnabled(false);
                tilEmaiPhone.setErrorEnabled(false);
                tilPassword.setErrorEnabled(false);
                highlightWithColor(etEnterUserName, R.color.color_blue2);
                highlightWithColor(etEnterEmailOrPhone, R.color.color_blue2);
                highlightWithColor(etEnterPassword, R.color.color_blue2);
            }
        });
    }

    private void changeStrokeAndHintClrOnFocus(View view, TextInputEditText et, TextInputLayout til) {
        Drawable background = et.getBackground();
        et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    changeColors(background, til, R.color.color_blue2);
                } else {
                    changeColors(background, til, R.color.color_gray);
                }
            }
        });
    }

    private void highlightWithColor(TextInputEditText et, int color) {
        Drawable background = et.getBackground();
        ((GradientDrawable) background)
                .setStroke(getActivity()
                                .getResources()
                                .getInteger(R.integer.stroke_width),
                        ContextCompat.getColor(getActivity(), color));
    }

    private void changeColors(Drawable background, TextInputLayout til, int color) {
        ((GradientDrawable) background)
                .setStroke(getActivity()
                                .getResources()
                                .getInteger(R.integer.stroke_width),
                        ContextCompat.getColor(getActivity(), color));


//        int colorInt = getResources().getColor(color);
//        ColorStateList csl = ColorStateList.valueOf(colorInt);
//        til.setHintTextColor(csl);

    }

    private void initViewModelProviders() {
        //Retrofit
        registerVMRetrofit = new ViewModelProvider(this)
                .get(RegisterViewModel.class);
        verifyTokenViewModel = new ViewModelProvider(this)
                .get(VerifyTokenViewModel.class);

    }

    private void registerUser() {

        //gogo
//        etEnterEmailOrPhone.setText("wefiyih750@gilfun.com");
//        etEnterPassword.setText(UUID.randomUUID().toString());
//        etEnterUserName.setText(UUID.randomUUID().toString());

        String phoneOrEmail = String.valueOf(etEnterEmailOrPhone.getText()).trim();
        String username = String.valueOf(etEnterUserName.getText()).trim();
        String password = String.valueOf(etEnterPassword.getText()).trim();


        if (verifyData(username, phoneOrEmail, password)) return;

        //for hiding the error helper text
        setErrorEnabledDefault(false);


//        JSONObject jsonObjectRole = new JSONObject();
//        try {
//            jsonObjectRole.put("id", getUserSelectedRegAs());
//            jsonObjectRole.put("name", getUserSelectedRegAs());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        JSONArray jsonArrayRoles = new JSONArray();
        jsonArrayRoles.put(getApplicableRole());
        JSONObject jsonObject = new JSONObject();
        try {
            if (SiaUtils.isEmailOrPhone(phoneOrEmail) || SiaUtils.isValidEmail(phoneOrEmail)) {
                ///for email
                jsonObject.put(Constants.JSON_OBJ_EMAIL, phoneOrEmail);
                jsonObject.put(Constants.JSON_OBJ_PHONE, "");
            } else {
                //for phone
                jsonObject.put(Constants.JSON_OBJ_PHONE, phoneOrEmail);
                jsonObject.put(Constants.JSON_OBJ_EMAIL, "");
            }
            jsonObject.put("username", username);
            jsonObject.put("password", password);
            jsonObject.put("roles", jsonArrayRoles);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "registerUser: " + jsonObject.toString());


        //retrofit
        registerRetrofit(jsonObject.toString());


    }

    private JSONObject getApplicableRole() {
        JSONObject jsonObjectRole = new JSONObject();
        String regAs = Prefs.getString(Constants.REGISTER_AS, "");
        String id = null, name = null;
        int counter = 1;
        if (TextUtils.equals(regAs, Constants.REG_AS_STUDENT)) {
            id = "1";
            name = Constants.ROLE_STUDENT;
        } else {
            if (TextUtils.equals(regAs, Constants.REG_AS_TEACHER)) {
                id = "2";
                name = Constants.ROLE_TEACHER;
            } else {
                id = "3";
                name = Constants.ROLE_PARENT;
            }
        }
        try {
            jsonObjectRole.put("id", id);

            jsonObjectRole.put("name", name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObjectRole;
    }

    private void getThePassedRegisterAsRole() {
//        if (getArguments() != null && getArguments().containsKey(Constants.SIGN_UP_AS)) {
//            String strSignUpAs = getArguments().getString(Constants.SIGN_UP_AS, "");
//            signUpAsRole = mGson.fromJson(strSignUpAs, Role.class);
//            Log.e(TAG, signUpAsRole.getMainTitle());
//            Log.e(TAG, String.valueOf(signUpAsRole.getSelectedPos()));
//            String strRegisterAs = "";
//            switch (signUpAsRole.getSelectedPos()) {
//                case 0:
//                    strRegisterAs = "Student";
//                    break;
//                case 1:
//                    strRegisterAs = "Teacher";
//                    break;
//                case 2:
//                    strRegisterAs = "Parent";
//                    break;
//                default:
//                    break;
//
//            }
//            tvRegisterAs.setText(getString(R.string.register_as) + strRegisterAs);
//            //increment by 1 for the reg purpose.
//            signUpAsRole.setSelectedPos(signUpAsRole.getSelectedPos() + 1);
//        }

        String regAs = Prefs.getString(Constants.REGISTER_AS, ""),
                strRegisterAs = "";
        if (TextUtils.equals(regAs, Constants.REG_AS_STUDENT)) {
            strRegisterAs = "Student";
        } else {
            if (TextUtils.equals(regAs, Constants.REG_AS_TEACHER)) {
                strRegisterAs = "Teacher";

            } else {
                strRegisterAs = "Parent";

            }
        }
        tvRegisterAs.setText(getString(R.string.register_as) + strRegisterAs);


    }

    /**
     * Init get all roles ViewModel
     */
    private void initGetAllRolesViewModel() {
        rolesViewModel = new ViewModelProvider(this)
                .get(RolesViewModel.class);
    }

    /**
     * Observing on mutable state liveData changes
     * for the GetAllRolesViewModel
     */
    private void initObservingData() {
        rolesViewModel.getAllRoles()
                .observe(getViewLifecycleOwner(), response -> {
                    Log.e(TAG, "getAllBackEndRoles: " + response);
                    saveTheRolesOffline(response);
                });

        rolesViewModel.getError().observe(getViewLifecycleOwner(), error -> {
            if (error) {
                Toasty.error(getActivity(),
                        getString(R.string.error),
                        Toasty.LENGTH_LONG, true).show();
            }
        });

        rolesViewModel.startLoading().observe(getViewLifecycleOwner(), loading -> {
            if (loading) {
                mLoadingDialog.show();
            } else {
                mLoadingDialog.dismiss();
            }
        });
    }

    /**
     * Saving the back end fetched roles into shared pref
     * for offline storage
     *
     * @param response
     */
    private void saveTheRolesOffline(List<RoleResponse> response) {
        Prefs.edit().putString(Constants.MyPrefs.FETCHED_ROLES,
                mGson.toJson(response)).apply();
    }

    private boolean verifyData(String username,
                               String phoneOrEmail,
                               String password) {
        if (username.isEmpty()) {
            tilName.setErrorEnabled(true);
            highlightWithColor(etEnterUserName, R.color.red);
            tilName.setError(getString(R.string.err_enter_username));
            return true;
        }

        if (phoneOrEmail.isEmpty()) {
            tilEmaiPhone.setEnabled(true);
            tilName.setErrorEnabled(false);
            tilEmaiPhone.setError(getString(R.string.err_enter_phone_or_email));
            highlightWithColor(etEnterEmailOrPhone, R.color.red);
            etEnterEmailOrPhone.requestFocus();

            return true;
        }

        if (password.isEmpty()) {
            setErrorEnabledDefault(true);
            tilPassword.setError(getString(R.string.err_enter_password));
            highlightWithColor(etEnterPassword, R.color.red);
            etEnterPassword.requestFocus();
            return true;
        }
        return false;
    }

    /**
     * Before using any views, we need to inject ButterKnife by adding
     * below code in onCreate() method of the activity.
     * <p>
     * Butter Knife returns an Unbinder instance when you call bind to do this for you.
     * Call its unbind method in the appropriate lifecycle callback.
     */
//    private void bindingButter() {
//        mUnbinder = ButterKnife.bind(CreateAccountFragment.this, getView());
//    }
    private void onSuccess(StateData<BasicResponse> basicResponseStateData, String jwttoken) {
        String token = null;
        //check if it volley or retrofit
        if (basicResponseStateData == null) {
            token = jwttoken;
        } else {
            token = basicResponseStateData.getData().getJwttoken();
        }
        Prefs.edit().putString(Constants.REGISTER_TOKEN, token).apply();
        Log.e(TAG, "onSuccess: " + token);


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("code", "");
            jsonObject.put("email", etEnterEmailOrPhone.getText().toString());
            token = "jwt " + token;
            sendEmailVerificationToken(jsonObject.toString(), token);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * Clear all input fields for immediately for secure
     */
    private void clearAll() {
        etEnterPassword.getText().clear();
        etEnterEmailOrPhone.getText().clear();
        etEnterUserName.getText().clear();
    }

    /**
     * Passing the JSON object data as string and token to the create email
     * token for the Code generation purpose.
     *
     * @param data
     * @param token
     */
    private void sendEmailVerificationToken(String data, String token) {
        Log.e(TAG, "sendEmailVerificationToken: " + data);

        verifyTokenViewModel.postCreateEmailToken(data, token)
                .observe(getViewLifecycleOwner(), response -> {
                    Log.e(TAG, "sendEmailVerificationToken: " + response.toString());
                    onSuccessCreateEmailToken();
                });

        verifyTokenViewModel.getError().observe(getViewLifecycleOwner(), error -> {
            HttpException httpException = (HttpException) error;
            if (httpException != null) {
                try {
                    String errorBody = httpException.response().errorBody().string();
                    Log.e(TAG, "verifyMyEmailCodeNow: " + errorBody);
                    Snacks.showError(getActivity(), errorBody);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        verifyTokenViewModel.startLoading().observe(getViewLifecycleOwner(), loading -> {
            if (loading) {
                mLoadingDialog.show();
            } else {
                mLoadingDialog.dismiss();
            }
        });
    }

    /**
     * This method will be called after the email token created and sent
     * to the registered user eamil.
     */
    private void onSuccessCreateEmailToken() {
        Prefs.edit().putString(Constants.REGISTERED_EMAIL,
                etEnterEmailOrPhone.getText()
                        .toString()).apply();

        VerifyEnterCodeFragment verifyEnterCodeFragment =
                new VerifyEnterCodeFragment();
//        Bundle b = new Bundle();
//        b.putString(Constants.REGISTERED_EMAIL, etEnterEmailOrPhone.getText().toString());
//        verifyEnterCodeFragment.setArguments(b);
        ((MainActivity) getActivity()).pushFragment(verifyEnterCodeFragment);
//        Toasty.success(getActivity(), "Registered success")
//                .show();
        clearAll();
    }

    /**
     * String mobile,
     * String username,
     * String password,
     * List<RoleObject> roleObjects
     */
    private void registerRetrofit(String jsonObject) {

        registerVMRetrofit.postRegister(jsonObject)
                .observe(this, response -> {
                    Log.e(TAG, "observeRegister: " +
                            response.getJwttoken());
                    onSuccess(null, response.getJwttoken());
                });

        registerVMRetrofit.getError().observe(this, error -> {
            HttpException httpException = (HttpException) error;
            if (httpException != null) {
                try {
                    String errorBody = httpException.response().errorBody().string();
                    Log.e(TAG, "registerRetrofit: " + errorBody);
                    ErrorsMessages errorsMessages = mGson.fromJson(errorBody, ErrorsMessages.class);
                    Log.e(TAG, "registerRetrofit: " + errorsMessages.getErrorCode());
                    Snacks.showWarning(getActivity(), errorsMessages.getErrorMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        registerVMRetrofit.startLoading().observe(this, loading -> {
            if (loading) {
                mLoadingDialog.show();
            } else {
                mLoadingDialog.dismiss();
            }
        });

    }

    /**
     * @param v
     */
    @OnClick({R.id.btnLoginWithFacebook,
//            R.id.ivShowHidePass,
            R.id.btnContinue,
            R.id.tvBackToRoleChoice,
//            R.id.tvTermsOfServ,
//            R.id.tvPrivacyPolicy,
    })
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.btnLoginWithFacebook:

                break;
//            case R.id.ivShowHidePass:
//                if (v.getTag() != null) {
//                    hidePassword(v);
//                } else {
//                    showPassword(v);
//
//                }
//                break;
            case R.id.btnContinue:
                if (SiaUtils.isNetworkAvailable(getActivity())) {
                    registerUser();
                } else {
                    Snacks.showNetErrNoRetry(getActivity(), container);
                }
                break;
            case R.id.tvBackToRoleChoice:
                ((MainActivity) getActivity()).pushFragment(new StartFragment());
                break;
//            case R.id.tvTermsOfServ:
//                SiaUtils.openUrlWebBrowser(getActivity(), "www.google.com");
//                break;
//            case R.id.tvPrivacyPolicy:
//                SiaUtils.openUrlWebBrowser(getActivity(), "www.google.com");
//                break;
            default:
                break;

        }

    }

    private void hidePassword(View v) {
        v.setTag(null);
//        ivShowHidePass.setImageResource(R.drawable.visible);
        etEnterPassword.setTransformationMethod(new PasswordTransformationMethod());
    }

    private void showPassword(View v) {
//        ivShowHidePass.setImageResource(R.drawable.invisible);
        v.setTag(true);
        etEnterPassword.setTransformationMethod(null);
    }

    private void initializeLoadingDialog() {
        mLoadingDialog = DialogCreator.init(getActivity());
        mLoadingDialog.setCancelable(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbindingButter();

    }

    /**
     * Unbinding the ButterKnife view injection
     * set your bindings to null, freeing up memory
     */
    private void unbindingButter() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

}
