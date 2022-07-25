package com.example.myapplication2.kotlin

import kotlinx.coroutines.*



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

fun main(args:Array<String>) {
    test()

}

//}