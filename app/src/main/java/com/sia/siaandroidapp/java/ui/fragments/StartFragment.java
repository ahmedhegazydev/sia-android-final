package com.sia.siaandroidapp.java.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.pixplicity.easyprefs.library.Prefs;
import com.sia.siaandroidapp.R;
import com.sia.siaandroidapp.java.constants.Constants;
import com.sia.siaandroidapp.java.interfaces.IOnBackPressed;
import com.sia.siaandroidapp.java.model.Role;
import com.sia.siaandroidapp.java.ui.activities.MainActivity;

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
public class StartFragment extends Fragment implements IOnBackPressed {

    private ArrayList<Role> mListRolesSTP = null;
    private Gson mGson = new Gson();
    private Unbinder mUnbinder;
//    private View mRootView;

    /**
     * For creating a new StartFragment Instance
     * from everywhere and passing args to it.
     *
     * @return
     */
    public static StartFragment newInstance() {
        Bundle args = new Bundle();
        StartFragment fragment = new StartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_start, container, false);
        mUnbinder = ButterKnife.bind(StartFragment.this, mRootView);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mListRolesSTP = generateSTPRolesList();

    }

    /**
     * Before using any views, we need to inject ButterKnife by adding
     * below code in onCreate() method of the activity.
     * <p>
     * Butter Knife returns an Unbinder instance when you call bind to do this for you.
     * Call its unbind method in the appropriate lifecycle callback.
     */
//    private void bindingButter() {
//        mUnbinder = ButterKnife.bind(StartFragment.this, mRootView);
//    }

    /**
     * Redirecting to the  CreateAccountFragment with the convenient selected
     * role by user, and for creating the account with this a specific role.
     *
     * @param position
     */
    private void goToCreateAccountFrag(int position) {
//        Role role = mListRolesSTP.get(position);
//        role.setSelectedPos(position);
//        Bundle bundle = new Bundle();
//        bundle.putString(Constants.SIGN_UP_AS, mGson.toJson(role, Role.class));
//        CreateAccountFragment createAccountFragment = CreateAccountFragment.newInstance(bundle);
        CreateAccountFragment createAccountFragment = CreateAccountFragment.newInstance();
        ((MainActivity) Objects.requireNonNull(getActivity())).pushFragment(createAccountFragment);
    }

    /**
     * Generate the STP/Student Teacher Parent existing Roles
     * That the user will select one from them before redirecting to the
     * CreateAccountFragment for passing the selected one Role model
     * to CreateAccountFragment arguments
     *
     * @return
     */
    private ArrayList<Role> generateSTPRolesList() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new Role(getString(R.string.i_m_a_student),
                getString(R.string.i_want_to_study_and_view_courses_through_sia),
                R.drawable.avatar_student, Constants.ROLE_STUDENT));
        roles.add(new Role(getString(R.string.i_m_a_teacher),
                getString(R.string.i_want_to_upload_courses_to_help_srudents_through_sia),
                R.drawable.avatar_teacher, Constants.ROLE_TEACHER));
        roles.add(new Role(getString(R.string.i_m_a_parent),
                getString(R.string.i_want_to_keep_track_of_my_kids_progress),
                R.drawable.avatar_parent, Constants.ROLE_PARENT));
        return roles;
    }

    /**
     * Handling user views Clicks
     *
     * @param v
     */
    @OnClick({R.id.layoutStudent,
            R.id.layoutTeacher,
            R.id.layoutParent,
            R.id.btnLoginStart,

    })
    public void onUserClick(View v) {
        switch (v.getId()) {
            case R.id.layoutStudent:
                goToCreateAccountFrag(0);
                Prefs.edit().putString(Constants.REGISTER_AS,
                        Constants.REG_AS_STUDENT).apply();
                break;
            case R.id.layoutTeacher:
                goToCreateAccountFrag(1);
                Prefs.edit().putString(Constants.REGISTER_AS,
                        Constants.REG_AS_TEACHER).apply();
                break;
            case R.id.layoutParent:
                goToCreateAccountFrag(2);
                Prefs.edit().putString(Constants.REGISTER_AS,
                        Constants.REG_AS_PARENT).apply();
                break;
            case R.id.btnLoginStart:
                ((MainActivity) Objects.requireNonNull(getActivity())).pushFragment(new LoginFragment());
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

    @Override
    public boolean onBackPressed() {
        return true;//for preventing onBack
    }
}
