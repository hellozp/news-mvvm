package com.zp.library_news.headlinenews;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.zp.library_base.fragment.MvvmFragment;
import com.zp.library_base.viewmodel.MvvmBaseViewModel;
import com.zp.library_news.R;
import com.zp.library_news.databinding.FragmentNewsBinding;

/**
 * author : zp
 * e-mail :
 * time : 2020-02-22 10:30
 * desc : 首页-新闻列表
 * version: 1.0
 */
public class NewsFragment extends MvvmFragment<FragmentNewsBinding, MvvmBaseViewModel> {
    FragmentNewsBinding mBinding;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected MvvmBaseViewModel getViewModel() {
        return null;
    }

    @Override
    protected int getBindingVariable() {
        return 0;
    }

    @Override
    protected void onRetryBtnClickLoad() {

    }

    @Override
    protected String getFragmentTag() {
        return "NewsFragment";
    }

    @Override
    public void showContent() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void onRefreshEmpty() {

    }

    @Override
    public void onRefreshFailure(String errMsg) {

    }
}
