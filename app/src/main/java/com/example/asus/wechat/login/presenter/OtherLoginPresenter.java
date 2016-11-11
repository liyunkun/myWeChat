package com.example.asus.wechat.login.presenter;

import android.content.Context;

import com.example.asus.wechat.intf.UserBeanCallBack;
import com.example.asus.wechat.login.module.bean.UserBean;
import com.example.asus.wechat.login.module.impl.IOtherLoginDataImpl;
import com.example.asus.wechat.login.module.intf.IOtherLoginData;
import com.example.asus.wechat.login.view.intf.IOtherLoginView;

import java.util.List;

/**
 * Created by liyunkun on 2016/11/10 0010.
 */
public class OtherLoginPresenter {
    private IOtherLoginData data;
    private IOtherLoginView view;

    public OtherLoginPresenter(IOtherLoginView view) {
        this.view = view;
        data = new IOtherLoginDataImpl();
    }

    public void start(String userNumber) {
        data.getData((Context) view, userNumber, new UserBeanCallBack() {
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
