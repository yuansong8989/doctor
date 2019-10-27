package com.example.doctor.project.Acitivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doctor.R;
import com.example.doctor.project.Adapter.DanYuanAdapter;
import com.example.doctor.project.Interface.BookService;
import com.example.doctor.project.ToMeView.MoveImageView;
import com.example.doctor.project.entity.Answer;
import com.example.doctor.project.entity.DaTiRqe;
import com.example.doctor.project.entity.Event;
import com.example.doctor.project.entity.Finish;
import com.example.doctor.project.entity.HuiFu;
import com.example.doctor.project.entity.IntentShi;
import com.example.doctor.project.entity.Problem;
import com.example.doctor.project.entity.ProblemAll;
import com.example.doctor.project.entity.Result;
import com.example.doctor.project.entity.Result1;
import com.example.doctor.project.entity.Subject;
import com.example.doctor.project.entity.TiaoZhuan;
import com.fingerth.supdialogutils.SYSDiaLogUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DaTiAcitivity extends AppCompatActivity implements View.OnClickListener {
    int zhuangtai = 0; //0可以代表可做 1代表不能做
    TextView textView;
    int frist = 0;
    boolean jjj = false;
    TextView success;
    Result1 result1;
    MoveImageView move;
    List<Answer> answerList = new ArrayList<>();
    ProblemAll problemAll;
    TextView danan1;
    TextView danan2;
    TextView danan3;
    LinearLayout ll1;
    Gson gson = new Gson();
    TextView t1;
    TextView t3;
    TextView t4;
    TextView t5;
    ImageView n1;
    ImageView n3;
    ImageView n4;
    ImageView n5;
    Result result=new Result();
    Subject subject=new Subject();
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
    LinearLayout geini;
    Button submit;
    LinearLayout xia;
    LinearLayout shang;
    protected static final float FLIP_DISTANCE = 50;
    long frirstrime = 0;
    long time = 2000;
    boolean shijian = true;
    private Handler handler;
    Button timebt;
    int tupian = 0;
    TextView kkk;
    int id3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer);
        //代表已经做了
        id3=getIntent().getIntExtra("id5",0);//得到的章节id 把它显示出来
        EventBus.getDefault().register(this);
        intiview();
        anxia();
        if (zhuangtai == 1) {
            ll1.setEnabled(false);
            ll2.setEnabled(false);
            ll3.setEnabled(false);
            submit.setEnabled(false);
            ll4.setEnabled(false);
            timebt.setText(formatLongToTimeStr(frirstrime));
            submit.setBackground(getResources().getDrawable(R.drawable.jinyong));
            kkk.setText("已经完成");
            kkk.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else {
            //初始化答题时间
            startTime();
        }
//初始化答题页面
        Allshuju();
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 2) {
                    SYSDiaLogUtils.dismissProgress();
                    Intent intent1 = new Intent(DaTiAcitivity.this, DaTiKa.class);
                    intent1.putExtra("result", result1);
                    startActivity(intent1);
                    //这里跳转答题卡页面显示全部的 需要把值全部传到答题卡中
                }
                if (msg.what == 1) {
                    timebt.setText(formatLongToTimeStr(time--));
                    if (time == 0) {
                        mustSubmit();
                    }
                }
                if (msg.what == 0) {
                    SYSDiaLogUtils.dismissProgress();
                    list.addAll(subject.getProblems());
                    problem = list.get(frist);
                    startView(problem);
                    chushi(list, answerList);
                }
                super.handleMessage(msg);
            }
        };


        //左右滑动监听
    }

    private void intiview() {
        move = (MoveImageView) findViewById(R.id.move);
        geini = (LinearLayout) findViewById(R.id.geini);
        geini.setVisibility(View.GONE);
        kkk = (TextView) findViewById(R.id.kkk);
        success = (TextView) findViewById(R.id.success);
        pinglun = (LinearLayout) findViewById(R.id.pinglun);
        titletu = (TextView) findViewById(R.id.titletu);
        yeshu = (TextView) findViewById(R.id.yeshu);
        shang = (LinearLayout) findViewById(R.id.shang);
        submit = (Button) findViewById(R.id.submit);
        jiexi = (TextView) findViewById(R.id.jiexi);
        xia = (LinearLayout) findViewById(R.id.xia);
        dibu4 = (LinearLayout) findViewById(R.id.dibu4);
        t1=(TextView)findViewById(R.id.t1);
        t3=(TextView)findViewById(R.id.t3);
        t4=(TextView)findViewById(R.id.t4);
        t5=(TextView)findViewById(R.id.t5);
        n1=(ImageView)findViewById(R.id.n1);
        n3=(ImageView)findViewById(R.id.n3);
        n4=(ImageView)findViewById(R.id.n4);
        n5=(ImageView)findViewById(R.id.n5);
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.xia:
                if (frist == list.size() - 1) {
                    SYSDiaLogUtils.showInfoDialog(this, "已经是最后一题了", "很抱歉", "确定", false);
                } else {
                    problem = list.get(++frist);
                    startView(problem);
                    panduan();
                    tupain();
                }
                break;
            case R.id.shang:
                if (frist == 0) {
                    SYSDiaLogUtils.showInfoDialog(this, "已经是第一题了", "很抱歉", "确定", false);
                } else {
                    problem = list.get(--frist);
                    startView(problem);
                    panduan();
                    tupain();
                }
                break;
            case R.id.submit:
                submit.setEnabled(false);
                submit();
                break;
            case R.id.dibu4:
                if (zhuangtai == 0) {

                    Intent intent1 = new Intent(DaTiAcitivity.this, DaTiKa.class);
                    intent1.putExtra("number", list.size());
                    intent1.putIntegerArrayListExtra("shuzi", removeDuplicate(getA()));
                    startActivity(intent1);
                } else {
                    submit();
                }
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
                if (frist == list.size() - 1) {
                    SYSDiaLogUtils.showInfoDialog(this, "已经是最后一题了", "很抱歉", "确定", false);
                } else {
                    problem = list.get(++frist);
                    startView(problem);
                    panduan();
                    tupain();
                }
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
                if (frist == list.size() - 1) {
                    SYSDiaLogUtils.showInfoDialog(this, "已经是最后一题了", "很抱歉", "确定", false);
                } else {
                    problem = list.get(++frist);
                    startView(problem);
                    panduan();
                    tupain();
                }
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
                if (frist == list.size() - 1) {
                    SYSDiaLogUtils.showInfoDialog(this, "已经是最后一题了", "很抱歉", "确定", false);
                } else {
                    problem = list.get(++frist);
                    startView(problem);
                    panduan();
                    tupain();
                }
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
                if (frist == list.size() - 1) {
                    SYSDiaLogUtils.showInfoDialog(this, "已经是最后一题了", "很抱歉", "确定", false);
                } else {
                    problem = list.get(++frist);
                    startView(problem);
                    panduan();
                    tupain();
                }
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
        success.setText(problem.getSuccess());
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
        for(int i=0;i<answerList.size();i++){
            if(!answerList.get(i).getAnswer().equals("")){
                if(!a.contains(i)){
                    a.add(i);
                }

            }
        }
        return a;
    }
