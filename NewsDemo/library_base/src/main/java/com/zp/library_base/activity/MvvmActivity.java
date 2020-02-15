package com.zp.library_base.activity;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;
import android.view.View;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.zp.library_base.loadsir.EmptyCallback;
import com.zp.library_base.loadsir.ErrorCallback;
import com.zp.library_base.loadsir.LoadingCallback;
import com.zp.library_base.viewmodel.MvvmBaseViewModel;

public abstract class MvvmActivity<V extends ViewDataBinding, VM extends MvvmBaseViewModel> extends AppCompatActivity implements IBaseView {
    protected VM viewModel;
    protected V viewDataBinding;
    private LoadService loadService;

    protected abstract
    @LayoutRes
    int getLayoutId();

    protected abstract VM getViewModel();

    protected abstract int getBindingVariable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        performDatabinding();
    }

    private void performDatabinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());

        if (viewModel == null) {
            this.viewModel = getViewModel();
        }
        if (getBindingVariable() > 0) {
            viewDataBinding.setVariable(getBindingVariable(), viewModel);
        }
        viewDataBinding.executePendingBindings();
    }

    public void setLoadSir() {
        // Your can change the callback on sub thread directly.
        loadService = LoadSir.getDefault().register(this, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                // 重新加载逻辑
                onRetryBtnClickLoad();
            }
        });
    }

    protected abstract void onRetryBtnClickLoad();


    @Override
    public void onRefreshEmpty() {
        if (loadService != null) {
            loadService.showCallback(EmptyCallback.class);
        }
    }

    @Override
    public void onRefreshFailure(String errMsg) {
        if (loadService != null) {
            loadService.showCallback(ErrorCallback.class);
        }
    }

    @Override
    public void showLoading() {
        if (loadService != null) {
            loadService.showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void showContent() {
        if (loadService != null) {
            loadService.showSuccess();
        }
    }
}

