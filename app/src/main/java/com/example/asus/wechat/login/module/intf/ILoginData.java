package com.example.asus.wechat.login.module.intf;

import android.content.Context;

import com.example.asus.wechat.intf.UserBeanCallBack;

/**
 * Created by liyunkun on 2016/11/10 0010.
 */
public interface ILoginData {

    void getData(Context context,String areaCode, String phoneNumber, UserBeanCallBack callBack);
}
