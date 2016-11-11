package com.example.asus.wechat.login.module.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by liyunkun on 2016/11/9 0009.
 * 用户信息类
 */
public class UserBean extends BmobObject implements Serializable{

    private String userImgUrl;//用户头像的url
    private String nickName;//昵称
    private String QQNumber;//qq号
    private String E_mail;//邮箱地址
    private String weChatId;//微信号
    private String QRCodeUrl;//二维码的url
    private String address;//地址
    private String gender;//性别
    private String country;//地区
    private String signature;//签名
    private String phoneNumber;//手机号，即相当于ID，要查询该用户信息使用
    private String password;//用户密码
    private String phoneCode;//区号
    private String FirstLetter;

    public String getFirstLetter() {
        return FirstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        FirstLetter = firstLetter;
    }

    public UserBean() {
    }


    public UserBean(String userImgUrl, String nickName, String QQNumber, String e_mail, String weChatId, String QRCodeUrl, String address, String gender, String country, String signature, String phoneNumber, String password, String phoneCode, String firstLetter) {
        this.userImgUrl = userImgUrl;
        this.nickName = nickName;
        this.QQNumber = QQNumber;
        E_mail = e_mail;
        this.weChatId = weChatId;
        this.QRCodeUrl = QRCodeUrl;
        this.address = address;
        this.gender = gender;
        this.country = country;
        this.signature = signature;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.phoneCode = phoneCode;
        FirstLetter = firstLetter;
    }

    public String getE_mail() {
        return E_mail;
    }

    public void setE_mail(String e_mail) {
        E_mail = e_mail;
    }

    public String getQQNumber() {
        return QQNumber;
    }

    public void setQQNumber(String QQNumber) {
        this.QQNumber = QQNumber;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }


    public String getUserImgUrl() {
        return userImgUrl;
    }

    public void setUserImgUrl(String userImgUrl) {
        this.userImgUrl = userImgUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getWeChatId() {
        return weChatId;
    }

    public void setWeChatId(String weChatId) {
        this.weChatId = weChatId;
    }

    public String getQRCodeUrl() {
        return QRCodeUrl;
    }

    public void setQRCodeUrl(String QRCodeUrl) {
        this.QRCodeUrl = QRCodeUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
