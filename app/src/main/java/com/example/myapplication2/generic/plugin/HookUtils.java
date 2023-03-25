package com.example.myapplication2.generic.plugin;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class HookUtils {
    public static final String TARGET_INTENT = "target_intent";

    public static void hookAMS() {
        //获取singlon对象
        try {
            Field singletonField = null;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                Class<?> clazz = Class.forName("android.app.ActivityManagerNative");
                singletonField = clazz.getDeclaredField("gDefault");
            } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
                Class<?> clazz = Class.forName("android.app.ActivityManager");
                singletonField = clazz.getDeclaredField("IActivityManagerSingleton");
            } else {
                Class<?> clazz = Class.forName("android.app.ActivityTaskManager");
                singletonField = clazz.getDeclaredField("IActivityTaskManagerSingleton");
            }


            singletonField.setAccessible(true);
            Object singleton = singletonField.get(null);//静态方法

            //获取IActivityManager对象
            Class<?> singletonClass = Class.forName("android.util.Singleton");
            Field mInstanceField = singletonClass.getDeclaredField("mInstance");
            mInstanceField.setAccessible(true);
            final Object mInstance = mInstanceField.get(singleton);

            Class<?> iActivityManagerClass = null;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
                iActivityManagerClass = Class.forName("android.app.IActivityManager");
            } else {
                iActivityManagerClass = Class.forName("android.app.IActivityTaskManager");

            }
            //创建动态代理对象
            Object proxyInstance = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{
                    iActivityManagerClass
            }, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    //修改startActivity中的intent为代理activity
                    if ("startActivity".equals(method.getName())) {
                        int index = -1;
                        for (int i = 0; i < args.length; i++) {
                            if (args[i] instanceof Intent) {
                                index = i;
                                break;
                            }
                        }
                        Intent intent = (Intent) args[index];
                        Intent proxyIntent = new Intent();
                        proxyIntent.setClassName("com.example.myapplication2", "com.example.myapplication2.activity.ProxyActivity");
                        proxyIntent.putExtra(TARGET_INTENT, intent);
                        args[index] = proxyIntent;
                    }
                    return method.invoke(mInstance, args);
                }
            });

            //ActivityManager.getService替换成proxyInstance

            mInstanceField.set(singleton, proxyInstance);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hookHandler() {
        //获取ActivityThread class类对象
        try {
            Class<?> clazz = Class.forName("android.app.ActivityThread");
            //获取activityThread对象
            Field activityThreadField = clazz.getDeclaredField("sCurrentActivityThread");
            activityThreadField.setAccessible(true);
            Object activityThread = activityThreadField.get(null);

            //拿mH对象
            Field mHField = clazz.getDeclaredField("mH");
            mHField.setAccessible(true);
            final Handler mH = (Handler) mHField.get(activityThread);

            Field mCallBackField = Handler.class.getDeclaredField("mCallback");
            mCallBackField.setAccessible(true);


            Handler.Callback callback = new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message msg) {


                    Log.d("******", "what:" + msg.what);
                    //替换回要启动的activity
                    switch (msg.what) {
                        case 100://启动activity消息
                            try {
                                Field intentField = msg.obj.getClass().getDeclaredField("intent");
                                intentField.setAccessible(true);
                                //启动代理intent
                                Intent proxyIntent = (Intent) intentField.get(msg.obj);
                                Intent intent = proxyIntent.getParcelableExtra(TARGET_INTENT);

                                if (intent != null) {
                                    intentField.set(msg.obj, intent);
                                }
                                Log.d("******", "100:" + ((Intent) intentField.get(msg.obj)).getComponent().toString());

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case 159:
                            Log.d("******", "159999");
                            try {
                                //获取mActivityCallbacks对象
                                Field mActivityCallbacksField = msg.obj.getClass().getDeclaredField("mActivityCallbacks");
                                mActivityCallbacksField.setAccessible(true);
                                List mActivityCallbacks = (List) mActivityCallbacksField.get(msg.obj);
                                for (int i = 0; i < mActivityCallbacks.size(); i++) {
                                    if (mActivityCallbacks.get(i).getClass().getName().equals("android.app.servertransaction.LaunchActivityItem")) {
                                        Object launchActivityItem = mActivityCallbacks.get(i);
                                        //获取启动代理的intent
                                        Field mIntentField = launchActivityItem.getClass().getDeclaredField("mIntent");
                                        mIntentField.setAccessible(true);
                                        Intent proxyIntent = (Intent) mIntentField.get(launchActivityItem);
                                        Intent intent = proxyIntent.getParcelableExtra(TARGET_INTENT);
                                        if (intent != null) {
                                            Log.d("******", "159999:" + intent.getComponent().getClassName());
                                            mIntentField.set(launchActivityItem, intent);
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                    return false;
                }
            };
            mCallBackField.set(mH, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
