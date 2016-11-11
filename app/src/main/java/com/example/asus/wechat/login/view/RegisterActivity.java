package com.example.asus.wechat.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.wechat.BaseActivity;
import com.example.asus.wechat.R;
import com.example.asus.wechat.login.module.bean.UserBean;
import com.example.asus.wechat.login.presenter.RegisterPresenter;
import com.example.asus.wechat.login.view.intf.IRegisterView;
import com.example.asus.wechat.util.MyConstant;

import java.util.List;

import cn.bmob.v3.listener.SaveListener;

/**
 * @author liyunkun
 *         用户注册界面
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener, IRegisterView {

    private TextView mCountryTv;//国家
    private EditText mNickName;//用户昵称
    private ImageView mUserImg;//用户头像
    private EditText mPhoneNumber;//电话号码
    private EditText mPassword;//密码编写框
    private Button mRegister;//注册按钮
    private ImageView mSeePassword;//是否可见密码
    private ImageView mDeleteTv;//电话号码中的删除按钮
    private boolean isSeePassword = false;//密码是否可见
    private RegisterPresenter presenter = new RegisterPresenter(this);
    TextView area_code;
    private UserBean userBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setRegisterButtonEnabled();
        initSeePassword();
    }

    //设置注册按钮是否可以点击
    private void setRegisterButtonEnabled() {
        if (isEmpty()) {
            unClickable();
        } else {
            clickable();
        }
    }

    //初始化属性和查找控件
    private void initView() {
	area_code = ((TextView) findViewById(R.id.area_Code));
        ImageView goBack = (ImageView) findViewById(R.id.go_back);
        RelativeLayout country = (RelativeLayout) findViewById(R.id.country);
        mCountryTv = ((TextView) findViewById(R.id.country_tv));
        mNickName = ((EditText) findViewById(R.id.nick_name));
        mNickName.setSingleLine(true);
        mUserImg = ((ImageView) findViewById(R.id.user_img));
        mPhoneNumber = ((EditText) findViewById(R.id.phone_number));
        mPassword = ((EditText) findViewById(R.id.password));
        mPassword.setSingleLine(true);
        mRegister = ((Button) findViewById(R.id.register));
        mSeePassword = ((ImageView) findViewById(R.id.see_password));
        area_code = ((TextView) findViewById(R.id.area_Code));
        mDeleteTv = ((ImageView) findViewById(R.id.delete_tv));
        mSeePassword.setOnClickListener(this);
        mRegister.setOnClickListener(this);
        country.setOnClickListener(this);
        goBack.setOnClickListener(this);
        mDeleteTv.setOnClickListener(this);

        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setRegisterButtonEnabled();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mNickName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String result = s.toString();
                if (result != null) {
                    if (result.length() > 10) {
                        Toast.makeText(RegisterActivity.this, "长度在0至10个字符之间", Toast.LENGTH_SHORT).show();
                    }
                }
                setRegisterButtonEnabled();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String result = s.toString();
                if (result != null && !"".equals(result)) {
                    mDeleteTv.setVisibility(View.VISIBLE);
                } else {
                    mDeleteTv.setVisibility(View.GONE);
                }
                setRegisterButtonEnabled();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_back:
                finish();
                break;
            case R.id.country:
                startActivityForResult(new Intent(this,AreaActivity.class),0);
                break;
            case R.id.register:
                if (isRightPhoneNumber()) {
                    presenter.start(getPhoneNumber());
                } else {
                    Toast.makeText(RegisterActivity.this, "手机号码错误", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.see_password:
                if (isSeePassword) {
                    isSeePassword = false;
                    initSeePassword();
                } else {
                    isSeePassword = true;
                    initSeePassword();
                }
                break;
            case R.id.delete_tv:
                mPhoneNumber.setText("");
                break;
        }
    }

    //设置密码是否可见的图片资源
    private void initSeePassword() {
        if (isSeePassword) {
            mSeePassword.setImageResource(R.drawable.eye_opened);
            mPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            mSeePassword.setImageResource(R.drawable.eye_closed);
            mPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);;
        }
    }

    //验证是否为正确的电话号码格式
    private boolean isRightPhoneNumber() {
        String phoneNumber = getPhoneNumber();
        if (phoneNumber.matches("[1][358][0-9]{9}")) {
            return true;
        }
        return false;
    }

    //注册按钮可点击
    private void clickable() {
        mRegister.setBackgroundColor(getResources().getColor(R.color.green));
        mRegister.setEnabled(true);
    }

    //注册按钮不可点击
    private void unClickable() {
        mRegister.setBackgroundColor(getResources().getColor(R.color.green_dark));
        mRegister.setEnabled(false);
    }

    //验证用户是否输入信息
    private boolean isEmpty() {
        String nickName = getNickName();
        String phoneNumber = getPhoneNumber();
        String password = getPassword();
        if (nickName != null && !"".equals(nickName)
                && phoneNumber != null && !"".equals(phoneNumber)
                && password != null && !"".equals(password)) {
            return false;
        }
        return true;
    }

    @Override
    public void getData(List<UserBean> list) {
        if (list != null && list.size() > 0) {
            Toast.makeText(RegisterActivity.this, "该手机号已注册，请登录", Toast.LENGTH_SHORT).show();
        } else {
            saveUserBean();
        }
    }

    private void saveUserBean() {
        userBean = new UserBean();
        userBean.setNickName(getNickName());
        userBean.setPassword(getPassword());
        userBean.setCountry(getCountry());
        userBean.setPhoneCode(getAreaCode());
        userBean.setPhoneNumber(getPhoneNumber());
        userBean.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getAreaCode(){
        String result = area_code.getText().toString();
        return result.substring(1);
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        saveUserBean();
    }

    //获取用户输入的电话号码
    @NonNull
    private String getPhoneNumber() {
        return mPhoneNumber.getText().toString();
    }

    //获取用户选择的国家
    @NonNull
    private String getCountry() {
        return mCountryTv.getText().toString();
    }

    //获取用户输入的密码
    @NonNull
    private String getPassword() {
        return mPassword.getText().toString();
    }

    //获取用户输入的昵称
    @NonNull
    private String getNickName() {
        return mNickName.getText().toString();
    }
	//回调方法，从第二个页面回来的时候会执行这个方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0&&data!=null){
            String countryName = data.getStringExtra("CountryName");
            String areaCode = data.getStringExtra("AreaCode");
            mCountryTv.setText(countryName);
            area_code.setText("+"+areaCode);
        }
    }
}
