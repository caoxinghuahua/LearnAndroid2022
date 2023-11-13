package com.example.myapplication2.crash

import android.content.Context
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter

class CrashHandler : Thread.UncaughtExceptionHandler {


    companion object {
        private var mContext: Context? = null
        private var defaultExceptionHandler: Thread.UncaughtExceptionHandler? = null
        fun init(context: Context) {
            this.mContext = context
            defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
            Thread.setDefaultUncaughtExceptionHandler(CrashHandler())
        }
    }

    override fun uncaughtException(t: Thread, e: Throwable) {
        var fileDir = File(mContext?.externalCacheDir, "crashInfo")
        if (!fileDir.exists()) {
            fileDir.mkdirs()
        }
        var longTime = System.currentTimeMillis()
        var file = File(fileDir, "$longTime.txt")
        var printWriter: PrintWriter? = null
        try {
            printWriter = PrintWriter(FileWriter(file))
            printWriter.write("time:$longTime\n")
            printWriter.print("thread:${t.name}")
            e.printStackTrace(printWriter)
            printWriter.flush()
        } catch (e: Exception) {

        } finally {
            printWriter?.close()
            defaultExceptionHandler?.uncaughtException(t, e)
        }
    }
}