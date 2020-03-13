package com.zzj.community.ui.user.persenter;

import com.zzj.community.http.ApiService;
import com.zzj.community.http.HttpUtils;
import com.zzj.community.http.ResponseListener;
import com.zzj.community.mvp.BasePersenter;
import com.zzj.community.ui.user.view.RegisterView;
import com.zzj.community.util.MediaFileUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * @ProjectName: community
 * @Package: com.zzj.community.ui.user.persenter
 * @ClassName: RegisterPersenter
 * @Description: java类作用描述
 * @Author: Zhijun Zhang
 * @CreateDate: 2020/3/5 15:38
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/5 15:38
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RegisterPersenter extends BasePersenter<RegisterView> {

    public void sendRegister(String userName,String userEmail , String userPassword){
//        getMyBaseView().setData(userName + "<---->" + userEmail +  "<---->" + userPassword);
        HttpUtils.sendHttp(HttpUtils.createApi(ApiService.class).getComment(), new ResponseListener<String>() {
            @Override
            public void onSuccess(String data) {
                if (getMyBaseView()!=null){
                    getMyBaseView().setData(data);
                }
            }

            @Override
            public void onFail(String data) {
                if (getMyBaseView()!=null){
                    getMyBaseView().error(data);
                }
            }
        });
    }

    public void upLoadFile(String filePath){
//        getMyBaseView().setData(userName + "<---->" + userEmail +  "<---->" + userPassword);

        File file = new File(filePath);
        RequestBody requestBody =
                RequestBody.create(MediaType.parse(MediaFileUtil.getFileMimeType(filePath)), file);
//参数1 数组名，参数2 文件名。
        MultipartBody.Part photo1part =
                MultipartBody.Part.createFormData("fileName", file.getName(), requestBody);
        HttpUtils.sendUploadFileHttp(HttpUtils.createApi(ApiService.class).uploadFile(photo1part), new ResponseListener<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody data) {
                if (getMyBaseView()!=null){
                    try {
                        getMyBaseView().setData(data.byteString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFail(String data) {
                if (getMyBaseView()!=null){
                    getMyBaseView().error(data);
                }
            }
        });
    }

}
