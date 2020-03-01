package com.zp.library_network.interceptor;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * author : zp
 * e-mail :
 * time : 2020-02-22 20:09
 * desc : 响应拦截器
 * version: 1.0
 */
public class ResponseInterceptor implements Interceptor {
    public ResponseInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String url = request.url().toString();
        long startRequestTime = System.currentTimeMillis();
        Response response = null;
        try {
            response = chain.proceed(request);
        } catch (SocketTimeoutException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        String rawJson = response.body() == null ? "" : response.body().string();

        return response.newBuilder().body(ResponseBody.create(response.body().contentType(), rawJson)).build();
    }
}
