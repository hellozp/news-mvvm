package com.zp.library_base.activity;

/**
 * author : zp
 * e-mail :
 * time : 2020-02-15 21:34
 * desc :
 * version: 1.0
 */
public interface IBaseView {
    void showContent();

    void showLoading();

    void onRefreshEmpty();

    void onRefreshFailure(String errMsg);
}
