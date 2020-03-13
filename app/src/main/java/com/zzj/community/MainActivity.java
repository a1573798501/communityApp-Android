package com.zzj.community;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.zzj.community.base.BaseActivity;
import com.zzj.community.main.persenter.MainPersenter;
import com.zzj.community.main.view.MainView;


public class MainActivity extends BaseActivity<MainView, MainPersenter>
             implements MainView {


    @Override
    protected void initView() {
        findViewById(R.id.main_text_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMyBasePresenter().getTest();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {

    }

    @Override
    protected MainPersenter createPersenter() {
        return new MainPersenter();
    }


    @Override
    public void setData(Object data) {
        showToast(data.toString());
    }

    @Override
    public void error(Object data) {

    }
}
