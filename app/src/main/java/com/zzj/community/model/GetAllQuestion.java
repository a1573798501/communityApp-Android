package com.zzj.community.model;

import com.zzj.community.DTO.AllQuestionDto;

/**
 * @ProjectName: community
 * @Package: com.zzj.community.model
 * @ClassName: GetAllQuestion
 * @Description: java类作用描述
 * @Author: Zhijun Zhang
 * @CreateDate: 2020/3/11 13:22
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/11 13:22
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class GetAllQuestion {

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

}
