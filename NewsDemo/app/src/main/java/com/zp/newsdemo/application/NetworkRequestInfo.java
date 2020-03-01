package com.zp.newsdemo.application;

import com.zp.library_network.BuildConfig;
import com.zp.library_network.INetworkRequestInfo;

import java.util.HashMap;

/**
 * author : zp
 * e-mail :
 * time : 2020-02-28 15:32
 * desc : 实例化请求头信息
 * version: 1.0
 */
public class NetworkRequestInfo implements INetworkRequestInfo {
    HashMap<String, String> headerMap = new HashMap<>();

    public NetworkRequestInfo() {
        headerMap.put("os", "android");
        headerMap.put("versionName", BuildConfig.VERSION_NAME);
        headerMap.put("versionCode", String.valueOf(BuildConfig.VERSION_CODE));
        headerMap.put("applicationId", BuildConfig.APPLICATION_ID);
    }

    @Override
    public HashMap<String, String> getRequestHeadMap() {
        return headerMap;
    }

    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}
