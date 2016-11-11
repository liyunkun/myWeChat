package com.example.asus.wechat.homeActivity.view.activtity.mine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.asus.wechat.R;
//设置下的账号与安全
public class AccountAndSecurityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_and_security);
        init();
    }

    private void init() {
        ImageView iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountAndSecurityActivity.this.finish();
            }
        });
    }
}
