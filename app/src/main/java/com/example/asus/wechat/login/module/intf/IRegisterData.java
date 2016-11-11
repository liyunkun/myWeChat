package com.example.asus.wechat.login.module.intf;

import android.content.Context;

import com.example.asus.wechat.intf.UserBeanCallBack;

/**
 * Created by liyunkun on 2016/11/9 0009.
 * IRegisterData对应的重Bmob中获取数据的接口
 */
public interface IRegisterData {
    void getData(Context content, String phoneNumber, UserBeanCallBack callBack);
}
