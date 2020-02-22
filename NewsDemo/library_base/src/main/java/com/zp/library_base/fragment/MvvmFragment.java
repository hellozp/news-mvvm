package com.zp.library_base.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.zp.library_base.R;
import com.zp.library_base.loadsir.EmptyCallback;
import com.zp.library_base.loadsir.ErrorCallback;
import com.zp.library_base.loadsir.LoadingCallback;
import com.zp.library_base.viewmodel.MvvmBaseViewModel;


public abstract class MvvmFragment<V extends ViewDataBinding, VM extends MvvmBaseViewModel> extends Fragment implements IBasePagingView {
    protected VM viewModel;
    protected V viewDataBinding;
    private LoadService loadService;
    private boolean isShowedContent;

    protected abstract
    @LayoutRes
    int getLayoutId();

    protected abstract VM getViewModel();

    protected abstract int getBindingVariable();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

        initParameters();
    }

    /**
     * 初始化参数
     */
    private void initParameters() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return viewDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = getViewModel();
        if (viewModel != null) {
            viewModel.attachUI(this);
        }
        if (getBindingVariable() > 0) {
            viewDataBinding.setVariable(getBindingVariable(), viewModel);
            viewDataBinding.executePendingBindings();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(getFragmentTag(), this + ":" + "onActivityCreated");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(getFragmentTag(), this + ":" + "onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (viewModel != null && viewModel.isUIAttached()) {
            viewModel.detachUI();
        }
        Log.d(getFragmentTag(), this + ":" + "onDetach");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(getFragmentTag(), this + ":" + "onResume");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(getFragmentTag(), this + ":" + "onStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(getFragmentTag(), this + ":" + "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(getFragmentTag(), this + ":" + "onStop");
    }

    @Override
    public void onDestroy() {
        Log.d(getFragmentTag(), this + ":" + "onDestory");
        super.onDestroy();
    }

    @Override
    public void onRefreshEmpty() {
        if (loadService != null) {
            loadService.showCallback(EmptyCallback.class);
        }
    }

    @Override
    public void onRefreshFailure(String errMsg) {
        if (loadService != null) {
            if (!isShowedContent) {
                loadService.showCallback(ErrorCallback.class);
            } else {
                Toast.makeText(getContext(), errMsg, Toast.LENGTH_SHORT).show();
            }
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
            isShowedContent = true;
            loadService.showSuccess();
        }
    }

    @Override
    public void onLoadMoreFailure(String msg) {
        Log.d(getFragmentTag(), this + ":" + "onLoadMoreFailure");

        if (loadService != null) {
            if (!isShowedContent) {
                loadService.showCallback(ErrorCallback.class);
            } else {
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onLoadMoreEmpty() {
        Log.d(getFragmentTag(), this + ":" + "onLoadMoreEmpty");
        Toast.makeText(getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
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

    protected abstract String getFragmentTag();
}
