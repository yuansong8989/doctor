package com.example.doctor.project.Acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doctor.R;
import com.example.doctor.project.Adapter.DanYuanAdapter;
import com.fingerth.supdialogutils.SYSDiaLogUtils;

public class DanYuanActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    TextView textView;
    int a=200;
    int time=1;//记录点击时间 1能点击 0不能点击
    TextView back;
    ListView listView;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
          if(msg.what==0){
              time=1;
          }
        }
    };
    DanYuanAdapter danYuanAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xuanzhe);
        intiview();
    }
    private void intiview(){
        listView=(ListView)findViewById(R.id.danyuanlist);
        textView=(TextView)findViewById(R.id.txt_main_title);
        back=(TextView)findViewById(R.id.txt_left_title);
        textView.setText(getIntent().getStringExtra("title"));
        danYuanAdapter=new DanYuanAdapter(this);
        listView.setAdapter(danYuanAdapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(panduan(i)){
            a=i;
            Intent intent1=new Intent(DanYuanActivity.this,DaTiAcitivity.class);
            intent1.putExtra("title1",getIntent().getStringExtra("title"));
            startActivity(intent1);
            time=0;
            starttime();
        }else {
            //提示
            SYSDiaLogUtils.showInfoDialog(this, "你的操作太过频繁", "稍后再试", "确定", false);
        }
    }
    private  boolean panduan(int i){
        if(time==1||a!=i){
            return true;//代表能进去
        }return false;
    }
    private void starttime(){
        new Thread(){
            @Override
            public void run() {
                try{
                    Thread.sleep(4000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
        }.start();
    }
}
