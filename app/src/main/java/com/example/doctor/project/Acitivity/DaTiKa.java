package com.example.doctor.project.Acitivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doctor.R;
import com.example.doctor.project.Adapter.DaTiaAdapter;
import com.example.doctor.project.entity.Dtk;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class DaTiKa extends AppCompatActivity {
    TextView txt_left_title;
    GridView gridView;
    Button chongzuo;
    DaTiaAdapter daTiaAdapter;
    ArrayList<Integer> integers=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        integers.addAll(getIntent().getIntegerArrayListExtra("shuzi"));
        setContentView(R.layout.datika);
        intiview();
    }
    public  void intiview(){
        txt_left_title=(TextView)findViewById(R.id.txt_left_title);
        gridView=(GridView)findViewById(R.id.gridka);
        daTiaAdapter=new DaTiaAdapter(this);
        gridView.setAdapter(daTiaAdapter);
        chongzuo=(Button)findViewById(R.id.chongzuo) ;
        daTiaAdapter.setList(integers);
        daTiaAdapter.notifyDataSetChanged();
        txt_left_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        chongzuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

}
