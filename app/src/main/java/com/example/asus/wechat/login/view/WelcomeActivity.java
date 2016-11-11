package com.example.asus.wechat.login.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.asus.wechat.R;
import com.example.asus.wechat.util.MyConstant;


/**
 * @author liyunkun
 *         用户打开应用时，3秒的显示界面
 */

public class WelcomeActivity extends AppCompatActivity {

    private int mTotalTime = 4;//在该界面待的总时间
    private boolean mIsRunning = true;//该界面是否还在运行
    private Handler mHandler;
    private Button mJump;//跳过该界面的按钮
    private SharedPreferences mLoadSp;
    //在SharedPreferences中保存的字段名，是否已登录
    private boolean isLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
        new Thread(new Runnable() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0);
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIsRunning = false;
    }

    /**
     * 查找控件和初始化一些属性
     */
    private void initView() {
        mJump = ((Button) findViewById(R.id.jump));

        mLoadSp = getSharedPreferences(MyConstant.LOAD_SP, MODE_PRIVATE);
        isLogin = mLoadSp.getBoolean(MyConstant.IS_LOGIN, false);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        if (mIsRunning) {
                            if (mTotalTime > 0) {
                                mJump.setText("跳过 " + (--mTotalTime));
                                mHandler.sendEmptyMessageDelayed(0, 1000);
                            }
                            if (mTotalTime == 0) {
                                if (isLogin) {
                                    startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                                    finish();
                                } else {
                                    startActivity(new Intent(WelcomeActivity.this, LeadActivity.class));
                                    finish();
                                }
                            }
                        }
                        break;
                }
            }
        };
        mJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLogin) {
                    startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(WelcomeActivity.this, LeadActivity.class));
                    finish();
                }
            }
        });
    }
}
