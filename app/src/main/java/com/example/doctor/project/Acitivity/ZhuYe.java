package com.example.doctor.project.Acitivity;

import android.content.Context;
import android.graphics.Color;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.doctor.R;
import com.example.doctor.project.Adapter.GridViewAdapter;
import com.example.doctor.project.Adapter.ListFirstAdapter;
import com.example.doctor.project.Fragment.Support_Fr_1;
import com.example.doctor.project.Fragment.Support_Fr_2;
import com.example.doctor.project.Fragment.Support_Fr_3;
import com.example.doctor.project.Fragment.Support_Fr_4;
import com.example.doctor.project.Fragment.Support_Fr_5;

import com.example.doctor.project.entity.Event;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;

public class ZhuYe extends SupportActivity implements  View.OnClickListener {
    int position=1;//改变底部颜色
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    LinearLayout l1;
    LinearLayout l2;
    LinearLayout l3;
    LinearLayout l4;
    LinearLayout l5;
    private SupportFragment[] mFragments = new SupportFragment[5];
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.realmain);
        EventBus.getDefault().register(this);
        initview();
        mFragments[0] = Support_Fr_1.newInstance();
        mFragments[1] = Support_Fr_2.newInstance();
        mFragments[2] = Support_Fr_3.newInstance();
        mFragments[3] = Support_Fr_4.newInstance();
        mFragments[4] = Support_Fr_5.newInstance();
        loadRootFragment(R.id.fl_container, mFragments[0]);
    }
    private void initview(){
        imageView1=findViewById(R.id.n1);
        imageView2=findViewById(R.id.n2);
        imageView3=findViewById(R.id.n3);
        imageView4=findViewById(R.id.n4);
        imageView5=findViewById(R.id.n5);
        textView1=findViewById(R.id.t1);
        textView2=findViewById(R.id.t2);
        textView3=findViewById(R.id.t3);
        textView4=findViewById(R.id.t4);
        textView5=findViewById(R.id.t5);
        //五个按钮
        l1=findViewById(R.id.l1);
        l2=findViewById(R.id.l2);
        l3=findViewById(R.id.l3);
        l4=findViewById(R.id.l4);
        l5=findViewById(R.id.l5);
        l1.setOnClickListener(this);
        l2.setOnClickListener(this);
        l3.setOnClickListener(this);
        l4.setOnClickListener(this);
        l5.setOnClickListener(this);
        setFristBg(imageView1,textView1,R.mipmap.n11,Color.parseColor("#1296db"));
    }





    @Override
    protected void onStart() {
        super.onStart();
    }
    public void setFristBg(ImageView imageView, TextView textView, int drawable,int color){
        imageView.setImageResource(drawable);
        textView.setTextColor(color);//Color.parseColor("#1296db")
    }

    //改变颜色
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Event event) {
        if(position==1){
            setFristBg(imageView1,textView1,R.mipmap.n11,Color.parseColor("#1296db"));
            setFristBg(imageView2,textView2,R.mipmap.n2,Color.parseColor("#8a8a8a"));
            setFristBg(imageView3,textView3,R.mipmap.n3,Color.parseColor("#8a8a8a"));
            setFristBg(imageView4,textView4,R.mipmap.n4,Color.parseColor("#8a8a8a"));
            setFristBg(imageView5,textView5,R.mipmap.n5,Color.parseColor("#8a8a8a"));
        }
        if(position==2){
            setFristBg(imageView1,textView1,R.mipmap.n1,Color.parseColor("#8a8a8a"));
            setFristBg(imageView2,textView2,R.mipmap.n22,Color.parseColor("#1296db"));
            setFristBg(imageView3,textView3,R.mipmap.n3,Color.parseColor("#8a8a8a"));
            setFristBg(imageView4,textView4,R.mipmap.n4,Color.parseColor("#8a8a8a"));
            setFristBg(imageView5,textView5,R.mipmap.n5,Color.parseColor("#8a8a8a"));
        }
        if(position==3){
            setFristBg(imageView1,textView1,R.mipmap.n1,Color.parseColor("#8a8a8a"));
            setFristBg(imageView2,textView2,R.mipmap.n2,Color.parseColor("#8a8a8a"));
            setFristBg(imageView3,textView3,R.mipmap.n33,Color.parseColor("#1296db"));
            setFristBg(imageView4,textView4,R.mipmap.n4,Color.parseColor("#8a8a8a"));
            setFristBg(imageView5,textView5,R.mipmap.n5,Color.parseColor("#8a8a8a"));
        }
        if(position==4){
            setFristBg(imageView1,textView1,R.mipmap.n1,Color.parseColor("#8a8a8a"));
            setFristBg(imageView2,textView2,R.mipmap.n2,Color.parseColor("#8a8a8a"));
            setFristBg(imageView3,textView3,R.mipmap.n3,Color.parseColor("#8a8a8a"));
            setFristBg(imageView4,textView4,R.mipmap.n44,Color.parseColor("#1296db"));
            setFristBg(imageView5,textView5,R.mipmap.n5,Color.parseColor("#8a8a8a"));
        }
        if(position==5){
            setFristBg(imageView1,textView1,R.mipmap.n1,Color.parseColor("#8a8a8a"));
            setFristBg(imageView2,textView2,R.mipmap.n2,Color.parseColor("#8a8a8a"));
            setFristBg(imageView3,textView3,R.mipmap.n3,Color.parseColor("#8a8a8a"));
            setFristBg(imageView4,textView4,R.mipmap.n4,Color.parseColor("#8a8a8a"));
            setFristBg(imageView5,textView5,R.mipmap.n55,Color.parseColor("#1296db"));
        }
    }
    //还原颜色

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.l1:
                position=1;

                EventBus.getDefault().post(new Event());
                startWithPop(Support_Fr_1.newInstance());
                break;
            case R.id.l2:
                position=2;
                EventBus.getDefault().post(new Event());
                startWithPop(Support_Fr_2.newInstance());
                break;
            case R.id.l3:
                position=3;
                EventBus.getDefault().post(new Event());
                startWithPop(Support_Fr_3.newInstance());
                break;
            case R.id.l4:
                position=4;
                EventBus.getDefault().post(new Event());
                startWithPop(Support_Fr_4.newInstance());
                break;
            case R.id.l5:
                position=5;
                EventBus.getDefault().post(new Event());
                startWithPop(Support_Fr_5.newInstance());
                break;
                default:break;
        }
    }
}
