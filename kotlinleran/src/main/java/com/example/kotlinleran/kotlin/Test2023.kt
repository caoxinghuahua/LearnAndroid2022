package com.example.kotlinleran.kotlin



class Test2023(){
    var name:String?=null
    var age:Int=0
    constructor(name:String,age:Int):this(){
        this.name = name
        this.age = age
    }
}
fun main() {
    //let with run apply also
    var a = "ss"
    val f = a.let {
        "dddd"
        "ssss"
    }
    println("**sssss** f is $f")
    val g = a.run {
        "gggg"
    }
    println("**sssss** g is $g")
    val h = with(a) {
        "hhhh"
    }
    println("**sssss** h is $h")

    try {
        var json = Test2023()
        val json1 = json.apply {
            this.name = "hahaha"
        }
        println("**sssss** name is ${json1.name}")
        val json2 = json.also {
            it.age = 20
        }
        println("**sssss** age is ${json2.age}")
    } catch (e: Exception) {
        println("**sssss**  exception is $e")
    }

    val list = arrayListOf<String>("a", "b", "c")
    list.forEach({ it -> println(it) })
    list.forEach({ println(it) })
    list.forEach() { println(it) }
    list.forEach {
        println(it)
    }


    var s1 = "ssss";
    var s2 = "ssss"
    if (s1 == s2) {
        println("相等==")
    } else {
        println("不相等")
    }

    if (s1 === s2) {
        println("相等===")
    } else {
        println("不相等")
    }

    val sum1 = listOf<Int>(1, 2, 3).fold(5) { sum, element ->
        sum + element
    }
    val sum2 = listOf<Int>(1, 2, 3).reduce { sum, element ->
        sum + element
    }
    println("sum1:$sum1")
    println("sum2:$sum2")

}
