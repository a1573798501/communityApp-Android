package com.zzj.community.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zzj.community.DTO.CommentDto;
import com.zzj.community.R;
import com.zzj.community.model.Comment;
import com.zzj.community.view.MyImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class CommentAdapter extends BaseAdapter {

    private List<CommentDto> datas;
    private LayoutInflater inflater;
    private CommentAdapter.ViewHolder viewHolder;

    public CommentAdapter() {

    }

    public void setDatas(List<CommentDto> datas) {
        this.datas = datas;
    }

    public void setInflater(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        viewHolder = new CommentAdapter.ViewHolder();
        if(view == null ){
            view = inflater.inflate(R.layout.comment_item, null);
            viewHolder.img_avatar = view.findViewById(R.id.comment_img_avatar);
            viewHolder.tv_name = view.findViewById(R.id.comment_tv_name);
            viewHolder.tv_content = view.findViewById(R.id.comment_tv_content);
            viewHolder.tv_time = view.findViewById(R.id.comment_tv_time);

            view.setTag(viewHolder);
        }else {
            viewHolder = (CommentAdapter.ViewHolder)view.getTag();
        }
        viewHolder.img_avatar.setImageURL(datas.get(i).getUser().getAvatarUrl());
        viewHolder.tv_name.setText(datas.get(i).getUser().getName());
        Date dt = new Date(Long.parseLong("" + datas.get(i).getGmtCreate()));//获取后台传来的时间并转换为长整形数据
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        viewHolder.tv_time.setText(sdf.format(dt));
        viewHolder.tv_content.setText(datas.get(i).getContent());
        return view;
    }

    public static class ViewHolder{
        public MyImageView img_avatar;
        public TextView tv_name,tv_content,tv_time;
    }

}

