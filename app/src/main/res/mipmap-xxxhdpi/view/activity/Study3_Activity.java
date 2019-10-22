package com.example.myapplication3.view.activity;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.R;

public class Study3_Activity extends AppCompatActivity {
    String [] mobileArray={"小松松","小艳艳","小qioqio","我知道"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study3);
//        ArrayAdapter adapter=new ArrayAdapter<String>(this,R.layout.study3_item,mobileArray);
        ListView listView=(ListView) findViewById(R.id.mobile_list);
//        System.out.println("--------------------------");
//        listView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//        View view2 =LayoutInflater.from(this).inflate(R.layout.study3_item,null);
        BaseAdapter adapter=new BaseAdapter() {
            @Override
            public int getCount() {
                return 20;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                LayoutInflater layoutInflater=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
                View view1=layoutInflater.inflate(R.layout.study3_item,null);
                return view1;
            }
        };
        listView.setAdapter(adapter);
        final ImageView imageView =(ImageView)findViewById(R.id.doudong);
//        final Animation animation= AnimationUtils.loadAnimation(this,R.anim.traslate_animation);
        final AnimationDrawable animation=(AnimationDrawable)imageView.getBackground();
        animation.start();
        initView();
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                imageView.startAnimation(animation);
//
//            }
//        });

    }
    private void initView() {
      final   ImageView iv = (ImageView) findViewById(R.id.iv_object_animation_activity);
        //改变背景属性
    final     ValueAnimator colorAnim = ObjectAnimator.ofInt(iv, "backgroundColor", Color.parseColor("#FF4081"), Color.CYAN);
        colorAnim.setRepeatCount(2);
        colorAnim.setRepeatMode(ObjectAnimator.REVERSE);
        colorAnim.setDuration(1000);
        colorAnim.setEvaluator(new ArgbEvaluator());//估值器
        //动画集合
       final  AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(iv, "rotationX", 0, 360),//绕x轴旋转360度
                ObjectAnimator.ofFloat(iv, "rotation", 0, -90),//逆时针旋转90度
                ObjectAnimator.ofFloat(iv, "translationX", 0, 90),//右移
                ObjectAnimator.ofFloat(iv, "scaleY", 1, 0.5f),//y轴缩放到一半
                ObjectAnimator.ofFloat(iv, "alpha", 1, 0.25f, 1)//透明度变换
        );
        //延迟一秒开始
        set.setStartDelay(1000);
        Button bt = (Button) findViewById(R.id.bt_object_animation_activity);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator.ofFloat(iv, "translationY", iv.getHeight() / 2).start();
                //背景属性改变开始
                colorAnim.start();
                //集合动画
                set.setDuration(3000).start();
            }
        });
    }

}
