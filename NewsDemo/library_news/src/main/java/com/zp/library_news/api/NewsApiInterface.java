package com.zp.library_news.api;


import com.zp.library_news.api.beans.NewsChannelsBean;
import com.zp.library_news.api.beans.NewsListBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;


public interface NewsApiInterface {
    @GET("release/news")
    Observable<NewsListBean> getNewsList(
            @Header("Source") String source,
            @Header("Authorization") String authorization,
            @Header("Date") String date,
            @QueryMap Map<String, String> options);

    @GET("release/channel")
    Observable<NewsChannelsBean> getNewsChannels(
            @Header("Source") String source,
            @Header("Authorization") String authorization,
            @Header("Date") String date,
            @QueryMap Map<String, String> options);
}
