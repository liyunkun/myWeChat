package com.example.asus.wechat.login.module;

/**
 * Created by ASUS on 2016/11/9.
 */

public class CountryBean {
    private String EnglishName;
    private String CountryName;
    private String AreaCode;
    private String pinyin;
    private String firstLetter;

    @Override
    public String toString() {
        return "CountryBean{" +
                "EnglishName='" + EnglishName + '\'' +
                ", CountryName='" + CountryName + '\'' +
                ", AreaCode=" + AreaCode +
                ", pinyin='" + pinyin + '\'' +
                ", firstLetter='" + firstLetter + '\'' +
                '}';
    }

    public String getEnglishName() {
        return EnglishName;
    }

    public void setEnglishName(String englishName) {
        EnglishName = englishName;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }



    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public CountryBean() {

    }

    public String getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(String areaCode) {
        AreaCode = areaCode;
    }

    public CountryBean(String englishName, String countryName, String areaCode, String pinyin, String firstLetter) {

        EnglishName = englishName;
        CountryName = countryName;
        AreaCode = areaCode;
        this.pinyin = pinyin;
        this.firstLetter = firstLetter;
    }
}
