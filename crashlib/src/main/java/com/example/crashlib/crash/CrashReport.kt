package com.example.crashlib.crash

import android.content.Context

object CrashReport {

    fun report(context: Context) {
        CrashHandler.init(context.applicationContext)
    }

    external fun testNativeCrash()
}