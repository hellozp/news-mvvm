package com.zp.library_news.newslist;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.scwang.smart.refresh.footer.BallPulseFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;
import com.zp.library_base.customview.BaseCustomViewModel;
import com.zp.library_base.fragment.MvvmFragment;
import com.zp.library_base.utils.GsonUtils;
import com.zp.library_news.R;
import com.zp.library_news.databinding.FragmentNewsBinding;
import com.zp.library_news.headlinenews.ChannelModel;

import java.util.ArrayList;

/**
 * author : zp
 * e-mail :
 * time : 2020-02-29 20:53
 * desc :首页新闻列表
 * version: 1.0
 */
public class NewsListFragment extends MvvmFragment<FragmentNewsBinding, NewsListViewModel> implements NewsListViewModel.INewsView {
    protected final static String BUNDLE_KEY_PARAM_CHANNEL_ID = "bundle_key_param_channel_id";
    protected final static String BUNDLE_KEY_PARAM_CHANNEL_NAME = "bundle_key_param_channel_name";

    private String mChannelId = "";
    private String mChannelName = "";

    private NewsListRecyclerViewAdapter mAdapter;

    public static NewsListFragment newInstance(String channelId, String channelName) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_PARAM_CHANNEL_ID, channelId);
        bundle.putString(BUNDLE_KEY_PARAM_CHANNEL_NAME, channelName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initParameters() {
        super.initParameters();
        if (getArguments() != null) {
            mChannelId = getArguments().getString(BUNDLE_KEY_PARAM_CHANNEL_ID);
            mChannelName = getArguments().getString(BUNDLE_KEY_PARAM_CHANNEL_NAME);
            mFragmentTag = mChannelName;
        }
    }

    @Override
    protected NewsListViewModel getViewModel() {
        Log.e(this.getClass().getSimpleName(), this + ": createViewModel.");

        //new 的时候再构造方法中触发数据获取
        return new NewsListViewModel(mChannelId, mChannelName);
    }

    @Override
    protected int getBindingVariable() {
        return 0;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentTag = "NewsListFragment";
        viewDataBinding.listview.setHasFixedSize(true);
        viewDataBinding.listview.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new NewsListRecyclerViewAdapter();
        viewDataBinding.listview.setAdapter(mAdapter);
        //刷新样式定制参见：https://gitee.com/hello_zp/SmartRefreshLayout
        viewDataBinding.refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
//        viewDataBinding.refreshLayout.setRefreshHeader(new BezierRadarHeader(getContext()));
        viewDataBinding.refreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        viewDataBinding.refreshLayout.setOnRefreshListener(refreshlayout -> {
            viewModel.tryToRefresh();
        });
        viewDataBinding.refreshLayout.setOnLoadMoreListener(refreshlayout -> {
            viewModel.tryToLoadNextPage();
        });
        setLoadSir(viewDataBinding.refreshLayout);
        showLoading();
    }

    @Override
    protected void onRetryBtnClickLoad() {
        viewModel.tryToRefresh();
    }

    @Override
    protected String getFragmentTag() {
        return mChannelName;

    }

    @Override
    public void onNewsLoaded(ArrayList<BaseCustomViewModel> newsData) {
        Log.d("Well", "NewsListFragment获取数据--" + GsonUtils.toJson(newsData));
        if (newsData != null && newsData.size() > 0) {
            viewDataBinding.refreshLayout.finishLoadMore();
            viewDataBinding.refreshLayout.finishRefresh();
            showContent();
            mAdapter.setData(newsData);
        } else {
            onRefreshEmpty();
        }
    }
}
