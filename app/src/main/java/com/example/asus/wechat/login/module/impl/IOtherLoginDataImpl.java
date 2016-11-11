package com.example.asus.wechat.login.module.impl;

import android.content.Context;

import com.example.asus.wechat.intf.UserBeanCallBack;
import com.example.asus.wechat.login.module.bean.UserBean;
import com.example.asus.wechat.login.module.intf.IOtherLoginData;
import com.example.asus.wechat.util.UserBeanConstant;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by liyunkun on 2016/11/10 0010.
 */
public class IOtherLoginDataImpl implements IOtherLoginData {
    @Override
    public void getData(Context context, String userName, final UserBeanCallBack callBack) {
        BmobQuery<UserBean> queryEmail = new BmobQuery<>();
        queryEmail.addWhereEqualTo(UserBeanConstant.E_MAIL, userName);
        BmobQuery<UserBean> queryQQ = new BmobQuery<>();
        queryQQ.addWhereEqualTo(UserBeanConstant.QQ_NUMBER, userName);
        BmobQuery<UserBean> queryWeChat = new BmobQuery<>();
        queryWeChat.addWhereEqualTo(UserBeanConstant.WE_CHAT_ID, userName);
        List<BmobQuery<UserBean>> list = new ArrayList<>();
        list.add(queryEmail);
        list.add(queryQQ);
        list.add(queryWeChat);
        BmobQuery<UserBean> queryMain = new BmobQuery<>();
        queryMain.or(list);
        queryMain.findObjects(context, new FindListener<UserBean>() {
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
