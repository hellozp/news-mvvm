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
 * desc :
 * 原理：V——VM——M  例如：fragment相当于View，基类fragment会把View控件变量传到此VM中，也会将Model实例传进来。
 * 然后由继承此MvvmBaseViewModel的子VM来操作数据实现VM控制M的目的。所以整体传递过程是V——VM——M
 * <p>
 * 作用：将子VM的公共代码提取到该基类VM中，起到减少代码冗余的作用
 * <p>
 * version: 1.0
 */
public class MvvmBaseViewModel<V, M extends SuperBaseModel> extends ViewModel implements IMvvmBaseViewModel<V> {
    private Reference<V> mUIRef;
    protected M model;

    @Override
    public void attachUI(V view) {
        //弱引用实例化view，避免内存泄露
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
