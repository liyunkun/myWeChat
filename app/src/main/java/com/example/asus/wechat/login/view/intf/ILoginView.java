package com.example.asus.wechat.login.view.intf;

import com.example.asus.wechat.login.module.bean.UserBean;

import java.util.List;

/**
 * Created by liyunkun on 2016/11/10 0010.
 */
public interface ILoginView {

    void getData(List<UserBean> list);

    void getError(String msg);
}
