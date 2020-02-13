package com.zp.library_base;

import android.app.Application;

/**
 * author : zp
 * e-mail :
 * time : 2020-02-13 14:37
 * desc :
 * version: 1.0
 */
public class BaseApplication extends Application {

    //OOM won't happen.
    public static Application sApplication;
    public static boolean sDebug;

    public void setDebug(boolean isDebug) {
        sDebug = isDebug;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sApplication = this;
    }
}
