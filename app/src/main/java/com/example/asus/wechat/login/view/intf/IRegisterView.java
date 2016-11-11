package com.example.asus.wechat.login.view.intf;

import com.example.asus.wechat.login.module.bean.UserBean;

import java.util.List;

/**
 * Created by liyunkun on 2016/11/9 0009.
 * RegisterActivity对应的接口
 */
public interface IRegisterView {
    void getData(List<UserBean> list);
    void showErrorMsg(String errorMsg);
}
