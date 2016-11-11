package com.example.asus.wechat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.core.ConnectionStatus;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.newim.listener.ConnectStatusChangeListener;
import cn.bmob.newim.listener.ConversationListener;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;

/*  查询全部会话
     BmobIM.getInstance().loadAllConversation()
     查询未读消息
     查询指定会话下的未读消息数量：
     BmobIM.getInstance().getUnReadCount(String conversationId)
     查询全部会话的全部未读消息数量：
     BmobIM.getInstance().getUnReadCount.getAllUnReadCount()
     删除指定会话
        //提供两种方式删除会话
     BmobIM.getInstance().deleteConversation(BmobIMConversation c);
     BmobIM.getInstance().deleteConversation(String conversationId);
     清空会话
     BmobIM.getInstance().clearAllConversation();*/
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
//连接服务器：
       // User user = BmobUser.getCurrentUser(this, User.class);
        User user = new User("123", "456");

    //    user.setId("123");

        //调用connect方法，需要传入一个唯一的用户标示getId，Demo使用的是Bmob的用户登录系统。
        //BmobIM.init(this);
        BmobIM.connect(user.getId(), new ConnectListener() {
            @Override
            public void done(String uid, BmobException e) {
                if (e == null) {
                    Log.d("zanZQ", "done:connect success");
                } else {
                    Log.d("zanZQ", "done: "+e.getErrorCode() + "/" + e.getMessage());
                }
            }
        });

        //调用setOnConnectStatusChangeListener方法即可监听到当前长链接的连接状态
        BmobIM.getInstance().setOnConnectStatusChangeListener(new ConnectStatusChangeListener() {
            @Override
            public void onChange(ConnectionStatus status) {
                Log.d("zanZQ", "onChange: "+status.getMsg());
            }
        });
    }
    /*   参数：
       info：与谁聊天，就填谁的信息
       isTransient：true-不保存，false-保存  默认保存

       注：v2.0.4版本的NewIM开始提供此种方式创建暂态会话*/

    //BmobIMUserInfo类，是用户信息类，有三个属性需要开发者关注下：userId(用户id，唯一)，name(用户名)，avatar（用户头像）。
    //开启私聊会话，可设置会话是否保存到自己的本地会话表中
    public void getPrivateChat(BmobIMUserInfo userInfo){
       //如果需要更新用户资料，开发者只需要传新的info进去就可以了
      BmobIM.getInstance().startPrivateConversation( userInfo, new ConversationListener() {
           @Override
           public void done(BmobIMConversation c, BmobException e) {
               if(e==null){
                   //在此跳转到聊天页面
                   Bundle bundle = new Bundle();
                   bundle.putSerializable("c", c);
                   Intent intent = new Intent(BaseActivity.this, null);
                   startActivity(intent, bundle);
               }else{
                   ;
                   Toast.makeText(BaseActivity.this, e.getMessage()+"("+e.getErrorCode()+")", Toast.LENGTH_SHORT).show();
               }
           }
       });
   }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //断开连接：调用disConnect方法，客户端会断开与服务器之间的连接，再次聊天需要重新调用connect方法完成与服务器之间的连接。
        BmobIM.getInstance().disConnect();
    }
}
