package com.zp.library_news.newslist;

import com.zp.library_base.customview.BaseCustomViewModel;
import com.zp.library_base.fragment.IBasePagingView;
import com.zp.library_base.model.BasePagingModel;
import com.zp.library_base.viewmodel.MvvmBaseViewModel;
import com.zp.library_news.headlinenews.ChannelModel;

import java.util.ArrayList;

/**
 * author : zp
 * e-mail :
 * time : 2020-02-29 20:53
 * desc :
 * version: 1.0
 */
public class NewsListViewModel extends MvvmBaseViewModel<NewsListViewModel.INewsView, NewsListModel> implements BasePagingModel.IModelListener<ArrayList<BaseCustomViewModel>> {
    private ArrayList<BaseCustomViewModel> mNewsList = new ArrayList<>();

    public NewsListViewModel(String classId, String lboClassId) {
        model = new NewsListModel(classId, lboClassId);
        model.register(this);
        //触发第一次获取数据
        model.getCachedDataAndLoad();
    }

    @Override
    public void onLoadFinish(BasePagingModel model, ArrayList<BaseCustomViewModel> data, boolean isEmpty, boolean isFirstPage, boolean hasNextPage) {
        if (getPageView() != null) {
            if (model instanceof NewsListModel) {
                if (isFirstPage) {
                    mNewsList.clear();
                }
                if (isEmpty) {
                    if (isFirstPage) {
                        getPageView().onRefreshEmpty();
                    } else {
                        getPageView().onLoadMoreEmpty();
                    }
                } else {
                    mNewsList.addAll(data);
                    getPageView().onNewsLoaded(mNewsList);
                }
            }
        }
    }

    @Override
    public void onLoadFail(BasePagingModel model, String prompt, boolean isFirstPage) {
        if (getPageView() != null) {
            if (isFirstPage) {
                getPageView().onRefreshFailure(prompt);
            } else {
                getPageView().onLoadMoreFailure(prompt);
            }
        }
    }

    public void tryToRefresh() {
        model.refresh();
    }

    public void tryToLoadNextPage() {
        model.loadNexPage();
    }

    public interface INewsView extends IBasePagingView {
        void onNewsLoaded(ArrayList<BaseCustomViewModel> newsData);
    }
}
