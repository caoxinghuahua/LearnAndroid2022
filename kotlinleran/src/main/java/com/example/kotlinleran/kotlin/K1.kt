package com.example.kotlinleran.kotlin

import com.example.kotlinleran.java.J1
import kotlin.reflect.KClass

class K1 {
    fun show2() {
        System.out.println("show2 kotlin")
        var s= mutableListOf<String>()
        for(a in s){

        }
    }


    constructor() {
        J1().test()

//        testClass(J1::class.java)
        testClass2(K1::class)
        J1.`in`//java中与kotlin中关键字冲突

    }

    fun testClass(cls: Class<J1>) {

    }

    fun testClass2(cls: KClass<K1>) {

    }
    fun String.toShow(){
        println("show")
    }
}

fun show() {
    System.out.println("show kotlin")

}
fun String.toShow2(){
    println("show")
}

