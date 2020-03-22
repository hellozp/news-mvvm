package com.zp.library_base.viewmodel;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

import com.zp.library_base.model.SuperBaseModel;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * author : zp
 * e-mail :
 * time : 2020-02-15 18:12
 * desc :V——VM——M  例如：fragment相当于View，基类fragment会把View控件变量传到此VM中，也会将Model实例传进来。
 * 然后由继承此MvvmBaseViewModel的子VM来操作数据实现VM控制M的目的。所以整体传递过程是V——VM——M
 * version: 1.0
 */
public class MvvmBaseViewModel<V, M extends SuperBaseModel> extends ViewModel implements IMvvmBaseViewModel<V> {
    private Reference<V> mUIRef;
    protected M model;

    @Override
    public void attachUI(V view) {
        mUIRef = new WeakReference<>(view);
    }

    @Nullable
    public V getPageView() {
        if (mUIRef == null) {
            return null;
        }
        return mUIRef.get();
    }

    @Override
    public boolean isUIAttached() {
        return mUIRef != null && mUIRef.get() != null;
    }

    @Override
    public void detachUI() {
        if (mUIRef != null) {
            mUIRef.clear();
            mUIRef = null;
        }
        if (model != null) {
            model.cancel();
        }
    }

}
