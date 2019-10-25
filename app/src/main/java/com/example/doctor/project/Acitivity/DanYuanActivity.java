package com.example.doctor.project.Acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.doctor.R;
import com.example.doctor.project.Adapter.DanYuanAdapter;
import com.example.doctor.project.entity.DaTiRqe;
import com.example.doctor.project.entity.IntentShi;
import com.example.doctor.project.entity.Result1;
import com.example.doctor.project.entity.Subject;
import com.fingerth.supdialogutils.SYSDiaLogUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class DanYuanActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    TextView textView;
    int a=200;
    int time=1;//记录点击时间 1能点击 0不能点击
    TextView back;
    Subject subject=new Subject();
    ListView listView;
    Gson gson=new Gson();
    Handler handler;
    DanYuanAdapter danYuanAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xuanzhe);
        intiview();
        int id3=getIntent().getIntExtra("id",0);//得到的单元id
        handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==0){

                    time=1;
                }
                if(msg.what==3){
                    SYSDiaLogUtils.dismissProgress();
                    danYuanAdapter=new DanYuanAdapter(DanYuanActivity.this,subject.getAllZhangjies());
                    listView.setAdapter(danYuanAdapter);
                }
            }
        };
        rtShuju(id3);


    }
    private void intiview(){
        listView=(ListView)findViewById(R.id.danyuanlist);
        textView=(TextView)findViewById(R.id.txt_main_title);
        back=(TextView)findViewById(R.id.txt_left_title);
        textView.setText(getIntent().getStringExtra("title"));
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
        TextView lolo=(TextView)view.findViewById(R.id.lolo);
        int id4=Integer.parseInt(lolo.getText().toString());
        if(panduan(i)){
            a=i;
            Intent intent1=new Intent(DanYuanActivity.this,DaTiAcitivity.class);
            intent1.putExtra("title1",getIntent().getStringExtra("title"));
            intent1.putExtra("id5",id4);
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
    public void rtShuju(final int id1) {
        SYSDiaLogUtils.showProgressDialog(DanYuanActivity.this, SYSDiaLogUtils.SYSDiaLogType.IosType, "请稍后...", false, null);
                com.example.doctor.project.entity.Request request=new com.example.doctor.project.entity.Request();
                request.setId(id1);
                String url = "http://106.53.9.58:8761/allzhangjie";
                RequestQueue requestQueue = Volley.newRequestQueue(DanYuanActivity.this);
                JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, gson.toJson(request).toString(),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                System.out.println("成功");
                                subject=gson.fromJson(response.toString(),Subject.class);
                                handler.sendEmptyMessage(3);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("失败");
                    }
                });
                requestQueue.add(jsonRequest);
            }

    @Override
    protected void onStart() {

        super.onStart();
    }
}
