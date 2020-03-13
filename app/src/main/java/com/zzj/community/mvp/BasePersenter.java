package com.zzj.community.mvp;

/**
 * @ProjectName: community
 * @Package: com.zzj.community.mvp
 * @ClassName: BasePersenter
 * @Description: java类作用描述
 * @Author: Zhijun Zhang
 * @CreateDate: 2020/3/5 13:10
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/5 13:10
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BasePersenter<baseView extends BaseView> {

    private baseView myBaseView;

    public void bindView(baseView view){
        if (view != null){
            this.myBaseView = view;
        }
    }

    public void unBindView(){
        myBaseView = null;
    }

    public baseView getMyBaseView(){
        return myBaseView;
    }

}
