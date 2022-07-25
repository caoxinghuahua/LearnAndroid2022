package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.example.myapplication.R.*


class PluginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 暂时注释，没涉及到资源
//        setContentView(layout.activity_plugin)
        Log.d("******", "plugin Activity")
        val view: View = LayoutInflater.from(mContext).inflate(layout.activity_plugin, null)
        setContentView(view)
    }
}