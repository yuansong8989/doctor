package com.example.myapplication3.view.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.myapplication3.Adapter.MypageAdapter;
import com.example.myapplication3.Adapter.ViewPagerAdater;
import com.example.myapplication3.Fragment.FragmentTest;
import com.example.myapplication3.Fragment.FragmentTest1;
import com.example.myapplication3.Fragment.FragmentTest2;
import com.example.myapplication3.Fragment.FragmentTest3;
import com.example.myapplication3.R;
//import com.example.myapplication3.ToMeView.FirstView;
import com.example.myapplication3.Utils.Remember;
import com.example.myapplication3.entity.User;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ZhuYe extends AppCompatActivity implements OnBannerListener, GestureDetector.OnGestureListener {
    private String username;
    private Banner mBanner;
    PopupWindow popupWindow;
    PopupWindow popupWindow1;
    ImageView imageView;
    String mPhotoPath;
    Bitmap bitmap2;
    View contentView;
    View contentView1;
    GestureDetector detector;

    ImageView imageView3;
    Bitmap bitmap;
    int width, height;
    float currentScale = 1;
    Matrix matrix;
    Drawable drawable3;
    CircleImageView circleImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("主页被创造");
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hhh);

//        System.out.println("修起来"+Remember.getString("songsong","哈哈哈"));
        detector = new GestureDetector(this, this);
        matrix = new Matrix();
        showPopwindow();
        Intent intent = getIntent();
//        User user = (User) intent.getSerializableExtra("user");
        username ="dmldm";
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav1);
        View headView = navigationView.getHeaderView(0);
        //左滑
        GridView gridView = (GridView) headView.findViewById(R.id.grd1);
        GridView gridView1 = (GridView) headView.findViewById(R.id.grd2);
        BaseAdapter baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                View view1 = LayoutInflater.from(ZhuYe.this).inflate(R.layout.ooo, null);
                ImageView imageView = (ImageView) view1.findViewById(R.id.yyy1);
                TextView textView = (TextView) view1.findViewById(R.id.yyy2);
                String a[] = {"语文", "数学", "英语", "科学"};
                String url[] = {"http://10.9.5.9:8761/yw.png", "http://10.9.5.9:8761/shuxue1.png", "http://10.9.5.9:8761/yingyu.png", "http://10.9.5.9:8761/kexue.png"};
                for (int j = 0; j < 4; j++) {
                    textView.setText(a[i]);
                    Glide.with(ZhuYe.this).load(url[i]).into(imageView);
                }
                return view1;
            }
        };
        BaseAdapter baseAdapter1 = new BaseAdapter() {
            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                View view1 = LayoutInflater.from(ZhuYe.this).inflate(R.layout.ooo1, null);
                TextView textView = (TextView) view1.findViewById(R.id.yyy4);
                ImageView imageView = (ImageView) view1.findViewById(R.id.yyy3);
                String url[] = {"http://10.9.5.9:8761/banji.png", "http://10.9.5.9:8761/jiaofei.png", "http://10.9.5.9:8761/news.png", "http://10.9.5.9:8761/wdxy.png"};
                String a[] = {"我的班级", "学生缴费", "班级新闻", "校园传送门"};
                for (int j = 0; j < 4; j++) {
                    textView.setText(a[i]);
                    Glide.with(ZhuYe.this).load(url[i]).into(imageView);
                }
                return view1;
            }
        };
        gridView.setAdapter(baseAdapter);
        gridView1.setAdapter(baseAdapter1);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    Intent intent = new Intent(ZhuYe.this, CeHua.class);
                    startActivity(intent);
                }
                if (i == 1) {
                    Intent intent = new Intent(ZhuYe.this, ShuXue.class);
                    startActivity(intent);
                }
                if (i == 2) {
                    Intent intent = new Intent(ZhuYe.this, TcpClientActivity.class);
                    startActivity(intent);
                }
            }
        });
        final TextView textView = (TextView) headView.findViewById(R.id.jjj6);
        circleImageView = (CircleImageView) headView.findViewById(R.id.f1);

        String url = "http://10.9.5.9:8761/yan.png";
        Glide.with(this).load(url).into(circleImageView);
        imageView = (ImageView) headView.findViewById(R.id.jjj5);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
            }
        });
        circleImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showPopwindow1();
                imageView3 = (ImageView) contentView1.findViewById(R.id.u7);
                System.out.println("我我我");
                drawable3 = circleImageView.getDrawable();
                imageView3.setImageDrawable(drawable3);
                bitmap = drawableToBitmap(drawable3);
                width = bitmap.getWidth();
                height = bitmap.getHeight();
                imageView3.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        System.out.println("控件绑定");
                        return detector.onTouchEvent(motionEvent);
                    }
                });
                popupWindow1.showAtLocation(contentView1, Gravity.CENTER, 0, 0);
                return true;
            }
        });
        textView.setText(username);
        boolean b = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        System.out.println("文件路劲" + Environment.getDataDirectory().getParentFile());
        System.out.println("对话框的" + b);
