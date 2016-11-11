package com.example.asus.wechat.homeActivity.view.activtity.mine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.example.asus.wechat.R;

/*SettingActivity的新消息提醒的Activity
* */

public class NewsActivity extends AppCompatActivity {

    private RelativeLayout rl_news_hint;
    private RelativeLayout rl_notification_message_detail;
    private RelativeLayout rl_voice;
    private RelativeLayout rl_shake;
    private View view1;
    private View view2;
    private View view3;
    private Switch sw3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        init();
    }

    private void init() {
        ImageView iv_back = (ImageView) findViewById(R.id.iv_back);
        Switch sw1 = (Switch) findViewById(R.id.sw1);
        sw3 = ((Switch) findViewById(R.id.sw3));
        rl_news_hint = ((RelativeLayout) findViewById(R.id.rl_news_hint));
        rl_notification_message_detail = ((RelativeLayout) findViewById(R.id.rl_notification_message_detail));
        rl_voice = ((RelativeLayout) findViewById(R.id.rl_voice));
        rl_shake = ((RelativeLayout) findViewById(R.id.rl_shake));
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsActivity.this.finish();
            }
        });
//接收新消息通知的滑动按钮的点击事件
        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    view1.setVisibility(View.VISIBLE);
                    view2.setVisibility(View.VISIBLE);
                    view3.setVisibility(View.VISIBLE);
                    rl_news_hint.setVisibility(View.VISIBLE);
                    rl_notification_message_detail.setVisibility(View.VISIBLE);
                    rl_shake.setVisibility(View.VISIBLE);
                    rl_voice.setVisibility(View.VISIBLE);
                } else {
                    view1.setVisibility(View.GONE);
                    view2.setVisibility(View.GONE);
                    view3.setVisibility(View.GONE);
                    rl_news_hint.setVisibility(View.GONE);
                    rl_notification_message_detail.setVisibility(View.GONE);
                    rl_shake.setVisibility(View.GONE);
                    rl_voice.setVisibility(View.GONE);
                }
            }
        });

        //声音的滑动按键的点击事件
        sw3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    view3.setVisibility(View.VISIBLE);
                    rl_news_hint.setVisibility(View.VISIBLE);
                } else {
                    view3.setVisibility(View.GONE);
                    rl_news_hint.setVisibility(View.GONE);
                }
            }
        });
    }
}
