package com.example.doctor.project.Acitivity;

import android.content.DialogInterface;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doctor.R;
import com.example.doctor.project.Adapter.DanYuanAdapter;
import com.example.doctor.project.entity.Event;
import com.example.doctor.project.entity.Finish;
import com.example.doctor.project.entity.Problem;
import com.example.doctor.project.entity.ProblemAll;
import com.example.doctor.project.entity.Result;
import com.example.doctor.project.entity.TiaoZhuan;
import com.fingerth.supdialogutils.SYSDiaLogUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DaTiAcitivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    int frist = 0;
    List<Answer> answerList = new ArrayList<>();
    ProblemAll problemAll;
    TextView danan1;
    TextView danan2;
    TextView danan3;
    LinearLayout ll1;
    Gson gson = new Gson();
    Result result;
    LinearLayout ll2;
    TextView jiexi;
    LinearLayout ll3;
    LinearLayout ll4;
    //已经做了的题目
    Problem problem = new Problem();
    ArrayList<Integer> a = new ArrayList<>();
    TextView danan4;
    LinearLayout pinglun;
    ImageView danana;
    ImageView dananb;
    ImageView dananc;
    TextView yeshu;
    TextView titletu;
    ImageView danand;
    List<Problem> list = new ArrayList<>();
    TextView wenti;
    TextView back;
    LinearLayout dibu4;
    Button submit;
    LinearLayout xia;
    LinearLayout shang;
    protected static final float FLIP_DISTANCE = 50;
    GestureDetector gestureDetector;
    long time = 2500;
    boolean shijian = true;
    private Handler handler;
    Button timebt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer);
        EventBus.getDefault().register(this);
        intiview();
