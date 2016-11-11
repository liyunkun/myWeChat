package com.example.asus.wechat.homeActivity.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.asus.wechat.BaseFragment;
import com.example.asus.wechat.R;
import com.example.asus.wechat.homeActivity.view.activtity.PhotoSetActivity;
import com.example.asus.wechat.homeActivity.view.activtity.mine.CollectActivity;
import com.example.asus.wechat.homeActivity.view.activtity.mine.SettingActivity;

/**
 * Created by Administrator on 2016/11/9 0009.
 */
public class MineFragment extends BaseFragment {
    private LinearLayout ll_photo_album;
    private LinearLayout ll_setting;
    private LinearLayout ll_collect;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_fg_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        //相册
        ll_photo_album = ((LinearLayout) view.findViewById(R.id.ll_photo_album));
        ll_photo_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PhotoSetActivity.class));
            }
        });
        //设置
        ll_setting = ((LinearLayout) view.findViewById(R.id.ll_setting));
        ll_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SettingActivity.class));
            }
        });
        //收藏
        ll_collect = ((LinearLayout) view.findViewById(R.id.ll_collect));
        ll_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CollectActivity.class));
            }
        });
    }
}
