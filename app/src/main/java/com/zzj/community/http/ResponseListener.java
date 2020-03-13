package com.zzj.community.http;

/**
 * @ProjectName: community
 * @Package: com.zzj.community.http
 * @ClassName: ResponseListener
 * @Description: java类作用描述
 * @Author: Zhijun Zhang
 * @CreateDate: 2020/3/5 16:20
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/5 16:20
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface ResponseListener<T> {

    void onSuccess(T data);

    void onFail(String data);

}
