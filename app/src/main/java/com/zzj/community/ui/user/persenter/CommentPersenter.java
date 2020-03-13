package com.zzj.community.ui.user.persenter;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.zzj.community.DTO.CommentDto;
import com.zzj.community.DTO.CommentMobileDto;
import com.zzj.community.DTO.QuestionDto;
import com.zzj.community.http.ApiService;
import com.zzj.community.http.HttpUrlGet;
import com.zzj.community.model.Comment;
import com.zzj.community.mvp.BasePersenter;
import com.zzj.community.ui.user.CommentActivity;
import com.zzj.community.ui.user.model.CommentModel;
import com.zzj.community.ui.user.mvp.Persenter;
import com.zzj.community.ui.user.view.CommentView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ProjectName: community
 * @Package: com.zzj.community.ui.user.persenter
 * @ClassName: CommentPersenter
 * @Description: java类作用描述
 * @Author: Zhijun Zhang
 * @CreateDate: 2020/3/11 14:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/11 14:55
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CommentPersenter implements Persenter<Long,QuestionDto> {


    private CommentModel commentModel;

    private CommentView  commentView;

    public void showToast(String message, Activity activity) {
        Toast.makeText(activity,message,Toast.LENGTH_SHORT).show();
    }

    public String getEditText(EditText editText){
        return editText.getText().toString().trim();
    }

    public CommentPersenter(CommentView commentView){
        this.commentView = commentView;
        commentModel = new CommentModel(this);
    }


    public void setQuestionComment(List<CommentDto> questionCommentDtos){
        commentView.setQuestionComment(questionCommentDtos);
    }

    public void setComment(List<CommentDto> commentDtos){
        commentView.setComment(commentDtos);
    }


    @Override
    public void action(Long userId) {
        commentModel.request(userId);
    }


    public void setWhichComment(Long id , int type){

        commentModel.getComment(id,type);

    }

    public void sendComment(CommentMobileDto commentMobileDto){

        commentModel.upLoadComment(commentMobileDto);
        commentView.updateComment();

    }


    @Override
    public void progress(QuestionDto questionDto) {
        commentView.progress(questionDto);
    }
}
