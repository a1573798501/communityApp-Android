package com.zzj.community.http;

import android.util.Log;

import com.zzj.community.DTO.QuestionDto;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ProjectName: community
 * @Package: com.zzj.community.http
 * @ClassName: HttpUtils
 * @Description: 网络请求工具类
 * @Author: Zhijun Zhang
 * @CreateDate: 2020/3/5 15:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/5 15:55
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class HttpUtils {

    private static OkHttpClient okHttpClient;

    /*
    *生产环境
    */
    private static final String BASE_URL = "https://api.apiopen.top";

    /*
     *测试环境
     */
    private static final String TEST_URL = "https://api.apiopen.top";

    private static final String LOCAL_URL = "http://192.168.199.107";

    private static String  isTest(String urlType){
        switch (urlType){
            case "BASE_URL":
                return BASE_URL;

            case "LOCAL_URL":
                return LOCAL_URL;

            case "TEST_URL":
                return TEST_URL;

            default:
                break;

        };
        return null;
    }


    public static<T> T createApi(Class<T> tClass){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(isTest("LOCAL_URL"))
                .client(getClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

                .build();

        return retrofit.create(tClass);
    }

    private static OkHttpClient getClient(){
        if (okHttpClient == null){
            okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(20, TimeUnit.SECONDS)
                    .connectTimeout(20,TimeUnit.SECONDS)
                    .addInterceptor(getInterceptor())
                    .build();
        }
        return okHttpClient;
    }


    /**
     * 拦截器
     * @return
     */
    private static Interceptor getInterceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NotNull String message) {
                Log.e("http:=====>",message);
            }
        });
        return httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    public static<T> void sendHttp(Observable<T> observable, final ResponseListener<T> responseListener){
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(T t) {
                        if (responseListener!=null){
                            responseListener.onSuccess(t);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (responseListener!=null){
                            responseListener.onFail(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static void sendUploadFileHttp(Observable<ResponseBody> observable, ResponseListener<ResponseBody> responseListener) {

        observable.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                            if (responseListener!=null){
                                responseListener.onSuccess(responseBody);
                            }

                    }

                    @Override
                    public void onError(Throwable e) {

                        if (responseListener!=null){
                            responseListener.onFail(e.getMessage());
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static void getQuestion(Observable<ResponseBody> observable, ResponseListener<ResponseBody> responseListener) {

        observable.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                        if (responseListener!=null){
                            responseListener.onSuccess(responseBody);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                        if (responseListener!=null){
                            responseListener.onFail(e.getMessage());
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
