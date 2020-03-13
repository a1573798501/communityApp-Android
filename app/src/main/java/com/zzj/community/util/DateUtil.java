package com.zzj.community.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @ProjectName: community
 * @Package: com.zzj.community.util
 * @ClassName: DateUtil
 * @Description: java类作用描述
 * @Author: Zhijun Zhang
 * @CreateDate: 2020/3/11 21:13
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/11 21:13
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class DateUtil {

    public String formatDateTime(long datetime){

        Date dt = new Date(Long.parseLong("" + datetime));//获取后台传来的时间并转换为长整形数据
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        return sdf.format(dt);
    }



}
