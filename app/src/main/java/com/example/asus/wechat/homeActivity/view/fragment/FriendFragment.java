package com.example.asus.wechat.homeActivity.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.wechat.BaseFragment;
import com.example.asus.wechat.R;
import com.example.asus.wechat.homeActivity.model.bean.SendBean;
import com.example.asus.wechat.homeActivity.view.activtity.ChatActivity;
import com.example.asus.wechat.homeActivity.view.adapter.FriendLvAdapter;
import com.example.asus.wechat.login.module.bean.UserBean;
import com.example.asus.wechat.login.view.HomeActivity;
import com.example.asus.wechat.util.CustomView.LetterIndexView;
import com.example.asus.wechat.util.MyConstant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ASUS on 2016/11/10.
 */

public class FriendFragment extends BaseFragment {
    View view;
    ListView lv;
    List<UserBean> list;
    private FriendLvAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       if (view==null){
           view = inflater.inflate(R.layout.friend_fg_layout,container,false);
           initData();
          initView();
       }
        return view;
    }

    private void initData() {
       list = new ArrayList<>();
        String a[]=new String[]{"新的朋友","群聊","标签","公众号"};
        int []b=new int[]{R.drawable.wechat_click,R.drawable.moments,R.drawable.mine,R.drawable.friend};
        for (int i = 0; i < 4; i++) {
            UserBean userBean = new UserBean();
            userBean.setNickName(a[i]);
            userBean.setUserImgUrl(b[i]+"");
            userBean.setFirstLetter("↑");
            list.add(userBean);
        }
        for (int i = 0; i < 20; i++) {
            UserBean userBean = new UserBean();
            userBean.setUserImgUrl(R.drawable.friend_click+"");
            userBean.setNickName("z"+1);
            userBean.setObjectId(i + "");
            userBean.setFirstLetter("z");
            list.add(userBean);
        }
        Collections.sort(list, new Comparator<UserBean>() {
            @Override
            public int compare(UserBean o1, UserBean o2) {
                if (o1.getFirstLetter().equals("#")) {
                    return 1;
                } else if (o2.getFirstLetter().equals("#")) {
                    return -1;
                }else if (o2.getFirstLetter().equals("↑")){
                    return 1;
                } if (o1.getFirstLetter().equals("↑")) {
                    return -1;
                }else {
                    return o1.getFirstLetter().compareTo(o2.getFirstLetter());
                }
            }
        });
    }

    private void initView() {
        lv = ((ListView) view.findViewById(R.id.lv));
        final LetterIndexView letterIndexView = (LetterIndexView) view.findViewById(R.id.letterIndexView);
        TextView showLetterTv = (TextView) view.findViewById(R.id.showLetterTv);
        letterIndexView.setShowLetterTv(showLetterTv);
        adapter = new FriendLvAdapter(getActivity(),list);
        lv.setAdapter(adapter);
        lv.addFooterView(initFootView());

        //更新listview的位置
        letterIndexView.setUpdateListView(new LetterIndexView.UpdateListView() {
            @Override
            public void updateListView(int section) {
                int positionForSection = adapter.getPositionForSection(section);
                lv.setSelection(positionForSection);
            }
        });

        //设置listview滑动时间 更新右侧字母的显示
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int sectionForPosition = adapter.getSectionForPosition(firstVisibleItem);
                letterIndexView.updateLetterIndexView(sectionForPosition);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "发起聊天", Toast.LENGTH_SHORT).show();

               if (i>3){
                   Intent intent = new Intent(getActivity(), ChatActivity.class);
                   UserBean userBean = list.get(i);
              intent.putExtra(MyConstant.USER_BEAN, ((HomeActivity) getActivity()).loginUserBean);
                   intent.putExtra(MyConstant.FRIEND_BEAN,userBean);
                   getActivity().startActivity(intent);
               }
            }
        });
    }





    private TextView initFootView() {
        TextView textView = new TextView(getActivity());
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, getResources().getDisplayMetrics()));
         textView.setLayoutParams(layoutParams);
        textView.setText("共有"+(list.size()-4)+"位好友");
        textView.setGravity(Gravity.CENTER);
       textView.setBackgroundColor(Color.GRAY);
        textView.setTextSize(18);
        return textView;
    }
}
