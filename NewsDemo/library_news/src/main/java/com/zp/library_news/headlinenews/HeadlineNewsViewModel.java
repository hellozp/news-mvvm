package com.zp.library_news.headlinenews;

import com.zp.library_base.activity.IBaseView;
import com.zp.library_base.model.BaseModel;
import com.zp.library_base.viewmodel.MvvmBaseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * author : zp
 * e-mail :
 * time : 2020-02-29 17:39
 * desc : VM层用来获取获取频道数据
 * 思路：衔接model层与view层，ChannelModel中发起数据获取——>调用基类提供的公共方法getCachedDataAndLoad()——>定义接口用于view层实现获取回调
 * version: 1.0
 */
public class HeadlineNewsViewModel extends MvvmBaseViewModel<HeadlineNewsViewModel.IMainView, ChannelModel> implements BaseModel.IModelListener<ArrayList<ChannelModel.Channel>> {
    public ArrayList<ChannelModel.Channel> channels = new ArrayList<>();

    public HeadlineNewsViewModel() {
        model = new ChannelModel();
        model.register(this);
    }

    /**
     * 触发获取数据——从缓存或网络获取数据源
     */
    public void refresh() {
        model.getCachedDataAndLoad();
    }

    @Override
    public void onLoadFinish(BaseModel model, ArrayList<ChannelModel.Channel> data) {
        if (model instanceof ChannelModel) {
            if (getPageView() != null && data instanceof List) {
                channels.clear();
                channels.addAll(data);
                //将数据通知到HeadlineNewsFragment中
                getPageView().onChannelsLoaded(channels);
            }
        }
    }

    @Override
    public void onLoadFail(BaseModel model, String prompt) {

    }

    /**
     * 用于fragment获取数据回调
     */
    public interface IMainView extends IBaseView {
        void onChannelsLoaded(ArrayList<ChannelModel.Channel> channels);
    }
}
