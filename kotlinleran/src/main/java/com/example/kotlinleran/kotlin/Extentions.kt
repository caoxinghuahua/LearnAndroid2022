package com.example.kotlinleran.kotlin


//https://www.jianshu.com/p/e056a59cb7f1

//顶级扩展函数
fun String.testExt() {
    println("String.testExt")
}

class Extentions {
    private var s: String = "sass"
    public var mm: String = "mmmm"
    fun test() {
        "A".testExt2()
        "B".testExt()
    }

    //成员扩展函数
    fun String.testExt2() {

        println("String.testExt2$s")
    }
}


fun Extentions.show() {
    //无法访问私有成员
//    println("$s$mm")
}

class Extentions2 {
    fun test2() {
        //类的成员扩展函数在其他类里无法调用
//        "A".testExt2()
        "B".testExt()
    }
}