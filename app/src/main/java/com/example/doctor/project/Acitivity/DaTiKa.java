package com.example.doctor.project.Acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doctor.R;
import com.example.doctor.project.Adapter.DaTiaAdapter;
import com.example.doctor.project.entity.Dtk;
import com.example.doctor.project.entity.Event;
import com.example.doctor.project.entity.Finish;
import com.example.doctor.project.entity.TiaoZhuan;
import com.fingerth.supdialogutils.SYSDiaLogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class DaTiKa extends AppCompatActivity {
    TextView txt_left_title;
    Integer number;
    GridView gridView;
    Button chongzuo;
    DaTiaAdapter daTiaAdapter;
    ArrayList<Integer> integers = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        integers.addAll(getIntent().getIntegerArrayListExtra("shuzi"));
        number=getIntent().getIntExtra("number",0);
        setContentView(R.layout.datika);
        intiview();
    }

    public void intiview() {
        txt_left_title = (TextView) findViewById(R.id.txt_left_title);
        gridView = (GridView) findViewById(R.id.gridka);
        daTiaAdapter = new DaTiaAdapter(this,number);
        gridView.setAdapter(daTiaAdapter);
        chongzuo = (Button) findViewById(R.id.chongzuo);
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
                congzuo();
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                finish();
                EventBus.getDefault().post(new TiaoZhuan(i));
            }
        });
    }
    private void congzuo() {

            SYSDiaLogUtils.showConfirmDialog(this, true, SYSDiaLogUtils.SYSConfirmType.Tip, "提示", "是否需要重做？", new SYSDiaLogUtils.ConfirmDialogListener() {
                @Override
                public void onClickButton(boolean clickLeft, boolean clickRight) {
                    if (clickLeft) {
                    } else if (clickRight) {
                        //提交
                        EventBus.getDefault().post(new Finish());
                        Intent intent1=new Intent(DaTiKa.this,DaTiAcitivity.class);
                        startActivity(intent1);
                    }
                }
            });
        }

}
