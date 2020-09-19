package com.sia.siaandroidapp.java.ui.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pixplicity.easyprefs.library.Prefs;
import com.sia.siaandroidapp.R;
import com.sia.siaandroidapp.java.constants.Constants;
import com.sia.siaandroidapp.java.ui.activities.MainActivity;
import com.sia.siaandroidapp.java.utils.SiaUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;

import static com.sia.siaandroidapp.java.constants.Constants.REG_AS_STUDENT;
import static com.sia.siaandroidapp.java.constants.Constants.REG_AS_TEACHER;

public class CompleteAccountAsFragment extends DaggerFragment {

    private static final String TAG = "CompleteAccountAs";

    @BindView(R.id.tvTitleCreateAccountAs)
    TextView tvTitleCreateAccountAs;
    @BindView(R.id.viewNationalId)
    View viewNationalId;
    @BindView(R.id.viewCityTeacher)
    View viewCityTeacher;
    @BindView(R.id.viewCityStudent)
    View viewCityStudent;
    @BindView(R.id.viewGrade)
    View viewGrade;
    @BindView(R.id.viewCurri)
    View viewCurri;
    @BindView(R.id.viewSchoolName)
    View viewSchoolName;
    @BindView(R.id.spinnerSpecialization)
    Spinner spinnerSpecialization;
    @BindView(R.id.viewSpecialization)
    View viewSpecialization;
    @BindView(R.id.spinnerCity)
    Spinner spinnerCity;
    @BindView(R.id.spinnerGrade)
    Spinner spinnerGrade;
    @BindView(R.id.spinnerCurri)
    Spinner spinnerCurri;
    @BindView(R.id.spinnerSchoolName)
    Spinner spinnerSchoolName;
    @BindView(R.id.spinnerCityStudent)
    Spinner spinnerCityStudent;
    private Unbinder mUnbinder;

    public static CompleteAccountAsFragment newInstance(Bundle bundle) {
//        Bundle args = new Bundle();
        CompleteAccountAsFragment fragment = new CompleteAccountAsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static CompleteAccountAsFragment newInstance() {
        Bundle args = new Bundle();
        CompleteAccountAsFragment fragment = new CompleteAccountAsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_account, container, false);
        mUnbinder = ButterKnife.bind(CompleteAccountAsFragment.this, rootView);
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

        getPassedRegisterAs();

        SiaUtils.setArrayAdapterToSpinner(getActivity(), spinnerSpecialization, R.array.arrays_specialization);
        SiaUtils.setArrayAdapterToSpinner(getActivity(), spinnerCity, R.array.arrays_city);
        SiaUtils.setArrayAdapterToSpinner(getActivity(), spinnerGrade, R.array.arrays_grade);
        SiaUtils.setArrayAdapterToSpinner(getActivity(), spinnerCurri, R.array.arrays_curri);
        SiaUtils.setArrayAdapterToSpinner(getActivity(), spinnerSchoolName, R.array.arrays_school_name);
        SiaUtils.setArrayAdapterToSpinner(getActivity(), spinnerCityStudent, R.array.arrays_city);



    }

    private void getPassedRegisterAs() {

        viewCityStudent.setVisibility(View.GONE);
        viewCurri.setVisibility(View.GONE);
        viewSchoolName.setVisibility(View.GONE);
        viewGrade.setVisibility(View.GONE);
        viewCityTeacher.setVisibility(View.GONE);
        viewNationalId.setVisibility(View.GONE);
        viewSpecialization.setVisibility(View.GONE);


//        if (getArguments() != null) {
//            if (getArguments().containsKey(Constants.REGISTER_AS)) {
//                String registerAs = getArguments().getString(Constants.REGISTER_AS);
//                if (registerAs != null) {
//                    switch (registerAs) {
//                        case REG_AS_STUDENT:
//                            Log.e(TAG, "getPassedRegisterAs: " + REG_AS_STUDENT);
//                            viewCityStudent.setVisibility(View.VISIBLE);
//                            viewCurri.setVisibility(View.VISIBLE);
//                            viewSchoolName.setVisibility(View.VISIBLE);
//                            viewGrade.setVisibility(View.VISIBLE);
//                            tvTitleCreateAccountAs.setText(getString(R.string.as_a_student));
//                            break;
//                        case REG_AS_TEACHER:
//                            viewCityTeacher.setVisibility(View.VISIBLE);
//                            viewNationalId.setVisibility(View.VISIBLE);
//                            viewSpecialization.setVisibility(View.VISIBLE);
//                            Log.e(TAG, "getPassedRegisterAs: " + REG_AS_TEACHER);
//                            tvTitleCreateAccountAs.setText(getString(R.string.as_a_teacher));
//                            break;
//                        default:
//                    }
//                }
//            }
//        }

//        Prefs.edit().putString(Constants.REGISTER_AS, REG_AS_STUDENT).apply();
        Prefs.edit().putString(Constants.REGISTER_AS, REG_AS_TEACHER).apply();
        String regAs = Prefs.getString(Constants.REGISTER_AS, REG_AS_TEACHER);
        if (TextUtils.equals(regAs, Constants.REG_AS_STUDENT)) {
            Log.e(TAG, "getPassedRegisterAs: " + REG_AS_STUDENT);
            viewCityStudent.setVisibility(View.VISIBLE);
            viewCurri.setVisibility(View.VISIBLE);
            viewSchoolName.setVisibility(View.VISIBLE);
            viewGrade.setVisibility(View.VISIBLE);
            tvTitleCreateAccountAs.setText(getString(R.string.as_a_student));

        } else {
            viewCityTeacher.setVisibility(View.VISIBLE);
            viewNationalId.setVisibility(View.VISIBLE);
            viewSpecialization.setVisibility(View.VISIBLE);
            Log.e(TAG, "getPassedRegisterAs: " + REG_AS_TEACHER);
            tvTitleCreateAccountAs.setText(getString(R.string.as_a_teacher));

        }


    }

    @OnClick({
            R.id.viewSpecialization,
            R.id.btnCreateAccount,
    })
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.viewSpecialization:
//                spinnerSpecialization.performClick();
                break;
            case R.id.btnCreateAccount:
                ((MainActivity) getActivity()).pushFragment(new KnowYouBetterMainFragment());
                break;
            default:
                break;
        }
    }


    /**
     * Called After the fragment detached
     */
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
