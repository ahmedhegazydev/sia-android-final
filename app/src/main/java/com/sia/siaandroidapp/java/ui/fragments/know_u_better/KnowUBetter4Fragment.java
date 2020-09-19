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

//public class KnowUBetter4Fragment extends Fragment {
public class KnowUBetter4Fragment extends DaggerFragment {

    private static final String TAG = "KnowUBetter4Fragment";

    public static KnowUBetter4Fragment newInstance() {
        Bundle args = new Bundle();
        KnowUBetter4Fragment fragment = new KnowUBetter4Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    // newInstance constructor for creating fragment with arguments
    public static KnowUBetter4Fragment newInstance(int page, String title) {
        KnowUBetter4Fragment fragmentFirst = new KnowUBetter4Fragment();
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
        ButterKnife.bind(KnowUBetter4Fragment.this, rootView);
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
