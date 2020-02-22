package com.zp.library_base.viewmodel;

/**
 * author : zp
 * e-mail :
 * time : 2020-02-22 11:41
 * desc :
 * version: 1.0
 */
public interface IMvvmBaseViewModel<V> {

    void attachUI(V view);

    V getPageView();

    boolean isUIAttached();

    void detachUI();
}
