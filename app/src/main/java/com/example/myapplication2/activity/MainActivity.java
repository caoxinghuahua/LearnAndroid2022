package com.example.myapplication2.activity;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Trace;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication2.R;
import com.example.myapplication2.plugin.PluginMainActivity;
import com.example.myapplication2.service.MyService;

public class MainActivity extends Activity {
    private final String TAG = this.getClass().getSimpleName();
    private TextView textView;


    //    private final ArrayMap<String, Boolean> mBlackFirstFrame = new ArrayMap<>();
//
//    public boolean getFlag(String key) {
//        return mBlackFirstFrame.get(key);
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
//        Toast

        int a = 10;
        //局部内部类
        class Test {
            public void test() {
                System.out.println(a);
            }
        }

        Log.d(TAG, "path:" + getExternalFilesDir("cache").getAbsolutePath());

        Log.d(TAG, "onCreate");
//        Trace.beginSection("s");
//        Trace.endSection();
//        getFlag("sss");


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