package com.example.kotlinleran.kotlin

import com.kwai.koom.base.async
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import java.util.concurrent.Executors
import kotlin.coroutines.*

fun test() = runBlocking {

    val job = launch {
        repeat(1000) { i ->
            System.out.println("job: I'm sleeping $i ...")
            delay(500L)
        }
    }
    delay(1600L) // delay a bit
    System.out.println("main: I'm tired of waiting!")
    job.cancel() // cancels the job
    job.join() // waits for job's completion
    System.out.println("main: Now I can quit.")

    1111
}

fun main(args: Array<String>) {
//    println("${test()}")

//    testCoroutineCancel()
//    newtest()
//    testArray()
//    testFlow()

    testCoroutine()
}

fun testCoroutineCancel() {
    //https://blog.csdn.net/shulianghan/article/details/128119243
    runBlocking {

        // 创建协程作用域
        val coroutineScope = CoroutineScope(Dispatchers.Default)

        val job1 = coroutineScope.launch {
            var startTime = System.currentTimeMillis();
            println("协程任务执行开始" + startTime)
            var i = 0
            //方式一
            while (i < 10000000) {
//                ensureActive()
                yield()
                var j = i + 1
                i++
                if (j == 10000000) {
                    println("最后一次循环 : j = ${j}")
                    println("协程任务执行完毕" + (System.currentTimeMillis() - startTime));
                }
            }

        }

        // 100ms 后取消协程作用域
        delay(1)

        println("取消协程任务")
        // 取消协程任务
        job1.cancelAndJoin()
        println("退出协程作用域")

    }
}



fun newtest() {

    val scope = CoroutineScope(Job() + Dispatchers.Main)

    GlobalScope.launch {

        withContext(Dispatchers.IO) {
            println("iooooo")
        }

        //异常捕获
        aa()
        //只定义作用域，启动协程需launch
        coroutineScope {
            launch {

            }
        }

        supervisorScope {

        }

        val d = async {
            println("delay")
//            delay(2000)
        }
        launch {
            println("child")
        }
//        println("${d.await()}")
//        println("await")
//        runBlocking {
//            println("runBlocking")
//
//        }


    }


    Executors.newSingleThreadExecutor().asCoroutineDispatcher().use {

    }
//    GlobalScope.launch(Dispatchers.IO) {
//
//        print(coroutineContext[Job])
//
//    }

}

suspend fun aa() {
    val exceptionHandler = CoroutineExceptionHandler { context, throwable ->
        println("exceptionHandler: $throwable")
    }
    val job = GlobalScope.launch(exceptionHandler) {
        throw ArithmeticException()
    }
    val deferred = GlobalScope.async(exceptionHandler) {
        throw IllegalStateException()
    }
    job.join()
    deferred.await()
}


fun testArray() {
    var intArray = intArrayOf(1, 2)
    var strArray = buildString {

    }
    strArray.plus("c").plus("b")
    print(strArray.toString())

}

suspend fun a() {
    coroutineScope {

    }
}


//}


//自定义协程拦截器
class My : ContinuationInterceptor {
    override val key: CoroutineContext.Key<*>
        get() = TODO("Not yet implemented")

    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
        TODO("Not yet implemented")
    }

}


fun testCoroutine() {
    //阻塞当前线程指导子协程执行完
//    runBlocking {
//        launch {
//            println("coroutline one")
//        }
//        launch {
//            println("coroutline two")
//        }
//    }
//    println("runBlocking finish")

    val job = GlobalScope.launch {
        delay(800)
        println("GlobalScope")
        doWork()
    }
//    job.cancel()
    runBlocking {
        job.join()
    }
    println("sss")



    val supendS = suspend {
        "Create Coroutine"
    }
    val completion = object : Continuation<String> {
        override val context: CoroutineContext
            get() = EmptyCoroutineContext

        override fun resumeWith(result: Result<String>) {
            println(result.getOrThrow())
        }
    }
    //https://aisia.moe/2018/02/08/kotlin-coroutine-kepa/
    //创建一个未启动的协程
    val coroutine = supendS.createCoroutine(completion)
    //启动协程一
    coroutine.resume(Unit)
    //startCoroutine 函数来创建并立即启动一个协程
    supendS.startCoroutine(completion)

    //协程挂起和恢复执行
    val suspendLambda= suspend {
        println("before suspend")
        //挂起
        val mm=suspendCoroutine<Int> {
           Thread.sleep(1000)
            it.resume(123)
        }
        println("after suspend $mm")
    }
    suspendLambda.startCoroutine(object :Continuation<Any>{
        override val context: CoroutineContext
            get() = EmptyCoroutineContext

        override fun resumeWith(result: Result<Any>) {
           println("resumeWith")
        }
    })

    Thread.sleep(1000)

}

suspend fun doWork() {
    return withContext(Dispatchers.IO) {
        val deferred1 = async {
            "AAA"
        }
        delay(1000)
        val deferred2 = async {
            "BBB"
        }

        println("${deferred1.await()}--${deferred2.await()}")

    }
}
