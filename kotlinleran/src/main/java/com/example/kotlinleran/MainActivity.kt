package com.example.kotlinleran

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val a by lazy{

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.gotoSecTv).setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java);
            startActivity(intent)
        }
//        Log.d("**sssss**","sign:"+getSignString());

        val liveData = MutableLiveData<String>()
        liveData.observe(this, {
            println("###$it###")
        })
        liveData.value = "huahua"
        lifecycleScope.launch { }
        flow<Int> {

        }
        fun getSignString(): String? {
            return packageManager.getPackageInfo(
                "com.drojian.alpha.fasting",
                PackageManager.GET_SIGNATURES
            )
                .signatures[0].toCharsString()
        }


    }
}