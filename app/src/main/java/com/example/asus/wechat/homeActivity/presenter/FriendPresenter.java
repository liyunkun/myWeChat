package com.example.asus.wechat.homeActivity.presenter;

import android.content.Context;

import com.example.asus.wechat.homeActivity.model.bean.IFriendDataImpl;
import com.example.asus.wechat.homeActivity.view.fragment.IShowFriendView;
import com.example.asus.wechat.intf.UserBeanCallBack;
import com.example.asus.wechat.login.module.bean.UserBean;
import com.example.asus.wechat.login.module.intf.ILoginData;
import com.example.asus.wechat.login.view.intf.ILoginView;

import java.util.List;

/**
 * Created by ASUS on 2016/11/11.
 */

public class FriendPresenter {
    private ILoginData data;
    private IShowFriendView iShowFriendView;

    public FriendPresenter(IShowFriendView iShowFriendView) {
        this.iShowFriendView = iShowFriendView;
        data=new IFriendDataImpl();
    }
    public void start(String areaCode, String phoneNumber) {
        data.getData((Context) iShowFriendView, areaCode, phoneNumber, new UserBeanCallBack() {
            @Override
            public void onSuccessful(List<UserBean> list) {
                iShowFriendView.getData(list);
            }

            @Override
            public void onFailed(String errorMsg) {
                iShowFriendView.getError(errorMsg);
            }
        });
}
}
