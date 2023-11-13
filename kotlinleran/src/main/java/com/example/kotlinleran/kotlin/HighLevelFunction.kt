package com.example.kotlinleran.kotlin

import java.io.BufferedReader
import java.io.FileReader

//https://blog.csdn.net/duncan891101/article/details/121313676


fun main() {
//    testEx()
    testReader()
}


fun testEx(){
    //
    var list = arrayOf(1, 2, 3, 4)
    //方法引用
    list.forEach(::testPrint)
    list.forEach(::println)
    //求阶乘
    val a = list.reduce { a, b ->
        a * b
    }
    //
    val bufferBuilder = list.fold(StringBuffer()) { sb, i ->
        sb.append(i)
    }

    println("阶乘:$a---$bufferBuilder")

    val list2 = listOf<String>()
    list2.filter(String::isNotEmpty)

    val stringList = arrayListOf<String>()
    val intList = stringList.map {
        it.toInt()
    }
    var newList = intList.map {
        it * 5 + 10
    }
// result=[1]，遍历找出符合条件，和filter有点像，但是filter不同地方在filter总是会遍历当前IntArray的所有元素，
// 而takeWhile在第一次发现predict不满足的时候就不再遍历，后面的元素即使满足条件也不会加入到结果中。
    val array = intArrayOf(2, 1, 8, 11, 22, 55, 66)
    val result = array.takeWhile {
        it % 2 != 0
    }
    println(result)

    val re = array.run {
        111111
        222222
    }
    stringList.apply {
        this.add("aaaaa")
    }


    var arrayList: ArrayList<Int>? = ArrayList()

    // 相当于apply和let的组合体，this指向的是list，Lambda表达式的最后一行表示返回值
    println(re)
    println(stringList)

    val addResultSize = arrayList?.run {
        this.add(60)
        this.size
    }

    println(addResultSize)

    // 和run函数一样，区别是run是扩展函数，而with是普通函数，调用方式不同
    val addResultSize2 = with(arrayList!!) {
        this.add(80)
        this.size
    }
    println(addResultSize2)

}

//高阶函数
fun number(num1: Int, num2: Int, block: (n1: Int, n2: Int) -> Int): Int {
    return block(num1, num2)
}

//lamda
val result = number(1, 2) { n1, n2 ->
    n1 + n2
}
val result2 = number(2, 8) { n1, n2 ->
    n1 * n2
}

fun testPrint(content: Any) {
    println(content)
}


fun testReader(){
    try {
        BufferedReader(FileReader("build.gradle")).use {
            var readLine: String?
            while (true) {
                readLine = readLine() ?: break
                println(readLine)
            }
        }
    }catch (e:Exception){
        println("Exception:::$e")
    }

}