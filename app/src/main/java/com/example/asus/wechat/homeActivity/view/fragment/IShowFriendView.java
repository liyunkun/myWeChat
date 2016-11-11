package com.example.asus.wechat.homeActivity.view.fragment;

import com.example.asus.wechat.login.module.bean.UserBean;

import java.util.List;

/**
 * Created by ASUS on 2016/11/11.
 */

public interface IShowFriendView {
    void getData(List<UserBean> list);
    void getError(String msg);
}
