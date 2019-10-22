package com.example.doctor.project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.example.doctor.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ExAdapter extends BaseExpandableListAdapter {
    Context context;
    public ExAdapter(Context context1) {
        context=context1;
    }

    @Override
    public int getGroupCount() {
        return 5;
    }

    @Override
    public int getChildrenCount(int i) {
        return 4;
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.timulist, null);
        return view1;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.fenlei, null);
        return view1;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
