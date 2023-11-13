package com.example.myapplication2.generic.plugin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
//插件化  https://juejin.cn/post/6844903613865672718#heading-1
import com.example.myapplication2.R;

public class PluginMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin_main);
        findViewById(R.id.startPluginBt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("com.example.myapplication", "com.example.myapplication.PluginActivity");
                startActivity(intent);
            }
        });
    }

}