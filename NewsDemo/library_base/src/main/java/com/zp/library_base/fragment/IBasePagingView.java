package com.zp.library_base.fragment;

import com.zp.library_base.activity.IBaseView;

/**
 * author : zp
 * e-mail :
 * time : 2020-02-22 11:30
 * desc :
 * version: 1.0
 */
public interface IBasePagingView extends IBaseView {
    //上拉加载更多-加载失败时调用
    void onLoadMoreFailure(String message);

    //上拉加载更多-没有更多时调用
    void onLoadMoreEmpty();
}
