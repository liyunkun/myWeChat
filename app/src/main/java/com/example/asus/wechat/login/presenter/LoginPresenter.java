package com.example.asus.wechat.login.presenter;

import android.content.Context;

import com.example.asus.wechat.intf.UserBeanCallBack;
import com.example.asus.wechat.login.module.bean.UserBean;
import com.example.asus.wechat.login.module.impl.ILoginDataImpl;
import com.example.asus.wechat.login.module.intf.ILoginData;
import com.example.asus.wechat.login.view.intf.ILoginView;

import java.util.List;

/**
 * Created by liyunkun on 2016/11/10 0010.
 */
public class LoginPresenter {

    private ILoginData data;
    private ILoginView view;

    public LoginPresenter(ILoginView view) {
        this.view = view;
        data = new ILoginDataImpl();
    }

    public void start(String areaCode, String phoneNumber) {
        data.getData((Context) view, areaCode, phoneNumber, new UserBeanCallBack() {
            @Override
            public void onSuccessful(List<UserBean> list) {
                view.getData(list);
            }

            @Override
            public void onFailed(String errorMsg) {
                view.getError(errorMsg);
            }
        });
    }
}
