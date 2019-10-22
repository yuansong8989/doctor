package com.example.myapplication3.view.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.StackView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PopActivity extends AppCompatActivity {
    String[] mobileArray = {"小松松"};
    String[] content = {"我是小松松"};
    private int[] imageViews = {R.drawable.bjt};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        int NOTIFICATION_ID=0x123;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ddd);
        Intent intent=new Intent(PopActivity.this,Study3_Activity.class);
        PendingIntent pi=PendingIntent.getActivity(PopActivity.this,0,intent,0);
        NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Notification notification=new Notification.Builder(this).setAutoCancel(true)
                .setTicker("有新消息").setSmallIcon(R.drawable.bf).setContentTitle("一条新通知")
                .setContentText("恭喜你，你要当爸爸了").setVibrate(new long[]{0,50,100,150}).setContentIntent(pi).build();
        notificationManager.notify(NOTIFICATION_ID,notification);
        Spinner spinner=(Spinner)findViewById(R.id.s1);
        BaseAdapter baseAdapter=new BaseAdapter() {
            @Override
            public int getCount() {
                return 3;
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
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View view1 = layoutInflater.inflate(R.layout.study3_item, null);
                TextView textView=(TextView)view1.findViewById(R.id.t2);
                textView.setText("我是第一名"+i);
                return view1;
            }
        };
        spinner.setAdapter(baseAdapter);
        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.e1);
        ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
            @Override
            public int getGroupCount() {
                return 3;
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
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View view1 = layoutInflater.inflate(R.layout.study3_item, null);
                return view1;
            }

            @Override
            public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View view1 = layoutInflater.inflate(R.layout.fff, null);
                return view1;
            }

            @Override
            public boolean isChildSelectable(int i, int i1) {
                return true;
            }
        };
        expandableListView.setAdapter(adapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                            Toast.makeText(PopActivity.this, "用户名或者密码不能为空，妹妹", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        ListView listView1=(ListView) findViewById(R.id.bbb);
//        GridView gridView =(GridView)findViewById(R.id.g1) ;
//        System.out.println("--------------------------");
//        listView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//        View view2 =LayoutInflater.from(this).inflate(R.layout.study3_item,null);
        BaseAdapter adapter3=new BaseAdapter() {
            @Override
            public int getCount() {
                return 20;
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
                LayoutInflater layoutInflater=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
                View view1=layoutInflater.inflate(R.layout.study3_item,null);
                return view1;
            }
        };
        listView1.setAdapter(adapter3);
//        BaseAdapter adapter1=new BaseAdapter() {
//            @Override
//            public int getCount() {
//                return 10;
//            }
//
//            @Override
//            public Object getItem(int i) {
//                return null;
//            }
//
//            @Override
//            public long getItemId(int i) {
//                return i;
//            }
//
//            @Override
//            public View getView(int i, View view, ViewGroup viewGroup) {
//                LayoutInflater layoutInflater=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
//                View view1=layoutInflater.inflate(R.layout.fff,null);
//                return view1;
//            }
//        };
//        System.out.println("网格布好局");
//        gridView.setAdapter(adapter1);
        StackView stackView=(StackView)findViewById(R.id.st1);
        BaseAdapter baseAdapter1=new BaseAdapter() {
            @Override
            public int getCount() {
                return 4;
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
                Integer []integer={R.drawable.kzd,R.drawable.xuexiao,R.drawable.cyn,R.drawable.bjt};
                LayoutInflater layoutInflater=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
                View view1=layoutInflater.inflate(R.layout.jjj,null);
                ImageView imageView=(ImageView)view1.findViewById(R.id.im1);
                imageView.setImageResource(integer[i]);
                return view1;
            }
        };
        stackView.setAdapter(baseAdapter1);
    }

}
