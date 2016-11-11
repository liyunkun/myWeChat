package com.example.asus.wechat.login.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.asus.wechat.R;
import com.example.asus.wechat.login.module.bean.UserBean;
import com.example.asus.wechat.login.presenter.OtherLoginPresenter;
import com.example.asus.wechat.login.view.intf.IOtherLoginView;
import com.example.asus.wechat.util.MyConstant;
import com.example.asus.wechat.util.UserBeanConstant;

import java.util.List;

public class OtherLoginActivity extends AppCompatActivity implements View.OnClickListener, IOtherLoginView {

    private EditText mOtherNumber;
    private EditText mPassword;
    private Button mLogin;
    private ImageView mDeleteTv1;
    private ImageView mDeleteTv2;
    private OtherLoginPresenter presenter = new OtherLoginPresenter(this);
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_login);
        initView();
        initButton();
    }

    private void initView() {
        sp = getSharedPreferences(MyConstant.LOAD_SP,MODE_PRIVATE);

        ImageView goBack = (ImageView) findViewById(R.id.go_back);
        mOtherNumber = ((EditText) findViewById(R.id.other_number));
        mPassword = ((EditText) findViewById(R.id.password));
        mLogin = ((Button) findViewById(R.id.login));
        mDeleteTv1 = ((ImageView) findViewById(R.id.delete_tv));
        mDeleteTv2 = ((ImageView) findViewById(R.id.delete_tv2));
        mDeleteTv1.setOnClickListener(this);
        mDeleteTv2.setOnClickListener(this);
        goBack.setOnClickListener(this);
        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String result = s.toString();
                if (result != null && !"".equals(result)) {
                    mDeleteTv2.setVisibility(View.VISIBLE);
                } else {
                    mDeleteTv2.setVisibility(View.GONE);
                }
                initButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mOtherNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String result = s.toString();
                if (result != null && !"".equals(result)) {
                    mDeleteTv1.setVisibility(View.VISIBLE);
                } else {
                    mDeleteTv1.setVisibility(View.GONE);
                }
                initButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setButtonEnabled() {
        mLogin.setEnabled(true);
        mLogin.setBackgroundColor(getResources().getColor(R.color.green));
    }

    private void setButtonUnEnabled() {
        mLogin.setEnabled(false);
        mLogin.setBackgroundColor(getResources().getColor(R.color.green_dark));
    }

    private String getOtherNumber() {
        return mOtherNumber.getText().toString();
    }

    private String getPassword() {
        return mPassword.getText().toString();
    }

    private boolean isEmpty() {
        if (getPassword() != null && !"".equals(getPassword()) && getOtherNumber() != null && !"".equals(getOtherNumber())) {
            return false;
        }
        return true;
    }

    private void initButton() {
        if (isEmpty()) {
            setButtonUnEnabled();
        } else {
            setButtonEnabled();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_back:
                finish();
                break;
            case R.id.delete_tv:
                mOtherNumber.setText("");
                break;
            case R.id.delete_tv2:
                mPassword.setText("");
                break;
            case R.id.login:
                if (isRightInput()) {
                    presenter.start(getOtherNumber());
                } else {
                    Toast.makeText(OtherLoginActivity.this, "帐号的格式不正确", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void getData(List<UserBean> list) {
        UserBean userBean = list.get(0);
        if (getPassword().equals(userBean.getPassword())) {
            Toast.makeText(OtherLoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            sp.edit()
                    .putBoolean(MyConstant.IS_FIRST_LOGIN, true)
                    .putString(UserBeanConstant.USER_IMG, userBean.getUserImgUrl())
                    .putString(UserBeanConstant.NICK_NAME, userBean.getNickName())
                    .putString(UserBeanConstant.QQ_NUMBER, userBean.getQQNumber())
                    .putString(UserBeanConstant.E_MAIL, userBean.getE_mail())
                    .putString(UserBeanConstant.WE_CHAT_ID, userBean.getWeChatId())
                    .putString(UserBeanConstant.QR_CODE_URL, userBean.getQRCodeUrl())
                    .putString(UserBeanConstant.ADDRESS, userBean.getQRCodeUrl())
                    .putString(UserBeanConstant.GENDER, userBean.getQRCodeUrl())
                    .putString(UserBeanConstant.COUNTRY, userBean.getQRCodeUrl())
                    .putString(UserBeanConstant.SIGNATURE, userBean.getQRCodeUrl())
                    .putString(UserBeanConstant.PHONE_NUMBER, userBean.getQRCodeUrl())
                    .putString(UserBeanConstant.PASSWORD, userBean.getQRCodeUrl())
                    .putString(UserBeanConstant.PHONE_CODE, userBean.getQRCodeUrl())
                    .putString(UserBeanConstant.FIRST_LETTER, userBean.getFirstLetter())
                    .putString(UserBeanConstant.OBJECT_ID, userBean.getObjectId())
                    .commit();
            Intent intent = new Intent(OtherLoginActivity.this, HomeActivity.class);
            intent.putExtra(MyConstant.USER_BEAN, userBean);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(OtherLoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getError(String errorMsg) {
        Toast.makeText(OtherLoginActivity.this, "请确定是否已配置该帐号信息", Toast.LENGTH_SHORT).show();
    }

    private boolean isRightInput() {
        String EmailRegex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        String QQRegex = "[1-9][0-9]{4,14}";
        String weChatIdRegex = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$";//字母开头，允许字母数字下划线，5-16个字节
        if (getOtherNumber().matches(EmailRegex) || getOtherNumber().matches(QQRegex) || getOtherNumber().matches(weChatIdRegex)) {
            return true;
        }
        return false;
    }
}
