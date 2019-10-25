package com.example.doctor.project.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doctor.R;
import com.example.doctor.project.Acitivity.DaTiAcitivity;
import com.example.doctor.project.Acitivity.DanYuanActivity;
import com.example.doctor.project.Adapter.ExAdapter;
import com.example.doctor.project.Adapter.TitleAdapter;
import com.example.doctor.project.entity.Classify;
import com.example.doctor.project.entity.Event;
import com.example.doctor.project.entity.Result;
import com.example.doctor.project.entity.Subject;
import com.example.doctor.project.entity.TitEvent;
import com.fingerth.supdialogutils.SYSDiaLogUtils;
import com.google.gson.Gson;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

public class Support_Fr_2 extends SupportFragment implements View.OnClickListener , AdapterView.OnItemClickListener {
    ListView listView;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    TitleAdapter titleAdapter;
    Gson gson=new Gson();
Subject subject=new Subject();
    public static Support_Fr_2 newInstance() {
        Bundle args = new Bundle();
        Support_Fr_2 fragment = new Support_Fr_2();
        fragment.setArguments(args);
        return fragment;
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==0){
//                String a=msg.getData().getString("danyuan");
//                subject=gson.fromJson(a,Subject.class);
                SYSDiaLogUtils.dismissProgress();
            }
            super.handleMessage(msg);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragtwo, null);
        listView = (ListView) view.findViewById(R.id.titlelist);
        intinView(view);
        button1.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton1));
//        Button button=(Button)view.findViewById(R.id.bt3);
        titleAdapter = new TitleAdapter(getActivity(),subject.getSubject1());
        listView.setAdapter(titleAdapter);
        listView.setOnItemClickListener(this);
        return view;
    }

    private void intinView(View view) {
        button1 = (Button) view.findViewById(R.id.bt1);
        button2 = (Button) view.findViewById(R.id.bt2);
        button3 = (Button) view.findViewById(R.id.bt3);
        button4 = (Button) view.findViewById(R.id.bt4);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                button1.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton1));
                button2.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton2));
                button3.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton2));
                button4.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton2));
                EventBus.getDefault().post(subject.getSubject1());
                break;
            case R.id.bt2:
                button1.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton2));
                button2.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton1));
                button3.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton2));
                button4.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton2));
                EventBus.getDefault().post(subject.getSubject2());
                break;
            case R.id.bt3:
                button1.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton2));
                button2.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton2));
                button3.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton1));
                button4.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton2));
                EventBus.getDefault().post(new TitEvent(subject.getSubject3()));
                break;
            case R.id.bt4:
                button1.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton2));
                button2.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton2));
                button3.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton2));
                button4.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton1));
                EventBus.getDefault().post(new TitEvent(subject.getSubject4()));
                break;
                default:break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setList(TitEvent titEvent){
        titleAdapter.setData(titEvent.getList());
        titleAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView textView=(TextView)view.findViewById(R.id.title) ;
        Intent intent1=new Intent(getActivity(), DanYuanActivity.class);
        intent1.putExtra("title",textView.getText().toString());
        startActivity(intent1);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Allshuju();
        super.onCreate(savedInstanceState);
    }
    public void Allshuju() {
        {
            RequestQueue queue = Volley.newRequestQueue(getActivity());
            String url = "http://106.53.9.58:8761/subject";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            System.out.println("成功");
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("失败");
                }
            });
            queue.add(stringRequest);
        }
    }
}



