package com.example.doctor.project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.doctor.R;
import com.example.doctor.project.ToMeView.CustomExpandableListView;

public class ExpandAdapter extends BaseExpandableListAdapter {
    Context context;
    public ExpandAdapter(Context context1) {
        context=context1;

    }

    @Override
    public int getGroupCount() {
        return 2;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 2;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.uint,null);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.uintchild,null);
        ExpandAdapterchild expandAdapterchild=new ExpandAdapterchild(context);
        ExpandableListView expandableListView=(ExpandableListView) view.findViewById(R.id.expand2);
        expandableListView.setAdapter(expandAdapterchild);
        expandableListView.setIndicatorBounds(expandableListView.getWidth()+800, expandableListView.getWidth());
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}
