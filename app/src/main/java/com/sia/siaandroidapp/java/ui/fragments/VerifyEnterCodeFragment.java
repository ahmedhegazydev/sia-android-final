package com.sia.siaandroidapp.java.ui.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProvider;

import com.chaos.view.PinView;
import com.moos.library.HorizontalProgressView;
import com.pixplicity.easyprefs.library.Prefs;
import com.sia.siaandroidapp.R;
import com.sia.siaandroidapp.java.constants.Constants;
import com.sia.siaandroidapp.java.ui.activities.MainActivity;
import com.sia.siaandroidapp.java.ui.custom.TimerTextView;
import com.sia.siaandroidapp.java.ui.dialogs.DialogCreator;
import com.sia.siaandroidapp.java.ui.snacks.Snacks;
import com.sia.siaandroidapp.java.viewmodel.retrofit.VerifyCodeViewModel;
import com.sia.siaandroidapp.java.viewmodel.retrofit.VerifyTokenViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerFragment;
import es.dmoral.toasty.Toasty;
import retrofit2.HttpException;

public class VerifyEnterCodeFragment extends DaggerFragment {

    private static final String TAG = "VerifyEnterCodeFragment";
    @BindView(R.id.entryViewCode)
    PinView pinView;
    @BindView(R.id.nestedScroll)
    NestedScrollView nestedScrollView;
    @BindView(R.id.progressVerify)
    HorizontalProgressView horizontalProgressView;
    @BindView(R.id.tv_user_email)
    TextView tv_user_email;
    @BindView(R.id.tvResendVerificationCode)
    TextView tvResendVerificationCode;
    @BindView(R.id.tv_resend_after)
    TextView tv_resend_after;
    @BindView(R.id.timerText)
    TimerTextView timerTextView;
    @BindView(R.id.const_wait)
    View viewWaitAndProgress;
    private VerifyCodeViewModel verifyCodeViewModel;
    private String userEmailCode, registeredEmail;
    private VerifyTokenViewModel verifyTokenViewModel;
    private Dialog mLoadingDialog;
    private TextWatcher timerTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
//            Log.e(TAG, "afterTextChanged: " + s.toString());
            String subStr = s.toString().substring(3, s.length());
            Log.e(TAG, "afterTextChanged: " + subStr);
            if (TextUtils.equals(s.toString(), "00:00")) {
                timerTextView.stopTimer();
                viewWaitAndProgress.setVisibility(View.GONE);
                tvResendVerificationCode.setVisibility(View.VISIBLE);

            }
        }
    };

    public static VerifyEnterCodeFragment newInstance() {
        Bundle args = new Bundle();
        VerifyEnterCodeFragment fragment = new VerifyEnterCodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_verify_email, container, false);
        ButterKnife.bind(VerifyEnterCodeFragment.this, rootView);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");
    }

    private void initHorizontalProgressView() {
        horizontalProgressView.setProgressViewUpdateListener(new HorizontalProgressView.HorizontalProgressUpdateListener() {
            @Override
            public void onHorizontalProgressStart(View view) {

            }

            @Override
            public void onHorizontalProgressUpdate(View view, float progress) {

            }

            @Override
            public void onHorizontalProgressFinished(View view) {

            }
        });
    }

    private void initPintEntryEditTextView() {

    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //init progress dialogs
        mLoadingDialog = DialogCreator.init(getActivity());

        initHorizontalProgressView();

        initPintEntryEditTextView();

        //init ModelProvider
        verifyCodeViewModel = new ViewModelProvider(this)
                .get(VerifyCodeViewModel.class);
        verifyTokenViewModel = new ViewModelProvider(this)
                .get(VerifyTokenViewModel.class);

        //check if the token created success
//        if (getArguments() != null) {
//            if (getArguments().containsKey(Constants.REGISTERED_EMAIL)) {
//                registeredEmail = getArguments().getString(Constants.REGISTERED_EMAIL, "");
//                tv_user_email.setText(registeredEmail);
//                Sneaker.with(getActivity()) // Activity, Fragment or ViewGroup
////                        .setTitle(getString(R.string.email_craeted_success))
//                        .setMessage(getString(R.string.email_craeted_success))
//                        .sneakSuccess();
//            }
//        }
        registeredEmail = Prefs.getString(Constants.REGISTERED_EMAIL, "");
        tv_user_email.setText(registeredEmail);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Snacks.showSuccess(getActivity(), getString(R.string.email_craeted_success));
    }

    private void verifyMyEmailCodeNow() {
        String token = "jwt " + Prefs.getString(Constants.REGISTER_TOKEN, "");
        Log.e(TAG, "verifyMyEmailCodeNow: " + token);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("code", userEmailCode);
            jsonObject.put("email", registeredEmail);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e(TAG, "verifyMyEmailCodeNow: " + jsonObject.toString());

        verifyCodeViewModel.postVerifyCode(jsonObject.toString(), token)
                .observe(this, response -> {
                    Log.e(TAG, "verifyMyEmailCodeNow: " + response.toString());
//                    Toast.makeText(getActivity(), "Code matches", Toast.LENGTH_SHORT).show();
                    onVerifyCodeSuccess();
                });

        verifyCodeViewModel.getError().observe(this, error -> {
//                Toasty.error(getActivity(),
//                        getString(R.string.error),
//                        Toasty.LENGTH_LONG, true).show();

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

        verifyCodeViewModel.startLoading().observe(this, loading -> {
            if (loading) {
                mLoadingDialog.show();
            } else {
                mLoadingDialog.dismiss();
            }
        });


    }

    private void onVerifyCodeSuccess() {

////        Bundle bundle = new Bundle();
////        bundle.putString(Constants.REGISTER_AS, Constants.REG_AS_STUDENT);
////        bundle.putString(Constants.REGISTER_AS, Constants.REG_AS_TEACHER);
////        ((MainActivity)getActivity()).pushFragment(CompleteAccountAsFragment.newInstance(bundle));
        ((MainActivity)getActivity()).pushFragment(CompleteAccountAsFragment.newInstance());

    }

    @OnClick({R.id.btnVerifyMyEmail,
            R.id.tv_change_email,
            R.id.tvResendVerificationCode,
    })
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.btnVerifyMyEmail:
                userEmailCode = pinView.getText().toString();
                Log.e(TAG, "btnVerifyMyEmail: " + userEmailCode);
                Log.e(TAG, "btnVerifyMyEmail: " + registeredEmail);

                if (userEmailCode.isEmpty() || userEmailCode.length() != 4) {
//                    Toast.makeText(getActivity(), R.string.enter_sent_code,
//                            Toast.LENGTH_SHORT).show();
                    Snacks.showError(getActivity(), getString(R.string.enter_sent_code));
                } else {
                    verifyMyEmailCodeNow();
                }

                break;
            case R.id.tv_change_email:
                ((MainActivity) getActivity())
                        .pushFragment(new CreateAccountFragment());
                break;
            case R.id.tvResendVerificationCode:
                nestedScrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        nestedScrollView.fullScroll(View.FOCUS_DOWN);
                        resendVerificationCode();
                    }
                });
                break;
            default:
                break;
        }

    }

    private void resendVerificationCode() {

        viewWaitAndProgress.setVisibility(View.VISIBLE);
        tvResendVerificationCode.setVisibility(View.GONE);


//        int count_60_sec = 30000;
//        int num_of_min = 30;
//        new CountDownTimer(count_60_sec * num_of_min, 1000) {
//            public void onTick(long millisUntilFinished) {
//                tv_resend_after.setText(getString(R.string.resend_available_in) +
//                        millisUntilFinished / 1000);
//            }
//            public void onFinish() {
//                tv_resend_after.setText("done!");
//            }
//
//        }.start();

        int numOfSec = 6;
        int progressDuration = numOfSec * 1000;
        final int factorForFillingProgress = 1000;
//        long futureTimestamp = System.currentTimeMillis() + (numOfSec * 60 * 60 * 1000);//with hours
//        long futureTimestamp = System.currentTimeMillis() + (numOfSec * 60 * 1000);//no hours
        long futureTimestamp = System.currentTimeMillis() + (progressDuration);//no hours //no min //no sugar
        timerTextView.setEndTime(futureTimestamp);
        timerTextView.addTextChangedListener(timerTextWatcher);


        horizontalProgressView.setProgressDuration(progressDuration - factorForFillingProgress);
        horizontalProgressView.startProgressAnimation();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("code", "");
            jsonObject.put("email", Prefs.getString(Constants.REGISTERED_EMAIL, ""));
//            jsonObject.put("email", "lalibax434@psk3n.com");
            Log.e(TAG, "resendVerificationCode: " + jsonObject.toString() );
            String token = "jwt " + Prefs.getString(Constants.REGISTER_TOKEN, "");
            sendEmailVerificationToken(jsonObject.toString(), token);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void initializeLoadingDialog() {
        mLoadingDialog = DialogCreator.init(getActivity());
        mLoadingDialog.setCancelable(false);
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

    private void onSuccessCreateEmailToken() {
        Log.e(TAG, "onSuccessCreateEmailToken: ");
        Snacks.showSuccess(getActivity(), getString(R.string.enter_sent_code));
    }
}
