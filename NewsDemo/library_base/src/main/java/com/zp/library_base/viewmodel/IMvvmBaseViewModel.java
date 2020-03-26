package com.zp.library_base.viewmodel;

/**
 * author : zp
 * e-mail :
 * time : 2020-02-22 11:41
 * desc :
 * version: 1.0
 */
public interface IMvvmBaseViewModel<V> {

    /**
     * init the view
     *
     * @param view
     */
    void attachUI(V view);

    /**
     * get the view for VM
     *
     * @return
     */
    V getPageView();

    boolean isUIAttached();

    /**
     * won't oom when onDestory、onDetach
     */
    void detachUI();
}
