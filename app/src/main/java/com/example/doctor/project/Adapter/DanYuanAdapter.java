package com.example.doctor.project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.doctor.R;
import com.example.doctor.project.entity.AllZhangjie;

import java.util.ArrayList;
import java.util.List;

public class DanYuanAdapter extends BaseAdapter {
    Context context;
    TextView lolo;
    TextView textView;
    List<AllZhangjie> list=new ArrayList<>();
    public DanYuanAdapter(Context context1, List<AllZhangjie> allZhangjies) {
        context=context1;
        list.addAll(allZhangjies);
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
        View view1= LayoutInflater.from(context).inflate(R.layout.danyuan,null);
        lolo=(TextView)view1.findViewById(R.id.lolo);
        lolo.setText(String.valueOf(list.get(i).getId()));
        textView=(TextView)view1.findViewById(R.id.title);
        textView.setText(list.get(i).getUnitname());
        return view1;
    }
}
