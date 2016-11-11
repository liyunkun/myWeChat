package com.example.asus.wechat.login.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.wechat.R;
import com.example.asus.wechat.util.MyConstant;

/**
 * Created by liyunkun on 2016/11/8 0008.
 * 第一次下载时的登录或注册的引导页
 */
public class LeadActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);
        initView();
    }

    /**
     * 查找控件和初始化一些属性
     */
    private void initView() {
        sp = getSharedPreferences(MyConstant.LOAD_SP,MODE_PRIVATE);
        TextView language = (TextView) findViewById(R.id.language);
        Button login = (Button) findViewById(R.id.login);
        Button register = (Button) findViewById(R.id.register);
        language.setOnClickListener(this);
        login.setOnClickListener(this);
        register.setOnClickListener(this);

        sp.edit()
                .putBoolean(MyConstant.IS_LOGIN,true)
                .commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.language:
                break;
            case R.id.login:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }
}
