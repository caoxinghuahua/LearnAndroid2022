package com.example.myapplication2.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapUtil {
    public static byte[] bitmap2bytes(Bitmap bitmap){
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        return baos.toByteArray();
    }
    public static Bitmap bytes2Bitmap(byte[] bytes){
        if(bytes.length==0)return null;
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }
    public static void bitmap2Sdcard(byte[] bytes, Context context){
        if(bytes.length==0)return;
        if(context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            Log.d("sss","111"+Thread.currentThread().getName());
            new Handler(Looper.getMainLooper()).post(() -> {
                Toast.makeText(context,"请授权sdcard的读写权限",Toast.LENGTH_SHORT).show();
            });
            return;
        }
        Log.d("sss","222");

        String dirPath=context.getFilesDir()+"/test/";
        File dirFile=new File(dirPath);
        if(!dirFile.exists()) dirFile.mkdir();
        File file=new File(dirPath+"bigBitmap.png");

        FileOutputStream fos=null;
        try {
            fos=new FileOutputStream(file);
            fos.write(bytes);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
