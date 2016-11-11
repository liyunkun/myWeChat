package com.example.asus.wechat.login.module.impl;

import android.content.Context;

import com.example.asus.wechat.intf.UserBeanCallBack;
import com.example.asus.wechat.login.module.bean.UserBean;
import com.example.asus.wechat.login.module.intf.IRegisterData;
import com.example.asus.wechat.util.UserBeanConstant;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by liyunkun on 2016/11/9 0009.
 * RegisterActivity对应的IRegisterData接口的实现类
 */
public class IRegisterDataImpl implements IRegisterData {
    @Override
    public void getData(Context context, String phoneNumber, final UserBeanCallBack callBack) {
        BmobQuery<UserBean> query=new BmobQuery<>();
        query.addWhereEqualTo(UserBeanConstant.PHONE_NUMBER,phoneNumber);
        query.findObjects(context, new FindListener<UserBean>() {
            @Override
            public void onSuccess(List<UserBean> list) {
                callBack.onSuccessful(list);
            }

            @Override
            public void onError(int i, String s) {
                callBack.onFailed(s);
            }
        });
    }
}
