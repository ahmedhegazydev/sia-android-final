package com.sia.siaandroidapp.java.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sia.siaandroidapp.R;
import com.sia.siaandroidapp.java.utils.SiaUtils;
import com.sia.siaandroidapp.java.viewmodel.UploadAttachmentViewModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import okhttp3.ResponseBody;
import retrofit2.Response;

//import android.support.v4.app.NavUtils;

public class ReviewActivity extends AppCompatActivity implements UploadAttachmentViewModel.UploadImageHandler {
    public static ProgressDialog mProgressDialog;
    VideoView mVideoView;
    MediaController mc;
    private String mChosenAccountName;
    private Uri mFileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
        SiaUtils.fullScreen(this);
        setContentView(R.layout.activity_review);
        ButterKnife.bind(this);


        Button uploadButton = (Button) findViewById(R.id.upload_button);
        Intent intent = getIntent();
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            uploadButton.setVisibility(View.GONE);
            setTitle(R.string.playing_the_video_in_upload_progress);
        }
        mFileUri = intent.getData();

        reviewVideo(mFileUri);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getResources().getString(R.string.loading_msg));
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setMax(100);


    }

    private void reviewVideo(Uri mFileUri) {
        try {
            mVideoView = (VideoView) findViewById(R.id.videoView);
            mc = new MediaController(this);
            mVideoView.setMediaController(mc);
            mVideoView.setVideoURI(mFileUri);
            mc.show();
            mVideoView.start();
        } catch (Exception e) {
            Log.e(this.getLocalClassName(), e.toString());
        }
    }

    public void uploadVideo(View view) {
        // if a video is picked or recorded.
        if (mFileUri != null) {
//            Toast.makeText(this, R.string.youtube_upload_started,
//                    Toast.LENGTH_LONG).show();

            File file = new File(this.getCacheDir(), getFileName(mFileUri));
            int maxBufferSize = 1 * 1024 * 1024;
            try {
                InputStream inputStream = getContentResolver().openInputStream(mFileUri);
                Log.e("InputStream Size", "Size " + inputStream);
                int bytesAvailable = inputStream.available();
                int bufferSize = Math.min(bytesAvailable, maxBufferSize);
                final byte[] buffers = new byte[bufferSize];
//                tvAttachment.setText(getFileName(uri));
                FileOutputStream outputStream = new FileOutputStream(file);
                int read = 0;
                while ((read = inputStream.read(buffers)) != -1) {
                    outputStream.write(buffers, 0, read);
                }
                Log.e("File Size", "Size " + file.length());
                inputStream.close();
                outputStream.close();

                Log.e("File Path", "Path " + file.getPath());
                Log.e("File Size", "Size " + file.length());

                if (file.length() > 0) {
                    UploadAttachmentViewModel uploadAttachmentViewModel =
                            new UploadAttachmentViewModel(getApplicationContext(),
                                    this);
                    uploadAttachmentViewModel.doUploadAttachment(file);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }

        }
    }

    public String getFileName(Uri uri) {
        Cursor mCursor = getApplicationContext().getContentResolver().query(uri, null, null, null, null);
        int indexedname = mCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        mCursor.moveToFirst();
        String filename = mCursor.getString(indexedname);
        mCursor.close();
        return filename;
    }

    @Override
    public void onProgressUpdate(int percentage) {
        mProgressDialog.show();
        mProgressDialog.setProgress(percentage);
    }

    @Override
    public void onError() {
        mProgressDialog.dismiss();
        Toasty.error(this, getString(R.string.ef_error_create_image_file)).show();

    }

    @Override
    public void onFinish(Response<ResponseBody> imageResponse) {
        mProgressDialog.setProgress(100);
        mProgressDialog.dismiss();
        Toasty.success(this, getString(R.string.success)).show();
        finish();
    }
}
