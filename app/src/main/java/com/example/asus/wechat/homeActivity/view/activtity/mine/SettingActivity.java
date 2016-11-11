package com.example.asus.wechat.homeActivity.view.activtity.mine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.asus.wechat.R;
import com.example.asus.wechat.login.view.DefaultLoginActivity;
import com.example.asus.wechat.util.MyConstant;

/*MineFragment的设置的Activity
* */
public class SettingActivity extends AppCompatActivity {

    private LinearLayout ll_news;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        sp = getSharedPreferences(MyConstant.LOAD_SP, MODE_PRIVATE);
        init();
    }

    private void init() {
        //新消息提醒
        ll_news = ((LinearLayout) findViewById(R.id.ll_news));
        //勿扰模式
        LinearLayout ll_notiToggle = (LinearLayout) findViewById(R.id.ll_notiToggle);
        //聊天
        LinearLayout ll_chat = (LinearLayout) findViewById(R.id.ll_chat);
        //隐私
        LinearLayout ll_intimity = (LinearLayout) findViewById(R.id.ll_intimity);
        //通用
        LinearLayout ll_general = (LinearLayout) findViewById(R.id.ll_general);
        //账号与安全
        LinearLayout ll_account_and_security = (LinearLayout) findViewById(R.id.ll_account_and_security);
        //关于微信
        LinearLayout ll_about_wx = (LinearLayout) findViewById(R.id.ll_about_wx);
        //用户退出
        LinearLayout ll_quit = (LinearLayout) findViewById(R.id.ll_quit);
        //返回键
        ImageView iv_back = (ImageView) findViewById(R.id.iv_back);
/*以下是对应的点击事件*/
        ll_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, NewsActivity.class));
            }
        });

        ll_notiToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, NotiToggleActivity.class));
            }
        });


        ll_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, MineChatActivity.class));
            }
        });

        ll_intimity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, IntimityActivity.class));
            }
        });

        ll_general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, GeneralActivity.class));
            }
        });

        ll_account_and_security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, AccountAndSecurityActivity.class));
            }
        });

        ll_about_wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, AboutWXActivity.class));
            }
        });

        ll_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                View view = LayoutInflater.from(SettingActivity.this).inflate(R.layout.user_quit_layout, null);
                //设置下的退出的“退出当前账号”
                LinearLayout ll_quit = (LinearLayout) view.findViewById(R.id.ll_quit);
                //设置下的退出的“关闭微信”
                LinearLayout ll_close = (LinearLayout) view.findViewById(R.id.ll_close);
                ll_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SettingActivity.this, "关闭微信", Toast.LENGTH_SHORT).show();
                    }
                });
                ll_quit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sp.edit()
                                .putBoolean(MyConstant.IS_FIRST_LOGIN, false)
                                .commit();
                        Intent intent = new Intent(SettingActivity.this, DefaultLoginActivity.class);
                        intent.putExtra(MyConstant.USER_BEAN, MyConstant.userBean);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setView(view);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingActivity.this.finish();
            }
        });
    }

}
