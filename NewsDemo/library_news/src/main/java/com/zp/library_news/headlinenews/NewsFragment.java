package com.zp.library_news.headlinenews;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.zp.library_news.R;
import com.zp.library_news.databinding.FragmentNewsBinding;

/**
* author : zp
* e-mail :
* time : 2020-02-22 10:30
* desc : 首页-新闻列表
* version: 1.0
*/
public class NewsFragment extends Fragment {
    FragmentNewsBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false);
        return mBinding.getRoot();
    }
}
