package com.example.doctor.project.Acitivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doctor.R;
import com.example.doctor.project.Adapter.DanYuanAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DaTiAcitivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    TextView danan1;
    TextView danan2;
    TextView danan3;
    LinearLayout ll1;
    LinearLayout ll2;
    LinearLayout ll3;
    LinearLayout ll4;
//已经做了的题目
    ArrayList<Integer> a=new ArrayList<>();
    TextView danan4;
    ImageView danana;
    ImageView dananb;
    ImageView dananc;
    ImageView danand;
    TextView wenti;
    TextView back;
    LinearLayout dibu4;
    LinearLayout jiucuo;
    protected static final float FLIP_DISTANCE = 50;
    GestureDetector gestureDetector;
    long time=2500;
    boolean shijian=true;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==1){
                timebt.setText(formatLongToTimeStr(time--));
            }
            super.handleMessage(msg);
        }
    };
    Button timebt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer);

        intiview();

        //初始化答题时间
        startTime();
        huaDong();
        //左右滑动监听
    }
    private void intiview(){
        jiucuo=(LinearLayout)findViewById(R.id.jiucuo);
        dibu4=(LinearLayout)findViewById(R.id.dibu4);
        ll1=(LinearLayout)findViewById(R.id.ll1);
        ll2=(LinearLayout)findViewById(R.id.ll2);
        ll3=(LinearLayout)findViewById(R.id.ll3);
        ll4=(LinearLayout)findViewById(R.id.ll4);
        danana=(ImageView)findViewById(R.id.aaa);
        dananb=(ImageView)findViewById(R.id.bbb);
        dananc=(ImageView)findViewById(R.id.ccc);
        danand=(ImageView)findViewById(R.id.ddd);
        danan1=(TextView)findViewById(R.id.daan1);
        danan2=(TextView)findViewById(R.id.daan2);
        danan3=(TextView)findViewById(R.id.daan3);
        danan4=(TextView)findViewById(R.id.daan4);
        wenti=(TextView)findViewById(R.id.wenti);
        timebt=(Button)findViewById(R.id.timebt);
        textView=(TextView)findViewById(R.id.txt_main_title);
        back=(TextView)findViewById(R.id.txt_left_title);
        textView.setText(getIntent().getStringExtra("title1"));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        timebt.setText(formatLongToTimeStr(time));
        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        ll3.setOnClickListener(this);
        dibu4.setOnClickListener(this);
        ll4.setOnClickListener(this);
        jiucuo.setOnClickListener(this);
    }
public void huaDong(){
    gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            // TODO Auto-generated method stub

        }

        /**
         *
         * e1 The first down motion event that started the fling. e2 The
         * move motion event that triggered the current onFling.
         */
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() - e2.getX() > FLIP_DISTANCE) {
                wenti.setText("什么方法治疗脚气?");
                System.out.println("向左滑动");
                return true;
            }
            if (e2.getX() - e1.getX() > FLIP_DISTANCE) {
                System.out.println("向右滑动");
                return true;
            }
            if (e1.getY() - e2.getY() > FLIP_DISTANCE) {
                System.out.println("向上滑动");
                return true;
            }
            if (e2.getY() - e1.getY() > FLIP_DISTANCE) {
                System.out.println("向下滑动");
                return true;
            }

            System.out.println("其他");

            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            // TODO Auto-generated method stub
            return false;
        }
    });
}
public void startTime(){
        new Thread(){
            @Override
            public void run() {
                while (shijian){
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    if(time==0){
                        shijian=false;
                    }
                    handler.sendEmptyMessage(1);
                }
            }
        }.start();
}
    public String formatLongToTimeStr(Long l) {
        int hour = 0;
        int minute = 0;
        int second = 0;
        second = l.intValue() ;
        if (second > 60) {
            minute = second / 60;   //取整
            second = second % 60;   //取余
        }
        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        String strtime = "剩余："+hour+"小时"+minute+"分"+second+"秒";
        return strtime;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.jiucuo:
                break;
            case R.id.dibu4:
                Intent intent1=new Intent(DaTiAcitivity.this,DaTiKa.class);
                intent1.putIntegerArrayListExtra("shuzi",removeDuplicate(a));
                startActivity(intent1);
                break;
            case R.id.ll1:
                danan1.setTextColor(Color.parseColor("#1afa29"));
                danana.setImageResource(R.mipmap.aaa1);
                dananb.setImageResource(R.mipmap.bbb);
                dananc.setImageResource(R.mipmap.ccc);
                danand.setImageResource(R.mipmap.ddd);
                setbfg(danan2,danan3,danan4);
                break;
            case R.id.ll2:
                danan2.setTextColor(Color.parseColor("#1afa29"));
                dananb.setImageResource(R.mipmap.bbb1);
                setbfg(danan1,danan3,danan4);
                danana.setImageResource(R.mipmap.aaa);
                dananc.setImageResource(R.mipmap.ccc);
                danand.setImageResource(R.mipmap.ddd);
                break;
            case R.id.ll3:
                danan3.setTextColor(Color.parseColor("#1afa29"));
                dananc.setImageResource(R.mipmap.ccc1);
                setbfg(danan1,danan2,danan4);
                dananb.setImageResource(R.mipmap.bbb);
                danana.setImageResource(R.mipmap.aaa);
                danand.setImageResource(R.mipmap.ddd);
                break;
            case R.id.ll4:
                danan4.setTextColor(Color.parseColor("#1afa29"));
                danand.setImageResource(R.mipmap.ddd1);
                setbfg(danan1,danan3,danan2);
                dananb.setImageResource(R.mipmap.bbb);
                dananc.setImageResource(R.mipmap.ccc);
                danana.setImageResource(R.mipmap.aaa);
                break;
        }
    }
    public void setbfg(TextView t2,TextView t3,TextView t4){
        t2.setTextColor(Color.parseColor("#8a8a8a"));
        t4.setTextColor(Color.parseColor("#8a8a8a"));
        t3.setTextColor(Color.parseColor("#8a8a8a"));
    }
    public  ArrayList removeDuplicate(ArrayList list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }

}
