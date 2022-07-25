package com.example.myapplication;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.lang.reflect.Method;

public class LoadUtil {
    private static Resources resources;
    public static String apkPath = "/sdcard/plugin-release.apk";

    public static Resources getResource(Context context) {
        if (resources == null) {
            resources = loadResource(context);
        }
        return resources;
    }


    private static Resources loadResource(Context context) {
        //通过反射创建AssetManager,把插件的路径加入AssetPath
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPathMethod = AssetManager.class.getMethod("addAssetPath", String.class);
            addAssetPathMethod.invoke(assetManager, apkPath);
            // 如果传入的是Activity的 context 会不断循环，导致崩溃
            Resources resources = context.getResources();

            return new Resources(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