public void Allshuju(){
    SYSDiaLogUtils.showProgressDialog(DaTiAcitivity.this, SYSDiaLogUtils.SYSDiaLogType.IosType, "请稍后...", false, null);
    new Thread(){
            @Override
            public void run() {
                System.out.println("s输了"+id3);
                com.example.doctor.project.entity.Request request=new com.example.doctor.project.entity.Request();
                request.setId(id3);
                String url = "http://106.53.9.58:8761/alltitle";
                RequestQueue requestQueue = Volley.newRequestQueue(DaTiAcitivity.this);
                JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, gson.toJson(request).toString(),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                System.out.println(response.toString());
                                subject=gson.fromJson(response.toString(), Subject.class);
                                handler.sendEmptyMessage(0);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQueue.add(jsonRequest);
            }
        }.start();

        }

//    private void Allshuju() {
//        SYSDiaLogUtils.showProgressDialog(DaTiAcitivity.this, SYSDiaLogUtils.SYSDiaLogType.IosType, "加载中...", false, null);
//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                RequestQueue queue = Volley.newRequestQueue(DaTiAcitivity.this);
//                String url = "http://106.53.9.58:8080/default/hospital/com.primeton.eos.hospital.query.biz.ext";
//                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                        new com.android.volley.Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                System.out.println(response.toString());
//                                result = gson.fromJson(response.toString(), Result.class);
//                                handler.sendEmptyMessage(0);
//                            }
//                        }, new com.android.volley.Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                });
//                queue.add(stringRequest);
//            }
//        }.start();
//    }

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
                default:
                    quxiaodf();

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
        if (zhuangtai == 0) {
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
        } else {
            SYSDiaLogUtils.showConfirmDialog(this, true, SYSDiaLogUtils.SYSConfirmType.Tip, "你确定不在看看吗", "确定？", new SYSDiaLogUtils.ConfirmDialogListener() {
                @Override
                public void onClickButton(boolean clickLeft, boolean clickRight) {
                    if (clickLeft) {
                        Toast.makeText(DaTiAcitivity.this, "加油", Toast.LENGTH_SHORT).show();
                    } else if (clickRight) {
                        finish();
                    }
                }
            });
        }

    }

    private void submit() {
        if (answerList.size() != list.size()) {
            SYSDiaLogUtils.showConfirmDialog(this, true, SYSDiaLogUtils.SYSConfirmType.Tip, "警告", "你还有未完成的习题，确定提交？", new SYSDiaLogUtils.ConfirmDialogListener() {
                @Override
                public void onClickButton(boolean clickLeft, boolean clickRight) {
                    if (clickLeft) {
                    } else if (clickRight) {
                        //提交
                        rtShuju();
                    }
                }
            });
        } else {
            //提交
            rtShuju();

        }
    }

    public void rtShuju() {
        SYSDiaLogUtils.showProgressDialog(DaTiAcitivity.this, SYSDiaLogUtils.SYSDiaLogType.IosType, "请稍后...", false, null);
        new Thread() {
            @Override
            public void run() {
                super.run();
                DaTiRqe daTiRqe = new DaTiRqe();
                daTiRqe.setList(answerList);
                String url = "http://106.53.9.58:8080/default/hospital/com.primeton.eos.hospital.test.biz.ext";
                RequestQueue requestQueue = Volley.newRequestQueue(DaTiAcitivity.this);
                System.out.println(gson.toJson(daTiRqe));
                JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, gson.toJson(daTiRqe).toString(),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                EventBus.getDefault().post(new IntentShi(gson.fromJson(response.toString(), Result1.class)));

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQueue.add(jsonRequest);
            }
        }.start();

    }

    public void mustSubmit() {
        SYSDiaLogUtils.showProgressDialog(DaTiAcitivity.this, SYSDiaLogUtils.SYSDiaLogType.IosType, "时间到，交卷...", false, null);
    }

    //初始化
    public void chushi(List<Problem> problems, List<Answer> answers) {
        for (Problem problem : problems) {
            Answer answer = new Answer();
            answer.setId(problem.getId());
            answer.setAnswer("");
            answers.add(answer);
        }
    }

    public void getdaan() {
        new Thread() {
            @Override
            public void run() {
                Retrofit retrofit1 = new Retrofit.Builder()
                        .baseUrl("http://10.9.5.9:8761/")//设置网络请求url，后面一段写在网络请求接口里面
                        .addConverterFactory(GsonConverterFactory.create())//添加Gson支持，然后Retrofit就会使用Gson将响应体（api接口的Take）转换我们想要的类型。
                        .build();
                BookService bookService1 = retrofit1.create(BookService.class);
                DaTiRqe daTiRqe = new DaTiRqe();
                daTiRqe.setList(answerList);
                Call<Result1> call = bookService1.check(daTiRqe);
                call.enqueue(new Callback<Result1>() {
                    @Override
                    public void onResponse(Call<Result1> call, retrofit2.Response<Result1> response) {
                        Result1 result1 = response.body();

                    }

                    @Override
                    public void onFailure(Call<Result1> call, Throwable t) {

                    }
                });
            }
        }.start();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dhdj(IntentShi intentShi) {
        result1 = intentShi.getResult1();
        handler.sendEmptyMessage(2);
    }

    //恢复
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void ddada(HuiFu huiFu) {
        jjj = true;
        zhuangtai = 1;
        tupain();
        geini.setVisibility(View.VISIBLE);
        ll1.setEnabled(false);
        ll2.setEnabled(false);
        ll3.setEnabled(false);
        ll4.setEnabled(false);
        timebt.setText(formatLongToTimeStr(frirstrime));
        submit.setBackground(getResources().getDrawable(R.drawable.jinyong));
        kkk.setText("已经完成");
        kkk.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

    }

    private void tupain() {
        if (jjj) {
            if (result1.getPer().get(frist).getCheck().equals("true")) {
                move.setBackgroundResource(R.mipmap.dui);
            } else {
                move.setBackgroundResource(R.mipmap.cuo);
            }
        }
    }
public void anxia(){
    pinglun.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == MotionEvent.ACTION_DOWN) {
                // 按下  
                t1.setTextColor(Color.parseColor("#1296db"));
                n1.setImageResource(R.mipmap.biji1);
            } else if (action == MotionEvent.ACTION_UP) { // 松开  
                t1.setTextColor(Color.parseColor("#8a8a8a"));
                n1.setImageResource(R.mipmap.biji);//  
            }
            return false;
        }
    });
    xia.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == MotionEvent.ACTION_DOWN) {
                // 按下  
                t4.setTextColor(Color.parseColor("#1296db"));
                n4.setImageResource(R.mipmap.xia1);
            } else if (action == MotionEvent.ACTION_UP) { // 松开   
                t4.setTextColor(Color.parseColor("#8a8a8a"));
                n4.setImageResource(R.mipmap.xia);//  
            }
            return false;
        }
    });
    shang.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == MotionEvent.ACTION_DOWN) {
                // 按下  
                t3.setTextColor(Color.parseColor("#1296db"));
                n3.setImageResource(R.mipmap.shang1);
            } else if (action == MotionEvent.ACTION_UP) { // 松开
                t3.setTextColor(Color.parseColor("#8a8a8a"));
                n3.setImageResource(R.mipmap.shang);//  //   
            }
            return false;
        }
    });
        dibu4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    // 按下  
                    t5.setTextColor(Color.parseColor("#1296db"));
                    n5.setImageResource(R.mipmap.datika1);
                } else if (action == MotionEvent.ACTION_UP) { // 松开   
                    t5.setTextColor(Color.parseColor("#8a8a8a"));
                    n5.setImageResource(R.mipmap.datika);//  
                }
                return false;
            }
        });
    }
}
