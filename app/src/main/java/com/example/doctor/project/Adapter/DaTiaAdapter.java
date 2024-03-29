package com.example.doctor.project.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.doctor.R;
import com.example.doctor.project.entity.Answer;
import com.example.doctor.project.entity.Dtk;
import com.example.doctor.project.entity.JieGuo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class DaTiaAdapter extends BaseAdapter {
    Context context;
    List<JieGuo> answers=new ArrayList<>();
    Integer number;
    ArrayList<Integer> list = new ArrayList<>();

    public DaTiaAdapter(Context context1, Integer integer) {
        number = integer;
        context = context1;
    }

    @Override
    public int getCount() {
        return number;
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
        View view1 = LayoutInflater.from(context).inflate(R.layout.ka, null);
        TextView textView = (TextView) view1.findViewById(R.id.shuzi);
        textView.setText(String.valueOf(i + 1));
        if (list.size() != 0) {//表示有选择
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j) == i) {
                    textView.setTextColor(Color.WHITE);
                    textView.setBackground(context.getResources().getDrawable(R.drawable.dati));
                    list.remove(j);
                }
                break;
            }
        }else{
            if(answers.size()!=0){
                for (int j = 0; j < answers.size(); j++) {
                    if (answers.get(i).getCheck().equals("true")) {
                        textView.setTextColor(Color.WHITE);
                        textView.setBackground(context.getResources().getDrawable(R.drawable.zhengque));
                    } else {
                        textView.setTextColor(Color.WHITE);
                        textView.setBackground(context.getResources().getDrawable(R.drawable.cuowu));
                    }
                    break;
                }
            }
        }
        return view1;
    }


    public void setList(ArrayList<Integer> list1) {
        list.addAll(list1);
    }

    public void setList1(List<JieGuo> answers1) {
        answers.addAll(answers1);
    }
}
