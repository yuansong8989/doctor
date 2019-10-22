package com.example.myapplication3.view.activity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication3.Adapter.XuanZheAdapter;
import com.example.myapplication3.Interface.BookService;
import com.example.myapplication3.R;
import com.example.myapplication3.entity.User;
import com.example.myapplication3.entity.XuanZhe;
import com.example.myapplication3.service.BindService;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CeHua extends AppCompatActivity implements SensorEventListener {
    BindService.MyBinder myBinder;
    boolean quit=false;
    Integer cont=0;
    View contentView;
    PopupWindow popupWindow;
    private TextView txt_show;
    XuanZheAdapter xuanZheAdapter;
    SensorManager sm;
     Sensor sensor;
     Vibrator vibrator;
     ListView listView;
     List<String> a =new ArrayList<>();

     boolean yaodong=false;
    private ServiceConnection conn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            System.out.println("service connected");
            myBinder=(BindService.MyBinder)iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            System.out.println("service disconnect");
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yuwen);
//        Intent intent1=getIntent();
//        User user=(User)intent1.getSerializableExtra("user");
//        System.out.println("我登记"+user.getName());
        listView=(ListView)findViewById(R.id.list7);
        getList();
        vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
//        Button button=(Button)findViewById(R.id.bunt);
//        Button button1=(Button)findViewById(R.id.bunt1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool1);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("语文（Chinese）");
        toolbar.setSubtitle("年轻少读书，毕业就养猪");
        toolbar.setNavigationIcon(R.drawable.xjt);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_edit:
                        Toast.makeText(CeHua.this, "查找按钮", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_share:
                        Toast.makeText(CeHua.this, "分享按钮", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_settings:
                        Toast.makeText(CeHua.this, "设置", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_settings1:
                        Toast.makeText(CeHua.this, "设置1", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }
//        final TextView textView=(TextView)findViewById(R.id.yuwen);
//        final Intent intent=new Intent(this,BindService.class);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                quit=false;
//                unbindService(conn);
//            }
//        });
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                System.out.println("点击啊");
//                quit=true;
//                bindService(intent,conn,BIND_AUTO_CREATE);
//                final Handler handler =new Handler(){
//                    @Override
//                    public void handleMessage(@NonNull Message msg) {
//                        if(msg.what==0x1233){
//                            System.out.println(cont);
//                            textView.setText(cont.toString());
//                        }
////                        super.handleMessage(msg);
//                    }
//                };
//                if(quit){
//                    new Thread(){
//                        @Override
//                        public void run() {
//                            while (quit){
//                                try{
//                                    Thread.sleep(1000);
//                                }catch (InterruptedException e){
//
//                                }
//                                cont+=myBinder.getCount();
//                                handler.sendEmptyMessage(0x1233);
//                            }
//                        }
//                    }.start();
//                }
//            }
//        });
//    }
    private void showPopwindow() {
        //加载弹出框的布局
        contentView= LayoutInflater.from(CeHua.this).inflate(
                R.layout.pop2, null);
        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
//        popupWindow.setFocusable(true);// 取得焦点
        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //点击外部消失
        popupWindow.setOutsideTouchable(false);
        //设置可以点击
        popupWindow.setTouchable(true);
        //进入退出的动画，指定刚才定义的style
        popupWindow.setAnimationStyle(R.style.anim_menu_bottombar);
        // 按下android回退物理键 PopipWindow消失解决
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorType = event.sensor.getType();
        //values[0]:X轴，values[1]：Y轴，values[2]：Z轴
        float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];
//        System.out.println("怎么说");
        if(!yaodong){
            if (sensorType == Sensor.TYPE_ACCELEROMETER) {
                int value = 15;//摇一摇阀值,不同手机能达到的最大值不同,如某品牌手机只能达到20
                if (x >= value || x <= -value || y >= value || y <= -value || z >= value || z <= -value) {
                    System.out.println("在摇动");
                    vibrator.vibrate(500);
                    yaodong=true;
                    //画面弹出
                    popupWindow.showAtLocation(contentView, Gravity.CENTER, 0, 0);
                    final ImageView imageView=(ImageView)contentView.findViewById(R.id.imh1);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            yaodong= false;
                            popupWindow.dismiss();
                        }
                    });
                }
        }

        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
        super.onResume();
    }

    @Override
    protected void onStart() {
        showPopwindow();
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        System.out.println("摧毁");
        sm.registerListener(this, sensor, android.hardware.SensorManager.SENSOR_DELAY_UI);
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        System.out.println("暂停");
        super.onPause();
    }
    //json
    Handler handler;
    public void getList(){
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl("http://10.9.5.9:8761/")//设置网络请求url，后面一段写在网络请求接口里面
                .addConverterFactory(GsonConverterFactory.create())//添加Gson支持，然后Retrofit就会使用Gson将响应体（api接口的Take）转换我们想要的类型。
                .build();
        BookService bookService1 = retrofit1.create(BookService.class);
        Call<List<XuanZhe>> call = bookService1.getYuWen();
        call.enqueue(new Callback<List<XuanZhe>>() {
            @Override
            public void onResponse(Call<List<XuanZhe>> call, Response<List<XuanZhe>> response) {
                System.out.println("请求求成功"+response.body().get(1).getTitle());
                List<XuanZhe> xuanZhes=new ArrayList<>();
                xuanZhes=response.body();
                xuanZheAdapter=new XuanZheAdapter(CeHua.this,xuanZhes);
                System.out.println("这儿多长"+xuanZhes.size());
                listView.setAdapter(xuanZheAdapter);
                Button button=(Button)findViewById(R.id.dj);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        showNormalDialog();
                    }
                });

            }
            @Override
            public void onFailure(Call<List<XuanZhe>> call, Throwable t) {
                System.out.println("我的错误"+t.toString());
            }
        });
    }
    private void showNormalDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(CeHua.this);
        normalDialog.setIcon(R.drawable.vd);
        normalDialog.setTitle("确定提交吗");
        normalDialog.setMessage("提交后是不能修改的?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getdaan(xuanZheAdapter.getString());
                        handler =new Handler(){
                            @Override
                            public void handleMessage(@NonNull Message msg) {
                                List<String> stringList=new ArrayList<>();
                                stringList=(List<String>) msg.obj;
                                System.out.println("我爱你"+stringList.get(0));
                                xuanZheAdapter.setzhengque(stringList);
                                xuanZheAdapter.notifyDataSetChanged();
                            }
                        };
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }
    public  void getdaan(final String a[]){
        new Thread(){
            @Override
            public void run() {
                Retrofit retrofit1 = new Retrofit.Builder()
                        .baseUrl("http://10.9.5.9:8761/")//设置网络请求url，后面一段写在网络请求接口里面
                        .addConverterFactory(GsonConverterFactory.create())//添加Gson支持，然后Retrofit就会使用Gson将响应体（api接口的Take）转换我们想要的类型。
                        .build();
                BookService bookService1 = retrofit1.create(BookService.class);
                User user=new User();
                user.setAnswer(a);
                Call<List<String>> call = bookService1.getAnswer(user);
                call.enqueue(new Callback<List<String>>() {
                    @Override
                    public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                        List<String> list3=response.body();
                        Message message=new Message();
                        message.obj=list3;
//                        Bundle bundle=new Bundle();
//                        String b[]=(String [])list3.toArray();
//                        bundle.putStringArray("daan",b);
//                        message.setData(bundle);
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onFailure(Call<List<String>> call, Throwable t) {

                    }
                });
            }
        }.start();
    }
}
