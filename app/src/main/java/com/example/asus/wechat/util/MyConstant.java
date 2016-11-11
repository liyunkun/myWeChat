package com.example.asus.wechat.util;

import com.example.asus.wechat.login.module.bean.UserBean;

/**
 * Created by liyunkun on 2016/11/8 0008.
 * 用于存放所用的常量的工具类
 */
public class MyConstant {
    //好友的userbean 传递需要字段
    public static final String FRIEND_BEAN="friend_bean";
    //欢迎界面和登录界面所用的SharedPreferences的名称
    public static final String LOAD_SP = "load";
    public static final String IS_FIRST_LOGIN="isFirstLogin";
    //在SharedPreferences中保存的字段名，是否已登录
    public static final String IS_LOGIN = "isLogin";
    //传递UserBean时需要的字段,或者保存的字段
    public static final String USER_BEAN="userBean";

    //登录完成后，获取到的UserBean对象，可在其他Activity中使用
    public static UserBean userBean;

}
