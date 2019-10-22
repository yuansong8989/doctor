package com.example.doctor.project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.doctor.R;

public class GridViewAdapter extends BaseAdapter {
    Context context;

    public GridViewAdapter(Context context1) {
        context = context1;
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.wangge, null);
        TextView textView = (TextView) view1.findViewById(R.id.yyy4);
        ImageView imageView = (ImageView) view1.findViewById(R.id.yyy3);
        int[] b={R.mipmap.banji,R.mipmap.jiaofei,R.mipmap.news,R.mipmap.wdxy, R.mipmap.banji,R.mipmap.jiaofei,R.mipmap.news,R.mipmap.wdxy};
        String url[] = {"http://10.9.5.9:8761/banji.png", "http://10.9.5.9:8761/jiaofei.png", "http://10.9.5.9:8761/news.png", "http://10.9.5.9:8761/wdxy.png","http://10.9.5.9:8761/banji.png", "http://10.9.5.9:8761/jiaofei.png", "http://10.9.5.9:8761/news.png", "http://10.9.5.9:8761/wdxy.png"};
        String a[] = {"医学鉴赏", "专家题型", "实验课程", "医学考试","全真模拟","经验分享","VIP服务","心肺听诊"};
        for (int j = 0; j < 8; j++) {
            textView.setText(a[i]);
            imageView.setImageResource(b[i]);
//            Glide.with(context).load(url[i]).into(imageView);
        }
        return view1;
    }
}
