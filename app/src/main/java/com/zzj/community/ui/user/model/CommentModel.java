package com.zzj.community.ui.user.model;

import android.util.Log;

import com.google.gson.Gson;
import com.zzj.community.DTO.CommentDto;
import com.zzj.community.DTO.CommentMobileDto;
import com.zzj.community.DTO.QuestionDto;
import com.zzj.community.DTO.ResultDto;
import com.zzj.community.http.ApiService;
import com.zzj.community.http.HttpUrlGet;
import com.zzj.community.mvp.BaseModel;
import com.zzj.community.ui.user.mvp.Model;
import com.zzj.community.ui.user.mvp.Persenter;
import com.zzj.community.ui.user.persenter.CommentPersenter;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ProjectName: community
 * @Package: com.zzj.community.ui.user.model
 * @ClassName: CommentModel
 * @Description: java类作用描述
 * @Author: Zhijun Zhang
 * @CreateDate: 2020/3/11 14:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/11 14:55
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CommentModel implements Model<Long, Object> {


    private CommentPersenter persenter;
    private QuestionDto questionDto;
    private boolean flag = true;
    private List<CommentDto> commentDtos;

    public CommentModel(CommentPersenter commentPersenter){
        this.persenter = commentPersenter;
    }


    public void getQuestionById(long id){
        HttpUrlGet httpUrlGet = new HttpUrlGet();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpUrlGet.getLOCAL_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService api = retrofit.create(ApiService.class);
        Call<QuestionDto> call = api.getQuestionById(id);
        call.enqueue(new Callback<QuestionDto>() {
            @Override
            public void onResponse(Call<QuestionDto> call, Response<QuestionDto> response) {
                questionDto = response.body();
                persenter.progress(questionDto);
            }

            @Override
            public void onFailure(Call<QuestionDto> call, Throwable t) {
                System.out.println("TAG" + "onFailure: " + t.toString() );
                // Log error here since request failed
            }

        });
    }

    public void upLoadComment(CommentMobileDto commentMobileDto){
        HttpUrlGet httpUrlGet = new HttpUrlGet();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpUrlGet.getLOCAL_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService api = retrofit.create(ApiService.class);
        Gson gson = new Gson();
        String json = gson.toJson(commentMobileDto);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),json);
        Call<ResultDto> call = api.upLoadComment(requestBody);
        call.enqueue(new Callback<ResultDto>() {
            @Override
            public void onResponse(Call<ResultDto> call, Response<ResultDto> response) {


            }

            @Override
            public void onFailure(Call<ResultDto> call, Throwable t) {
                System.out.println("TAG" + "onFailure: " + t.toString() );
                // Log error here since request failed
            }

        });
    }


    public void getQuestionComment(long id){
        HttpUrlGet httpUrlGet = new HttpUrlGet();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpUrlGet.getLOCAL_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService api = retrofit.create(ApiService.class);
        Call<List<CommentDto>> call = api.getAllQuestionCommentById(id);
        call.enqueue(new Callback<List<CommentDto>>() {
            @Override
            public void onResponse(Call<List<CommentDto>> call, Response<List<CommentDto>> response) {
                commentDtos = response.body();
                persenter.setQuestionComment(commentDtos);

            }

            @Override
            public void onFailure(Call<List<CommentDto>> call, Throwable t) {
                System.out.println("TAG" + "onFailure: " + t.toString() );
                // Log error here since request failed
            }

        });
    }

    public void getComment(long id){
        HttpUrlGet httpUrlGet = new HttpUrlGet();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpUrlGet.getLOCAL_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService api = retrofit.create(ApiService.class);
        Call<List<CommentDto>> call = api.getAllCommentById(id);
        call.enqueue(new Callback<List<CommentDto>>() {
            @Override
            public void onResponse(Call<List<CommentDto>> call, Response<List<CommentDto>> response) {
                commentDtos = response.body();
                persenter.setComment(commentDtos);

            }

            @Override
            public void onFailure(Call<List<CommentDto>> call, Throwable t) {
                System.out.println("TAG" + "onFailure: " + t.toString() );
                // Log error here since request failed
            }

        });
    }


    @Override
    public Object request(Long aLong) {
        getQuestionById(aLong);
        return null;
    }

    public void getComment(Long id, int type){

        if (type == 1){
            getQuestionComment(id);
        }else {
            getComment(id);
        }

    }

}
