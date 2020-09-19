package com.sia.siaandroidapp.java.rest.main;



import com.sia.siaandroidapp.java.constants.Constants;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface IrApiService {
    @Multipart
    @POST(Constants.UPLOAD_VIDEO)
//    Call<BasicResponse<FileResponse>> doUploadProfilePicture(@Header("Authorization") String token, @Part MultipartBody.Part file);
//    Call<BasicResponse<FileResponse>> doUploadProfilePicture(@Part MultipartBody.Part file);
    Call<ResponseBody> doUploadProfilePicture(@Part MultipartBody.Part file);
}
