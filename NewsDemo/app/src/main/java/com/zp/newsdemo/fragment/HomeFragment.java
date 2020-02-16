package com.zp.newsdemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.zp.newsdemo.R;
import com.zp.newsdemo.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {
    FragmentHomeBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        mBinding.tvTitle.setText(getString(R.string.menu_home));
        return mBinding.getRoot();
    }

}
