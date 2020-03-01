package com.zp.library_network.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * author : zp
 * e-mail :
 * time : 2020-02-22 20:06
 * desc : 网络请求响应基类
 * version: 1.0
 */
public class BaseResponse {
    @SerializedName("showapi_res_code")
    @Expose
    public Integer showapiResCode;
    @SerializedName("showapi_res_error")
    @Expose
    public Integer showapiResError;
}
