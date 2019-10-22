package com.example.doctor.project.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

import com.example.doctor.R;
import com.example.doctor.project.Acitivity.DanYuanActivity;
import com.example.doctor.project.Adapter.ExAdapter;
import com.example.doctor.project.Adapter.TitleAdapter;
import com.example.doctor.project.entity.Event;
import com.example.doctor.project.entity.TitEvent;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import me.yokeyword.fragmentation.SupportFragment;

public class Support_Fr_2 extends SupportFragment implements View.OnClickListener , AdapterView.OnItemClickListener {
    ListView listView;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    TitleAdapter titleAdapter;
    String a[]={"病理学","生理学","药理学","医学微生物学","医学免疫学","医学统计学"};
    String h[]={"内科学","外科学","妇产科学","儿科学","传染病学","神经病学学"};
    String b[]={"预防医学"};
    String d[]={"卫生法","医疗心里苦法","医学伦理学"};
    public static Support_Fr_2 newInstance() {
        Bundle args = new Bundle();
        Support_Fr_2 fragment = new Support_Fr_2();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragtwo, null);
        listView = (ListView) view.findViewById(R.id.titlelist);

        intinView(view);
        button1.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton1));
//        Button button=(Button)view.findViewById(R.id.bt3);
       titleAdapter = new TitleAdapter(getActivity(),a);
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
                EventBus.getDefault().post(new TitEvent(a));
                break;
            case R.id.bt2:
                button1.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton2));
                button2.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton1));
                button3.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton2));
                button4.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton2));
                EventBus.getDefault().post(new TitEvent(d));
                break;
            case R.id.bt3:
                button1.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton2));
                button2.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton2));
                button3.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton1));
                button4.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton2));
                EventBus.getDefault().post(new TitEvent(h));
                break;
            case R.id.bt4:
                button1.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton2));
                button2.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton2));
                button3.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton2));
                button4.setBackground(getActivity().getResources().getDrawable(R.drawable.bontton1));
                EventBus.getDefault().post(new TitEvent(b));
                break;
                default:break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setList(TitEvent titEvent){
        titleAdapter.setData(titEvent.getMessage());
        titleAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView textView=(TextView)view.findViewById(R.id.title) ;
        Intent intent1=new Intent(getActivity(), DanYuanActivity.class);
        intent1.putExtra("title",textView.getText().toString());
        startActivity(intent1);
    }
}
