package com.zp.library_news.headlinenews;

import com.google.gson.reflect.TypeToken;
import com.zp.library_base.model.BaseModel;
import com.zp.library_network.errorhandler.ExceptionHandle;
import com.zp.library_network.observer.BaseObserver;
import com.zp.library_news.api.NewsApi;
import com.zp.library_news.api.beans.NewsChannelsBean;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * author : zp
 * e-mail :
 * time : 2020-02-29 17:43
 * desc : 新闻首页tabLayout频道列表数据逻辑层
 * version: 1.0
 */
public class ChannelModel extends BaseModel<ArrayList<ChannelModel.Channel>> {
    private static final String PREF_KEY_HOME_CHANNEL = "pref_key_home_channel";
    public static final String PREDEFINED_CHANNELS = "[\n" +
            "    {\n" +
            "        \"channelId\": \"5572a108b3cdc86cf39001cd\",\n" +
            "        \"channelName\": \"国内焦点\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"channelId\": \"5572a108b3cdc86cf39001ce\",\n" +
            "        \"channelName\": \"国际焦点\"\n" +
            "    }\n" +
            "]";


    @Override
    public void refresh() {

    }

    @Override
    protected void load() {
        NewsApi.getInstance().getNewsChannels(new BaseObserver<NewsChannelsBean>(this) {
            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                e.printStackTrace();
                loadFail(e.message);
            }

            @Override
            public void onNext(NewsChannelsBean newsChannelsBean) {
                ArrayList<Channel> channels = new ArrayList<>();
                for (NewsChannelsBean.ChannelList source : newsChannelsBean.showapiResBody.channelList) {
                    Channel channel = new Channel();
                    channel.channelId = source.channelId;
                    channel.channelName = source.name;
                    channels.add(channel);
                }
                loadSuccess(channels);
            }
        });
    }

    protected String getCachedPreferenceKey() {
        return PREF_KEY_HOME_CHANNEL;
    }

    /**
     * SuperBaseModel中需要通过该方法获取到反序列化的类的类型
     *
     * @return
     */
    protected Type getTClass() {
        return new TypeToken<ArrayList<Channel>>() {
        }.getType();
    }

    protected String getApkString() {
        return PREDEFINED_CHANNELS;
    }

    // TODO: 2020-03-24 非静态内部类是否会内存泄露？？
    public class Channel {
        public String channelId;
        public String channelName;
    }
}
