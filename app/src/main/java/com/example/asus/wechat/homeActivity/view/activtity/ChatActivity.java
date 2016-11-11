package com.example.asus.wechat.homeActivity.view.activtity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asus.wechat.R;
import com.example.asus.wechat.homeActivity.model.bean.SendBean;
import com.example.asus.wechat.homeActivity.view.adapter.ChatAdapter;
import com.example.asus.wechat.login.module.bean.UserBean;
import com.example.asus.wechat.util.MyConstant;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    UserBean userBean;
    UserBean friendBean;
    ListView lv;
    private SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");
     List<SendBean> list=new ArrayList<>();
    TextView friendName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Intent intent = getIntent();
        userBean = (UserBean) intent.getSerializableExtra(MyConstant.USER_BEAN);
        friendBean = (UserBean) intent.getSerializableExtra(MyConstant.FRIEND_BEAN);
        initData();
        initView();

    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            SendBean sendBean;
            if (i%2==0){
                sendBean = new SendBean(i+"",dateFormat.format(new Date()),"我",i+"",R.mipmap.ic_launcher,0);
            }else{
                sendBean = new SendBean("qwrqwdsagrwqehgrwfgfgfggrwhwreryweqwewqeqwewqeeqwrqwdasfqwrfdffqweefqwrwerwegdfvdffsdfsd"+"",dateFormat.format(new Date()),friendBean.getNickName(),i+"",R.drawable.friend,1);
            }
            Log.d("zanZQ", "initData: "+sendBean.toString());
            list.add(sendBean );
        }
    }

    private void initView() {
        lv = ((ListView) findViewById(R.id.lv));
        friendName = ((TextView) findViewById(R.id.tv_friendName));
        friendName.setText(friendBean.getNickName());
        ChatAdapter adapter = new ChatAdapter(list, this);
        lv.setAdapter(adapter);
        int firstVisiblePosition = lv.getFirstVisiblePosition();
        int lastVisiblePosition = lv.getLastVisiblePosition();
        Log.d("zanZQ", "initView: "+firstVisiblePosition+"last"+lastVisiblePosition);
        lv.setSelection(list.size());

    }

    public void iv_back(View view) {
        this.finish();
    }
}
