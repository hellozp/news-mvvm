package com.zp.library_network.errorhandler;

import androidx.arch.core.util.Function;

import com.zp.library_network.beans.BaseResponse;

/**
 * author : zp
 * e-mail :
 * time : 2020-02-29 16:54
 * desc :
 * version: 1.0
 */
public class AppDataErrorHandler implements Function<BaseResponse, BaseResponse> {
    @Override
    public BaseResponse apply(BaseResponse response) {
//        if (response instanceof BaseResponse && response != 0)
            return null;
    }
}
