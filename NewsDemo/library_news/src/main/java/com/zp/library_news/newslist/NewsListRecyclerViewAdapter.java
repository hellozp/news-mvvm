package com.zp.library_news.newslist;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.zp.library_base.customview.BaseCustomViewModel;
import com.zp.library_base.recyclerview.BaseViewHolder;
import com.zp.library_common.views.picturetitleview.PictureTitleViewViewModel;
import com.zp.library_common.views.picturetitleview.PictureView;
import com.zp.library_common.views.titleview.TitleView;
import com.zp.library_common.views.titleview.TitleViewViewModel;

import java.util.ArrayList;


/**
 * Created by Allen on 2017/7/20.
 * 保留所有版权，未经允许请不要分享到互联网和其他人
 */
public class NewsListRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<BaseCustomViewModel> mItems;
    private final int VIEW_TYPE_PICTURE_TITLE = 1;
    private final int VIEW_TYPE_TITLE = 2;

    NewsListRecyclerViewAdapter() {
    }

    void setData(ArrayList<BaseCustomViewModel> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mItems != null && mItems.size() > 0) {
            return mItems.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (mItems.get(position) instanceof PictureTitleViewViewModel) {
            return VIEW_TYPE_PICTURE_TITLE;
        } else if (mItems.get(position) instanceof TitleViewViewModel) {
            return VIEW_TYPE_TITLE;
        }
        return -1;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_PICTURE_TITLE) {
            PictureView pictureTitleView = new PictureView(parent.getContext());
            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            pictureTitleView.setLayoutParams(layoutParams);
            return new BaseViewHolder(pictureTitleView);
        } else if (viewType == VIEW_TYPE_TITLE) {
            TitleView titleView = new TitleView(parent.getContext());
            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            titleView.setLayoutParams(layoutParams);
            return new BaseViewHolder(titleView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }
}
