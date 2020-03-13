package com.zzj.community.mvp;

/**
 * @ProjectName: community
 * @Package: com.zzj.community.mvp
 * @ClassName: BaseView
 * @Description: java类作用描述
 * @Author: Zhijun Zhang
 * @CreateDate: 2020/3/5 13:10
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/5 13:10
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface BaseView<T> {

    void setData(T data);

    void error(T data);
}
