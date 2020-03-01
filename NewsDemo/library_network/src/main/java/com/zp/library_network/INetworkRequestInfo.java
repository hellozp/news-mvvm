package com.zp.library_network;

import java.util.HashMap;

/**
 * author : zp
 * e-mail :
 * time : 2020-02-22 20:07
 * desc : 存放不同app module特有的请求头参数
 * version: 1.0
 */
public interface INetworkRequestInfo {
    HashMap<String, String> getRequestHeadMap();

    boolean isDebug();
}
