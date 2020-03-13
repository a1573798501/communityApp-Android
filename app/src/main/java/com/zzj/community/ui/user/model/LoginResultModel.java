package com.zzj.community.ui.user.model;

import com.google.gson.Gson;
import com.zzj.community.DTO.ResultDto;
import com.zzj.community.http.ApiService;
import com.zzj.community.http.HttpUrlGet;
import com.zzj.community.model.User;
import com.zzj.community.model.UserMobile;
import com.zzj.community.ui.user.mvp.Model;
import com.zzj.community.ui.user.persenter.CommentPersenter;
import com.zzj.community.ui.user.persenter.LoginResultPersenter;

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
 * @ClassName: LoginResultModel
 * @Description: java类作用描述
 * @Author: Zhijun Zhang
 * @CreateDate: 2020/3/13 19:54
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/13 19:54
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LoginResultModel implements Model<UserMobile,Object> {



    private LoginResultPersenter loginResultPersenter;

    ResultDto<User> resultDto = new ResultDto<User>();

    public LoginResultModel(LoginResultPersenter loginResultPersenter){
        this.loginResultPersenter = loginResultPersenter;
    }

    @Override
    public Object request(UserMobile userMobile) {
        isLogin(userMobile);
        return null;
    }


    public void isLogin(UserMobile userMobile){
        HttpUrlGet httpUrlGet = new HttpUrlGet();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(httpUrlGet.getLOCAL_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService api = retrofit.create(ApiService.class);
        Gson gson = new Gson();
        String json = gson.toJson(userMobile);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),json);
        Call<ResultDto<User>> call = api.isLogin(requestBody);
        call.enqueue(new Callback<ResultDto<User>>() {
            @Override
            public void onResponse(Call<ResultDto<User>> call, Response<ResultDto<User>> response) {
                resultDto = response.body();
                if (resultDto.getCode() == 3){
                    loginResultPersenter.progress(resultDto);
                }


            }

            @Override
            public void onFailure(Call<ResultDto<User>> call, Throwable t) {
                System.out.println("TAG" + "onFailure: " + t.toString() );
                // Log error here since request failed
            }

        });
    }
}
