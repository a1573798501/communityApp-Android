package com.zzj.community.ui.user;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zzj.community.MainActivity;
import com.zzj.community.R;
import com.zzj.community.base.BaseActivity;
import com.zzj.community.main.persenter.MainPersenter;
import com.zzj.community.main.view.MainView;
import com.zzj.community.ui.user.persenter.RegisterPersenter;
import com.zzj.community.ui.user.view.RegisterView;
import com.zzj.community.util.MediaFileUtil;

import com.zzj.community.util.MediaFileUtil.*;

import java.io.IOException;

public class RegisterActivity extends BaseActivity<RegisterView, RegisterPersenter>
        implements TextWatcher, View.OnClickListener, RegisterView {

    private ImageView registerAvatar;

    private EditText registerUserName;

    private EditText registerUserEmail;

    private EditText registerUserPassword;

    private Button registerUserButton;

    private TextView registerUserLoginText;

    private String userName;

    private String userEmail;

    private String userPassword;

    private Integer passwordLength = 6;



    @Override
    public void initView() {
        registerAvatar = findViewById(R.id.register_avatar);
        registerUserName = findViewById(R.id.register_user_name);
        registerUserEmail = findViewById(R.id.register_user_email);
        registerUserPassword = findViewById(R.id.register_user_password);
        registerUserButton = findViewById(R.id.register_user_button);
        registerUserLoginText = findViewById(R.id.register_user_login_text);
        registerUserName.addTextChangedListener(this);
        registerUserEmail.addTextChangedListener(this);
        registerUserPassword.addTextChangedListener(this);
        registerAvatar.setOnClickListener(this);
        registerUserButton.setOnClickListener(this);
        initButton(registerUserButton,R.drawable.bg_register_button_not_enable);
        registerUserLoginText.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }


    @Override
    public void initData() {

    }

    @Override
    protected RegisterPersenter createPersenter() {
        return new RegisterPersenter();
    }

    private void selectImage(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI,"image/*");
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100){
            if (resultCode == RESULT_OK){
                Uri imageUri = data.getData();
                registerAvatar.setImageURI(imageUri);
                getMyBasePresenter().upLoadFile(MediaFileUtil.getPath(this,imageUri));
            }
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
        userName = getEditText(registerUserName);
        userEmail = getEditText(registerUserEmail);
        userPassword = getEditText(registerUserPassword);
        if (userName.length() > 0 && userEmail.length() > 0 && userPassword.length() >= passwordLength) {
            registerUserButton.setEnabled(true);
            registerUserButton.setBackgroundResource(R.drawable.bg_register_button);
        } else {
            registerUserButton.setEnabled(false);
            registerUserButton.setBackgroundResource(R.drawable.bg_register_button_not_enable);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_user_button:
                getMyBasePresenter().sendRegister(userName,userEmail,userPassword);
                break;
            case R.id.register_user_login_text:
                startIntent(LoginActivity.class);
                break;
            case R.id.register_avatar:
                selectImage();
                break;
            default:
                break;
        }
    }

    @Override
    public void setData(Object data) {
            Log.e("success",""+data);
    }

    @Override
    public void error(Object data) {
        Log.e("error",""+data);
    }
}
