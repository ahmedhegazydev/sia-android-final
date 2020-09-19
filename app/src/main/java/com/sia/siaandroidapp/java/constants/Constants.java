package com.sia.siaandroidapp.java.constants;

import com.pixplicity.easyprefs.library.Prefs;

public final class Constants {

    //    public static final String BASE_URL = "http://cdfd613f.ngrok.io/";
//    public static final String BASE_URL = getBaseUrl();
    public static final String BASE_URL = "http://3.214.226.245:30316/";//AWS
    public static final String BASE_URL_UPLOAD_FILE = getBaseUrlUploadVid();
    public static final String REGISTER_TOKEN = "REGISTER_TOKEN";
    public static final long MILL_SEC = 1500;
    public static final String REGISTER_AS = "REGISTER_AS";

    public static final String REG_AS_TEACHER = "REG_AS_TEACHER";
    public static final String REG_AS_STUDENT = "REG_AS_STUDENT";
    public static final String REG_AS_PARENT = "REG_AS_STUDENT";

    public static final String SUCCESS_CREATE_EMAIL_TOKEN = "CREATE_TOEKN";
    public static final String REGISTERED_EMAIL = "REGISTERED_EMAIL";
    public static final String JSON_OBJ_EMAIL = "email";
    public static final String JSON_OBJ_PHONE = "mobile";
    public static final String ROLE_PARENT = "PARENT";
    public static final String ROLE_STUDENT = "STUDENT";
    public static final String ROLE_TEACHER = "TEACHER";
    public static final int ERR_CODE_519 = 519;


    private static String getBaseUrl() {
        return Prefs.getString(Constants.BASE_URL,
                "http://cdfd613f.ngrok.io/");
    }

    private static String getBaseUrlUploadVid() {
        return Prefs.getString(Constants.BASE_URL,
                "http://cdfd613f.ngrok.io/");
    }

    //Register as
    public static final String SIGN_UP_AS = "SIGN_UP_AS";
    public static final String SIGN_UP_AS_STUDENT = "STUDENT";
    public static final String SIGN_UP_AS_PARENT = "PARENT";
    public static final String SIGN_UP_AS_TEACHER = "TEACHER";
    public static final String SIGN_UP_AS_ADMIN = "ADMIN";

    //End points
    public static final String LOGIN = "api/v1/sia/authenticate";
    public static final String REGISTER = "api/v1/sia/register";
    public static final String UPLOAD_VIDEO = "video/upload";
    public static final String END_GET_ALL_ROLES = "api/v1/sia/roles";
    public static final String END_VERIFY_CREATE_EMAIL_TOKEN = "api/v1/sia/email-verification";
    public static final String END_VERIFY_EMAIL_CODE = "api/v1/sia/verify-email";


    public class MyPrefs {
        public static final String SAVED_USER_INFO = "SAVED_USER_INFO";
        public static final String EMAIL = "EMAIL";
        public static final String PASSWORD = "PASSWORD";
        public static final String PREFS_NAME = "SiaWorld";
        public static final String IS_LOGIN = "is_login";

        public static final String FETCHED_ROLES = "FETCHED_ROLES";
    }


}
