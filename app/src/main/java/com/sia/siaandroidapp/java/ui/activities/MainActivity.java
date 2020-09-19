package com.sia.siaandroidapp.java.ui.activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.CompositeMultiplePermissionsListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.multi.SnackbarOnAnyDeniedMultiplePermissionsListener;
import com.karumi.dexter.listener.single.CompositePermissionListener;
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.karumi.dexter.listener.single.SnackbarOnDeniedPermissionListener;
import com.sia.siaandroidapp.R;
import com.sia.siaandroidapp.java.base.BaseActivity;
import com.sia.siaandroidapp.java.dexter.SampleBackgroundThreadPermissionListener;
import com.sia.siaandroidapp.java.dexter.SampleErrorListener;
import com.sia.siaandroidapp.java.dexter.SampleMultiplePermissionListener;
import com.sia.siaandroidapp.java.dexter.SamplePermissionListener;
import com.sia.siaandroidapp.java.model.events.MsgEvtAllGranted;
import com.sia.siaandroidapp.java.model.events.MsgEvtRoleSelectWithDenied;
import com.sia.siaandroidapp.java.ui.fragments.CompleteAccountAsFragment;
import com.sia.siaandroidapp.java.ui.fragments.CreateAccountFragment;
import com.sia.siaandroidapp.java.ui.fragments.StartFragment;
import com.sia.siaandroidapp.java.ui.fragments.VerifyEnterCodeFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//public class MainActivity extends AppCompatActivity {
public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    @BindView(android.R.id.content)
    View contentView;
    private MultiplePermissionsListener allPermissionsListener;
    private PermissionListener cameraPermissionListener;
    private PermissionListener contactsPermissionListener;
    private PermissionListener audioPermissionListener;
    private PermissionRequestErrorListener errorListener;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        SiaUtils.fullScreen(this);
//        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        createPermissionListeners();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


//        checkDexterPermissions();


//        pushFragment(new VerifyEnterCodeFragment());
        //$$$$$$$$$$$$$$$$$$$$$$$$$$
//        Bundle bundle = new Bundle();
//        bundle.putString(Constants.REGISTER_AS, Constants.REG_AS_STUDENT);
//        bundle.putString(Constants.REGISTER_AS, Constants.REG_AS_TEACHER);
//        pushFragment(CompleteAccountAsFragment.newInstance(bundle));
//        pushFragment(CompleteAccountAsFragment.newInstance());
        //$$$$$$$$$$$$$$$$$$$$$$$$$$
//        pushFragment(new StartFragment());
        ////$$$$$$$$$$$$$$$$$$$$$$$$$$
