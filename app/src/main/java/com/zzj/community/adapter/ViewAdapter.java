package com.zzj.community.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.zzj.community.DTO.QuestionDto;
import com.zzj.community.R;
import com.zzj.community.model.Question;
import com.zzj.community.view.MyImageView;
import com.zzj.community.view.NineGridTestLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * @ProjectName: community
 * @Package: com.zzj.community.adapter
 * @ClassName: ViewAdapter
 * @Description: java类作用描述
 * @Author: Zhijun Zhang
 * @CreateDate: 2020/3/9 16:29
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/9 16:29
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ViewAdapter  extends BaseAdapter {


    private List<QuestionDto> datas;
    private LayoutInflater inflater;
    private ViewHolder viewHolder;


    public ViewAdapter() {
    }


    public void setDatas(List<QuestionDto> datas) {
        this.datas = datas;
    }

    public void setInflater(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public ViewAdapter(List<QuestionDto> datas, Context context) {
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        viewHolder = new ViewHolder();
        if(convertView == null ){
            convertView = inflater.inflate(R.layout.questions_item_layout, null);
            viewHolder.img_avatar = convertView.findViewById(R.id.img_avatar);
            viewHolder.tv_name = convertView.findViewById(R.id.tv_name);
            viewHolder.tv_content = convertView.findViewById(R.id.tv_content);
            viewHolder.mImageLayout = convertView.findViewById(R.id.img_image);
            viewHolder.tv_time = convertView.findViewById(R.id.tv_time);
            viewHolder.img_shoucang = convertView.findViewById(R.id.img_shoucang);
            viewHolder.img_pinglun = convertView.findViewById(R.id.img_pinglun);
            viewHolder.img_dianzan = convertView.findViewById(R.id.img_dianzan);
            viewHolder.tv_num_dianzan = convertView.findViewById(R.id.tv_num_dianzan);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.img_avatar.setImageURL(datas.get(position).getUser().getAvatarUrl());
        viewHolder.tv_name.setText(datas.get(position).getUser().getName());
//        viewHolder.tv_time.setText(datas.get(position).getGmtCreate().intValue());
        Date dt = new Date(Long.parseLong("" + datas.get(position).getGmtCreate()));//获取后台传来的时间并转换为长整形数据
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        viewHolder.tv_time.setText(sdf.format(dt));
        viewHolder.tv_content.setText(datas.get(position).getDescription());
        viewHolder.tv_num_dianzan.setText(Integer.toString(datas.get(position).getLikeCount()));

        viewHolder.mImageLayout.setIsShowAll(false);
        viewHolder.mImageLayout.setSpacing(5);

        if(datas == null)
            viewHolder.img_shoucang.setSelected(true);
        else
            viewHolder.img_shoucang.setSelected(false);

        if(datas.get(position).getLikeCount() >=1)
            viewHolder.img_dianzan.setSelected(true);
        else
            viewHolder.img_dianzan.setSelected(false);

//        String sImage = datas.get(position).getMom_image();
//        String image_item;
//        List<String> image_list = new ArrayList<>();
//        int p=0;
//        for(int k=0; k<sImage.length();k++){
//            if(sImage.charAt(k) == '|'){
//                image_item = "http://192.168.43.121:8080/uploadimage/"+sImage.substring(p,k);
//                //image_item = sImage.substring(p,k);
//                Log.v("Myimage2",image_item);
//                p=k+1;
//                image_list.add(image_item);
//            }
//        }
//        if(image_list.size() == 0){
//            Log.v("Myimage1",sImage);
//            image_list.add("http://192.168.43.121:8080/uploadimage/"+sImage);
//        }
//
//        else{
//            image_item = sImage.substring(p,sImage.length());
//            image_list.add("http://192.168.43.121:8080/uploadimage/"+image_item);
//            Log.v("Myimage2",image_item);
//        }
//        viewHolder.mImageLayout.setUrlList(image_list);

        viewHolder.img_shoucang.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mOnItemCollectListener.onCollectClick(position);
            }
        });
        viewHolder.img_pinglun.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mOnItemCommentListener.onCommentClick(position);
            }
        });
        viewHolder.img_dianzan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mOnItemPraiseListener.onPraiseClick(position);
            }
        });

        return convertView;
    }


    public static class ViewHolder{
        public MyImageView img_avatar;
        public TextView tv_name,tv_content,tv_time,tv_num_dianzan;
        public NineGridTestLayout mImageLayout;
        public ImageView img_shoucang,img_dianzan,img_pinglun;
    }

    public interface onItemCollectListener{
        void onCollectClick(int i);
    }

    public interface onItemCommentListener{
        void onCommentClick(int i);
    }

    public interface onItemPraiseListener{
        void onPraiseClick(int i);
    }

    private onItemCollectListener mOnItemCollectListener;
    private onItemCommentListener mOnItemCommentListener;
    private onItemPraiseListener mOnItemPraiseListener;

    public void setOnItemCollectListener(onItemCollectListener mOnItemCollectListener) {
        this.mOnItemCollectListener = mOnItemCollectListener;
    }

    public void setOnItemCommentClickListener(onItemCommentListener mOnItemCommentListener) {
        this.mOnItemCommentListener = mOnItemCommentListener;
    }
    public void setOnItemPraiseClickListener(onItemPraiseListener mOnItemPraiseListener) {
        this.mOnItemPraiseListener = mOnItemPraiseListener;
    }

}
