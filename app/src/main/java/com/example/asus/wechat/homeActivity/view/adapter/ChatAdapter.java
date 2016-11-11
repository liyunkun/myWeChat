package com.example.asus.wechat.homeActivity.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.wechat.R;
import com.example.asus.wechat.homeActivity.model.bean.SendBean;

import java.util.List;

/**
 * Created by ASUS on 2016/11/10.
 */

public class ChatAdapter extends BaseAdapter {
  private List<SendBean > list;
    private Context context;
    private LayoutInflater inflater;

    public ChatAdapter(List<SendBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {

        return list.get(position).getType();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SendBean sendBean = list.get(i);
        int type = sendBean.getType();
        LeftHolder leftHolder;
        RightHolder rightHolder;
        if (type==0){
              if (view==null){
                  view=inflater.inflate(R.layout.activity_chat_lv_item1,viewGroup,false);
                leftHolder=new LeftHolder();
                  leftHolder.face= (ImageView) view.findViewById(R.id.iv_userFace);
              //    leftHolder.tv_name= (TextView) view.findViewById(R.id.tv_userName);
                  leftHolder.tv_time= (TextView) view.findViewById(R.id.tv_time);
                  leftHolder.content= (TextView) view.findViewById(R.id.content);
                  view.setTag(leftHolder);
              }else{
                  leftHolder= (LeftHolder) view.getTag();
              }
            leftHolder.face.setImageResource(sendBean.getFace());
            leftHolder.content.setText(sendBean.getContent());
          //  leftHolder.tv_name.setText(sendBean.getName());
            leftHolder.tv_time.setText(sendBean.getTime());
        }else if (type==1){
            if (view==null){
                view=inflater.inflate(R.layout.activity_chat_lv_item2,viewGroup,false);
                rightHolder=new RightHolder();
                rightHolder.face= (ImageView) view.findViewById(R.id.iv_userFace);
              //  rightHolder.tv_name= (TextView) view.findViewById(R.id.tv_userName);
                rightHolder.tv_time= (TextView) view.findViewById(R.id.tv_time);
                rightHolder.content= (TextView) view.findViewById(R.id.content);
                view.setTag(rightHolder);
            }else{
                rightHolder= (RightHolder) view.getTag();
            }
            rightHolder.face.setImageResource(sendBean.getFace());
            rightHolder.content.setText(sendBean.getContent());
          //  rightHolder.tv_name.setText(sendBean.getName());
            rightHolder.tv_time.setText(sendBean.getTime());
        }

        return view;
    }
    class LeftHolder{
        ImageView face;
        TextView tv_time,tv_name,content;
    }
    class RightHolder{
        ImageView face;
        TextView tv_time,tv_name,content;
    }
}
