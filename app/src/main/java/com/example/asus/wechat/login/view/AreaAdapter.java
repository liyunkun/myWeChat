package com.example.asus.wechat.login.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.example.asus.wechat.R;
import com.example.asus.wechat.login.module.CountryBean;
import com.example.asus.wechat.util.PinYin4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by ASUS on 2016/11/9.
 */

public class AreaAdapter extends BaseAdapter implements SectionIndexer {
    private List<CountryBean> list;

    private Context context;
    private LayoutInflater inflater;

    public AreaAdapter(List<CountryBean> list, Context context) {
        this.list = list;
        this.context = context;
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
            view = inflater.inflate(R.layout.activity_area_item, viewGroup, false);
        holder=new ViewHolder();
            holder.firstLetterTv= ((TextView) view.findViewById(R.id.firstLetterTv));
            holder.countrynameTv= (TextView) view.findViewById(R.id.CountrynameTv);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        CountryBean bean = list.get(i);
        holder.countrynameTv.setText(bean.getCountryName());
        //获取当前要显示的item所属的分组
        int sectionForPosition = getSectionForPosition(i);
        //获取该分组中第一个item的position
        int positionForSection = getPositionForSection(sectionForPosition);
        if (i == positionForSection) {
            holder.firstLetterTv.setVisibility(View.VISIBLE);
            holder.firstLetterTv.setText(bean.getFirstLetter());
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
        TextView firstLetterTv, countrynameTv;
    }

}
