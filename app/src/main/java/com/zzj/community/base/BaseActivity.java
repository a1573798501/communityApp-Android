package com.zzj.community.base;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zzj.community.R;
import com.zzj.community.mvp.BasePersenter;
import com.zzj.community.mvp.BaseView;

public abstract class BaseActivity<baseView extends BaseView, basePresenter extends BasePersenter> extends AppCompatActivity
                      implements BaseView{


    private basePresenter myBasePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            View view = window.getDecorView();
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        if (myBasePresenter == null){
            myBasePresenter = createPersenter();
            myBasePresenter.bindView(this);
        }

        setContentView(getLayoutId());

        initView();
        initData();

    }

    protected abstract void initView();

    public abstract int getLayoutId();

    public abstract void initData();



    protected abstract basePresenter createPersenter();

    public basePresenter getMyBasePresenter(){
        return myBasePresenter;
    }

    protected void initButton(Button button, int buttonBackground) {

        button.setEnabled(false);
        button.setBackgroundResource(buttonBackground);

    }

    protected String getEditText(EditText editText){
        return editText.getText().toString().trim();
    }

    protected void startIntent(Class<?> viewClass){
        Intent intent = new Intent(this,viewClass);
        startActivity(intent);
    }

    protected void showToast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
