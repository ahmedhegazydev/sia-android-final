package com.sia.siaandroidapp.java.ui.fragments.know_u_better;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sia.siaandroidapp.R;
import com.sia.siaandroidapp.java.base.BaseFragment;
import com.sia.siaandroidapp.java.utils.SiaUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

//public class KnowUBetter2Fragment extends Fragment {
//public class KnowUBetter2Fragment extends DaggerFragment {
public class KnowUBetter2Fragment extends BaseFragment {

    private static final String TAG = "KnowUBetter2Fragment";
    @BindView(R.id.spinnerYourGoal)
    Spinner spinnerYourGoal;
    @BindView(R.id.spinnerGoalPeriod)
    Spinner spinnerGoalPeriod;


    public static KnowUBetter2Fragment newInstance() {
        Bundle args = new Bundle();
        KnowUBetter2Fragment fragment = new KnowUBetter2Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    // newInstance constructor for creating fragment with arguments
    public static KnowUBetter2Fragment newInstance(int page, String title) {
        KnowUBetter2Fragment fragmentFirst = new KnowUBetter2Fragment();
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
        View rootView = inflater.inflate(R.layout.fragment_know_you_2,
                container, false);
//        ButterKnife.bind(KnowUBetter2Fragment.this, getView());
        ButterKnife.bind(KnowUBetter2Fragment.this, rootView);
//        return getView();
        return rootView;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_know_you_2;
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

        Log.e(TAG, "onViewCreated: WoWoW");

        SiaUtils.setArrayAdapterToSpinner(getActivity(),
                spinnerGoalPeriod, R.array.arrays_goal_period);
        SiaUtils.setArrayAdapterToSpinner(getActivity(),
                spinnerYourGoal, R.array.arrays_your_goal);



    }


}
