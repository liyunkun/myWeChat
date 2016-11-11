package com.example.asus.wechat.homeActivity.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.example.asus.wechat.R;
import com.example.asus.wechat.login.module.bean.UserBean;

import java.util.List;

/**
 * Created by ASUS on 2016/11/10.
 */

public class FriendLvAdapter extends BaseAdapter implements SectionIndexer {
    private LayoutInflater inflater;
    private Context context;
    private List<UserBean> list;

    public FriendLvAdapter(Context context, List<UserBean> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
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
        ViewHolder holder;
        if (view==null){
            view=inflater.inflate(R.layout.friend_fg_lv_item,viewGroup,false);
            holder=new ViewHolder();
            holder.firstLetterTv= ((TextView) view.findViewById(R.id.firstLetterTv));
            holder.userface= (ImageView) view.findViewById(R.id.iv_userFace);
            holder.nameTv= (TextView) view.findViewById(R.id.tv_userName);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        UserBean bean = list.get(i);
        holder.nameTv.setText(bean.getNickName());
        holder.userface.setImageResource(Integer.valueOf(bean.getUserImgUrl()));
        //获取当前要显示的item所属的分组
        int sectionForPosition = getSectionForPosition(i);
        //获取该分组中第一个item的position
        int positionForSection = getPositionForSection(sectionForPosition);
        if (i == positionForSection) {
            if (!bean.getFirstLetter().equals("↑")){
                holder.firstLetterTv.setVisibility(View.VISIBLE);
                holder.firstLetterTv.setText(bean.getFirstLetter());
            }else{
                holder.firstLetterTv.setVisibility(View.GONE);
            }
        }else{
            holder.firstLetterTv.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getFirstLetter().charAt(0) == sectionIndex) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        if (list!=null&&list.size()>0){
            return list.get(position).getFirstLetter().charAt(0);

        }
        return 0;
    }
    class ViewHolder {
        TextView firstLetterTv, nameTv;
        ImageView userface;
    }
}
