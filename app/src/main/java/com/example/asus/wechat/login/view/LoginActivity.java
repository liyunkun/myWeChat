package com.example.asus.wechat.login.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.wechat.R;
import com.example.asus.wechat.login.module.bean.UserBean;
import com.example.asus.wechat.login.presenter.LoginPresenter;
import com.example.asus.wechat.login.view.intf.ILoginView;
import com.example.asus.wechat.util.MyConstant;
import com.example.asus.wechat.util.UserBeanConstant;

import java.util.List;

/**
 * @author liyunkun
 *         用户登录界面
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ILoginView {

    private TextView mCountryTv;
    private TextView area_code;
    private EditText mPhoneNumber;
    private EditText mPassword;
    private Button mLogin;
    private ImageView mDeleteTv;
    private ImageView mDeleteTv2;
    private LoginPresenter presenter = new LoginPresenter(this);
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp = getSharedPreferences(MyConstant.LOAD_SP, MODE_PRIVATE);
        if (sp.getBoolean(MyConstant.IS_FIRST_LOGIN, false)) {
            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);          
            String userImg = sp.getString(UserBeanConstant.USER_IMG, "");
            String nickName = sp.getString(UserBeanConstant.NICK_NAME, "");
            String qq = sp.getString(UserBeanConstant.QQ_NUMBER, "");
            String e = sp.getString(UserBeanConstant.E_MAIL, "");
            String weChatId = sp.getString(UserBeanConstant.WE_CHAT_ID, "");
            String qr = sp.getString(UserBeanConstant.QR_CODE_URL, "");
            String add = sp.getString(UserBeanConstant.ADDRESS, "");
            String gen = sp.getString(UserBeanConstant.GENDER, "");
            String country = sp.getString(UserBeanConstant.COUNTRY, "");
            String signature = sp.getString(UserBeanConstant.SIGNATURE, "");
            String phoneNumber = sp.getString(UserBeanConstant.PHONE_NUMBER, "");
            String password = sp.getString(UserBeanConstant.PASSWORD, "");
            String phoneCode = sp.getString(UserBeanConstant.PHONE_CODE, "");
            String firstLetter = sp.getString(UserBeanConstant.FIRST_LETTER, "");
            String objectId = sp.getString(UserBeanConstant.OBJECT_ID, "");
            UserBean bean=new UserBean(userImg,nickName,qq,e,weChatId,qr,add,gen,country,signature,phoneNumber,password,phoneCode,firstLetter);
            bean.setObjectId(objectId);
            intent.putExtra(MyConstant.USER_BEAN,bean);
            startActivity(intent);
            finish();
        }
        initView();
    }

    private void initView() {
        ImageView goBack = (ImageView) findViewById(R.id.go_back);
        RelativeLayout country = (RelativeLayout) findViewById(R.id.country);
        mCountryTv = ((TextView) findViewById(R.id.country_tv));
        area_code = ((TextView) findViewById(R.id.area_Code));
        mPhoneNumber = ((EditText) findViewById(R.id.phone_number));
        mPassword = ((EditText) findViewById(R.id.password));
        mPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        mDeleteTv = ((ImageView) findViewById(R.id.delete_tv));
        mDeleteTv2 = ((ImageView) findViewById(R.id.delete_tv2));
        TextView otherLogin = (TextView) findViewById(R.id.other_login);
        otherLogin.setOnClickListener(this);
        mPassword.setSingleLine(true);
        mLogin = ((Button) findViewById(R.id.login));
        country.setOnClickListener(this);
        goBack.setOnClickListener(this);
        mLogin.setOnClickListener(this);
        mDeleteTv.setOnClickListener(this);
        mDeleteTv2.setOnClickListener(this);
        mPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String result = s.toString();
                if (result != null && result.length() > 0) {
                    mDeleteTv.setVisibility(View.VISIBLE);
                    showLoginButton();
                } else {
                    mDeleteTv.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String result = s.toString();
                if (result != null && result.length() > 0) {
                    mDeleteTv2.setVisibility(View.VISIBLE);
                } else {
                    mDeleteTv2.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                if (isRightPhoneNumber()) {
                    presenter.start(getAreaCode(), getPhoneNumber());
                } else {
                    Toast.makeText(LoginActivity.this, "手机号不正确", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.go_back:
                finish();
                break;
            case R.id.country:
                startActivityForResult(new Intent(this, AreaActivity.class), 0);
                break;
            case R.id.delete_tv:
                mPhoneNumber.setText("");
                break;
            case R.id.delete_tv2:
                mPassword.setText("");
                break;
            case R.id.other_login:
                startActivity(new Intent(this, OtherLoginActivity.class));
                break;
        }
    }

    //获取用户选择的国家
    @NonNull
    private String getCountry() {
        return mCountryTv.getText().toString();
    }

    private String getAreaCode() {
        String result = area_code.getText().toString();
        return result.substring(1);
    }

    //回调方法，从第二个页面回来的时候会执行这个方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && data != null) {
            String countryName = data.getStringExtra("CountryName");
            String areaCode = data.getStringExtra("AreaCode");
            mCountryTv.setText(countryName);
            area_code.setText("+" + areaCode);
        }
    }

    private void showLoginButton() {
        if (isEmpty()) {
            mLogin.setEnabled(false);
            mLogin.setBackgroundColor(getResources().getColor(R.color.green_dark));
        } else {
            mLogin.setEnabled(true);
            mLogin.setBackgroundColor(getResources().getColor(R.color.green));
        }
    }

    private boolean isEmpty() {
        if (getPhoneNumber() != null && !"".equals(getPhoneNumber())) {
            return false;
        }
        return true;
    }

    private String getPhoneNumber() {
        return mPhoneNumber.getText().toString();
    }

    private String getPassword() {
        return mPassword.getText().toString();
    }

    //验证是否为正确的电话号码格式
    private boolean isRightPhoneNumber() {
        String phoneNumber = getPhoneNumber();
        if (phoneNumber.matches("[1][358][0-9]{9}")) {
            return true;
        }
        return false;
    }


    @Override
    public void getData(List<UserBean> list) {
        if (list != null && list.size() > 0) {
            UserBean userBean = list.get(0);
            if (getPassword().equals(userBean.getPassword())) {
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.putExtra(MyConstant.USER_BEAN, userBean);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(LoginActivity.this, "请核对你的信息", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getError(String msg) {
        Toast.makeText(LoginActivity.this, "请核对你的信息", Toast.LENGTH_SHORT).show();
    }
}
