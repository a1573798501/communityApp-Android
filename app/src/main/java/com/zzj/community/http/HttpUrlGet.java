package com.zzj.community.http;

/**
 * @ProjectName: community
 * @Package: com.zzj.community.http
 * @ClassName: HttpUri
 * @Description: java类作用描述
 * @Author: Zhijun Zhang
 * @CreateDate: 2020/3/10 15:05
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/10 15:05
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class HttpUrlGet {

    /*
     *生产环境
     */
    private String BASE_URL = "https://api.apiopen.top";

    /*
     *测试环境
     */
    private String TEST_URL = "https://api.apiopen.top";

    private String LOCAL_URL = "http://192.168.199.107";

    public String getBASE_URL() {
        return BASE_URL;
    }

    public void setBASE_URL(String BASE_URL) {
        this.BASE_URL = BASE_URL;
    }

    public String getTEST_URL() {
        return TEST_URL;
    }

    public void setTEST_URL(String TEST_URL) {
        this.TEST_URL = TEST_URL;
    }

    public String getLOCAL_URL() {
        return LOCAL_URL;
    }

    public void setLOCAL_URL(String LOCAL_URL) {
        this.LOCAL_URL = LOCAL_URL;
    }
}
