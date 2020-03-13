package com.zzj.community.ui.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zzj.community.DTO.ResultDto;
import com.zzj.community.R;
import com.zzj.community.application.GetAllQuestionDtoApplication;
import com.zzj.community.model.User;
import com.zzj.community.model.UserMobile;
import com.zzj.community.ui.user.persenter.LoginResultPersenter;
import com.zzj.community.ui.user.view.LoginResultView;

public class LoginResultActivity extends AppCompatActivity
                                 implements LoginResultView, View.OnClickListener, TextWatcher {


    private EditText loginUserName;

    private EditText loginUserPassword;

    private Button loginUserButton;



    private String userName;

    private String userPassword;

    private Integer passwordLength = 6;

    private LoginResultPersenter loginResultPersenter;

    private ResultDto<User> userResultDto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_result);

        loginResultPersenter = new LoginResultPersenter(this);

        initView();


    }



    public void initView() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            View view = window.getDecorView();
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        loginUserName = findViewById(R.id.login_user_name);
        loginUserPassword = findViewById(R.id.login_user_password);
        loginUserButton = findViewById(R.id.login_user_button);

        initButton(loginUserButton,R.drawable.bg_register_button_not_enable);
        loginUserName.addTextChangedListener(this);
        loginUserPassword.addTextChangedListener(this);
        loginUserButton.setOnClickListener(this);
    }


    @Override
    public void progress(ResultDto<User> userResultDto) {
        if (userResultDto.getCode() == 3){
            Intent intent=new Intent();
            intent.putExtra("userId", userResultDto.getData().getAccountId()+"登录成功！");
            this.setResult(3, intent);
            ((GetAllQuestionDtoApplication)this.getApplication()).setLoginFlag(true);
            ((GetAllQuestionDtoApplication)this.getApplication()).setUser(userResultDto.getData());
            this.finish();
        }else if (userResultDto.getCode() == 2){
            Intent intent=new Intent();
            intent.putExtra("userId", "密码错误！");
            this.setResult(2, intent);
            this.finish();
        } else if (userResultDto.getCode() == 1){
            Intent intent=new Intent();
            intent.putExtra("userId", "并未查询到该用户！");
            this.setResult(1, intent);
            this.finish();
        }
    }


    protected void initButton(Button button, int buttonBackground) {

        button.setEnabled(false);
        button.setBackgroundResource(buttonBackground);

    }

    protected String getEditText(EditText editText){
        return editText.getText().toString().trim();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_user_button:
                UserMobile userMobile = new UserMobile();
                userMobile.setPassword(userPassword);
                userMobile.setName(userName);
                userMobile.setAccountId(userName);
                loginResultPersenter.action(userMobile);
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
}
