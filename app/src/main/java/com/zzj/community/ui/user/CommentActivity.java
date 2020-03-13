package com.zzj.community.ui.user;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.zzj.community.DTO.CommentCreateDto;
import com.zzj.community.DTO.CommentDto;
import com.zzj.community.DTO.CommentMobileDto;
import com.zzj.community.DTO.QuestionDto;
import com.zzj.community.R;
import com.zzj.community.adapter.CommentAdapter;
import com.zzj.community.application.GetAllQuestionDtoApplication;
import com.zzj.community.base.BaseActivity;
import com.zzj.community.model.Comment;
import com.zzj.community.ui.user.mvp.Persenter;
import com.zzj.community.ui.user.persenter.CommentPersenter;
import com.zzj.community.ui.user.persenter.RegisterPersenter;
import com.zzj.community.ui.user.view.CommentView;
import com.zzj.community.ui.user.view.RegisterView;
import com.zzj.community.util.DateUtil;
import com.zzj.community.view.MyImageView;
import com.zzj.community.view.MyScrollView;
import com.zzj.community.view.NineGridTestLayout;
import com.zzj.community.view.UnScrollListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity
                            implements CommentView<List<CommentDto>, List<CommentDto>>, TextWatcher, View.OnClickListener{


    private List<CommentDto> mComment;
    private UnScrollListView lv_comment;
    private MyScrollView scroll;
    private EditText et_comment;
    private TextView tv_submit;
    QuestionDto questionDto;
    CommentCreateDto commentCreateDto = new CommentCreateDto();
    private CommentAdapter adapter = new CommentAdapter();

    private MyImageView img_avatar;
    private TextView tv_name,tv_content,tv_time;
    private NineGridTestLayout mImageLayout;

    private CommentPersenter commentPersenter;

    private DateUtil dateUtil = new DateUtil();


    private Activity activity;

    private String commentContent;

    private long questionId ;

    private int mCurrentDialogStyle = R.style.QMUI_Dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Intent intent = getIntent();
        questionId = intent.getLongExtra("questionId",23);

        initView();

    }


    protected void initView() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            View view = window.getDecorView();
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        activity = this;

        commentPersenter = new CommentPersenter(this);

        commentPersenter.action(questionId);

        commentPersenter.setWhichComment(questionId,1);

        img_avatar = findViewById(R.id.mom_img_avatar);
        tv_name = findViewById(R.id.mom_tv_name);
        tv_content = findViewById(R.id.mom_tv_content);
        mImageLayout = findViewById(R.id.mom_img_image);
        tv_time = findViewById(R.id.mom_tv_time);
        lv_comment = findViewById(R.id.lv_comment);

        et_comment = findViewById(R.id.et_comment);
        tv_submit = findViewById(R.id.btn_submit);

        et_comment.addTextChangedListener(this);

        tv_submit.setOnClickListener(this);

        tv_submit.setEnabled(false);
        tv_submit.setBackgroundResource(R.drawable.bg_comment_button_not_enable);
    }


    public void initData() {

        img_avatar.setImageURL(questionDto.getUser().getAvatarUrl());
        tv_name.setText(questionDto.getUser().getName());
        tv_time.setText(dateUtil.formatDateTime(questionDto.getGmtCreate()));
        tv_content.setText(questionDto.getDescription());


    }

    public void initCommentData(){

        adapter.setDatas(mComment);
        adapter.setInflater(CommentActivity.this);
        lv_comment.setAdapter(adapter);

    }





    @Override
    public void progress(QuestionDto questionDto) {

        this.questionDto = questionDto;
        Log.e("get question ", "not in thread" + this.questionDto.getDescription());
        initData();

    }


    @Override
    public void setQuestionComment(List<CommentDto> questionCommentDtos) {
        this.mComment = questionCommentDtos;
        if (mComment.size()>0) {
            Log.e("get question ", "not in thread" + this.mComment.get(0).getContent());
            initCommentData();
        }
    }

    @Override
    public void setComment(List<CommentDto> commentDtos) {

    }

    @Override
    public void updateComment() {

        commentPersenter.setWhichComment(questionId,1);


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        commentContent = commentPersenter.getEditText(et_comment);
        if (commentContent.length() > 0 ) {
            tv_submit.setEnabled(true);
            tv_submit.setBackgroundResource(R.drawable.bg_comment_button_enable);
        } else {
            tv_submit.setEnabled(false);
            tv_submit.setBackgroundResource(R.drawable.bg_comment_button_not_enable);
        }
    }

    @Override
    public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_submit:
                    if (((GetAllQuestionDtoApplication)this.getApplication()).isLoginFlag){
                        CommentMobileDto commentMobileDto = new CommentMobileDto();
                        commentMobileDto.setUser(((GetAllQuestionDtoApplication)this.getApplication()).getUser());
                        commentMobileDto.setContent(commentContent);
                        commentMobileDto.setType(1);
                        commentMobileDto.setParentId(questionId);
                        commentPersenter.sendComment(commentMobileDto);
                    }else {
                        showMessageNegativeDialog();
                    }

                    break;
                default:
                    break;
            }

    }

    private void showMessageNegativeDialog() {
        new QMUIDialog.MessageDialogBuilder(this)
                .setTitle("您还没有登录，无法发送评论")
                .setMessage("确定登录？")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction(0, "登录", QMUIDialogAction.ACTION_PROP_NEGATIVE, new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        commentPersenter.showToast("跳转至登录",activity);
                        Intent intent=new Intent(activity,LoginResultActivity.class);
                        //参数：1.intent对象  2.请求编码(标记)可以是正整数值
                        startActivityForResult(intent, 1);
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1:
                if(resultCode==1){
                    String result =data.getStringExtra("userId");
                    Log.e("Login result",result);
                }
                if(resultCode==2){
                    String result =data.getStringExtra("userId");
                    Log.e("Login result",result);
                }
                if(resultCode==2){
                    String result =data.getStringExtra("userId");
                    Log.e("Login result",result);
                }
                break;

            default:
                break;
        }
    }

    protected void showToast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
