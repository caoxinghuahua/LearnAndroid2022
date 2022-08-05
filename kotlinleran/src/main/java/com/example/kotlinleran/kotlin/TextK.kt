package com.example.kotlinleran.kotlin

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*


//class TextK {
//    @RequiresApi(Build.VERSION_CODES.N)
//    fun main(args: Array<String>) {
//
//        var arr = ArrayList<Int>()
//
//        for (i in 0..10) {
//            arr.add(i + 100)
//        }
//        arr.forEach {
//            System.out.print("a is$it")
//        }
//        arrayOfNulls<Int>(6)
//
//    }
//
//}
@RequiresApi(Build.VERSION_CODES.N)
fun main(args: Array<String>) {
    var arr = ArrayList<Int>()

    for (i in 0..5) {
        arr.add(i + 100)
    }
    arr.forEach {
        System.out.println("a is $it")
    }
    arrayOfNulls<Int>(6)
    //初始值的数组
    var arr2 = Array(5) { i ->
        (i * i).toString()
    }
    var arr3 = arrayOf(1, 2, 3, 4, 5)
    //数组的遍历
    for (item in arr3) {

    }

    val array3: IntArray = IntArray(5)//原生类型数组,不用装箱

    var map=mutableMapOf("a" to 1,"b" to 2)

}
