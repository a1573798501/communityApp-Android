package com.zzj.community.ui.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.zzj.community.R;
import com.zzj.community.fragment.QuestionFragment;

public class UserMainViewActivity extends AppCompatActivity implements View.OnClickListener {


    private QuestionFragment questionFragment;

    private FragmentManager fManager;


    //UI Object
    private TextView txt_topbar;
    private TextView txt_channel;
    private TextView txt_message;
    private TextView txt_setting;
    private FrameLayout ly_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_view);


        fManager = getSupportFragmentManager();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            View view = window.getDecorView();
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        bindViews();



    }


    //Fragment
    private void bindViews() {
        txt_topbar = (TextView)findViewById(R.id.txt_topbar);
        txt_channel = (TextView)findViewById(R.id.txt_channel);
        txt_message = (TextView)findViewById(R.id.txt_message);
        txt_setting = (TextView)findViewById(R.id.txt_setting);
        ly_content = (FrameLayout) findViewById(R.id.ly_content);

        txt_channel.setOnClickListener(this);
        txt_message.setOnClickListener(this);
        txt_setting.setOnClickListener(this);
    }

    private void setSelected(){
        txt_channel.setSelected(false);
        txt_message.setSelected(false);
        txt_setting.setSelected(false);
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(questionFragment != null)fragmentTransaction.hide(questionFragment);
    }


    @Override
    public void onClick(View view) {

        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (view.getId()){
            case R.id.txt_channel:
                setSelected();
                txt_topbar.setText(R.string.tab_menu_normal);
                txt_channel.setSelected(true);
                if(questionFragment == null){
                    questionFragment = new QuestionFragment();
                    fTransaction.add(R.id.ly_content,questionFragment);
                }else{
                    fTransaction.show(questionFragment);
                }break;
            case R.id.txt_message:
                break;
            case R.id.txt_setting:
                break;
        }
        fTransaction.commit();

    }
}
