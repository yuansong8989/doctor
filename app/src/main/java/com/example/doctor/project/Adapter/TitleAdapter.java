package com.example.doctor.project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.doctor.R;
import com.example.doctor.project.entity.Classify;

import java.util.ArrayList;
import java.util.List;

public class TitleAdapter extends BaseAdapter {
    Context context;
    TextView textView;
    TextView kkkid;
List<Classify> list=new ArrayList<>();
    public TitleAdapter(Context context1,List<Classify> list1) {
        context=context1;
       list=list1;
    }

    @Override
    public int getCount() {
        return list.size();
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
        kkkid=(TextView)view1.findViewById(R.id.kkkid);
        kkkid.setText(String.valueOf(list.get(i).getId()));
        textView.setText(list.get(i).getClassifyname());
        return view1;
    }
    public  void setData(List<Classify> list2){
        list=list2;
    }
}
