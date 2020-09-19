package com.sia.siaandroidapp.java.ui.fragments.know_u_better;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sia.siaandroidapp.R;

import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

//public class KnowUBetter3Fragment extends Fragment {
public class KnowUBetter3Fragment extends DaggerFragment {


    private static final String TAG = "KnowUBetter3Fragment";

    public static KnowUBetter3Fragment newInstance() {
        Bundle args = new Bundle();
        KnowUBetter3Fragment fragment = new KnowUBetter3Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    // newInstance constructor for creating fragment with arguments
    public static KnowUBetter3Fragment newInstance(int page, String title) {
        KnowUBetter3Fragment fragmentFirst = new KnowUBetter3Fragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_know_you_1,
                container, false);
        ButterKnife.bind(KnowUBetter3Fragment.this, rootView);
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


}
