package com.example.asus.wechat.login.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.wechat.R;
import com.example.asus.wechat.login.module.CountryBean;
import com.example.asus.wechat.util.CustomView.LetterIndexView;
import com.example.asus.wechat.util.ParseJsonUtil;
import com.example.asus.wechat.util.PinYin4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AreaActivity extends AppCompatActivity {
    private List<Set<String>> NamePinYin; //全拼集合
    private List<Set<String>> NamePY;     //简拼集合
    private List<String> NameEnglish;     //英语名称集合
    //过滤后的数据源
    private List<CountryBean> countryBeanList;
    private PinYin4j pinYin4j;
    //原数据源
    List<CountryBean> countryDataByJson;
    private ListView lv;
    RelativeLayout search_layout;
    RelativeLayout edit_layout;
    AreaAdapter adapter;
    EditText edit_text;

   private boolean isAdd=false;
    // 搜索结果之后的数据
    List<CountryBean> beanList;
    TextView hint_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);
        //解析地区数据
        countryDataByJson = ParseJsonUtil.getCountryDataByJson(this);
        pinYin4j = new PinYin4j();
        NamePinYin=new ArrayList<>();
        NameEnglish=new ArrayList<>();
        NamePY=new ArrayList<>();
        countryBeanList=new ArrayList<>();
        //准备搜索条件
        for (CountryBean countryBean : countryDataByJson) {
            countryBeanList.add(countryBean);
            NamePinYin.add(pinYin4j.getAllPinyin(countryBean.getCountryName()));
            NamePY.add(pinYin4j.getPinyin(countryBean.getCountryName()));
            NameEnglish.add(countryBean.getEnglishName().toString().toLowerCase());
        }
        initView();
    }



    private void initView() {
        hint_text = ((TextView) findViewById(R.id.hint_text));
        lv = (ListView) findViewById(R.id.lv);

        edit_layout = ((RelativeLayout) findViewById(R.id.edit_layout));
        edit_text = ((EditText) findViewById(R.id.edit_query));
        search_layout = ((RelativeLayout) findViewById(R.id.search_layout));
        TextView showLetterTv = (TextView) findViewById(R.id.showLetterTv);
        final LetterIndexView letterIndexView = (LetterIndexView) findViewById(R.id.letterIndexView);
        letterIndexView.setShowLetterTv(showLetterTv);
        adapter = new AreaAdapter(countryBeanList, this);
        lv.setAdapter(adapter);
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
                CountryBean countryBean = countryBeanList.get(i);
                Intent intent = new Intent();
                intent.putExtra("CountryName",countryBean.getCountryName());
                intent.putExtra("AreaCode",countryBean.getAreaCode());
                AreaActivity.this.setResult(0,intent);
                AreaActivity.this.finish();
                Toast.makeText(AreaActivity.this,countryBean.getCountryName(), Toast.LENGTH_SHORT).show();
            }
        });
        //设置edit的文本改变监听事件。更新查询结果
         edit_text.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
              //   Log.d("zanZQ", "onTextChanged: "+charSequence+"b"+i);
                 //每次更新完重新备份数据源
                 countryBeanList.clear();
                 countryBeanList.addAll(countryDataByJson);
                 if (charSequence==null||charSequence.toString().length()==0){

                 }else{
                     //保存查询结果
                     beanList = new ArrayList<>();
                     //获取用户输入文本
                     String lowerCase = charSequence.toString().toLowerCase();
                     for (int j = 0; j < countryBeanList.size(); j++) {
                         isAdd = false;
                         CountryBean bean = countryBeanList.get(j);
                         //假设用户输入了地区码查询条件
                         if (bean.getAreaCode().contains(lowerCase)){
                             beanList.add(bean);
                             isAdd=true;
                         }
                         //假设用户输入了中文查询条件
                       else  if (bean.getCountryName().contains(lowerCase)){
                             beanList.add(bean);
                             isAdd=true;
                         }else{
                             if (!isAdd) {
                                 //用户输入了拼音查询条件
                                 //姓名全拼查询
                                 Set<String> stringPinyinSet = NamePinYin.get(j);
                                 Iterator<String> iterator = stringPinyinSet.iterator();
                                 while (iterator.hasNext()) {
                                     String next = iterator.next();
                                     if (next.contains(lowerCase)) {
                                         beanList.add(bean);
                                         isAdd = true;
                                         break;
                                     }
                                 }
                             }
                             if (!isAdd){
                                 //姓名简拼查询
                                 Set<String> NamePYSet = NamePY.get(j);
                                 Iterator<String> iterator1 = NamePYSet.iterator();
                                 while (iterator1.hasNext()) {
                                     String next = iterator1.next();
                                     if (next.contains(lowerCase)) {
                                         beanList.add(bean);
                                         isAdd = true;
                                         break;
                                     }
                                 }
                             }
                             if (!isAdd){
                                 //英文查询
                                 if (NameEnglish.get(j).contains(lowerCase)) {
                                     beanList.add(bean);
                                     isAdd = true;
                                     break;

                                 }
                             }
             }}
                     // 设置查询结果
                     countryBeanList.clear();
                     countryBeanList.addAll(beanList);
                 }

                 adapter.notifyDataSetChanged();
             }

             @Override
             public void afterTextChanged(Editable editable) {

             }
         });
    }
 //点击切换到输入框 并修改原来控件显示本文，以免点击后后退 直接销毁activity
    public void search_iv(View view) {

        search_layout.setVisibility(View.GONE);
        edit_layout.setVisibility(View.VISIBLE);
        hint_text.setText("123");
    }
    //后退时间的处理
    public void go_back(View view) {
        if (hint_text.getText().toString().equals("选择国家和地区代码")){
            this.finish();
        }else{
            search_layout.setVisibility(View.VISIBLE);
            edit_layout.setVisibility(View.GONE);
            // 关闭输入框，重新设置文本
            hint_text.setText("选择国家和地区代码");
        }

    }
    // 清空输入框文本
    public void delete_input(View view) {
        edit_text.setText("");
    }
}
