package com.zzj.community.application;

import android.app.Application;

import com.zzj.community.DTO.AllQuestionDto;
import com.zzj.community.model.User;
import com.zzj.community.model.UserMobile;

/**
 * @ProjectName: community
 * @Package: com.zzj.community.application
 * @ClassName: GetAllQuestionDtoApplication
 * @Description: java类作用描述
 * @Author: Zhijun Zhang
 * @CreateDate: 2020/3/10 22:05
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/10 22:05
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class GetAllQuestionDtoApplication extends Application {

    public boolean flag = true;

    public AllQuestionDto getAllQuestionDtoReturn() {
        return allQuestionDtoReturn;
    }

    public void setAllQuestionDtoReturn(AllQuestionDto allQuestionDtoReturn) {
        this.allQuestionDtoReturn = allQuestionDtoReturn;
    }

    public AllQuestionDto allQuestionDtoReturn;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isLoginFlag = false;

    public UserMobile userMobile;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User user;

    public boolean isLoginFlag() {
        return isLoginFlag;
    }

    public void setLoginFlag(boolean loginFlag) {
        isLoginFlag = loginFlag;
    }

    public UserMobile getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(UserMobile userMobile) {
        this.userMobile = userMobile;
    }
}
