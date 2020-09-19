package com.sia.siaandroidapp.java.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.sia.siaandroidapp.R;
import com.sia.siaandroidapp.java.utils.SiaUtils;
import com.sia.siaandroidapp.java.utils.anim.AnimUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class TubeUploadActivity extends AppCompatActivity {

    private static final int RESULT_PICK_IMAGE_CROP = 4;
    private static final int RESULT_VIDEO_CAP = 5;
    private Uri mFileURI = null;

    @BindView(R.id.etEnterVideoTitle)
    TextInputEditText etEnterVideoTitle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SiaUtils.fullScreen(this);
        setContentView(R.layout.activity_tube_upload);
        ButterKnife.bind(this);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    @OnClick(R.id.pick_button)
    public void pickFile(View view) {

        if (!checkVideoTitle()){
            return;
        }

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("video/*");
        startActivityForResult(intent, RESULT_PICK_IMAGE_CROP);

    }

    @OnClick(R.id.record_button)
    public void recordVideo(View view) {

        if (!checkVideoTitle()){
            return;
        }


        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        // Workaround for Nexus 7 Android 4.3 Intent Returning Null problem
        // create a file to save the video in specific folder (this works for
        // video only)
        // mFileURI = getOutputMediaFile(MEDIA_TYPE_VIDEO);
        // intent.putExtra(MediaStore.EXTRA_OUTPUT, mFileURI);

        // set the video image quality to high
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

        // start the Video Capture Intent
        startActivityForResult(intent, RESULT_VIDEO_CAP);
    }

    private boolean checkVideoTitle() {
        if (etEnterVideoTitle.getText().toString().trim().length() == 0){
            AnimUtils.shakeView(etEnterVideoTitle);
            Toasty.error(getApplicationContext(), "Enter video title")
                    .show();
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_PICK_IMAGE_CROP:
                if (resultCode == RESULT_OK) {
                    mFileURI = data.getData();
                    if (mFileURI != null) {
                        Intent intent = new Intent(this, ReviewActivity.class);
                        intent.setData(mFileURI);
                        startActivity(intent);
                    }
                }
                break;

            case RESULT_VIDEO_CAP:
                if (resultCode == RESULT_OK) {
                    mFileURI = data.getData();
                    if (mFileURI != null) {
                        Intent intent = new Intent(this, ReviewActivity.class);
                        intent.setData(mFileURI);
                        startActivity(intent);
                    }
                }
                break;
        }
    }

}
