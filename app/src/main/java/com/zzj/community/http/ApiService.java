package com.zzj.community.http;

import com.zzj.community.DTO.AllQuestionDto;
import com.zzj.community.DTO.CommentCreateDto;
import com.zzj.community.DTO.CommentDto;
import com.zzj.community.DTO.QuestionDto;
import com.zzj.community.DTO.ResultDto;
import com.zzj.community.model.User;
import com.zzj.community.model.UserMobile;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


/**
 * @ProjectName: community
 * @Package: com.zzj.community.http
 * @ClassName: ApiService
 * @Description: java类作用描述
 * @Author: Zhijun Zhang
 * @CreateDate: 2020/3/5 16:24
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/5 16:24
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface ApiService {

    @GET("/getTangPoetry")
    Observable<String> getRegister(@Query("page")int page, @Query("page")int size);

    @GET("/")
    Observable<String> getIndex(@Query("page")int page, @Query("page")int size);

    @GET("/comment/23")
    Observable<String> getComment();

    @Multipart
    @POST("/file/fileUpload")
    Observable<ResponseBody> uploadFile(
            @Part() MultipartBody.Part file);

    @Multipart
    @POST("/file/avatarUpload")
    Observable<ResponseBody> uploadAvatar(
            @Part() MultipartBody.Part file,
            @Query("avatarName")String avatarName);


    @POST("/allQuestion")
    Observable<ResponseBody> getAllQuestion();

    @POST("/allQuestion")
    Call<AllQuestionDto> getAllQuestionJson();

    @POST("/getQuestionById")
    Call<QuestionDto> getQuestionById(@Query("id") long id);


    @POST("/getAllCommentById")
    Call<List<CommentDto>> getAllCommentById(@Query("id") long id);


    @POST("/getAllQuestionCommentById")
    Call<List<CommentDto>> getAllQuestionCommentById(@Query("id") long id);

    @POST("/userMobileRegister")
    Call<ResultDto> register(@Body RequestBody userMobile);

    @POST("/isLogin")
    Call<ResultDto<User>> isLogin(@Body RequestBody userMobile);

    @POST("/upLoadComment")
    Call<ResultDto> upLoadComment(@Body RequestBody commentBody);

}
