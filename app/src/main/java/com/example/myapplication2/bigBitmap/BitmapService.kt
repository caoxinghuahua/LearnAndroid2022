package com.example.myapplication2.bigBitmap

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.os.Parcel
import android.os.ParcelFileDescriptor
import android.util.Log
import android.widget.Toast
import com.example.myapplication2.utils.BitmapUtil
import java.io.FileInputStream

class BitmapService :Service() {

    private val TAG=BitmapService::class.java.name
    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG,"onBind")
        return BitmapBinder(baseContext)
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG,"onCreate")
//        Toast.makeText(baseContext, "service create", Toast.LENGTH_SHORT).show()
    }
    class BitmapBinder(val context: Context) : Binder() {

        override fun onTransact(code: Int, data: Parcel, reply: Parcel?, flags: Int): Boolean {
            val parent = super.onTransact(code, data, reply, flags)
            if (code != 1000) {
                return parent
            }
            // 读取 ParcelFileDescriptor 并转为 FileDescriptor
            val parcelFileDescriptor= data.readParcelable<ParcelFileDescriptor>(javaClass.classLoader)
                ?: return parent

            val fileDescriptor=parcelFileDescriptor.fileDescriptor
            // 读取共享内存中数据的大小
            val size = data.readInt()
            // 根据 FileDescriptor 创建 InputStream
            val input = FileInputStream(fileDescriptor)
            // 从 共享内存 中读取字节，并转为文字
            val bytes = input.readBytes()
//            val message = String(bytes, 0, size, Charsets.UTF_8)
            val bitmap= BitmapUtil.bytes2Bitmap(bytes)

            BitmapUtil.bitmap2Sdcard(bytes,context);
            Log.d("BitmapService","message:${bytes.size}")
            // 回复调用进程
            reply?.writeString("Server 端收到 FileDescriptor, 并且从共享内存中读到了：「」")

            return true
        }

    }
}