package com.zp.library_news.newslist;

import com.zp.library_base.fragment.MvvmFragment;
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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected NewsListViewModel getViewModel() {
        return new NewsListViewModel();
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
        return null;
    }

    @Override
    public void onChannelsLoaded(ArrayList<ChannelModel.Channel> channels) {

    }
}
