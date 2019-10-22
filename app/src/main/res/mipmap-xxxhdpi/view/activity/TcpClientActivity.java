package com.example.myapplication3.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alipay.sdk.app.EnvUtils;
import com.example.myapplication3.Adapter.LiaoTianAdpter;
import com.example.myapplication3.Fragment.Support_Fr_1;
import com.example.myapplication3.Fragment.Support_Fr_2;
import com.example.myapplication3.Fragment.Support_Fr_3;
import com.example.myapplication3.Fragment.Support_Fr_4;
import com.example.myapplication3.R;
import com.example.myapplication3.Socket.TcpClientThread;
import com.example.myapplication3.Socket.TcpServerThread;
import com.example.myapplication3.ToMeView.Rbotton;
import com.example.myapplication3.entity.Event;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class TcpClientActivity extends SupportActivity
        {
    Rbotton rbotton4;
    Rbotton rbotton3;
    Rbotton rbotton2;
    Rbotton rbotton1;
    TextView mTxtContent;


    int position = 1;
    LiaoTianAdpter liaoTianAdpter;
    public static final int HOME = 0;
    public static final int CLASSES = 1;
    public static final int SCHOOLYARD = 2;
    public static final int ATTENDANCE = 3;
    private SupportFragment[] mFragments = new SupportFragment[4];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcpclient);
        initView();
        mFragments[HOME] = Support_Fr_1.newInstance();
        mFragments[CLASSES] = Support_Fr_2.newInstance();
        mFragments[SCHOOLYARD] = Support_Fr_3.newInstance();
        mFragments[ATTENDANCE] = Support_Fr_4.newInstance();
        //装载
        loadRootFragment(R.id.fl_container, mFragments[HOME]);
//        loadMultipleRootFragment(R.id.fl_container, HOME,
////                mFragments[HOME],
////                mFragments[CLASSES],
////                mFragments[SCHOOLYARD],
////                mFragments[ATTENDANCE]);

        rbotton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("ddbd");
                startWithPop(Support_Fr_4.newInstance());
                position=4;
                EventBus.getDefault().post(new Event());
            }
        });
        rbotton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("ddbd");
                startWithPop(Support_Fr_3.newInstance());
                position=3;
                EventBus.getDefault().post(new Event());
            }
        });
        rbotton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("ddbd");
                startWithPop(Support_Fr_2.newInstance());
                position=2;
                EventBus.getDefault().post(new Event());
            }
        });
        rbotton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("ddbd");
                startWithPop(Support_Fr_1.newInstance());
                position=1;
                EventBus.getDefault().post(new Event());
            }
        });
//        start(Support_Fr_4.newInstance());
        EventBus.getDefault().register(this);

    }

    private void initView() {
        rbotton4 = (Rbotton) findViewById(R.id.kkkk4);
        rbotton3 = (Rbotton) findViewById(R.id.kkkk3);
        rbotton2 = (Rbotton) findViewById(R.id.kkkk2);
        rbotton1 = (Rbotton) findViewById(R.id.kkkk1);
        rbotton1.set2Text("聊天互动");
        rbotton2.set2Text("班级学生");
        rbotton3.set2Text("教师电话");
        rbotton4.set2Text("学生须知");
        rbotton1.setBackgroundColor(Color.GREEN);
        rbotton2.setBackgroundColor(Color.WHITE);
        rbotton3.setBackgroundColor(Color.WHITE);
        rbotton4.setBackgroundColor(Color.WHITE);

    }


    /**
     * Handler
     */


//修改ui eventbus

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Event event) {
        System.out.println("这是一个美丽的夜晚11111");
        if (position == 1) {
            rbotton1.setBackgroundColor(Color.GREEN);
            rbotton2.setBackgroundColor(Color.WHITE);
            rbotton3.setBackgroundColor(Color.WHITE);
            rbotton4.setBackgroundColor(Color.WHITE);
        }
        if (position == 2) {
            rbotton1.setBackgroundColor(Color.WHITE);
            rbotton2.setBackgroundColor(Color.GREEN);
            rbotton3.setBackgroundColor(Color.WHITE);
            rbotton4.setBackgroundColor(Color.WHITE);
        }
        if (position == 3) {
            rbotton1.setBackgroundColor(Color.WHITE);
            rbotton2.setBackgroundColor(Color.WHITE);
            rbotton3.setBackgroundColor(Color.GREEN);
            rbotton4.setBackgroundColor(Color.WHITE);
        }
        if (position == 4) {
            rbotton1.setBackgroundColor(Color.WHITE);
            rbotton2.setBackgroundColor(Color.WHITE);
            rbotton3.setBackgroundColor(Color.WHITE);
            rbotton4.setBackgroundColor(Color.GREEN);
        }
    }


    public static void main(String[] args) {
        try {
            @SuppressWarnings("resource")
            ServerSocket serverSocket = new ServerSocket(12345);
            while (true) {
                //一条消息发送进来 然后发送出去给每一个人
                System.out.println("Server开始~~~监听~~~");
                // accept方法会阻塞，直到有客户端与之建立连接
                Socket socket = serverSocket.accept();
                TcpServerThread serverThread = new TcpServerThread(socket);
                serverThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        System.out.println("消失的fragment");
        return super.onCreateFragmentAnimator();
    }
}
