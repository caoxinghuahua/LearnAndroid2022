package com.example.myapplication2.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication2.R
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class ActivityB : AppCompatActivity() {
    private val TAG: String = "ActivityB"
    val a by lazy{

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)
        Log.d(TAG, "onCreate")
        startActivity(Intent(this,MainActivity::class.java))

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d(TAG, "onNewIntent")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "onRestoreInstanceState")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")

    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")

    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")

    }


    private fun count(): Flow<Int> = flow {
        var x = 0
        while (true) {
            if (x > 20) {
                break
            }
            delay(500)
            Log.d("Coroutine", "emit on ${Thread.currentThread().name}")
            emit(x)
            x = x.plus(1)
        }
    }

    fun testFlow() {

//
//        flow 构建器函数会创建数据流；emit 函数发送新值至数据流；map函数修改数据流；collect函数收集数据流；catch函数捕获异常。
//        map等属于中间运算符，可在应用于数据流时，设置一系列暂不执行的链式运算，留待将来使用值时执行。仅将一个中间运算符应用于数据流不会启动数据流收集。
//        collect等终端运算符可触发数据流开始监听值。由于 collect 是挂起函数，因此需要在协程中执行。
//        catch函数只能捕获上游的异常，无法捕获下游的异常。
//        catch函数捕获到异常后，collect函数无法执行。可以考虑通过catch函数执行emit操作处理后续逻辑。
//        注意：flowOn 只会更改上游数据流的 CoroutineContext。这个特性和catch一样，catch也只能捕获上游数据流产生的异常。
        GlobalScope.launch {
            count().flowOn(Dispatchers.Unconfined
            ).map {
                "this is $it"
            }.flowOn(Dispatchers.IO)
                    .catch {
                ex ->
                ex.printStackTrace()
                Log.d("Coroutine", ex.toString())
                emit("-1")
            }
                    .collect {
                Log.d("Coroutine", it)
            }
        }
        SingIeInstance.getInstance//kotlin单例
        runBlocking {

        }
        GlobalScope.launch {

        }
        CoroutineScope(Dispatchers.Main).launch {

        }

    }



}