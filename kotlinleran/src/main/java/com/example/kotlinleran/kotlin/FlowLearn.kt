package com.example.kotlinleran.kotlin

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlin.coroutines.*

fun main() {
    testFlow()

}
//https://blog.csdn.net/lyabc123456/article/details/127800121


@OptIn(InternalCoroutinesApi::class)
fun testFlow() {
    runBlocking {
        //简单数据流
        val flow = flow<Int> {
//            kotlinx.coroutines.withContext(Dispatchers.IO) {
//                emit(222)
//            }
            emit(1)

        }

        flow.map {
            "aaa$it"
        }.filter {
            it == "aaa"
        }.collect {
            println(it)
        }
        flowOf(1, 2, 3).take(2).collect {
            println("take$it")
        }
        val list = listOf<Int>(1, 2, 3, 4, 5)
        list.asFlow().collect {
            println(it)
        }
        //复杂些的逻辑，比如线程切换
        channelFlow {
            kotlinx.coroutines.withContext(Dispatchers.IO) {
                send(11111)
            }
            send(1111)
        }.collect {
            println(it)
        }
//        callbackFlow {
//            send(123)
//            awaitClose {
//                close()
//            }
//        }.collect {
//            println(it)
//        }

    }
    val coroutineScope = CoroutineScope(Job())
    coroutineScope.launch {
        flow<String> {
            emit("ssss s")
        }.collect(object : FlowCollector<String> {
            override suspend fun emit(value: String) {
                println(value)
            }

        })
    }

    Thread.sleep(1000)
    println("end")

}