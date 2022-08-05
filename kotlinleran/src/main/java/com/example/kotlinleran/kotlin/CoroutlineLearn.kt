package com.example.kotlinleran.kotlin

import kotlinx.coroutines.*
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

}

fun main(args: Array<String>) {
//    test()


    newtest()
}

fun newtest() {
//    CoroutineScope(Dispatchers.Main).launch { }

    GlobalScope.launch(Dispatchers.IO) {

        print(coroutineContext[Job])

    }


}

suspend fun a() {
    coroutineScope {

    }
}


//}




//自定义协程拦截器
class My :ContinuationInterceptor{
    override val key: CoroutineContext.Key<*>
        get() = TODO("Not yet implemented")

    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
        TODO("Not yet implemented")
    }

}