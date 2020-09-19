package com.sia.siaandroidapp.java.viewmodel;

import android.content.Context;
import android.util.Log;

import com.sia.siaandroidapp.java.rest.main.ApiUtilise;
import com.sia.siaandroidapp.java.rest.main.IrApiService;
import com.sia.siaandroidapp.java.utils.volley.ProgressRequestBody;

import org.jetbrains.annotations.NotNull;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadAttachmentViewModel implements ProgressRequestBody.UploadCallbacks {

    private Context mContext;
    private UploadImageHandler handler;

    public UploadAttachmentViewModel(Context mContext, UploadImageHandler handler) {
        this.mContext = mContext;
        this.handler = handler;
    }

    @Override
    public void onProgressUpdate(int percentage) {
        handler.onProgressUpdate(percentage);
    }

    private static final String TAG = "UploadAttachmentViewMod";


    public void doUploadAttachment(File file) {
        IrApiService uploadAPIs = ApiUtilise.getAPIService();
        //Create a file object using file path

        final ProgressRequestBody fileBody = new ProgressRequestBody(file, "image", this);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), fileBody);
//        String token = PreferenceProcessor.getInstance(mContext)
//                .getStr(MyConfig.MyPrefs.TOKEN, "");
//        String auth = "Bearer " + token;
//        String auth = "Bearer ";
//        Call<BasicResponse<FileResponse>> call = uploadAPIs.doUploadProfilePicture(filePart);
        Call<ResponseBody> call = uploadAPIs.doUploadProfilePicture(filePart);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e(TAG, "onResponse: " + response.message() );
                //                if (response.message().equals("OK")) {
//                    if (response.body().getSuccessful()) {
                handler.onFinish(response);
//                    }
//                } else {
//                    Log.e("ResponseUpload", response.message());
//                    handler.onError();
//                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, Throwable t) {
                Log.e("ResponseUpload", t.getMessage());
                handler.onError();

            }
        });

    }

    public interface UploadImageHandler {

        void onProgressUpdate(int percentage);

        void onError();

        //        void onFinish(BasicResponse<FileResponse> imageResponse);
        void onFinish(Response<ResponseBody> imageResponse);


    }
}
