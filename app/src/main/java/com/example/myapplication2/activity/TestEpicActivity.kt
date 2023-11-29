package com.example.myapplication2.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication2.databinding.ActivityTestEpicBinding

class TestEpicActivity : AppCompatActivity() {
    val TAG: String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTestEpicBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.createThreadTv.setOnClickListener {

        }
        binding.hookDexTv.setOnClickListener {

        }
        binding.hookThreadTv.setOnClickListener {
//            DexposedBridge.hookAllConstructors(Thread::class.java, object : XC_MethodHook() {
//                @Throws(Throwable::class)
//                protected fun afterHookedMethod(param: MethodHookParam) {
//                    super.afterHookedMethod(param)
//                    val thread = param.thisObject as Thread
//                    val clazz: Class<*> = thread.javaClass
//                    if (clazz != Thread::class.java) {
//                        Log.d(TAG, "found class extend Thread:$clazz")
//                        DexposedBridge.findAndHookMethod(clazz, "run", ThreadMethodHook())
//                    }
//                    Log.d(
//                        TAG,
//                        "Thread: " + thread.name + " class:" + thread.javaClass + " is created."
//                    )
//                }
//            })
//            DexposedBridge.findAndHookMethod(Thread::class.java, "run", ThreadMethodHook())
        }
    }
//    internal class ThreadMethodHook : XC_MethodHook() {
//        @Throws(Throwable::class)
//        protected fun beforeHookedMethod(param: MethodHookParam) {
//            super.beforeHookedMethod(param)
//            val t = param.thisObject as Thread
//            Log.i(TAG, "thread:$t, started..")
//        }
//
//        @Throws(Throwable::class)
//        protected fun afterHookedMethod(param: MethodHookParam) {
//            super.afterHookedMethod(param)
//            val t = param.thisObject as Thread
//            Log.i(TAG, "thread:$t, exit..")
//        }
//    }
}

