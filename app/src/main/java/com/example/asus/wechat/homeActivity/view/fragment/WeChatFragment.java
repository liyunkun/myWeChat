package com.example.asus.wechat.homeActivity.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asus.wechat.BaseFragment;
import com.example.asus.wechat.R;
import com.example.asus.wechat.homeActivity.model.bean.SendBean;
import com.example.asus.wechat.homeActivity.view.activtity.ChatActivity;
import com.example.asus.wechat.homeActivity.view.adapter.WeChatLvAdapter;
import com.example.asus.wechat.login.module.bean.UserBean;
import com.example.asus.wechat.login.view.HomeActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.core.ConnectionStatus;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.newim.listener.ConnectStatusChangeListener;
import cn.bmob.newim.listener.ConversationListener;
import cn.bmob.v3.exception.BmobException;

/**
 * Created by Administrator on 2016/11/9 0009.
 */
public class WeChatFragment extends BaseFragment  {
    private ListView lv;
    private SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: {
                    //如果需要更新用户资料，开发者只需要传新的info进去就可以了
                    BmobIMUserInfo info = new BmobIMUserInfo();
                    info.setId(101L);

                    BmobIM.getInstance().startPrivateConversation(info, new ConversationListener() {
                        @Override
                        public void done(BmobIMConversation c, BmobException e) {
                            if (e == null) {
                                //在此跳转到聊天页面
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("c", c);
                                Intent intent = new Intent(getActivity(), ChatActivity.class);
                                startActivity(intent, bundle);
                            } else {
                                Toast.makeText(getActivity(), e.getMessage() + "(" + e.getErrorCode() + ")", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                break;
            }
        }
    };
    List<SendBean> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wechat_fg_layout, container, false);
        initView(view);
        createChat(); // 创建聊天，连接服务器
        return view;
    }

    private void createChat() {

        UserBean user = ((HomeActivity) getActivity()).loginUserBean;

        //监听到当前长链接的连接状态
        BmobIM.getInstance().setOnConnectStatusChangeListener(new ConnectStatusChangeListener() {
            @Override
            public void onChange(ConnectionStatus status) {
                Toast.makeText(getActivity(), status.getMsg(), Toast.LENGTH_LONG).show();
            }
        });

        BmobIM.connect(user.getObjectId(), new ConnectListener() {
            @Override
            public void done(String uid, BmobException e) {
                if (e == null) {
                    Toast.makeText(getActivity(), "connect success", Toast.LENGTH_SHORT).show();
                    mHandler.sendEmptyMessage(0);
                } else {
                    Toast.makeText(getActivity(), "e.getErrorCode() + \"/\" + e.getMessage()", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void initView(View view) {
        lv = ((ListView) view.findViewById(R.id.lv));
        initLv(lv);
    }

    private void initLv(ListView lv) {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
         SendBean   sendBean = new SendBean(i+"",dateFormat.format(new Date()),"我",i+"",R.mipmap.ic_launcher,0);
        list.add(sendBean);
        }
        lv.setAdapter(new WeChatLvAdapter(list, getActivity()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BmobIM.getInstance().disConnect();// 断开与服务器的连接
    }
}
