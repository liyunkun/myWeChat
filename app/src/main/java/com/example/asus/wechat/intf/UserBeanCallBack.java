package com.example.asus.wechat.intf;

import com.example.asus.wechat.login.module.bean.UserBean;

import java.util.List;

/**
 * Created by liyunkun on 2016/11/9 0009.
 * UserBean对应的回调接口
 */
public interface UserBeanCallBack {
    void onSuccessful(List<UserBean> list);

    void onFailed(String errorMsg);
}
