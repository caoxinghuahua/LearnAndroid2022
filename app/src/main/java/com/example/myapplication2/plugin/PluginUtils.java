package com.example.myapplication2.plugin;

import android.content.Context;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import dalvik.system.DexClassLoader;

public class PluginUtils {
    public static String apkPath = "/sdcard/plugin-release.apk";

    public static void loadPluginClass(Context context) {

        /***
         * 1.获取宿主的dexElements
         * 2.获取插件的dexElements
         * 3.合并两个dexElement
         * 4.合并后的赋值给宿主的dexElement
         *
         *
         * */

        try {
            Class<?> cls = Class.forName("dalvik.system.BaseDexClassLoader");
            Field pathListField = cls.getDeclaredField("pathList");
            pathListField.setAccessible(true);

            //获取dexPathList类
            Class<?> dexPathListCls = Class.forName("dalvik.system.DexPathList");
            Field dexElementsField = dexPathListCls.getDeclaredField("dexElements");
            dexElementsField.setAccessible(true);


            //获取宿主的classloader
            ClassLoader pathClassLoader = context.getClassLoader();
            //DexPathList类对象
            Object hostPathList = pathListField.get(pathClassLoader);
            //宿主类的dexElements
            Object[] hostDexElements = (Object[]) dexElementsField.get(hostPathList);

            //获取插件的classloader
            ClassLoader dexClassLoader = new DexClassLoader(apkPath, context.getCacheDir().getAbsolutePath(), null, pathClassLoader);
            //插件DexPathList类对象
            Object pluginPathList = pathListField.get(dexClassLoader);
            //插件类的dexElements
            Object[] pluginDexElements = (Object[]) dexElementsField.get(pluginPathList);


            //合并dexElements
            // 创建一个新数组
            Object newDexElements = Array.newInstance(hostDexElements.getClass().getComponentType(), hostDexElements.length + pluginDexElements.length);

            System.arraycopy(hostDexElements, 0, newDexElements, 0, hostDexElements.length);
            System.arraycopy(pluginDexElements, 0, newDexElements, hostDexElements.length, pluginDexElements.length);
            //赋值
            dexElementsField.set(hostPathList, newDexElements);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
