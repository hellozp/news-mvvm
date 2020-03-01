package com.zp.library_news.headlinenews;

import com.zp.library_base.activity.IBaseView;
import com.zp.library_base.viewmodel.MvvmBaseViewModel;

import java.util.ArrayList;

/**
 * author : zp
 * e-mail :
 * time : 2020-02-29 17:39
 * desc :
 * version: 1.0
 */
public class HeadlineNewsViewModel extends MvvmBaseViewModel<HeadlineNewsViewModel.IMainView, ChannelModel> {
    public interface IMainView extends IBaseView {
        void onChannelsLoaded(ArrayList<ChannelModel.Channel> channels);
    }
}
