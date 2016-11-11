package com.example.asus.wechat;

import android.app.Application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import cn.bmob.newim.BmobIM;
import cn.bmob.v3.Bmob;

/**
 * Created by ASUS on 2016/11/8.
 */

public class BmobIMApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //NewIM初始化
        BmobIM.init(this);
       // Bmob.initialize(this,"8d1f7d6d50e3ad77324f0a447d845b1d");
        //注册消息接收器
        BmobIM.registerDefaultMessageHandler(new DemoMessageHandler(this));
    }

}
