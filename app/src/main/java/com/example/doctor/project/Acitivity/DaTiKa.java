package com.example.doctor.project.Acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doctor.R;
import com.example.doctor.project.Adapter.DaTiaAdapter;
import com.example.doctor.project.entity.Dtk;
import com.example.doctor.project.entity.Event;
import com.example.doctor.project.entity.Finish;
import com.example.doctor.project.entity.HuiFu;
import com.example.doctor.project.entity.Result1;
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
    TextView zongfen;
    Button chongzuo;
    LinearLayout showrs;
    TextView zhengque;
    TextView cuowu;
    DaTiaAdapter daTiaAdapter;
    ArrayList<Integer> integers = new ArrayList<>();
    Result1 result1=new Result1();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        number=getIntent().getIntExtra("number",0);
        setContentView(R.layout.datika);
        intiview();
        showwangge();

    }

    public void intiview() {
        zhengque=(TextView)findViewById(R.id.zhengque);
        cuowu=(TextView)findViewById(R.id.cuowu);
        zongfen=(TextView)findViewById(R.id.zongfen);
        showrs=(LinearLayout)findViewById(R.id.showrs);
        txt_left_title = (TextView) findViewById(R.id.txt_left_title);
        gridView = (GridView) findViewById(R.id.gridka);
        chongzuo = (Button) findViewById(R.id.chongzuo);
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
                        //重做
                        EventBus.getDefault().post(new Finish());
                        Intent intent1=new Intent(DaTiKa.this,DaTiAcitivity.class);
                        startActivity(intent1);
                        finish();
                    }
                }
            });
        }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
public  void showwangge(){
    if(number!=0){
        integers.addAll(getIntent().getIntegerArrayListExtra("shuzi"));
        showrs.setVisibility(View.GONE);
        daTiaAdapter = new DaTiaAdapter(this,number);
        gridView.setAdapter(daTiaAdapter);
        daTiaAdapter.setList(integers);
        daTiaAdapter.notifyDataSetChanged();
    }else{
        result1=(Result1)getIntent().getSerializableExtra("result");
        zongfen.setText(String.valueOf(result1.getGrade()));
        zhengque.setText(String.valueOf(result1.getGrade()/5));
        cuowu.setText(String.valueOf(result1.getList().size()-result1.getGrade()/5));
        daTiaAdapter = new DaTiaAdapter(this,result1.getList().size());
        gridView.setAdapter(daTiaAdapter);
        daTiaAdapter.setList1(result1.getList());
        daTiaAdapter.notifyDataSetChanged();
        EventBus.getDefault().post(new HuiFu());
    }
}
    //提交答案 返回结果
}