//初始化答题页面
        Allshuju();
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 1) {
                    timebt.setText(formatLongToTimeStr(time--));
                }
                if (msg.what == 0) {
                    SYSDiaLogUtils.dismissProgress();
                    list.addAll(result.getRes());
                    problem = list.get(frist);
                    startView(problem);
                }
                super.handleMessage(msg);
            }
        };
        //初始化答题时间
        startTime();
        huaDong();

        //左右滑动监听
    }

    private void intiview() {
        pinglun=(LinearLayout)findViewById(R.id.pinglun);
        titletu = (TextView) findViewById(R.id.titletu);
        yeshu = (TextView) findViewById(R.id.yeshu);
        shang = (LinearLayout) findViewById(R.id.shang);
        submit = (Button) findViewById(R.id.submit);
        jiexi = (TextView) findViewById(R.id.jiexi);
        xia = (LinearLayout) findViewById(R.id.xia);
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
                show();
            }
        });
        timebt.setText(formatLongToTimeStr(time));
        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        ll3.setOnClickListener(this);
        submit.setOnClickListener(this);
        dibu4.setOnClickListener(this);
        pinglun.setOnClickListener(this);
        ll4.setOnClickListener(this);
        shang.setOnClickListener(this);
        xia.setOnClickListener(this);
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

                    return true;
                }
                if (e2.getX() - e1.getX() > FLIP_DISTANCE) {
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
            case R.id.xia:
                if (frist == list.size() - 1) {
                    SYSDiaLogUtils.showInfoDialog(this, "已经是最后一题了", "很抱歉", "确定", false);
                } else {
                    problem = list.get(++frist);
                    startView(problem);
                    panduan();
                }
                break;
            case R.id.shang:
                if (frist == 0) {
                    SYSDiaLogUtils.showInfoDialog(this, "已经是第一题了", "很抱歉", "确定", false);
                } else {
                    problem = list.get(--frist);
                    startView(problem);
                    panduan();
                }
                break;
            case R.id.submit:
                submit();

                break;
            case R.id.dibu4:
                Intent intent1 = new Intent(DaTiAcitivity.this, DaTiKa.class);
                intent1.putExtra("number", list.size());
                intent1.putIntegerArrayListExtra("shuzi", removeDuplicate(getA()));
                startActivity(intent1);
                break;
            case R.id.ll1:
                if (chazzhao()) {
                    for (int i = 0; i < answerList.size(); i++) {
                        if (answerList.get(i).getId() == problem.getId()) {
                            answerList.get(i).setAnswer("A");
                        }
                    }
                } else {
                    Answer answer = new Answer();
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
                if (chazzhao()) {
                    for (int i = 0; i < answerList.size(); i++) {
                        if (answerList.get(i).getId() == problem.getId()) {
                            answerList.get(i).setAnswer("B");
                        }
                    }
                } else {
                    Answer answer = new Answer();
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
                if (chazzhao()) {
                    for (int i = 0; i < answerList.size(); i++) {
                        if (answerList.get(i).getId() == problem.getId()) {
                            answerList.get(i).setAnswer("C");
                        }
                    }
                } else {
                    Answer answer = new Answer();
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
                if (chazzhao()) {
                    for (int i = 0; i < answerList.size(); i++) {
                        if (answerList.get(i).getId() == problem.getId()) {
                            answerList.get(i).setAnswer("D");
                        }
                    }
                } else {
                    Answer answer = new Answer();
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
            case R.id.pinglun:
                SYSDiaLogUtils.showInfoDialog(this, "操作提示", "考试结束后才能评论!", "确定", false);
                break;

        }
    }

    public void setbfg(TextView t2, TextView t3, TextView t4) {
        t2.setTextColor(Color.parseColor("#8a8a8a"));
        t4.setTextColor(Color.parseColor("#8a8a8a"));
        t3.setTextColor(Color.parseColor("#8a8a8a"));
    }

    public void quxiaodf() {
        danana.setImageResource(R.mipmap.aaa);
        dananb.setImageResource(R.mipmap.bbb);
        dananc.setImageResource(R.mipmap.ccc);
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
        yeshu.setText(String.valueOf(frist + 1) + "/" + String.valueOf(list.size()));
        titletu.setText(String.valueOf(frist + 1));
    }

    private boolean chazzhao() {
        for (Answer answer : answerList) {
            if (answer.getId() == problem.getId()) {
                return true;
            }
        }
        return false;
    }
//测试

    public ArrayList<Integer> getA() {
        for (Answer answer : answerList) {
            a.add(answer.getId());
        }
        return a;
    }

    private void Allshuju() {
        SYSDiaLogUtils.showProgressDialog(DaTiAcitivity.this, SYSDiaLogUtils.SYSDiaLogType.IosType, "加载中...", false, null);
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                RequestQueue queue = Volley.newRequestQueue(DaTiAcitivity.this);
                String url = "http://106.53.9.58:8080/default/hospital/com.primeton.eos.hospital.query.biz.ext";
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                System.out.println(response.toString());
                                result = gson.fromJson(response.toString(), Result.class);
                                handler.sendEmptyMessage(0);
                            }
                        }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(stringRequest);
            }
        }.start();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void change(TiaoZhuan tiaoZhuan) {
        frist = tiaoZhuan.getId();
        problem = list.get(tiaoZhuan.getId());
        startView(problem);
        panduan();

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setOut(Finish finish) {
        this.finish();
    }
    public void panduan() {
        boolean a = false;
        Answer answer1 = new Answer();
        for (Answer answer : answerList) {
            if (answer.getId() == problem.getId()) {
                answer1 = answer;
                a = true;
                break;
            }
        }
        if (a) {
            switch (answer1.getAnswer()) {
                case "A":
                    danan1.setTextColor(Color.parseColor("#1afa29"));
                    danana.setImageResource(R.mipmap.aaa1);
                    dananb.setImageResource(R.mipmap.bbb);
                    dananc.setImageResource(R.mipmap.ccc);
                    danand.setImageResource(R.mipmap.ddd);
                    setbfg(danan2, danan3, danan4);
                    break;
                case "B":
                    danan2.setTextColor(Color.parseColor("#1afa29"));
                    dananb.setImageResource(R.mipmap.bbb1);
                    setbfg(danan1, danan3, danan4);
                    danana.setImageResource(R.mipmap.aaa);
                    dananc.setImageResource(R.mipmap.ccc);
                    danand.setImageResource(R.mipmap.ddd);
                    break;
                case "C":
                    danan3.setTextColor(Color.parseColor("#1afa29"));
                    dananc.setImageResource(R.mipmap.ccc1);
                    setbfg(danan1, danan2, danan4);
                    dananb.setImageResource(R.mipmap.bbb);
                    danana.setImageResource(R.mipmap.aaa);
                    danand.setImageResource(R.mipmap.ddd);
                    break;
                case "D":
                    danan4.setTextColor(Color.parseColor("#1afa29"));
                    danand.setImageResource(R.mipmap.ddd1);
                    setbfg(danan1, danan3, danan2);
                    dananb.setImageResource(R.mipmap.bbb);
                    dananc.setImageResource(R.mipmap.ccc);
                    danana.setImageResource(R.mipmap.aaa);
                    break;
            }
        } else {
            quxiaodf();
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    private void show() {
        SYSDiaLogUtils.showConfirmDialog(this, true, SYSDiaLogUtils.SYSConfirmType.Tip, "你未完成考试", "确定要退出考试？", new SYSDiaLogUtils.ConfirmDialogListener() {
            @Override
            public void onClickButton(boolean clickLeft, boolean clickRight) {
                if (clickLeft) {
                    Toast.makeText(DaTiAcitivity.this, "考试继续", Toast.LENGTH_SHORT).show();
                } else if (clickRight) {
                    finish();
                }
            }
        });
    }
    private void submit() {
        if(answerList.size()!=list.size()){
            SYSDiaLogUtils.showInfoDialog(this, "提示", "你还有未完成的习题！", "确定", false);
        }else{
            SYSDiaLogUtils.showConfirmDialog(this, true, SYSDiaLogUtils.SYSConfirmType.Tip, "完成", "确定提交试卷？", new SYSDiaLogUtils.ConfirmDialogListener() {
                @Override
                public void onClickButton(boolean clickLeft, boolean clickRight) {
                    if (clickLeft) {
                    } else if (clickRight) {
                        //提交
                        System.out.println("问题的答案" + gson.toJson(answerList).toString());
                    }
                }
            });
        }
    }

}
