package com.zp.newsdemo.application;

import com.billy.cc.core.component.CC;
import com.kingja.loadsir.core.LoadSir;
import com.zp.library_base.BaseApplication;
import com.zp.library_base.loadsir.CustomCallback;
import com.zp.library_base.loadsir.EmptyCallback;
import com.zp.library_base.loadsir.ErrorCallback;
import com.zp.library_base.loadsir.LoadingCallback;
import com.zp.library_base.loadsir.TimeoutCallback;
import com.zp.newsdemo.BuildConfig;

/**
 * author : zp
 * e-mail :
 * time : 2020-02-13 14:37
 * desc :
 * version: 1.0
 */
public class NewsApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        setDebug(BuildConfig.DEBUG);//注意要使用NewsApplication的BuildConfig

        //LoadSir全局配置
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())//添加各种状态页
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)//设置默认状态页
                .commit();

        CC.enableDebug(BuildConfig.DEBUG); // 默认是false: 开启debug模式
        CC.enableVerboseLog(BuildConfig.DEBUG);	// 默认是false: 开启CC调用日志跟踪
        CC.enableRemoteCC(BuildConfig.DEBUG); // 默认是false: 开启跨app组件调用
    }
}
