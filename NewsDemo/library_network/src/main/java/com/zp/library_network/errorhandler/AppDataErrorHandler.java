package com.zp.library_network.errorhandler;


import com.zp.library_network.beans.BaseResponse;

import io.reactivex.functions.Function;

/**
 * author : zp
 * e-mail :
 * time : 2020-02-29 16:54
 * desc : 应用数据的错误会抛RuntimeException——http请求正常，但是返回的应用数据里提示发生了异常，表明服务器已经接收到了来自客户端的请求，但是由于
 * * 某些原因，服务器没有正常处理完请求，可能是缺少参数，或者其他原因；
 * version: 1.0
 */
public class AppDataErrorHandler implements Function<BaseResponse, BaseResponse> {
    @Override
    public BaseResponse apply(BaseResponse response) {
        if (response instanceof BaseResponse && response.showapiResCode != 0)
            throw new RuntimeException(response.showapiResCode + "" + (response.showapiResError != null ? response.showapiResError : ""));
        return response;
    }
}
