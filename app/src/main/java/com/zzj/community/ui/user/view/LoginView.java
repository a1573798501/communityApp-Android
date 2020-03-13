package com.zzj.community.ui.user.view;

import com.zzj.community.model.User;
import com.zzj.community.model.UserMobile;
import com.zzj.community.mvp.BaseView;

/**
 * @ProjectName: community
 * @Package: com.zzj.community.ui.user.view
 * @ClassName: LoginView
 * @Description: java类作用描述
 * @Author: Zhijun Zhang
 * @CreateDate: 2020/3/5 15:18
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/5 15:18
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface LoginView extends BaseView {

    public void showLogin(User user);

}
