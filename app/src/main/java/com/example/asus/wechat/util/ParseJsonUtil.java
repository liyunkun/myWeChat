package com.example.asus.wechat.util;

import android.content.Context;
import android.util.Log;

import com.example.asus.wechat.login.module.CountryBean;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ASUS on 2016/11/9.
 */

public class ParseJsonUtil {
    public  static List<CountryBean> getCountryDataByJson(Context context) {
        List<CountryBean> list = new ArrayList<>();
        StringBuffer json = new StringBuffer();
        BufferedReader br = null;
        ChineseToPinyinHelper chineseToPinyinHelper = new ChineseToPinyinHelper();
        try {
            br = new BufferedReader(new InputStreamReader(context.getAssets().open("country.json")));
            String str;
            while ((str = br.readLine()) != null) {
                json.append(str);
            }
            try {
                JSONArray array = new JSONArray(json.toString());
                for (int i = 0; i < array.length(); i++) {
                    String string = array.getString(i);
                    String EnglishName = string.substring(0, string.indexOf("-"));
                    String CountryName = string.substring(string.indexOf("-")+1,string.lastIndexOf("-"));
                    String AreaCode = string.substring(string.lastIndexOf("-")+1,string.length());
                    String pinyin = chineseToPinyinHelper.getPinyin(CountryName);
                    String firstLetter = pinyin.substring(0, 1).toUpperCase();
                    if (!firstLetter.matches("[A-Z]")) {
                        firstLetter = "#";
                    }
                    list.add(new CountryBean(EnglishName,CountryName,AreaCode,pinyin,firstLetter));
                }
                Collections.sort(list, new Comparator<CountryBean>() {
                    @Override
                    public int compare(CountryBean o1, CountryBean o2) {
                        if (o1.getFirstLetter().equals("#")) {
                            return 1;
                        } else if (o2.getFirstLetter().equals("#")) {
                            return -1;
                        } else {
                            return o1.getFirstLetter().compareTo(o2.getFirstLetter());
                        }
                    }
                });

                return list;
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
