package com.example.doctor.project.Acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.tu.loadingdialog.LoadingDailog;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arialyy.aria.core.Aria;
import com.example.doctor.R;
import com.example.doctor.project.Every.RemenberString;
import com.example.doctor.project.Interface.BookService;
import com.example.doctor.project.Untils.Remember;
import com.example.doctor.project.entity.Api;
import com.example.doctor.project.entity.User;
import com.fingerth.supdialogutils.SYSDiaLogUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    LoadingDailog.Builder loadBuilder;
    LoadingDailog dialog;
    private Handler handler = new Handler() {
        @Override
        //当有消息发送出来的时候就执行Handler的这个方法
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                System.out.println("进入主页");
                Intent intent = new Intent(MainActivity.this, ZhuYe.class);
                finish();
                startActivity(intent);
                SYSDiaLogUtils.dismissProgress();
            }
            if (msg.what == 1) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                finish();
                startActivity(intent);
                //进入登录页面
            }
            //只要执行到这里就关闭对话框
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        processThread();
        //首先先判断是否之前登录 如果没有登录 跳转到登录 如果登录勒 自动登录
        if (Remember.getString(RemenberString.loginuser, RemenberString.login, "", MainActivity.this).equals("123456")) {
            //转圈圈登录
            SYSDiaLogUtils.showProgressDialog(MainActivity.this, SYSDiaLogUtils.SYSDiaLogType.IosType, "登陆中...", false, null);

//            raitThe();
            enterZhuYe();
        } else {
            login();
            //进入登录页面
        }
    }

    private void processThread() {

        //构建一个下载进度条
        loadBuilder = new LoadingDailog.Builder(this)
                .setMessage("登录中...")
                .setCancelable(true)
                .setCancelOutside(true);
        dialog = loadBuilder.create();

    }

    public void raitThe() {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException r) {
                    r.printStackTrace();
                }
                Retrofit retrofit1 = new Retrofit.Builder()
                        .baseUrl(Api.api)//设置网络请求url，后面一段写在网络请求接口里面
                        .addConverterFactory(GsonConverterFactory.create())//添加Gson支持，然后Retrofit就会使用Gson将响应体（api接口的Take）转换我们想要的类型。
                        .build();
                BookService bookService1 = retrofit1.create(BookService.class);
                User user = new User();
                user.setName(Remember.getString(RemenberString.loginuser, RemenberString.login, "", MainActivity.this));
                user.setPassword(Remember.getString(RemenberString.loginpw, RemenberString.login, "", MainActivity.this));
                Call<User> call = bookService1.getBook12(user);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        String name = response.body().getName();
                        if (name.equals("yuansong")) {
                            handler.sendEmptyMessage(0);
                        } else {
                            Toast.makeText(MainActivity.this, "你的密码以及用户不对劲", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        System.out.println("请求失败" + t.toString());
                    }
                });
            }
        }.start();
//        if (preferences.getString("wangyan", "djkdjkddkdk").equals("yss")) {
//            Intent intent = new Intent(MainActivity.this, ZhuYe.class);
//            startActivity(intent);
//            finish();
//            handler.sendEmptyMessage(0);
//        }
    }

    public void login() {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(1);
            }
        }.start();
    }

    public void enterZhuYe() {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
        }.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

//    private void showStu() {
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url = "http://106.53.9.58:8080/default/hospital/com.primeton.eos.hospital.test.biz.ext";
//
//        // Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new    com.android.volley.Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        System.out.println(response.toString());
//                    }
//                }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        queue.add(stringRequest);
//    }
}
