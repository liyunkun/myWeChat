package com.example.asus.wechat;

import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.event.OfflineMessageEvent;
import cn.bmob.newim.listener.BmobIMMessageHandler;

/**
 * Created by ASUS on 2016/11/8.
 */
//定义消息接收器继承自BmobIMMessageHandler来处理服务器发来的消息和离线消息。
public class DemoMessageHandler extends BmobIMMessageHandler {


    public DemoMessageHandler(BmobIMApplication bmobIMApplication) {

    }

    @Override
    public void onMessageReceive(final MessageEvent event) {
        //当接收到服务器发来的消息时，此方法被调用
    }

    @Override
    public void onOfflineReceive(final OfflineMessageEvent event) {
        //每次调用connect方法时会查询一次离线消息，如果有，此方法会被调用
    }
}
