package com.example.myapplication3.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.tu.loadingdialog.LoadingDailog;
import com.example.myapplication3.Interface.BookService;
import com.example.myapplication3.MyService;
import com.example.myapplication3.NetworkReceiver;
import com.example.myapplication3.R;
import com.example.myapplication3.SecondActivity;
import com.example.myapplication3.Utils.Remember;
import com.example.myapplication3.entity.User;
import com.example.myapplication3.presenter.impl.MainAPresenterImpl;
import com.example.myapplication3.presenter.inter.IMainAPresenter;
import com.example.myapplication3.view.inter.IMainAView;
import com.fingerth.supdialogutils.SYSDiaLogUtils;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements IMainAView, GestureDetector.OnGestureListener {
    private String title;
    private IMainAPresenter mIMainAPresenter;
    String API = "http://10.9.5.9:8761/";
    Retrofit retrofit = new Retrofit.Builder().baseUrl(API).build();
    BookService bookService = retrofit.create(BookService.class);
    private NetworkChangeReceiver networkChangeReceiver;
    GestureDetector detector;
    SharedPreferences preferences;
    LoadingDailog.Builder loadBuilder;
    LoadingDailog dialog;
    private Handler handler = new Handler() {
        @Override
        //当有消息发送出来的时候就执行Handler的这个方法
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                Intent intent = new Intent(MainActivity.this, ZhuYe.class);
                finish();
                startActivity(intent);
                dialog.dismiss();
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
        setContentView(R.layout.login);
        SYSDiaLogUtils.showSuccessDialog(this, false);
        processThread();
        preferences = getSharedPreferences("login", MODE_PRIVATE);
        System.out.println(Remember.getString("jjjj", "frist_sp", "不知道", MainActivity.this));
        detector = new GestureDetector(this, this);
        mIMainAPresenter = new MainAPresenterImpl(this);
        getNework();
        //注册网络变化的广播接收器
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        if (!Remember.getString("username","login-user","",MainActivity.this).equals("")) {//证明不是第一次登录
            dialog.show();
            raitThe();
        }
    }

    @Override
    public void finish() {
        overridePendingTransition(R.anim.traslate_animation, R.anim.traslate_animation);
        System.out.println("退出activity");
        super.finish();
    }

    @Override
    public <T> T request(int requestFlag) {
        return null;
    }

    @Override
    public <T> void response(T response, int responseFlag) {

    }

    public void loginSuccess(View view) {
//        EditText editText=(EditText) findViewById(R.id.edit) ;
//        String name =editText.getText().toString();
//        ImageButton sButton = (ImageButton) findViewById(R.id.imageButton);
//        sButton.setVisibility(View.GONE);
//        Toast.makeText(this,name,Toast.LENGTH_SHORT).show();
        EditText nameText = (EditText) findViewById(R.id.name);
        EditText passwordText = (EditText) findViewById(R.id.password);
        String name = nameText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        Remember.putString("username","ys","login-user",MainActivity.this);
        Remember.putString("password","123","login-pw",MainActivity.this);
        Intent intent = new Intent(MainActivity.this, ZhuYe.class);
        intent.putExtras(bundle);
        finish();
        startActivity(intent);
//        overridePendingTransition(R.anim.traslate_animation,R.anim.traslate_animation);
//        Login(user);
//        if (Login(user)) {
//            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//            startActivity(intent);
//        } else {
//            Toast.makeText(this, "你的密码以及用户不对劲", Toast.LENGTH_SHORT).show();
//        }
//        if (!name.isEmpty() && !password.isEmpty()) {
//            mIMainAPresenter.loginCheck(name, password);
//        } else {
//            Toast.makeText(this, "用户名或者密码不能为空，妹妹", Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    public void login() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }

    @Override
    public void fail() {
        Toast.makeText(this, "登录密码以及账户名错误仔细检查", Toast.LENGTH_SHORT).show();
    }

    public void startService(View view) {
        startService(new Intent(getBaseContext(), MyService.class));

    }

    public void stopService(View view) {
//        stopService(new Intent(getBaseContext(), MyService.class));
        Intent intent = new Intent();
        intent.setAction("com.example.CUSTOM_INTENT");
        sendBroadcast(intent);
    }

    private class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "网络可用", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "网络不可用", Toast.LENGTH_SHORT).show();
            }
        }

    }

    //get方式
    private void getNework() {
        Call<ResponseBody> call = bookService.getBook11(1);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String s = response.body().string();
                    TextView textView = (TextView) findViewById(R.id.uuu);
                    textView.setText(s);
                    System.out.println("成功" + s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("报错" + t.toString());
            }
        });
    }

    Boolean d = false;

    //post方式
    public Boolean Login(User user) {
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl("http://10.9.5.9:8761/")//设置网络请求url，后面一段写在网络请求接口里面
                .addConverterFactory(GsonConverterFactory.create())//添加Gson支持，然后Retrofit就会使用Gson将响应体（api接口的Take）转换我们想要的类型。
                .build();
        BookService bookService1 = retrofit1.create(BookService.class);
        Call<User> call = bookService1.getBook12(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                String name = response.body().getName();
                if (name.equals("yuansong")) {
                    Intent intent = new Intent(MainActivity.this, Study3_Activity.class);
                    finish();
                    startActivity(intent);
                    overridePendingTransition(R.anim.traslate_animation, R.anim.traslate_animation);
                } else {
                    Toast.makeText(MainActivity.this, "你的密码以及用户不对劲", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("请求失败" + t.toString());
            }
        });
        return d;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        System.out.println("按下按下");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        System.out.println("滚动");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println("首先调用dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
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
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                }catch (InterruptedException r){
                    r.printStackTrace();
                }
                Retrofit retrofit1 = new Retrofit.Builder()
                        .baseUrl("http://10.9.5.9:8761/")//设置网络请求url，后面一段写在网络请求接口里面
                        .addConverterFactory(GsonConverterFactory.create())//添加Gson支持，然后Retrofit就会使用Gson将响应体（api接口的Take）转换我们想要的类型。
                        .build();
                BookService bookService1 = retrofit1.create(BookService.class);
                User user=new User();
                user.setName(Remember.getString("username","login-user","",MainActivity.this));
                user.setPassword(Remember.getString("password","login-pw","",MainActivity.this));
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
}