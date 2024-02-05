package com.example.kotlinleran.kotlin

import kotlinx.coroutines.runBlocking
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

fun main() {

    //高阶函数调用
    println("ss:" + compose(1) {
        return@compose it;
    })

    //seal class
    testSeal(SealPerson.Man)
    testEnum(Operators.MULTI)
    testList()
    testInline("aa") {
        println("inline $it")
    }

    //infix
    "hello world" plusTo "sssss"

    //挂起函数
    testRequest()
    val a = A1(name = "A", age = 1).copy()
    println("A1 copy ${a.name}")
}
//fun july() {
//    compose(1) {
//        return@compose it;
//    }
//}


fun compose(m: Int, block: (a: Int) -> Int): String {
    return "${block(m)}"
}

//数据类
data class A1( var name: String, var age: Int) {

}


//密封类 解决条件分值else无法抵达,密封类和子类必须在同一文件夹下（可见性）
sealed class SealPerson() {
    object Man : SealPerson()
    object Woman : SealPerson()
}

open class Common(){

}

//data class 不能继承


//class NN():Common(){
//
//}

fun testSeal(sealPerson: SealPerson) {

    when (sealPerson) {
        SealPerson.Man -> {
            println("seal is man")
        }
        SealPerson.Woman -> {
            println("seal is woman")
        }
    }
}

//枚举类
enum class Operators() {
    MINUS, PLUS, DIV, MULTI
}

fun testEnum(operators: Operators) {
    when (Operators.MINUS) {
        Operators.MINUS -> {
            println("method is minus")
        }
        Operators.PLUS -> {
            println("method is plus")
        }
        Operators.MULTI -> {
            println("method is multi")
        }
        Operators.DIV -> {
            println("method is div")
        }

    }
}

fun testList() {
    val list = listOf<String>("a", "b", "c")
    println("list :${list[1]}")
    println("list :${list.indexOf("a")}")
    val list2 = mutableListOf<Int>(1, 2, 3, 4, 5)
    println("mutableList:${list2.removeAt(2)}")
    val map = mapOf("a" to "a", "b" to "b")
    println("map:${map.get("a")}")
    val map2 = mutableMapOf("c" to "c", "d" to "d")
    println("mutmap:${map.get("d")}")
}

//扩展属性
val String.firstChar: Char
    get() {
        return get(0)
    }


//范型

class FZ<T> {
    fun add(a: T, b: T) {

    }

    fun <T> print1(m: T, n: T) {
        println("aa")
//        T::class.java
    }

    //泛型实化
    inline fun <reified T> print2() {
//        T::class.java
    }

    fun test() {
        val fz = FZ<Int>()
        fz.print1(1, "2")

    }
}

//內连
inline fun testInline(a: String, block: (String) -> Unit) {
    println("inline start")
    block(a)
    println("inline end")
}

//infix to语法糖 也属于扩展函数
infix fun String.plusTo(params: String) {
    println("$this $params")
}


//value class Colors(val intColor:Int){
//
//}

fun testRequest() {
//    val request=HttpRequest("http://www.baidu.com");
//    request.asyncRequest(object :HttpRequest.CallBack{
//        override fun success() {
//            println("request success")
//        }
//
//        override fun fail() {
//            println("request fail")
//        }
//
//    })
    runBlocking {
        try {
            testSuspend("http://www.baidu.com")
        } catch (e: Throwable) {

        }
    }
}


suspend fun testSuspend(url: String): String {
    return suspendCoroutine { continuation ->
        val request = HttpRequest("http://www.baidu.com");
        request.asyncRequest(object : HttpRequest.CallBack {
            override fun success(content: String) {
                println("request success")
                continuation.resume(content)
            }

            override fun fail(e: Throwable) {
                println("request fail")
                continuation.resumeWithException(e)
            }

        })
    }
}

class HttpRequest(private val url: String) {


    interface CallBack {
        fun success(content: String)
        fun fail(e: Throwable)

    }

    fun asyncRequest(callBack: CallBack) {
        println("SSS$url")
        callBack.success("success")
        callBack.fail(Throwable("sss"))
    }
}