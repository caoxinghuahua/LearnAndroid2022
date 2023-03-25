package com.example.myapplication2.bigBitmap;

import android.os.MemoryFile;
import android.os.ParcelFileDescriptor;

import java.io.FileDescriptor;
import java.lang.reflect.Method;

public class TestBitmap {
    //https://www.jianshu.com/p/4a4bc36000fc
    public static ParcelFileDescriptor createMemoryFile(byte[] bytes) {


        MemoryFile memoryFile;
        try {
            // 创建 MemoryFile 对象，1024 是最大占用内存的大小
            memoryFile = new MemoryFile("myBitmap", bytes.length);
            // 获取文件描述符，因为方法被标注为 @hide，只能反射获取
            Method method=memoryFile.getClass().getDeclaredMethod("getFileDescriptor");
            method.setAccessible(true);
            FileDescriptor fileDescriptor= (FileDescriptor) method.invoke(memoryFile,null);
            if(fileDescriptor==null){
                System.out.println("获取匿名共享内存的 FileDescriptor 失败");
            }else{
                // 往共享内存中写入数据
                memoryFile.writeBytes(bytes, 0, 0, bytes.length);

                // 因为要跨进程传递，需要序列化 FileDescriptor
                return ParcelFileDescriptor.dup(fileDescriptor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
