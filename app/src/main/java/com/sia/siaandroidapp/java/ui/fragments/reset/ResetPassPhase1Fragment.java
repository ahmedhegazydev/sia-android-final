package com.sia.siaandroidapp.java.ui.fragments.reset;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.sia.siaandroidapp.R;
import com.sia.siaandroidapp.java.ui.activities.MainActivity;
import com.sia.siaandroidapp.java.ui.fragments.LoginFragment;
import com.sia.siaandroidapp.java.ui.fragments.PasswordCodeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;

//public class ResetPassPhase1Fragment extends Fragment {
public class ResetPassPhase1Fragment extends DaggerFragment {

    private static final String TAG = "ResetPassPhase1Fragment";

    //    @BindView(R.id.etEnterEmailOrPhoneNumber)
//    EditText etEnterEmailOrPhoneNumber;
    @BindView(R.id.etEnterEmailOrPhoneNumber)
    TextInputEditText etEnterEmailOrPhoneNumber;

    public static ResetPassPhase1Fragment newInstance() {
        Bundle args = new Bundle();
        ResetPassPhase1Fragment fragment = new ResetPassPhase1Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reset_pass_phase1, container, false);
        mUnbinder  = ButterKnife.bind(ResetPassPhase1Fragment.this, rootView);
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


    }

    @OnClick({R.id.btnSendResetPassword,
            R.id.tvBackToLogin,
    })
    public void btnSendResetPassword(View v) {
        switch (v.getId()){
            case R.id.btnSendResetPassword:
                ((MainActivity) getActivity()).pushFragment(new PasswordCodeFragment());

                break;
                case R.id.tvBackToLogin:
                    ((MainActivity) getActivity()).pushFragment(new LoginFragment());

                    break;

        }
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

    private Unbinder mUnbinder;



}
