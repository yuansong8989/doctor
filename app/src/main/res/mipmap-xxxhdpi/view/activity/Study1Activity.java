package com.example.myapplication3.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.myapplication3.R;
import com.example.myapplication3.SecondActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Study1Activity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private View contentView;
    private PopupWindow popupWindow;
    private Integer bbb=0;
    Integer [] imageArray={R.drawable.cyn,R.drawable.hng,R.drawable.xuexiao,R.drawable.kzd};
    String [] mobileArray={"白山茶 - 陈雪凝","英雄联盟海牛歌 - 官方","학교에서 배운 것(从学校学到的东西) - 金振飙","测试铃声 - 袁震东","我们的明天 - 鹿娘炮","明天，你好 - 牛奶咖啡","喜欢你 - Beyond - 25周年精选","没那么简单 - 黄小琥","奇妙能力歌 - 陈丽","Seven - Tobu","同手同角 - 温岚","Diamonds - Afrojack/Jay Karama","我 - 张国荣 - 大热","If I Were a Boy - Beyonce","两个人 - 蔡妍 - 摇摆","Halo - Beyonce"};
    String [] content={"我是小松松"};
    private  int[] imageViews={R.drawable.bjt};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study);
        showPopwindow();
        final DrawerLayout drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        ImageView imageView=(ImageView)findViewById(R.id.open_ch);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
//LIST

    }
//    public void huanfu(View view) {
////        final LinearLayout layout =(LinearLayout)findViewById(R.id.o1);
////        final TextView textView =(TextView)findViewById(R.id.x1);
////        final ScrollView scrollView=(ScrollView)findViewById(R.id.y1);
////        textView.setOnClickListener(new View.OnClickListener(){
////            @Override
////            public void onClick(View view) {
////                if(textView.getText()=="夜间模式"){
////                    layout.setBackgroundColor(Color.BLACK);
////                    scrollView.setBackgroundColor(Color.BLACK);
////                    textView.setText("日间模式");
////                    textView.setTextColor(Color.WHITE);
////                    textView.setBackgroundColor(Color.BLACK);
////                    setTextColor(layout);
////                }else{
////                    textView.setText("夜间模式");
////                    layout.setBackgroundColor(Color.WHITE);
////                    scrollView.setBackgroundColor(Color.WHITE);
////                    textView.setTextColor(Color.BLACK);
////                    textView.setBackgroundColor(Color.WHITE);
//////                    this.onClick(view);
////                }
////            }
////        });
////        Toast.makeText(this,"登录密码以及账户名错误仔细检查",Toast.LENGTH_SHORT).show();
//    }
//    private void setTextColor(ViewGroup viewGroup){
//        int count =viewGroup.getChildCount();
//        for(int i=0;i<count;i++){
//            View view=viewGroup.getChildAt(count);
//            if(view instanceof TextView){
//                TextView textView=(TextView)view;
//                textView.setTextColor(Color.WHITE);
//            }
//            else if(view instanceof ViewGroup){
//                this.setTextColor((ViewGroup) view);
//            }
//        }
//    }

    @Override
    protected void onStart() {
        final ImageView imageViewcon =(ImageView)findViewById(R.id.tp);
        final ImageView imageView=(ImageView)findViewById(R.id.bf);
        mediaPlayer = MediaPlayer.create(Study1Activity.this,R.raw.ttt);
//        Uri s=Uri.parse("android.resource://com.android.sim/"+R.raw.ttt);
//        mediaPlayer=new MediaPlayer();
//        try{
//            mediaPlayer.setDataSource(this,s);
//            mediaPlayer.prepare();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        mediaPlayer.setWakeMode(this, PowerManager.PARTIAL_WAKE_LOCK);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mediaPlayer.isPlaying()){
                    System.out.println("首先播放");
                    imageView.setImageResource(R.drawable.zt2);
                    mediaPlayer.start();
                }else{
                    imageView.setImageResource(R.drawable.jz);
                    mediaPlayer.pause();
                }
            }
        });
        ImageView imageView1=(ImageView)findViewById(R.id.pop);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
            }
        });
        Integer x=R.raw.bsc;
        final TextView textView=(TextView)findViewById(R.id.yy_title);
        ListView listView =(ListView)contentView.findViewById(R.id.mobile_list1);
