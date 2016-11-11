package com.example.asus.wechat.login.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.wechat.R;
import com.example.asus.wechat.login.module.bean.UserBean;
import com.example.asus.wechat.login.presenter.RegisterPresenter;
import com.example.asus.wechat.login.view.intf.IRegisterView;
import com.example.asus.wechat.util.MyConstant;
import com.example.asus.wechat.util.UserBeanConstant;

import java.util.ArrayList;
import java.util.List;

public class DefaultLoginActivity extends AppCompatActivity implements View.OnClickListener, IRegisterView {

    private EditText mPassword;
    private ImageView mDeleteTv;
    private Button mLogin;
    private UserBean userBean;
    private TextView mPhoneNumber;
    private LayoutInflater mInflater;
    private PopupWindow pw;
    private RegisterPresenter presenter = new RegisterPresenter(this);
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_login);
        initView();
    }

    @Override
    public void onBackPressed() {
        if (pw != null && pw.isShowing()) {
            pw.dismiss();
        } else {
            super.onBackPressed();
        }
    }

    private void initView() {
        sp = getSharedPreferences(MyConstant.LOAD_SP,MODE_PRIVATE);
        userBean = ((UserBean) getIntent().getSerializableExtra(MyConstant.USER_BEAN));
        mPassword = ((EditText) findViewById(R.id.password));
        mDeleteTv = ((ImageView) findViewById(R.id.delete_tv));
        mLogin = ((Button) findViewById(R.id.login));
        TextView more = (TextView) findViewById(R.id.more);
        mPhoneNumber = (TextView) findViewById(R.id.phone_number);
        mInflater = LayoutInflater.from(this);
        mPhoneNumber.setText(userBean.getPhoneNumber());
        more.setOnClickListener(this);
        mLogin.setOnClickListener(this);
        mDeleteTv.setOnClickListener(this);
        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String result = s.toString();
                if (result != null && result.length() > 0) {
                    mDeleteTv.setVisibility(View.VISIBLE);
                    setButtonEnabledTrue();

                } else {
                    mDeleteTv.setVisibility(View.GONE);
                    setButtonEnabledFalse();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private String getPhoneNumber() {
        return mPhoneNumber.getText().toString();
    }

    private String getPassword() {
        return mPassword.getText().toString();
    }

    private void setButtonEnabledTrue() {
        mLogin.setEnabled(true);
        mLogin.setBackgroundColor(getResources().getColor(R.color.green));
    }

    private void setButtonEnabledFalse() {
        mLogin.setEnabled(false);
        mLogin.setBackgroundColor(getResources().getColor(R.color.green_dark));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete_tv:
                mPassword.setText("");
                break;
            case R.id.login:
                presenter.start(getPhoneNumber());
                break;
            case R.id.more:
                showPw();
                break;
        }
    }

    private void showPw() {
        View view = mInflater.inflate(R.layout.login_pw_item, null);
        initPwView(view);
        pw = new PopupWindow(view,
                (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, getResources().getDisplayMetrics())),
                ViewGroup.LayoutParams.WRAP_CONTENT);
        pw.setOutsideTouchable(true);
        pw.setBackgroundDrawable(new BitmapDrawable());
        pw.showAtLocation(mLogin, Gravity.CENTER, 0, 0);
    }

    private void initPwView(View view) {
        ListView lv = (ListView) view.findViewById(R.id.lv);
        List<String> list = new ArrayList<>();
        list.add("切换帐号");
        list.add("注册");
        list.add("微信安全中心");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(DefaultLoginActivity.this, LoginActivity.class));
                        pw.dismiss();
                        break;
                    case 1:
                        startActivity(new Intent(DefaultLoginActivity.this, RegisterActivity.class));
                        pw.dismiss();
                        break;
                    case 2:
                        Toast.makeText(DefaultLoginActivity.this, "该功能还在完善中", Toast.LENGTH_SHORT).show();
                        pw.dismiss();
                        break;
                }
            }
        });
    }


    @Override
    public void getData(List<UserBean> list) {
        if (list != null && list.size() > 0) {
            UserBean bean = list.get(0);
            if (getPassword().equals(bean.getPassword())) {
                Toast.makeText(DefaultLoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                sp.edit()
                        .putBoolean(MyConstant.IS_FIRST_LOGIN, true)
                        .putString(UserBeanConstant.USER_IMG, bean.getUserImgUrl())
                        .putString(UserBeanConstant.NICK_NAME, bean.getNickName())
                        .putString(UserBeanConstant.QQ_NUMBER, bean.getQQNumber())
                        .putString(UserBeanConstant.E_MAIL, bean.getE_mail())
                        .putString(UserBeanConstant.WE_CHAT_ID, bean.getWeChatId())
                        .putString(UserBeanConstant.QR_CODE_URL, bean.getQRCodeUrl())
                        .putString(UserBeanConstant.ADDRESS, bean.getQRCodeUrl())
                        .putString(UserBeanConstant.GENDER, bean.getQRCodeUrl())
                        .putString(UserBeanConstant.COUNTRY, bean.getQRCodeUrl())
                        .putString(UserBeanConstant.SIGNATURE, bean.getQRCodeUrl())
                        .putString(UserBeanConstant.PHONE_NUMBER, bean.getQRCodeUrl())
                        .putString(UserBeanConstant.PASSWORD, bean.getQRCodeUrl())
                        .putString(UserBeanConstant.PHONE_CODE, bean.getQRCodeUrl())
                        .putString(UserBeanConstant.FIRST_LETTER, bean.getFirstLetter())
                        .putString(UserBeanConstant.OBJECT_ID, bean.getObjectId())
                        .commit();
                Intent intent = new Intent(DefaultLoginActivity.this, HomeActivity.class);
                intent.putExtra(MyConstant.USER_BEAN, bean);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(DefaultLoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(DefaultLoginActivity.this, "信息填写错误", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        Toast.makeText(DefaultLoginActivity.this, "信息填写错误", Toast.LENGTH_SHORT).show();
    }
}
