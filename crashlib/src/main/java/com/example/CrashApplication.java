package com.example;

import android.app.Application;

import com.example.crashlib.crash.CrashReport;

public class CrashApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.INSTANCE.report(this);
    }
}
