package com.example.myapplication2.blockCanary

import android.os.Handler
import android.os.Looper
import android.util.Printer
import java.util.concurrent.atomic.AtomicBoolean

object BlockManager {
    private var isStart: Boolean = false;
    private var startTime: Long = 0;
    private var handler: Handler = Handler(Looper.getMainLooper());
    private var runStart: AtomicBoolean = AtomicBoolean(false)
    private var runnable: Runnable = object : Runnable {
        override fun run() {
            if (!runStart.get()) return
            val buffer = StringBuffer()
            Thread.currentThread().stackTrace.forEach {
                buffer.append(it.toString()).append("\n")
            }
            println("stack:$buffer")
            Runnable { handler.postDelayed(this, 200) }.run()
        }

    }

    internal class MyPrinter : Printer {
        override fun println(x: String?) {

            x?.let {
                if (!isStart) {
                    isStart = true
                    startTime = System.currentTimeMillis()
                } else {
                    isStart = false
                    if ((System.currentTimeMillis() - startTime) > 1000) {
                        println("卡顿")
                        dumpTrace()
                    }
                }
            }
        }

    }

    fun register() {
        runStart.set(true)
        Looper.getMainLooper().setMessageLogging(MyPrinter())
    }

    fun unRegister() {
        runStart.set(false)
        handler.removeCallbacks(runnable)
    }


    fun dumpTrace() {
        handler.removeCallbacks(runnable)
        handler.postDelayed(runnable, 200)
    }
}