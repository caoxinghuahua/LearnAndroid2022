package com.example.myapplication2.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity

class MyFlutterActivity : FlutterActivity() {

    private var params: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        params = intent.getStringExtra(PARAMS);
    }

    override fun getInitialRoute(): String? {
        return params ?: super.getInitialRoute()
    }
    companion object {
        const val PARAMS = "params"
        fun startFlutterPage(context: Context, params: String) {
            val intent = Intent(context,MyFlutterActivity::class.java);
            intent.putExtra(PARAMS, params);
            context.startActivity(intent);
        }
    }
}