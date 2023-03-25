package com.example.kotlinleran

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KProperty

class SecondActivity : AppCompatActivity() {

    //委托属性的使用
    private val a by lazy {
        test()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_second)
        Log.d("SecondActivity", "a:${a}")
        var p = P()
        Log.d("SecondActivity", "b修改前${p.b}")
        p.b= "ccccc"
        Log.d("SecondActivity", "b修改后${p.b}")    }

    private fun test(): String {
        return "lazy test"
    }
}

class P {
    //被委托类要实现getValue,setValue
    var b: String by AA()
}

class AA {
    private var aa: String = "bbb"

    operator fun getValue(p: P, property: KProperty<*>): String {
        return aa

    }

    operator fun setValue(p: P, property: KProperty<*>, s: String) {
        aa = s
    }

}