package com.example.asus.wechat.login.view;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.asus.wechat.R;
import com.example.asus.wechat.homeActivity.view.adapter.WeChatVpAdapter;
import com.example.asus.wechat.homeActivity.view.fragment.FindFragment;
import com.example.asus.wechat.homeActivity.view.fragment.FriendFragment;
import com.example.asus.wechat.homeActivity.view.fragment.MineFragment;
import com.example.asus.wechat.homeActivity.view.fragment.WeChatFragment;
import com.example.asus.wechat.login.module.bean.UserBean;
import com.example.asus.wechat.util.MyConstant;

import java.util.ArrayList;
import java.util.List;


/**
 * @author liyunkun
 *         登录过后进入的首页
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;  // 登录后看到的ViewPager
    private LinearLayout ll_wechat; // 位于底部导航栏的第一个按钮，微信
    private LinearLayout ll_friend; // 位于底部导航栏的第二个按钮，通讯录
    private LinearLayout ll_moment; // 位于底部导航栏的第二个按钮，朋友圈
    private LinearLayout ll_mine;  // 位于底部导航栏的第二个按钮，我的

    private ImageView iv_wechat; // 点击了底部导航栏的按钮之后，图片换了另一张，显示被点击状态
    private ImageView iv_friend;
    private ImageView iv_moment;
    private ImageView iv_mine;
    private TextView tv_wechat;
    private TextView tv_friend;
    private TextView tv_moment;
    private TextView tv_mine;
    public UserBean loginUserBean;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        loginUserBean = (UserBean) getIntent().getSerializableExtra(MyConstant.USER_BEAN);
        MyConstant.userBean=loginUserBean;
        // loginUserBean=new UserBean();

        initView();
    }

    private void initView() {
        inflater = LayoutInflater.from(this);


        viewPager = ((ViewPager) findViewById(R.id.viewPager));
        ll_wechat = ((LinearLayout) findViewById(R.id.ll_wechat));
        ll_friend = ((LinearLayout) findViewById(R.id.ll_friend));
        ll_moment = ((LinearLayout) findViewById(R.id.ll_moment));
        ll_mine = ((LinearLayout) findViewById(R.id.ll_mine));

        iv_wechat = ((ImageView) findViewById(R.id.iv_wechat));
        iv_friend = ((ImageView) findViewById(R.id.iv_friend));
        iv_moment = ((ImageView) findViewById(R.id.iv_moment));
        iv_mine = ((ImageView) findViewById(R.id.iv_mine));

        tv_wechat = ((TextView) findViewById(R.id.tv_wechat));
        tv_friend = ((TextView) findViewById(R.id.tv_friend));
        tv_moment = ((TextView) findViewById(R.id.tv_moment));
        tv_mine = ((TextView) findViewById(R.id.tv_mine));

        ImageView iv_add = ((ImageView) findViewById(R.id.iv_add));
        iv_add.setOnClickListener(this);

        initViewPager(viewPager); // 初始化ViewPager里面的Fragment

        wechat_click();// 第一个按钮默认被选中

        ll_wechat.setOnClickListener(new View.OnClickListener() { // 如果底部导航栏的第一个按钮被点击了，就更换图片，告诉用户，当前这个被点击了
            @Override
            public void onClick(View v) {

                wechat_click();// 当wechat按钮被点击

                viewPager.setCurrentItem(0); // 点击按钮wechat之后，显示ViewPager中的第一个Fragment
            }
        });

        ll_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                friend_click();

                viewPager.setCurrentItem(1);
            }
        });

        ll_moment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moment_click();

                viewPager.setCurrentItem(2);
            }
        });

        ll_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mine_click();

                viewPager.setCurrentItem(3);
            }
        });


    }

    private void mine_click() {
        iv_wechat.setImageResource(R.drawable.wechat);
        iv_friend.setImageResource(R.drawable.friend);
        iv_moment.setImageResource(R.drawable.moment);
        iv_mine.setImageResource(R.drawable.mine_click);

        tv_wechat.setTextColor(getResources().getColor(android.R.color.darker_gray));
        tv_friend.setTextColor(getResources().getColor(android.R.color.darker_gray));
        tv_moment.setTextColor(getResources().getColor(android.R.color.darker_gray));
        tv_mine.setTextColor(getResources().getColor(R.color.green));
    }

    private void moment_click() {
        iv_wechat.setImageResource(R.drawable.wechat);
        iv_friend.setImageResource(R.drawable.friend);
        iv_moment.setImageResource(R.drawable.moment_click);
        iv_mine.setImageResource(R.drawable.mine);

        tv_wechat.setTextColor(getResources().getColor(android.R.color.darker_gray));
        tv_friend.setTextColor(getResources().getColor(android.R.color.darker_gray));
        tv_moment.setTextColor(getResources().getColor(R.color.green));
        tv_mine.setTextColor(getResources().getColor(android.R.color.darker_gray));
    }

    private void friend_click() {
        iv_wechat.setImageResource(R.drawable.wechat);
        iv_friend.setImageResource(R.drawable.friend_click);
        iv_moment.setImageResource(R.drawable.moment);
        iv_mine.setImageResource(R.drawable.mine);

        tv_wechat.setTextColor(getResources().getColor(android.R.color.darker_gray));
        tv_friend.setTextColor(getResources().getColor(R.color.green));
        tv_moment.setTextColor(getResources().getColor(android.R.color.darker_gray));
        tv_mine.setTextColor(getResources().getColor(android.R.color.darker_gray));
    }

    private void wechat_click() {
        iv_wechat.setImageResource(R.drawable.wechat_click);
        iv_friend.setImageResource(R.drawable.friend);
        iv_moment.setImageResource(R.drawable.moment);
        iv_mine.setImageResource(R.drawable.mine);

        tv_wechat.setTextColor(getResources().getColor(R.color.green));
        tv_friend.setTextColor(getResources().getColor(android.R.color.darker_gray));
        tv_moment.setTextColor(getResources().getColor(android.R.color.darker_gray));
        tv_mine.setTextColor(getResources().getColor(android.R.color.darker_gray));
    }

    private void initViewPager(ViewPager viewPager) {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new WeChatFragment()); // 微信，第一个按钮
        fragmentList.add(new FriendFragment()); // 通讯录，第二个按钮
        fragmentList.add(new FindFragment()); // 发现，第三个按钮
        fragmentList.add(new MineFragment());// 我的，第四个按钮
        viewPager.setAdapter(new WeChatVpAdapter(getSupportFragmentManager(), fragmentList));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    wechat_click();
                } else if (position == 1) {
                    friend_click();
                } else if (position == 2) {
                    moment_click();
                } else if (position == 3) {
                    mine_click();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add:
                showPw();
                break;
        }
    }

    private void showPw() {
        View view = inflater.inflate(R.layout.home_pw_item, null);
        PopupWindow pw = new PopupWindow(view,
                (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 256, getResources().getDisplayMetrics()))
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        pw.setOutsideTouchable(true);
        pw.setBackgroundDrawable(new BitmapDrawable());
        pw.showAtLocation(iv_friend, Gravity.TOP,
                (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics())),
                (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics())));
    }
}
