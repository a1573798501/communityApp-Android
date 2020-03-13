package com.zzj.community.ui.user.mvp;

/**
 * @ProjectName: community
 * @Package: com.zzj.community.ui.user.mvp
 * @ClassName: Model
 * @Description: java类作用描述
 * @Author: Zhijun Zhang
 * @CreateDate: 2020/3/11 17:09
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/11 17:09
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface Model<T,R> {

    R request(T t);

}
