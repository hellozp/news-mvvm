package com.zp.library_news.headlinenews;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;
import com.zp.library_base.fragment.MvvmFragment;
import com.zp.library_news.R;
import com.zp.library_news.databinding.FragmentHeadlinenewsBinding;

import java.util.ArrayList;

/**
 * author : zp
 * e-mail :
 * time : 2020-02-22 10:30
 * desc : 首页-新闻列表
 * version: 1.0
 */
public class HeadlineNewsFragment extends MvvmFragment<FragmentHeadlinenewsBinding, HeadlineNewsViewModel> implements HeadlineNewsViewModel.IMainView {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_headlinenews;
    }

    @Override
    protected HeadlineNewsViewModel getViewModel() {
        return new HeadlineNewsViewModel();
    }

    @Override
    protected int getBindingVariable() {
        return 0;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewDataBinding.tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        initChannnel();
    }

    /**
     * tabLayout初始化
     */
    private void initChannnel(){

    }

    @Override
    protected void onRetryBtnClickLoad() {

    }

    @Override
    protected String getFragmentTag() {
        return "HeadlineNewsFragment";
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

    @Override
    public void onChannelsLoaded(ArrayList<ChannelModel.Channel> channels) {

    }
}
