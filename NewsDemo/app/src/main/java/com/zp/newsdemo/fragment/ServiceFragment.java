package com.zp.newsdemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.zp.newsdemo.R;
import com.zp.newsdemo.databinding.FragmentOthersBinding;


public class ServiceFragment extends Fragment {
    FragmentOthersBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_others, container, false);
        mBinding.tvTitle.setText(getString(R.string.menu_services));
        return mBinding.getRoot();
    }

}
