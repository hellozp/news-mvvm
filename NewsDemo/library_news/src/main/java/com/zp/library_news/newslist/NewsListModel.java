package com.zp.library_news.newslist;

import com.google.gson.reflect.TypeToken;
import com.zp.library_base.customview.BaseCustomViewModel;
import com.zp.library_base.model.BasePagingModel;
import com.zp.library_common.views.picturetitleview.PictureTitleViewViewModel;
import com.zp.library_common.views.titleview.TitleViewViewModel;
import com.zp.library_network.errorhandler.ExceptionHandle;
import com.zp.library_network.observer.BaseObserver;
import com.zp.library_news.api.NewsApi;
import com.zp.library_news.api.beans.NewsListBean;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * author : zp
 * e-mail :
 * time : 2020-02-29 20:53
 * desc :
 * version: 1.0
 */
public class NewsListModel<T> extends BasePagingModel<T> {
    private String mChannelId = "";
    private String mChannelName = "";
    private static final String PREF_KEY_NEWS_CHANNEL = "pref_key_news_";

    @Override
    protected String getCachedPreferenceKey() {
        return PREF_KEY_NEWS_CHANNEL + mChannelId;
    }

    protected Type getTClass() {
        return new TypeToken<ArrayList<PictureTitleViewViewModel>>() {
        }.getType();
    }

    public NewsListModel(String channelId, String channelName) {
        mChannelId = channelId;
        mChannelName = channelName;
    }

    @Override
    public void refresh() {
        isRefresh = true;
        load();
    }

    public void loadNexPage() {
        isRefresh = false;
        load();
    }

    @Override
    protected void load() {
        NewsApi.getInstance().getNewsList(new BaseObserver<NewsListBean>(this) {
            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                e.printStackTrace();
                loadFail(e.message, isRefresh);
            }

            @Override
            public void onNext(NewsListBean newsChannelsBean) {
                // All observer run on main thread, no need to synchronize
                pageNumber = isRefresh ? 2 : pageNumber + 1;
                ArrayList<BaseCustomViewModel> baseViewModels = new ArrayList<>();

                for (NewsListBean.Contentlist source : newsChannelsBean.showapiResBody.pagebean.contentlist) {
                    if (source.imageurls != null && source.imageurls.size() > 1) {
                        PictureTitleViewViewModel viewModel = new PictureTitleViewViewModel();
                        viewModel.avatarUrl = source.imageurls.get(0).url;
                        viewModel.link = source.link;
                        viewModel.title = source.title;
                        baseViewModels.add(viewModel);
                    } else {
                        TitleViewViewModel viewModel = new TitleViewViewModel();
                        viewModel.link = source.link;
                        viewModel.title = source.title;
                        baseViewModels.add(viewModel);
                    }
                }
                loadSuccess((T) baseViewModels, baseViewModels.size() == 0, isRefresh, baseViewModels.size() == 0);
            }
        }, mChannelId, mChannelName, String.valueOf(isRefresh ? 1 : pageNumber));
    }

}
