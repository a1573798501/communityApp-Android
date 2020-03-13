package com.zzj.community.ui.user.mvp;

/**
 * @ProjectName: community
 * @Package: com.zzj.community.ui.user.mvp
 * @ClassName: Persenter
 * @Description: java类作用描述
 * @Author: Zhijun Zhang
 * @CreateDate: 2020/3/11 17:11
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/11 17:11
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface Persenter<T,R> {

    void action(T t);

    void progress(R r);

}
