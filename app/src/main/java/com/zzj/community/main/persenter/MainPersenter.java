package com.zzj.community.main.persenter;

import com.zzj.community.main.view.MainView;
import com.zzj.community.mvp.BasePersenter;

/**
 * @ProjectName: community
 * @Package: com.zzj.community.main.persenter
 * @ClassName: MainPersenter
 * @Description: java类作用描述
 * @Author: Zhijun Zhang
 * @CreateDate: 2020/3/5 13:25
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/5 13:25
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MainPersenter extends BasePersenter<MainView> {

    public void getTest(){
        getMyBaseView().setData("test");
    }

}
