package com.zp.library_network.interceptor;

import android.text.TextUtils;

import com.zp.library_network.INetworkRequestInfo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author : zp
 * e-mail :
 * time : 2020-02-22 20:09
 * desc :
 * version: 1.0
 */
public class RequestIntercepter implements Interceptor {
    private INetworkRequestInfo networkRequestInfo;

    public RequestIntercepter(INetworkRequestInfo requestInfo) {
        this.networkRequestInfo = requestInfo;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        if (networkRequestInfo != null) {
            for (String key : networkRequestInfo.getRequestHeadMap().keySet()) {
                if (TextUtils.isEmpty(networkRequestInfo.getRequestHeadMap().get(key))) {
                    builder.addHeader(key, networkRequestInfo.getRequestHeadMap().get(key));
                }
            }
        }
        return (Response) chain.proceed(builder.build());
    }
}
