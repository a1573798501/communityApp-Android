package com.zzj.community.fragment;

import android.app.Activity;
import android.content.Context;
import android.util.Config;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zzj.community.DTO.AllQuestionDto;
import com.zzj.community.DTO.QuestionDto;
import com.zzj.community.application.GetAllQuestionDtoApplication;
import com.zzj.community.http.ApiService;
import com.zzj.community.http.HttpUrlGet;
import com.zzj.community.http.HttpUtils;
import com.zzj.community.http.ResponseListener;
import com.zzj.community.model.Question;


import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ProjectName: community
 * @Package: com.zzj.community.fragment
 * @ClassName: QuestionPersenter
 * @Description: java类作用描述
 * @Author: Zhijun Zhang
 * @CreateDate: 2020/3/9 22:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/9 22:55
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class QuestionPersenter {

    public List<QuestionDto> questionDtos;

    public AllQuestionDto allQuestionDtoReturn = new AllQuestionDto();

    public Object getAllQues(){
//        getMyBaseView().setData(userName + "<---->" + userEmail +  "<---->" + userPassword);



        HttpUtils.getQuestion(HttpUtils.createApi(ApiService.class).getAllQuestion(), new ResponseListener<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                try {

                    String responseBodyString = responseBody.byteString().toString();

                    String[] splitTmp = responseBodyString.split("=");
                    JSONObject jsonObject = JSON.parseObject(splitTmp[2]);
                    Log.e("Result =====>", jsonObject.toString());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String data) {
                Log.e("The error is =========>",data);
            }
        });

        return questionDtos;

    }

    public  void getQuestionJson(Activity activity){



        HttpUrlGet httpUrlGet = new HttpUrlGet();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpUrlGet.getLOCAL_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService api = retrofit.create(ApiService.class);
        Call<AllQuestionDto> call = api.getAllQuestionJson();
        call.enqueue(new Callback<AllQuestionDto>() {
            @Override
            public void onResponse(Call<AllQuestionDto> call, Response<AllQuestionDto> response) {

                AllQuestionDto allQuestionDto = response.body();
                allQuestionDtoReturn = allQuestionDto;
                Log.e("App =========>","" + ((GetAllQuestionDtoApplication)activity.getApplication()).isFlag());
                ((GetAllQuestionDtoApplication)activity.getApplication()).setAllQuestionDtoReturn(allQuestionDtoReturn);
                ((GetAllQuestionDtoApplication)activity.getApplication()).setFlag(false);
                System.out.println("OK!!");

            }

            @Override
            public void onFailure(Call<AllQuestionDto> call, Throwable t) {
                System.out.println("TAG" + "onFailure: " + t.toString() );
                // Log error here since request failed
            }

        });


    }


}
