package com.example.asus.wechat.homeActivity.model.bean;

import android.content.Context;

import com.example.asus.wechat.intf.UserBeanCallBack;
import com.example.asus.wechat.login.module.bean.UserBean;
import com.example.asus.wechat.login.module.intf.ILoginData;
import com.example.asus.wechat.util.UserBeanConstant;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by ASUS on 2016/11/11.
 */

public class IFriendDataImpl implements ILoginData {
    @Override
    public void getData(Context context, String areaCode, String phoneNumber, final UserBeanCallBack callBack) {
        BmobQuery<UserBean> query = new BmobQuery<>();

        query.addWhereNotEqualTo(UserBeanConstant.PHONE_NUMBER, phoneNumber);
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
