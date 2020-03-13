package com.zzj.community.ui.user.persenter;

import com.zzj.community.DTO.ResultDto;
import com.zzj.community.model.User;
import com.zzj.community.model.UserMobile;
import com.zzj.community.ui.user.UserMainViewActivity;
import com.zzj.community.ui.user.model.CommentModel;
import com.zzj.community.ui.user.model.LoginResultModel;
import com.zzj.community.ui.user.mvp.Persenter;
import com.zzj.community.ui.user.view.CommentView;
import com.zzj.community.ui.user.view.LoginResultView;

/**
 * @ProjectName: community
 * @Package: com.zzj.community.ui.user.persenter
 * @ClassName: LoginResultPersenter
 * @Description: java类作用描述
 * @Author: Zhijun Zhang
 * @CreateDate: 2020/3/13 19:51
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/13 19:51
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LoginResultPersenter implements Persenter<UserMobile , ResultDto<User>> {

    private LoginResultView loginResultView;

    private LoginResultModel loginResultModel;

    public LoginResultPersenter(LoginResultView loginResultView){
        this.loginResultView = loginResultView;
        loginResultModel = new LoginResultModel(this);
    }

    @Override
    public void action(UserMobile userMobile) {

        loginResultModel.request(userMobile);

    }

    @Override
    public void progress(ResultDto<User> userResultDto) {
        loginResultView.progress(userResultDto);
    }
}
