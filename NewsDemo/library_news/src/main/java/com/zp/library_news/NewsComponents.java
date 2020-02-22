package com.zp.library_news;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponent;
import com.zp.library_news.headlinenews.NewsFragment;

/**
 * author : zp
 * e-mail :
 * time : 2020-02-21 10:49
 * desc : news新闻模块组件管理器——提供新闻fragment给app module加载；
 * version: 1.0
 */
public class NewsComponents implements IComponent {
    @Override
    public String getName() {
        //指定组件的名称
        return "News";
    }

    @Override
    public boolean onCall(CC cc) {
        switch (cc.getActionName()) {
            case "getHeadlineNewsFragment"://APP首页新闻列表fragment
                CCResult ccResult = new CCResult();
                ccResult.addData("fragment", new NewsFragment());
                //返回处理结果给调用方
                CC.sendCCResult(cc.getCallId(), ccResult);
                //同步方式实现（在return之前听过CC.sendCCResult()返回组件调用结果），return false
                return true;
            default:
                CC.sendCCResult(cc.getCallId(), CCResult.errorUnsupportedActionName());
                return false;
        }
    }
}
