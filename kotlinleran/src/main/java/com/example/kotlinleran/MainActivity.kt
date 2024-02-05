package com.example.kotlinleran

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity() {
    val a by lazy {

    }
    lateinit var viewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.gotoSecTv).setOnClickListener {
//            val intent = Intent(this, SecondActivity::class.java);
//            startActivity(intent)
//            viewModel.data.value = MyBean("hua", 18)
//            testCoroutlineException()
              testFunction()
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
//        fun getSignString(): String? {
//            return packageManager.getPackageInfo(
//                "com.drojian.alpha.fasting",
//                PackageManager.GET_SIGNATURES
//            )
//                .signatures[0].toCharsString()
//        }

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)
            .create(MyViewModel::class.java)
        viewModel.data.observe(this, Observer {
            println("${it.name}+${it.age}")
        })



    }

    class MyBean(val name: String, val age: Int) {

    }

    class MyViewModel : ViewModel() {
        var data = MutableLiveData<MyBean>()
        fun test() {
            viewModelScope.launch {

            }
        }

    }

    fun testCoroutlineException() {

        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("aaaa:" + throwable)
        }
        val exceptionHandler1 = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("aaaa1:" + throwable)
        }
        GlobalScope.launch(exceptionHandler) {
            launch {

                println("exception MainScope before")


                MainScope().launch(exceptionHandler1) {

                    throw  java.lang.NullPointerException()

                }
                println("exception MainScope after")
                launch {
                    throw  java.lang.ArithmeticException()

                }

            }

            println("exception GlobalScope")
        }
    }


    private fun testFunction() {
        val scope = CoroutineScope(Job())
        //创建协程A
        val jobA = scope.launch(CoroutineName("A")) {
            //协程A中-创建子协程jobChildA 延时1秒执行
            val jobChildA = launch(CoroutineName("child-A")) {
                delay(1000)
                Log.d("KotlinActivity", "jobA的子类jobChildA执行...")
            }
            Log.d("KotlinActivity", "jobA自己执行...")
        }
        //创建协程B 延时1秒执行
        val jobB = scope.launch(CoroutineName("B")) {
            delay(1000)
            Log.d("KotlinActivity", "jobB自己执行...")
        }
    }

}