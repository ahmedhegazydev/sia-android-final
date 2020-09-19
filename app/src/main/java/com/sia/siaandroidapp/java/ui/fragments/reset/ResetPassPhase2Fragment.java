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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;

//public class ResetPassPhase2Fragment extends Fragment {
public class ResetPassPhase2Fragment extends DaggerFragment {


    private static final String TAG = "ResetPassPhase2Fragment";

    @BindView(R.id.etEnterNewPassword)
    TextInputEditText etEnterNewPassword;
    @BindView(R.id.etEnterConfPassword)
    TextInputEditText etEnterConfPassword;

    public static ResetPassPhase2Fragment newInstance() {
        Bundle args = new Bundle();
        ResetPassPhase2Fragment fragment = new ResetPassPhase2Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reset_pass_phase2, container, false);
        mUnbinder = ButterKnife.bind(ResetPassPhase2Fragment.this, rootView);
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

    @OnClick(R.id.btnResetPassword)
    public void btnResetPassword(View v) {
        ((MainActivity) getActivity()).pushFragment(new ResetPassPhase2Fragment());
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
