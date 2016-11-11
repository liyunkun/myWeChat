package com.example.asus.wechat.homeActivity.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.wechat.BaseFragment;
import com.example.asus.wechat.R;

/**
 * Created by Administrator on 2016/11/9 0009.
 */
public class FindFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_fg_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

    }
}