//        mediaPlayer = MediaPlayer.create(Study1Activity.this,x);
       final MediaPlayer mediaPlayer1[]={MediaPlayer.create(Study1Activity.this,R.raw.ttt),MediaPlayer.create(Study1Activity.this,R.raw.ttt),MediaPlayer.create(Study1Activity.this,R.raw.ttt),MediaPlayer.create(Study1Activity.this,R.raw.ttt)};
//       for(int k=0;k<3;k++){
           mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
               @Override
               public void onCompletion(MediaPlayer mediaPlayer2) {
                   if(textView.getText().equals(mobileArray[3])){
                       mediaPlayer.release();
                       mediaPlayer=mediaPlayer1[0];
                       textView.setText(mobileArray[0]);
                       imageViewcon.setImageResource(imageArray[0]);
                       System.out.println("haishi顺序播放 第一首");
                       mediaPlayer.start();
                   }else {
                       mediaPlayer.release();
                       mediaPlayer=mediaPlayer1[getWeiZhi((String)textView.getText())+1];
                       textView.setText(mobileArray[getWeiZhi((String)textView.getText())+1]);
                       imageViewcon.setImageResource(getWeiZhi((String)textView.getText())+1);
                       System.out.println("haishi顺序播放 下一首");
                       mediaPlayer.start();
                   }
               }
           });
//       }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                if(textView.getText().equals(mobileArray[i])){
                    System.out.println("与刚开始音乐文件一致"+l);
                    popupWindow.dismiss();
                }else {
                    if(mediaPlayer1[i].isPlaying()){
                        System.out.println("再次点击播放");
                        popupWindow.dismiss();
                    }else {
                        for(MediaPlayer mediaPlayer:mediaPlayer1){
                            if(mediaPlayer.isPlaying()){
                                System.out.println("检测到有播放的音乐。直接关闭");
                                mediaPlayer.stop();
                                mediaPlayer.release();
                                try{
                                    mediaPlayer.prepare();
                                }catch (IOException e){
                                    e.printStackTrace();
                                }
                            }
                        }
                        if(mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            try{
                                mediaPlayer.prepare();
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                        System.out.println("第一次播放");
                        TextView textView1=(TextView)view.findViewById(R.id.t2);
                        popupWindow.dismiss();
                        mediaPlayer=mediaPlayer1[i];
                        imageView.setImageResource(R.drawable.zt2);
                        imageViewcon.setImageResource(imageArray[i]);
                        textView.setText(textView1.getText());
                        mediaPlayer.start();
                               for(int k=0;k<3;k++) {
                                   mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                       @Override
                                       public void onCompletion(MediaPlayer mediaPlayer2) {
                                           if (i == 3) {
                                               mediaPlayer = mediaPlayer1[0];
                                               System.out.println("顺序播放 第一首");
                                               textView.setText(mobileArray[0]);
                                               imageViewcon.setImageResource(imageArray[0]);
                                               mediaPlayer.start();
                                           } else {
                                               mediaPlayer = mediaPlayer1[i + 1];
                                               System.out.println("顺序播放 下一首");
                                               textView.setText(mobileArray[i + 1]);
                                               imageViewcon.setImageResource(imageArray[i + 1]);
                                               mediaPlayer.start();
                                           }
                                       }
                                   });
                               }
                    }
                }
            }
        });
        super.onStart();
    }

    private void showPopwindow() {
        //加载弹出框的布局
        contentView=LayoutInflater.from(Study1Activity.this).inflate(
                R.layout.pop, null);
        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);// 取得焦点
        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //点击外部消失
        popupWindow.setOutsideTouchable(true);
        //设置可以点击
        popupWindow.setTouchable(true);
        //进入退出的动画，指定刚才定义的style
        popupWindow.setAnimationStyle(R.style.anim_menu_bottombar);
        ListView listView=(ListView)contentView.findViewById(R.id.mobile_list1);
        List<Map<String,Object>> lists=new ArrayList<>();
        for(int i=0;i<mobileArray.length;i++){
            Map<String,Object> map=new HashMap<>();
//            map.put("image",imageViews[i]);
            map.put("theme",mobileArray[i]);
//            map.put("content",content[i]);
            lists.add(map);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(Study1Activity.this,lists,R.layout.study3_item,new String[]{"theme"},new int[]{R.id.t2});
        listView.setAdapter(simpleAdapter);
        // 按下android回退物理键 PopipWindow消失解决
    }
    private Integer getWeiZhi(String a){
        for(int i=0;i<mobileArray.length;i++){
            if(mobileArray[i].equals(a)){
                bbb=i;
            }
        }
        return bbb;
    }

}
