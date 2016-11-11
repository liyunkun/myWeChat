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
import com.example.asus.wechat.login.module.bean.UserBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/9 0009.
 */
public class WeChatLvAdapter extends BaseAdapter{
    private List<SendBean> list;
    private LayoutInflater inflater;
    private Context context;

    public WeChatLvAdapter(List<SendBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.weachat_fg_lv_item,parent,false);
            holder = new ViewHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tv_detail = (TextView) convertView.findViewById(R.id.tv_detail);
            convertView.setTag(holder);
        }else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        SendBean sendBean = list.get(position);
        holder.iv.setImageResource(sendBean.getFace());
        holder.tv_title.setText(sendBean.getName());
        holder.tv_detail.setText(sendBean.getContent());
        holder.tv_time.setText(sendBean.getTime());
        return convertView;
    }

    class ViewHolder{
        ImageView iv;
        TextView tv_time,tv_title,tv_detail;
    }
}
