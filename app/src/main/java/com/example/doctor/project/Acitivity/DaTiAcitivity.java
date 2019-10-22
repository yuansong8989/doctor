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
import com.example.doctor.project.entity.Problem;
import com.example.doctor.project.entity.ProblemAll;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DaTiAcitivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    int frist = 0;
    List<Answer> answerList=new ArrayList<>();
    ProblemAll problemAll = new ProblemAll();
    TextView danan1;
    TextView danan2;
    TextView danan3;
    LinearLayout ll1;
    LinearLayout ll2;
    TextView jiexi;
    LinearLayout ll3;
    LinearLayout ll4;
    //已经做了的题目
    Problem problem=new Problem();
    ArrayList<Integer> a = new ArrayList<>();
    TextView danan4;
    ImageView danana;
    ImageView dananb;
    ImageView dananc;
    ImageView danand;
    TextView wenti;
    TextView back;
    LinearLayout dibu4;
    Button submit;
    LinearLayout jiucuo;
    protected static final float FLIP_DISTANCE = 50;
    GestureDetector gestureDetector;
    long time = 2500;
    boolean shijian = true;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 1) {
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
//初始化答题页面
        problemAll=getProblemAll();
        problem=problemAll.getList().get(frist);
        startView(problem);
        //初始化答题时间
        startTime();
        huaDong();

        //左右滑动监听
    }

    private void intiview() {
        submit=(Button)findViewById(R.id.submit);
        jiexi = (TextView) findViewById(R.id.jiexi);
        jiucuo = (LinearLayout) findViewById(R.id.jiucuo);
        dibu4 = (LinearLayout) findViewById(R.id.dibu4);
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        ll2 = (LinearLayout) findViewById(R.id.ll2);
        ll3 = (LinearLayout) findViewById(R.id.ll3);
        ll4 = (LinearLayout) findViewById(R.id.ll4);
        danana = (ImageView) findViewById(R.id.aaa);
        dananb = (ImageView) findViewById(R.id.bbb);
        dananc = (ImageView) findViewById(R.id.ccc);
        danand = (ImageView) findViewById(R.id.ddd);
        danan1 = (TextView) findViewById(R.id.daan1);
        danan2 = (TextView) findViewById(R.id.daan2);
        danan3 = (TextView) findViewById(R.id.daan3);
        danan4 = (TextView) findViewById(R.id.daan4);
        wenti = (TextView) findViewById(R.id.wenti);
        timebt = (Button) findViewById(R.id.timebt);
        textView = (TextView) findViewById(R.id.txt_main_title);
        back = (TextView) findViewById(R.id.txt_left_title);
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
        submit.setOnClickListener(this);
        dibu4.setOnClickListener(this);
        ll4.setOnClickListener(this);
        jiucuo.setOnClickListener(this);
    }

    public void huaDong() {
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
                    quxiaodf();
                    problem=problemAll.getList().get(frist++);
                    startView(problem);
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

    public void startTime() {
        new Thread() {
            @Override
            public void run() {
                while (shijian) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (time == 0) {
                        shijian = false;
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
        second = l.intValue();
        if (second > 60) {
            minute = second / 60;   //取整
            second = second % 60;   //取余
        }
        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        String strtime = "剩余：" + hour + "小时" + minute + "分" + second + "秒";
        return strtime;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit:
                for (Answer answer : answerList){
                    System.out.println("问题的答案"+answer.getId()+answer.getAnswer());
                }
                break;
            case R.id.dibu4:

                Intent intent1 = new Intent(DaTiAcitivity.this, DaTiKa.class);
                intent1.putIntegerArrayListExtra("shuzi", removeDuplicate(getA()));
                startActivity(intent1);
                break;
            case R.id.ll1:
                if(chazzhao()){
                    for(int i=0;i<answerList.size();i++){
                        if(answerList.get(i).getId()==problem.getId()){
                            answerList.get(i).setAnswer("A");
                        }
                    }
                }else{
                    Answer answer=new Answer();
                    answer.setAnswer("A");
                    answer.setId(problem.getId());
                    answerList.add(answer);
                }
                danan1.setTextColor(Color.parseColor("#1afa29"));
                danana.setImageResource(R.mipmap.aaa1);
                dananb.setImageResource(R.mipmap.bbb);
                dananc.setImageResource(R.mipmap.ccc);
                danand.setImageResource(R.mipmap.ddd);
                setbfg(danan2, danan3, danan4);
                break;
            case R.id.ll2:
                if(chazzhao()){
                    for(int i=0;i<answerList.size();i++){
                        if(answerList.get(i).getId()==problem.getId()){
                            answerList.get(i).setAnswer("B");
                        }
                    }
                }else{
                    Answer answer=new Answer();
                    answer.setAnswer("B");
                    answer.setId(problem.getId());
                    answerList.add(answer);
                }
                danan2.setTextColor(Color.parseColor("#1afa29"));
                dananb.setImageResource(R.mipmap.bbb1);
                setbfg(danan1, danan3, danan4);
                danana.setImageResource(R.mipmap.aaa);
                dananc.setImageResource(R.mipmap.ccc);
                danand.setImageResource(R.mipmap.ddd);
                break;
            case R.id.ll3:
                if(chazzhao()){
                    for(int i=0;i<answerList.size();i++){
                        if(answerList.get(i).getId()==problem.getId()){
                            answerList.get(i).setAnswer("C");
                        }
                    }
                }else{
                    Answer answer=new Answer();
                    answer.setAnswer("C");
                    answer.setId(problem.getId());
                    answerList.add(answer);
                }
                danan3.setTextColor(Color.parseColor("#1afa29"));
                dananc.setImageResource(R.mipmap.ccc1);
                setbfg(danan1, danan2, danan4);
                dananb.setImageResource(R.mipmap.bbb);
                danana.setImageResource(R.mipmap.aaa);
                danand.setImageResource(R.mipmap.ddd);
                break;
            case R.id.ll4:
                if(chazzhao()){
                    for(int i=0;i<answerList.size();i++){
                        if(answerList.get(i).getId()==problem.getId()){
                            answerList.get(i).setAnswer("D");
                        }
                    }
                }else{
                    Answer answer=new Answer();
                    answer.setAnswer("D");
                    answer.setId(problem.getId());
                    answerList.add(answer);
                }
                danan4.setTextColor(Color.parseColor("#1afa29"));
                danand.setImageResource(R.mipmap.ddd1);
                setbfg(danan1, danan3, danan2);
                dananb.setImageResource(R.mipmap.bbb);
                dananc.setImageResource(R.mipmap.ccc);
                danana.setImageResource(R.mipmap.aaa);
                break;
        }
    }

    public void setbfg(TextView t2, TextView t3, TextView t4) {
        t2.setTextColor(Color.parseColor("#8a8a8a"));
        t4.setTextColor(Color.parseColor("#8a8a8a"));
        t3.setTextColor(Color.parseColor("#8a8a8a"));
    }
    public void quxiaodf(){
        danana.setImageResource(R.mipmap.aaa);
        dananb.setImageResource(R.mipmap.bbb);dananc.setImageResource(R.mipmap.ccc);
        danand.setImageResource(R.mipmap.ddd);
        dananb.setImageResource(R.mipmap.bbb);
        danan1.setTextColor(Color.parseColor("#8a8a8a"));
        danan2.setTextColor(Color.parseColor("#8a8a8a"));
        danan3.setTextColor(Color.parseColor("#8a8a8a"));
        danan4.setTextColor(Color.parseColor("#8a8a8a"));
    }

    public ArrayList removeDuplicate(ArrayList list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }

    public void startView(Problem problem) {
        wenti.setText(problem.getProblemtitle());
        danan1.setText(problem.getChoicea());
        danan2.setText(problem.getChoiceb());
        danan3.setText(problem.getChoicec());
        danan4.setText(problem.getChoiced());
        jiexi.setText(problem.getAnalysis());
    }
private boolean chazzhao(){
        for(Answer answer:answerList){
            if(answer.getId()==problem.getId()){
                return true;
            }
        }
        return false;
}
//测试
    public ProblemAll getProblemAll(){
        ProblemAll problemAll=new ProblemAll();
        Problem problem=new Problem();
        problem.setAnalysis("大城市");
        problem.setChoicea("大萨达多");
        problem.setChoiceb("搭搭撒撒");
        problem.setChoicec("打打所多");
        problem.setChoiced("打法对方");
        problem.setId(1);
        problem.setProblemtitle("s大家看到门口");
        Problem problem1=new Problem();
        problem1.setAnalysis("大城市1");
        problem1.setChoicea("大萨达多1");
        problem1.setChoiceb("搭搭撒撒1");
        problem1.setChoicec("打打所多1");
        problem1.setChoiced("打法对方1");
        problem1.setId(2);
        problem.setProblemtitle("s大家看到门口1");
        List<Problem> list1=new ArrayList<>();
        list1.add(problem); list1.add(problem1);
        problemAll.setList(list1);
        return problemAll;
    }
    public ArrayList<Integer> getA(){
        for (Answer answer :answerList){
            a.add(answer.getId());
        }
        return  a;
    }

}
