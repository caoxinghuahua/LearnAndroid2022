package com.example.kotlinleran.kotlin

import kotlinx.coroutines.*
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ThreadFactory
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext

fun test() = runBlocking {

    val job = launch {
        repeat(1000) { i ->
            System.out.println("job: I'm sleeping $i ...")
            delay(500L)
        }
    }
    delay(1300L) // delay a bit
    System.out.println("main: I'm tired of waiting!")
    job.cancel() // cancels the job
    job.join() // waits for job's completion
    System.out.println("main: Now I can quit.")

    1111
}

fun main(args: Array<String>) {
//    println( "${test()}")


    newtest()
//    testArray()
}

//https://blog.csdn.net/lyabc123456/article/details/127800121

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