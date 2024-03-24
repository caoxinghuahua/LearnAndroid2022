package com.example.myapplication2.activity;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.asynclayoutinflater.view.AsyncLayoutInflater;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.LayoutInflaterCompat;

import com.example.myapplication2.MyApplication;
import com.example.myapplication2.R;
import com.example.myapplication2.annotion.InjectUtil;
import com.example.myapplication2.annotion.InjectView;
import com.example.myapplication2.bigBitmap.BitmapService;
import com.example.myapplication2.bigBitmap.TestBitmap;
import com.example.myapplication2.generic.plugin.PluginMainActivity;
import com.example.myapplication2.matrix.TestMatrixActivity;
import com.example.myapplication2.service.MyService;
import com.example.myapplication2.utils.BitmapUtil;

import org.w3c.dom.Text;

import java.io.FileDescriptor;
import java.util.HashMap;
import java.util.HashSet;

import io.flutter.embedding.android.FlutterActivity;

public class MainActivity extends Activity {
    private final String TAG = this.getClass().getSimpleName();
    private TextView textView;

    @InjectView(R.id.annotation)
    private TextView annotationView;
    @InjectView(R.id.bigBitmap)
    private TextView bigBitmapView;
    @InjectView(R.id.showFlutterView)
    private TextView showFlutterViewTv;
    @InjectView(R.id.testEpic)
    private TextView testEpic;

    byte[] bytes = "落霞与孤鹜齐飞，秋水共长天一色。".getBytes();
    private int transitCode = 1000;

    //    private final ArrayMap<String, Boolean> mBlackFirstFrame = new ArrayMap<>();
//
//    public boolean getFlag(String key) {
//        return mBlackFirstFrame.get(key);
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //factory2统一替换TextView背景
        LayoutInflaterCompat.setFactory2(LayoutInflater.from(this), new LayoutInflater.Factory2() {
            @Nullable
            @Override
            public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
                if (name.equals("TextView")) {
                    TextView textView = new TextView(context, attrs);
                    textView.setTextColor(Color.WHITE);
                    return textView;
                } else {
                    return null;
                }
            }


            @Nullable
            @Override
            public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
                return onCreateView(null, name, context, attrs);
            }
        });
        super.onCreate(savedInstanceState);
        //加载解析xml放在子线程处理
//        new AsyncLayoutInflater(this).inflate(R.layout.activity_main, null, new AsyncLayoutInflater.OnInflateFinishedListener() {
//            @Override
//            public void onInflateFinished(@NonNull View view, int resid, @Nullable ViewGroup parent) {
//                setContentView(view);
//                textView = findViewById(R.id.text);
//            }
//        });


        setContentView(R.layout.activity_main);

        findViewById(R.id.goPluginTv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PluginMainActivity.class);
                startActivity(intent);
            }
        });
        textView = findViewById(R.id.text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityB.class);
                intent.resolveActivity(getPackageManager());
                startActivity(intent);
            }
        });
        findViewById(R.id.goServiceTv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentService = new Intent(MainActivity.this, MyService.class);
                startService(intentService);
            }
        });
        findViewById(R.id.goTaskTv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("com.example.kotlinleran", "com.example.kotlinleran.SecondActivity");
                startActivity(intent);
            }
        });
        findViewById(R.id.testBlock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestBlockActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.gotoMatrix).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestMatrixActivity.class);
                startActivity(intent);
            }
        });
//        Toast

        int a = 10;
        //局部内部类
        class Test {
            public void test() {
                System.out.println(a);
            }
        }

//        Log.d(TAG, "path:" + getExternalFilesDir("cache").getAbsolutePath());
//
//        Log.d(TAG, "onCreate");
        Trace.beginSection("s");
//        Trace.endSection();
//        getFlag("sss");

        //自定义注解findview
        InjectUtil.Companion.inject(this);
        annotationView.setOnClickListener(v -> {

            Toast.makeText(this, "自定义获取注解成功", Toast.LENGTH_SHORT).show();
        });
        //intent 传大图方案
        ServiceConnection serviceConnection = new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName name, IBinder binder) {
                Log.d(TAG, "onServiceConnected");

                if (binder == null) return;
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test_big_bitmap);
                ParcelFileDescriptor descriptor = TestBitmap.createMemoryFile(BitmapUtil.bitmap2bytes(bitmap));
                // 传递 FileDescriptor 和 共享内存中数据的大小
                Parcel sendData = Parcel.obtain();
                sendData.writeParcelable(descriptor, 0);
                sendData.writeInt(bitmap.getByteCount());
                // 保存对方进程的返回值
                Parcel reply = Parcel.obtain();
                try {
                    //跨进程传输
                    binder.transact(transitCode, sendData, reply, 0);
                    //打印返回的信息
                    String replayStr = reply.readString();
                    Log.d(TAG, "replyStr:" + replayStr);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "onServiceDisconnected");
            }

            @Override
            public void onBindingDied(ComponentName name) {
                ServiceConnection.super.onBindingDied(name);
            }

            @Override
            public void onNullBinding(ComponentName name) {
                ServiceConnection.super.onNullBinding(name);
            }
        };
        bigBitmapView.setOnClickListener(v -> {
            Intent intent = new Intent(this, BitmapService.class);
            bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        });
        showFlutterViewTv.setOnClickListener(v -> {
//                  startActivity(FlutterActivity.withNewEngine().initialRoute("route3").build(MainActivity.this));
            //缓存FlutterEngin加快页面跳转速度
//                    startActivity(FlutterActivity.withCachedEngine(MyApplication.CACHED_ENGINE_ID).build(MainActivity.this));

            MyFlutterActivity.Companion.startFlutterPage(this, "router2");

        });
//        DisplayMetrics displayMetrics=new DisplayMetrics();
//        getWindow().getDecorView().getDisplay().getMetrics(displayMetrics);
//        int heightPixels = displayMetrics.heightPixels;
//
//        getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        testEpic.setOnClickListener(v -> {

        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.d(TAG, "onWindowFocusChanged-w=" + textView.getWidth() + "-h=" + textView.getHeight());
    }

}