package com.zp.newsdemo.application;

import com.zp.library_base.BaseApplication;
import com.zp.newsdemo.BuildConfig;

/**
 * author : zp
 * e-mail :
 * time : 2020-02-13 14:37
 * desc :
 * version: 1.0
 */
public class NewsApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        setDebug(BuildConfig.DEBUG);//注意要使用NewsApplication的BuildConfig
    }
}
