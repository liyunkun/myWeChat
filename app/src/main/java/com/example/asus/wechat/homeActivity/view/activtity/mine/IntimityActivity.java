package com.example.asus.wechat.homeActivity.view.activtity.mine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.asus.wechat.R;
//设置下的隐私
public class IntimityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intimity);
        init();
    }

    private void init() {
        //返回按钮
        ImageView iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntimityActivity.this.finish();
            }
        });
    }
}
