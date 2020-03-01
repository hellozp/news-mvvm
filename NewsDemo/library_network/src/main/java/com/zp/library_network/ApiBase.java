package com.zp.library_network;

import android.text.TextUtils;

import com.zp.library_network.interceptor.RequestIntercepter;
import com.zp.library_network.interceptor.ResponseInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author : zp
 * e-mail :
 * time : 2020-02-22 20:06
 * desc :
 * version: 1.0
 */
public class ApiBase {

    protected Retrofit retrofit;
    protected static INetworkRequestInfo networkRequestInfo;

    private static RequestIntercepter requestIntercepter;
    private static ResponseInterceptor responseInterceptor;

    protected ApiBase(String baseUrl) {

        retrofit = new Retrofit
                .Builder()
                .client(getOkHttpClient())//实例化OkHttpClent
                .baseUrl(baseUrl)//主机地址
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static void setNetworkRequestInfo(INetworkRequestInfo requestInfo) {
        networkRequestInfo = requestInfo;
        requestIntercepter = new RequestIntercepter(requestInfo);
        responseInterceptor = new ResponseInterceptor();
    }

    /**
     * 初始化OkHttpClent 拦截器、缓存机制、重连机制、https校验
     *
     * @return OkHttpClent
     */
    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder mOkHttpClientBuilder = new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS);

        mOkHttpClientBuilder.addInterceptor(requestIntercepter);
        mOkHttpClientBuilder.addInterceptor(responseInterceptor);
        OkHttpClient okHttpClient = mOkHttpClientBuilder.build();
        okHttpClient.dispatcher().setMaxRequestsPerHost(20);

        return okHttpClient;
    }

    /**
     * 调式模式打印请求参数和返回结果
     */
    private void setLoggingLevel(OkHttpClient.Builder builder) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(networkRequestInfo.isDebug() ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        builder.addInterceptor(logging);
    }
}
