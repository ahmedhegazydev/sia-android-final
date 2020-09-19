package com.sia.siaandroidapp.java.ui.fragments.splash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.sia.siaandroidapp.R;
import com.sia.siaandroidapp.java.constants.Constants;
import com.sia.siaandroidapp.java.interfaces.IOnBackPressed;
import com.sia.siaandroidapp.java.model.Role;
import com.sia.siaandroidapp.java.ui.activities.MainActivity;
import com.sia.siaandroidapp.java.ui.fragments.CreateAccountFragment;
import com.sia.siaandroidapp.java.ui.fragments.LoginFragment;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * The StartFragment Class that will be pushed after as first fragment child
 * in the MainActivity parent after the splash screen finishing.
 *
 * @author Ahmed Hegazy
 * @version 1.0
 * @since June 3, 2020
 */
public class SplashFragment extends Fragment {

    private ArrayList<Role> mListRolesSTP = null;
    private Gson mGson = new Gson();
    private Unbinder mUnbinder;
    private View mRootView;

    /**
     * For creating a new StartFragment Instance
     * from everywhere and passing args to it.
     *
     * @return
     */
    public static SplashFragment newInstance() {
        Bundle args = new Bundle();
        SplashFragment fragment = new SplashFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_start, container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
