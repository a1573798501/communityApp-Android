package com.zzj.community.ui.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zzj.community.R;
import com.zzj.community.application.GetAllQuestionDtoApplication;
import com.zzj.community.base.BaseActivity;
import com.zzj.community.main.persenter.MainPersenter;
import com.zzj.community.main.view.MainView;
import com.zzj.community.model.User;
import com.zzj.community.model.UserMobile;
import com.zzj.community.ui.user.persenter.LoginPersenter;
import com.zzj.community.ui.user.view.LoginView;

public class LoginActivity extends BaseActivity<LoginView, LoginPersenter>
        implements View.OnClickListener, TextWatcher, LoginView {


    private EditText loginUserName;

    private EditText loginUserPassword;

    private Button loginUserButton;

    private TextView loginUserRegisterText;

    private String userName;

    private String userPassword;

    private Integer passwordLength = 6;

    @Override
    public void initView() {

        loginUserName = findViewById(R.id.login_user_name);
        loginUserPassword = findViewById(R.id.login_user_password);
        loginUserButton = findViewById(R.id.login_user_button);
        loginUserRegisterText = findViewById(R.id.login_user_register_text);
        initButton(loginUserButton,R.drawable.bg_register_button_not_enable);
        loginUserName.addTextChangedListener(this);
        loginUserPassword.addTextChangedListener(this);
        loginUserButton.setOnClickListener(this);
        loginUserRegisterText.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {

    }

    @Override
    protected LoginPersenter createPersenter() {
        return new LoginPersenter();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_user_button:
                getMyBasePresenter().sendLogin(userName,userPassword);
                break;
            case R.id.login_user_register_text:
                startIntent(RegisterActivity.class);
                break;
            default:
                break;
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        userName = getEditText(loginUserName);
        userPassword = getEditText(loginUserPassword);
        if (userName.length() > 0 && userPassword.length() >= passwordLength) {
            loginUserButton.setEnabled(true);
            loginUserButton.setBackgroundResource(R.drawable.bg_register_button);
        } else {
            loginUserButton.setEnabled(false);
            loginUserButton.setBackgroundResource(R.drawable.bg_register_button_not_enable);
        }
    }


    @Override
    public void setData(Object data) {

    }

    @Override
    public void error(Object data) {

    }

    @Override
    public void showLogin(User user) {

        ((GetAllQuestionDtoApplication)this.getApplication()).setLoginFlag(true);
        ((GetAllQuestionDtoApplication)this.getApplication()).setUser(user);
        Log.e("Get User name",user.getName());
        showToast("成功登录");
    }
}
