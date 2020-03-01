package com.zp.library_news.newslist;

import com.zp.library_base.activity.IBaseView;
import com.zp.library_base.viewmodel.MvvmBaseViewModel;
import com.zp.library_news.headlinenews.ChannelModel;
import com.zp.library_news.headlinenews.HeadlineNewsViewModel;

import java.util.ArrayList;

/**
 * author : zp
 * e-mail :
 * time : 2020-02-29 20:53
 * desc :
 * version: 1.0
 */
public class NewsListViewModel extends MvvmBaseViewModel<NewsListViewModel.INewsView, ChannelModel> {
    public interface INewsView extends IBaseView {
        void onChannelsLoaded(ArrayList<ChannelModel.Channel> channels);
    }
}
