package com.example.doctor.project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.doctor.R;

public class TitleAdapter extends BaseAdapter {
    Context context;
    TextView textView;
    String a[];
    public TitleAdapter(Context context1,String []c) {
        context=context1;
        a=c;
    }

    @Override
    public int getCount() {
        return a.length;
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
        View view1= LayoutInflater.from(context).inflate(R.layout.timulist,null);
        textView=(TextView)view1.findViewById(R.id.title);
        textView.setText(a[i]);
        return view1;
    }
    public  void setData(String d []){
        a=d;
    }
}