//        FileOutputStream fileOutputStream=openFileOutput("",MODE_APPEND)
//        getSupportFragmentManager()    //
//                .beginTransaction()
//                .add(R.id.jjjj,new FragmentTest1())   // 此处的R.id.fragment_container是要盛放fragment的父容器
//                .commit();
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentTest());
        fragments.add(new FragmentTest1());
        fragments.add(new FragmentTest2());
        fragments.add(new FragmentTest3());
//        FragmentTransaction fragmentTransaction= this.getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.add(new FragmentTest1(),"是");
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab2);
        tabLayout.setSelectedTabIndicatorColor(Color.GRAY);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
//        ViewPagerAdater viewPagerAdater=new ViewPagerAdater(this);
//        viewPager.setAdapter(viewPagerAdater);
        MypageAdapter mypageAdapter = new MypageAdapter(getSupportFragmentManager(), fragments, this);
        viewPager.setAdapter(mypageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < mypageAdapter.getCount(); i++) {
            tabLayout.getTabAt(i).setCustomView(mypageAdapter.setCustomView(i));
        }
        LoadingBanner();
    }

    @Override
    protected void onStart() {
        Button button = (Button) contentView.findViewById(R.id.paizhao);
        Button button1 = (Button) contentView.findViewById(R.id.tuku);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(ZhuYe.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ZhuYe.this, new
                            String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                } else {
//打开系统相册
                    openAlbum();
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");//开始拍照
                    mPhotoPath = getSDPath() + "/" + getPhotoFileName();//设置图片文件路径，getSDPath()和getPhotoFileName()具体实现在下面

                    File mPhotoFile = new File(mPhotoPath);
                    if (!mPhotoFile.exists()) {
                        mPhotoFile.createNewFile();//创建新文件
                    }
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,//Intent有了图片的信息
                            Uri.fromFile(mPhotoFile));
                    startActivityForResult(intent, 1);//跳转界面传回拍照所得数据
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("主页开始了");
//        FirstView firstView=(FirstView)findViewById(R.id.f1);
//        ImageView imageView=(ImageView)firstView.findViewById(R.id.title_tab_left) ;
//        imageView.setImageResource(R.drawable.jz);
        Toast.makeText(this, "欢迎学生" + username, Toast.LENGTH_SHORT).show();
        mBanner.startAutoPlay();//开始轮播
        super.onStart();
    }

    public void LoadingBanner() {

        mBanner = findViewById(R.id.banner);
        //图片资源
        int[] imageResourceID = new int[]{R.drawable.kx1, R.drawable.kx2, R.drawable.kx3, R.drawable.kx4};
        List<Integer> imgeList = new ArrayList<>();
        //轮播标题
        String[] mtitle = new String[]{"三星中心小学待准备修建第二教学楼", "开学季，你准备好了吗", "暑假收心，状态回神", "年度电影《开学了》正式上映"};
        List<String> titleList = new ArrayList<>();

        for (int i = 0; i < imageResourceID.length; i++) {
            imgeList.add(imageResourceID[i]);//把图片资源循环放入list里面
            titleList.add(mtitle[i]);//把标题循环设置进列表里面
            //设置图片加载器，通过Glide加载图片
            mBanner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(ZhuYe.this).load(path).into(imageView);
                }
            });
            //设置轮播的动画效果,里面有很多种特效,可以到GitHub上查看文档。
            mBanner.setBannerAnimation(Transformer.Default);
            mBanner.setImages(imgeList);//设置图片资源
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);//设置banner显示样式（带标题的样式）
            mBanner.setBannerTitles(titleList); //设置标题集合（当banner样式有显示title时）
            //设置指示器位置（即图片下面的那个小圆点）
            mBanner.setIndicatorGravity(BannerConfig.CENTER);
            mBanner.setDelayTime(1500);//设置轮播时间3秒切换下一图
            mBanner.setOnBannerListener(ZhuYe.this);//设置监听
            mBanner.start();//开始进行banner渲染
        }
    }

    @Override
    protected void onStop() {
        System.out.println("主页已经结束");
        super.onStop();
        mBanner.stopAutoPlay();//结束轮播
    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(this, "你点击了第" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        System.out.println("你已经被暂停");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        System.out.println("重新启动被回调");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        System.out.println("恢复ac得时候被回调");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        System.out.println("被摧毁得时候回调");
        super.onDestroy();
    }

    private void showPopwindow() {
        //加载弹出框的布局
        contentView = LayoutInflater.from(ZhuYe.this).inflate(
                R.layout.pop1, null);
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
        // 按下android回退物理键 PopipWindow消失解决
    }


    ///加大图片
    private void showPopwindow1() {
        //加载弹出框的布局
        contentView1 = LayoutInflater.from(ZhuYe.this).inflate(
                R.layout.nnn, null);
        popupWindow1 = new PopupWindow(contentView1,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow1.setFocusable(true);// 取得焦点
        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
        popupWindow1.setBackgroundDrawable(new BitmapDrawable());
        //点击外部消失
        popupWindow1.setOutsideTouchable(true);
        //设置可以点击
        popupWindow1.setTouchable(true);
        //进入退出的动画，指定刚才定义的style
        popupWindow1.setAnimationStyle(R.style.anim_menu_bottombar);
        // 按下android回退物理键 PopipWindow消失解决
    }

    public String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED);   //判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        return Environment.getExternalStorageDirectory() + "/AAA" + "/";

    }

    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Bitmap bitmap = BitmapFactory.decodeFile(mPhotoPath, null);
            circleImageView.setImageBitmap(bitmap);
        }
        if (requestCode == 2) {
            if (Build.VERSION.SDK_INT >= 19) {
                handleImageOnKitkat(data);
            } else {
                handleImageBeforeKitkat(data);
            }
        }
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, 2);//打开系统相册
    }

    private void handleImageOnKitkat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            //如果是document类型的uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content:" +
                        "//downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //如果是content类型的uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            //如果是File类型的uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        //根据图片路径显示图片
        displayImage(imagePath);
    }

    private void handleImageBeforeKitkat(Intent data) {
        System.out.println("旧版本");
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        //通过uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            bitmap2 = BitmapFactory.decodeFile(imagePath);
            showNormalDialog();

        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    private void showNormalDialog() {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(ZhuYe.this);
        normalDialog.setIcon(R.drawable.sanxing);
        normalDialog.setTitle("确定修改你的头像");
        normalDialog.setMessage("修改了很麻烦的哦");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        circleImageView.setImageBitmap(bitmap2);
                        popupWindow.dismiss();
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
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        return detector.onTouchEvent(event);
//    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        System.out.println("按下");
        return false;
    }

    public ZhuYe() {
        super();
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
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        System.out.println("长按");
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        System.out.println("怎么滑动");
        v = v > 4000 ? 4000 : v;
        v = v < -4000 ? -4000 : v;
        currentScale += currentScale * v / 4000.0f;
        currentScale = currentScale > 0.01 ? currentScale : 0.01f;
        matrix.reset();
        matrix.setScale(currentScale, currentScale, 160, 200);
        BitmapDrawable tmp = (BitmapDrawable) imageView3.getDrawable();
        if (!tmp.getBitmap().isRecycled()) {
            tmp.getBitmap().recycle();
        }
        Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        imageView3.setImageBitmap(bitmap2);
        return true;
    }

    //drawble转Bitmap
    public static Bitmap drawableToBitmap(Drawable drawable) {

        Bitmap bitmap = Bitmap.createBitmap(

                drawable.getIntrinsicWidth(),

                drawable.getIntrinsicHeight(),

                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888

                        : Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);

        //canvas.setBitmap(bitmap);

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        drawable.draw(canvas);

        return bitmap;
    }
}
