package com.zp.library_common.views.picturetitleview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.zp.library_base.customview.BaseCustomView;
import com.zp.library_common.R;
import com.zp.library_common.databinding.PictureViewBinding;

/**
 * author : zp
 * e-mail :
 * time : 2020-03-25 17:02
 * desc :
 * version: 1.0
 */
public class PictureView extends BaseCustomView<PictureViewBinding, PictureTitleViewViewModel> {
    public PictureView(Context context) {
        super(context);
    }

    public PictureView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int setViewLayoutId() {
        return R.layout.picture_view;
    }

    @Override
    public void setDataToView(PictureTitleViewViewModel data) {
        getDataBinding().setViewModel(data);
    }

    @Override
    public void onRootClick(View view) {
//        WebActivity.startCommonWeb(view.getContext(), "", getViewModel().link);
    }
}
