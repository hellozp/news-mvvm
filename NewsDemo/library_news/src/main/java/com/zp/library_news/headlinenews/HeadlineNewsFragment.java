package com.zp.library_news.headlinenews;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;
import com.zp.library_base.fragment.MvvmFragment;
import com.zp.library_base.utils.GsonUtils;
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
    HeadlineNewsFragmentAdapter mAdapter;

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

        //发起数据获取  VM——M.get——callBack——V
        viewModel.refresh();

        initChannelList();
    }

    /**
     * tabLayout初始化
     */
    private void initChannelList() {
        mAdapter = new HeadlineNewsFragmentAdapter(getChildFragmentManager());
        viewDataBinding.viewpager.setAdapter(mAdapter);
        viewDataBinding.viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(viewDataBinding.tablayout));
        viewDataBinding.viewpager.setOffscreenPageLimit(1);
        //绑定tab点击事件
        viewDataBinding.tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewDataBinding.viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onRetryBtnClickLoad() {

    }

    @Override
    protected String getFragmentTag() {
        return "HeadlineNewsFragment";
    }

    /**
     * 获取数据回调
     *
     * @param channels
     */
    @Override
    public void onChannelsLoaded(ArrayList<ChannelModel.Channel> channels) {
        Log.d("well", "HeadlineNewsFragment数据--" + GsonUtils.toJson(channels));

        mAdapter.setChannels(channels);
        viewDataBinding.tablayout.removeAllTabs();
        for (ChannelModel.Channel channel : channels) {
            viewDataBinding.tablayout.addTab(viewDataBinding.tablayout.newTab().setText(channel.channelName));
        }
        viewDataBinding.tablayout.scrollTo(0, 0);
    }
}
