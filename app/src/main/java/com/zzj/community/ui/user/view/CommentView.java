package com.zzj.community.ui.user.view;

import com.zzj.community.DTO.QuestionDto;
import com.zzj.community.mvp.BaseView;
import com.zzj.community.ui.user.mvp.View;

/**
 * @ProjectName: community
 * @Package: com.zzj.community.ui.user.view
 * @ClassName: CommentView
 * @Description: java类作用描述
 * @Author: Zhijun Zhang
 * @CreateDate: 2020/3/11 14:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/11 14:55
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface CommentView<R,Q> extends View<QuestionDto> {

    void setQuestionComment(R r);

    void setComment(Q q);

    void updateComment();


}
