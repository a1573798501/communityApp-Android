package com.zzj.community.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.utils.L;
import com.zzj.community.DTO.AllQuestionDto;
import com.zzj.community.DTO.QuestionDto;
import com.zzj.community.MainActivity;
import com.zzj.community.R;
import com.zzj.community.adapter.ViewAdapter;
import com.zzj.community.application.GetAllQuestionDtoApplication;
import com.zzj.community.http.ApiService;
import com.zzj.community.http.HttpUrlGet;
import com.zzj.community.model.GetAllQuestion;
import com.zzj.community.model.Question;
import com.zzj.community.ui.user.CommentActivity;
import com.zzj.community.ui.user.RegisterActivity;

import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ProjectName: community
 * @Package: com.zzj.community.fragment
 * @ClassName: QuestionFragment
 * @Description: java类作用描述
 * @Author: Zhijun Zhang
 * @CreateDate: 2020/3/9 22:47
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/9 22:47
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class QuestionFragment extends Fragment {


    public QuestionFragment() {

        // Required empty public constructor
    }

    private QuestionPersenter questionPersenter = new QuestionPersenter();

    private Activity activity;

    private GetAllQuestion getAllQuestion = new GetAllQuestion();



    ListView lv_view;
    List<QuestionDto> list_item;
    ViewAdapter adapter = new ViewAdapter();
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            adapter.setDatas((List<QuestionDto>)msg.obj);
            adapter.setInflater(getActivity());
            lv_view.setAdapter(adapter);

            adapter.setOnItemCollectListener(new ViewAdapter.onItemCollectListener() {
                @Override
                public void onCollectClick(final int i) {

                }
            });

            adapter.setOnItemCommentClickListener(new ViewAdapter.onItemCommentListener() {
                @Override
                public void onCommentClick(int i) {
                    System.out.println("");
                    comment(i);
                }
            });

            adapter.setOnItemPraiseClickListener(new ViewAdapter.onItemPraiseListener() {
                @Override
                public void onPraiseClick(final int i) {

                }
            });
        }
    };

    Handler handlerPra = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if(msg.what == 0){
                adapter.notifyDataSetChanged();
            }
        }
    };


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.element_question,container,false);
        lv_view = view.findViewById(R.id.lv_view);




        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    questionPersenter.getQuestionJson(activity);
                    while ( ((GetAllQuestionDtoApplication)activity.getApplication()).isFlag() ){
                        Log.e("Loading ===========>", "Please wait!!");
                    }
                    list_item = ((GetAllQuestionDtoApplication)activity.getApplication()).getAllQuestionDtoReturn().getQuestionDtos();
                    if(!list_item.isEmpty())
                        handler.sendMessage(handler.obtainMessage(22,list_item));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

        return view;
    }

    private void comment(int i){
        QuestionDto questionDto = list_item.get(i);
        Intent commentDetail = new Intent(getActivity(), CommentActivity.class);

        commentDetail.putExtra("questionId",questionDto.getId());

        startActivity(commentDetail);
    }


}
