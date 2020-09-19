package com.sia.siaandroidapp.java.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sia.siaandroidapp.R;
import com.sia.siaandroidapp.java.ui.activities.MainActivity;
import com.sia.siaandroidapp.java.ui.fragments.reset.ResetPassPhase1Fragment;
import com.sia.siaandroidapp.java.ui.fragments.reset.ResetPassPhase2Fragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerFragment;

//public class PasswordCodeFragment extends Fragment {
public class PasswordCodeFragment extends DaggerFragment {


    private static final String TAG = "PasswordCodeFragment";

    public static PasswordCodeFragment newInstance() {
        Bundle args = new Bundle();
        PasswordCodeFragment fragment = new PasswordCodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pass_code, container, false);
        ButterKnife.bind(PasswordCodeFragment.this, rootView);
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

    @OnClick(R.id.btnConfirmCode)
    public void btnConfirmCode(View v) {
        ((MainActivity) getActivity()).pushFragment(new ResetPassPhase2Fragment());
    }

    @OnClick(R.id.tvChangeEmailAddress)
    public void tvChangeEmailAddress(View v) {
        ((MainActivity) getActivity()).pushFragment(new ResetPassPhase1Fragment());
    }


    @OnClick(R.id.tvResendVerificationCode)
    public void tvResendVerificationCode(View v) {

    }


}