//        pushFragment(new KnowUBetter1Fragment());
//        pushFragment(new KnowUBetter2Fragment());
//        pushFragment(new KnowUBetter3Fragment());
//        pushFragment(new KnowUBetter4Fragment());
//        pushFragment(new KnowUMainFragment());
//        $$$$$$$$$$$$$$$$$$$$$$$$$$
//        pushFragment(new ResetPassPhase1Fragment());
//        pushFragment(new ResetPassPhase2Fragment());
//        pushFragment(new PasswordCodeFragment());
//        pushFragment(new VerifyEnterCodeFragment());
//        pushFragment(new LoginFragment());
        pushFragment(new CreateAccountFragment());


    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    private void checkDexterPermissions() {
        Dexter.withContext(getApplicationContext())
                .withPermissions(Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(allPermissionsListener)
                .withErrorListener(errorListener)
                .check();
    }

    public void pushFragment(Fragment selectedFragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                .replace(R.id.fragment_container, selectedFragment)
                .addToBackStack(null)
                .commit();

    }

    public void clearFragmentStack() {
        FragmentManager fm = getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); i++) {
            fm.popBackStack();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }

        if (unbinder != null){
            unbinder.unbind();
        }
    }

    private void createPermissionListeners() {
        PermissionListener feedbackViewPermissionListener = new SamplePermissionListener(this);
        MultiplePermissionsListener feedbackViewMultiplePermissionListener =
                new SampleMultiplePermissionListener(this);

        allPermissionsListener =
                new CompositeMultiplePermissionsListener(feedbackViewMultiplePermissionListener,
                        SnackbarOnAnyDeniedMultiplePermissionsListener.Builder.with(contentView,
                                R.string.all_permissions_denied_feedback)
                                .withOpenSettingsButton(R.string.permission_rationale_settings_button_text)
                                .build()
                );
        contactsPermissionListener = new CompositePermissionListener(feedbackViewPermissionListener,
                SnackbarOnDeniedPermissionListener.Builder.with(contentView,
                        R.string.contacts_permission_denied_feedback)
                        .withOpenSettingsButton(R.string.permission_rationale_settings_button_text)
                        .withCallback(new Snackbar.Callback() {
                            @Override
                            public void onShown(Snackbar snackbar) {
                                super.onShown(snackbar);
                            }

                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {
                                super.onDismissed(snackbar, event);
                            }
                        })
                        .build());

        PermissionListener dialogOnDeniedPermissionListener =
                DialogOnDeniedPermissionListener.Builder.withContext(this)
                        .withTitle(R.string.audio_permission_denied_dialog_title)
                        .withMessage(R.string.audio_permission_denied_dialog_feedback)
                        .withButtonText(android.R.string.ok)
                        .withIcon(R.drawable.sia_logo_hoho_light)
//                        .withIcon(R.drawable.sia_logo_holo_dark)
                        .build();
        audioPermissionListener = new CompositePermissionListener(feedbackViewPermissionListener,
                dialogOnDeniedPermissionListener);
        cameraPermissionListener = new SampleBackgroundThreadPermissionListener(this);

        errorListener = new SampleErrorListener();
    }

    public void showPermissionGranted(String permission) {
//        Toast.makeText(this, permission, Toast.LENGTH_SHORT).show();
//        TextView feedbackView = getFeedbackViewForPermission(permission);
//        feedbackView.setText(R.string.permission_granted_feedback);
//        feedbackView.setTextColor(ContextCompat.getColor(this, R.color.permission_granted));

//        Toast.makeText(this, R.string.permission_granted_feedback
//                , Toast.LENGTH_SHORT).show();

        EventBus.getDefault().post(new MsgEvtAllGranted(true));

    }

    public void showPermissionDenied(String permission, boolean isPermanentlyDenied) {
//        TextView feedbackView = getFeedbackViewForPermission(permission);
//        feedbackView.setText(isPermanentlyDenied ? R.string.permission_permanently_denied_feedback
//                : R.string.permission_denied_feedback);
//        feedbackView.setTextColor(ContextCompat.getColor(this, R.color.permission_denied));

//        Toast.makeText(this, isPermanentlyDenied ?
//                R.string.permission_permanently_denied_feedback
//                : R.string.permission_denied_feedback,
//                Toast.LENGTH_SHORT).show();

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void showPermissionRationale(final PermissionToken token) {
        new AlertDialog.Builder(this).setTitle(R.string.permission_rationale_title)
                .setMessage(R.string.permission_rationale_message)
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        token.cancelPermissionRequest();
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        token.continuePermissionRequest();
                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        token.cancelPermissionRequest();
                    }
                })
                .show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRoleSelectedWithDeniedPerm(MsgEvtRoleSelectWithDenied evt) {
        if (evt != null) {
            checkDexterPermissions();
        }
    }

    @Override
    public void onBackPressed() {
        Log.e(TAG, "onBackPressed: ");
        finish();
//        Fragment fragment = getSupportFragmentManager()
//                .findFragmentById(R.id.fragment_container);
//        if (!(fragment instanceof IOnBackPressed) ||
//                !((IOnBackPressed) fragment).onBackPressed()) {
//            Log.e(TAG, "onBackPressed: 1");
//            super.onBackPressed();
//        }else {
//            Log.e(TAG, "onBackPressed: 2");
//        }
    }


}
