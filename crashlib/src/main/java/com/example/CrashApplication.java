package com.example;

import android.app.Application;

import com.example.crashlib.crash.CrashReport;

import xcrash.XCrash;

public class CrashApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.INSTANCE.report(this);
        //xcrash捕获异常
        initXCrash();
    }
    private void initXCrash(){
        //https://blog.csdn.net/cxmfzu/article/details/102624295
        XCrash.init(this,null);
    }
}
