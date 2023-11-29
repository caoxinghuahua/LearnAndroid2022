package com.example.myapplication2;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Choreographer;

import com.example.myapplication2.crash.CrashReport;
import com.example.myapplication2.generic.plugin.HookUtils;
import com.example.myapplication2.generic.plugin.PluginUtils;
import com.example.myapplication2.koom.OOMMonitorInitTask;
import com.kwai.koom.javaoom.monitor.OOMMonitor;
import com.tencent.mmkv.MMKV;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;
import xcrash.XCrash;

public class MyApplication extends Application {
    public static final String CACHED_ENGINE_ID = "MY_CACHED_ENGINE_ID";

    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.INSTANCE.report(this);
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long frameTimeNanos) {

            }
        });

//        testPlugin();


        if (isMainProcess(this)) {
            Log.d("*MyApplication*", "is main process");
        } else {
            Log.d("*MyApplication*", "is not main process");
        }
        initMMKV();
        initXCrash();
        initFlutter();
    }

    /**
     * 检查是否在主进程
     *
     * @return
     */
    public boolean isMainProcess(Context context) {
        int pid = android.os.Process.myPid();
        Log.d("*MyApplication*", "pid:" + pid);
        ActivityManager activityManager = (ActivityManager) context.getSystemService
                (Context.ACTIVITY_SERVICE);
        if (activityManager.getRunningAppProcesses() == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                Log.d("*MyApplication*", "current process name:" + appProcess.processName);
                return context.getApplicationInfo().packageName.equals(appProcess.processName);
            }
        }
        return false;
    }


    public void initMMKV() {
        MMKV.initialize(this);
        MMKV.defaultMMKV().encode("a123", "a");

        Log.d("*MyApplication*", "mmkv value1:" + MMKV.defaultMMKV().decodeString("a123"));
        MMKV.defaultMMKV().remove("a123");//删除
        Log.d("*MyApplication*", "mmkv value2:" + MMKV.defaultMMKV().decodeString("a123"));
        testImportSP();
    }

    public void testImportSP() {
        SharedPreferences preferences = getSharedPreferences("imported", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("bool", true);
        editor.putInt("int", Integer.MIN_VALUE);
        editor.putLong("long", Long.MAX_VALUE);
        editor.putFloat("float", -3.14f);
        editor.putString("string", "hello, imported");
        HashSet<String> set = new HashSet<String>();
        set.add("W");
        set.add("e");
        set.add("C");
        set.add("h");
        set.add("a");
        set.add("t");
        editor.putStringSet("string-set", set);
        editor.commit();

        MMKV kv = MMKV.mmkvWithID("imported");
        kv.clearAll();
        kv.importFromSharedPreferences(preferences);
        editor.clear().commit();

        Log.i("MMKV", "allKeys: " + Arrays.toString(kv.allKeys()));

        Log.i("MMKV", "bool: " + kv.getBoolean("bool", false));
        Log.i("MMKV", "int: " + kv.getInt("int", 0));
        Log.i("MMKV", "long: " + kv.getLong("long", 0));
        Log.i("MMKV", "float: " + kv.getFloat("float", 0));
        Log.i("MMKV", "double: " + kv.decodeDouble("double"));
        Log.i("MMKV", "string: " + kv.getString("string", null));
        Log.i("MMKV", "string-set: " + kv.getStringSet("string-set", null));
        Log.i("MMKV", "linked-string-set: " + kv.decodeStringSet("string-set", null, LinkedHashSet.class));

        // test @Nullable
        kv.putStringSet("string-set", null);
        Log.i("MMKV", "after set null, string-set: " + kv.getStringSet("string-set", null));
//        MMKV.defaultMMKV().reKey()
    }
    /**
     *  插件化
     * */
    private void testPlugin(){

        PluginUtils.loadPluginClass(this);
        HookUtils.hookAMS();
        HookUtils.hookHandler();

    }
    private void initXCrash(){
        //https://blog.csdn.net/cxmfzu/article/details/102624295
        XCrash.init(this,new XCrash.InitParameters().enableJavaCrashHandler());
    }
    private void initKoom(){
        OOMMonitorInitTask.INSTANCE.init(this);
        OOMMonitor.INSTANCE.startLoop(true, false,5_000L);

    }

    //https://zhuanlan.zhihu.com/p/450575646
    //https://blog.csdn.net/qq_34202054/article/details/117596583
    private void initFlutter(){
        //在MyApplication中预先初始化Flutter引擎以提升Flutter页面打开速度
        FlutterEngine flutterEngine=new FlutterEngine(this);
        // Start executing Dart code to pre-warm the FlutterEngine.

        flutterEngine.getDartExecutor().executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault());
        // Cache the FlutterEngine to be used by FlutterActivity.
        FlutterEngineCache.getInstance().put(CACHED_ENGINE_ID,flutterEngine);

    }
    private void testEpic(){

    }
}

