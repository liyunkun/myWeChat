package com.example.asus.wechat.login.presenter;

import android.content.Context;

import com.example.asus.wechat.intf.UserBeanCallBack;
import com.example.asus.wechat.login.module.bean.UserBean;
import com.example.asus.wechat.login.module.impl.IRegisterDataImpl;
import com.example.asus.wechat.login.module.intf.IRegisterData;
import com.example.asus.wechat.login.view.intf.IRegisterView;

import java.util.List;

/**
 * Created by liyunkun on 2016/11/9 0009.
 * RegisterActivity对应的Presenter
 */
public class RegisterPresenter {
    private IRegisterData data;
    private IRegisterView view;

    public RegisterPresenter(IRegisterView view) {
        this.view = view;
        data=new IRegisterDataImpl();
    }
    public void start(String phoneNumber){
        data.getData((Context) view, phoneNumber, new UserBeanCallBack() {
            @Override
            public void onSuccessful(List<UserBean> list) {
                view.getData(list);
            }

            @Override
            public void onFailed(String errorMsg) {
                view.showErrorMsg(errorMsg);
            }
        });
    }
}
