package com.zp.library_network;

import com.zp.library_network.errorhandler.AppDataErrorHandler;
import com.zp.library_network.errorhandler.HttpErrorHandler;
import com.zp.library_network.interceptor.RequestIntercepter;
import com.zp.library_network.interceptor.ResponseInterceptor;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author : zp
 * e-mail :
 * time : 2020-02-22 20:06
 * desc : 网络请求初始化工具
 * version: 1.0
 */
public class ApiBase {

    protected Retrofit retrofit;
    protected static INetworkRequestInfo networkRequestInfo;

    private static RequestIntercepter requestIntercepter;
    private static ResponseInterceptor responseInterceptor;

    private static ErrorTransformer sErrorTransformer = new ErrorTransformer();

    protected ApiBase(String baseUrl) {

        retrofit = new Retrofit
                .Builder()
                .client(getOkHttpClient())//实例化OkHttpClent
                .baseUrl(baseUrl)//主机地址
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * 请求头初始化方法-在application初始化时设置INetworkRequestInfo实现类对象
     *
     * @param requestInfo
     */
    public static void setNetworkRequestInfo(INetworkRequestInfo requestInfo) {
        networkRequestInfo = requestInfo;
        requestIntercepter = new RequestIntercepter(requestInfo);
        responseInterceptor = new ResponseInterceptor();
    }

    /**
     * 初始化OkHttpClent 拦截器、缓存机制、重连机制、https校验、日志打印范围
     *
     * @return OkHttpClent
     */
    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS);

        mOkHttpClient.addInterceptor(requestIntercepter);
        mOkHttpClient.addInterceptor(responseInterceptor);

        setLoggingLevel(mOkHttpClient);

        OkHttpClient okHttpClient = mOkHttpClient.build();
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

    /**
     * 封装线程管理和订阅的过程
     */
    protected void ApiSubscribe(Observable observable, Observer observer) {
        observable.subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .unsubscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(sErrorTransformer)
                .subscribe(observer);
    }

    /**
     * 处理错误的变换
     * 网络请求的错误处理，其中网络错误分为两类：
     * 1、http请求相关的错误，例如：404，403，socket timeout等等；
     * 2、http请求正常，但是返回的应用数据里提示发生了异常，表明服务器已经接收到了来自客户端的请求，但是由于
     * 某些原因，服务器没有正常处理完请求，可能是缺少参数，或者其他原因；
     */
    private static class ErrorTransformer<T> implements ObservableTransformer {

        @Override
        public ObservableSource apply(io.reactivex.Observable upstream) {
            //onErrorResumeNext当发生错误的时候，由另外一个Observable来代替当前的Observable并继续发射数据
            return (io.reactivex.Observable<T>) upstream
                    .map(new AppDataErrorHandler())/*返回的数据统一错误处理*/
                    .onErrorResumeNext(new HttpErrorHandler<T>());/*Http 错误处理**/
        }
    }
}
