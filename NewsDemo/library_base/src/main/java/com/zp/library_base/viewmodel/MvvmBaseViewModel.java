package com.zp.library_base.viewmodel;

import androidx.lifecycle.ViewModel;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * author : zp
 * e-mail :
 * time : 2020-02-15 18:12
 * desc :
 * version: 1.0
 */
public class MvvmBaseViewModel<V> extends ViewModel implements IMvvmBaseViewModel<V> {
    private Reference<V> mUIRef;

    public void attachUI(V ui) {
        mUIRef = new WeakReference(ui);
    }

    @Override
    public V getPageView() {
        return null;
    }

    @Override
    public boolean isUIAttached() {
        return false;
    }

    @Override
    public void detachUI() {

    }


}
