package com.example.kotlinleran.kotlin

import android.util.Log
import org.json.JSONObject

class Test2023 {
  var name="hua"
  var age=18

}

fun main() {
    //let with run apply also
    var a="ss"
    val f=a.let {
        "dddd"
        "ssss"
    }
    println("**sssss** f is $f")
    val g=a.run {
        "gggg"
    }
    println("**sssss** g is $g")
    val h=with(a) {
        "hhhh"
    }
    println("**sssss** h is $h")

    try {
        var json=Test2023()
        val json1=json.apply {
            this.name="hahaha"
        }
        println("**sssss** name is ${json1.name}")
        val json2=json.also {
            it.age=20
        }
        println("**sssss** age is ${json2.age}")
    }catch (e:Exception){
        println("**sssss**  exception is $e")
    }

    val list= arrayListOf<String>("a","b","c")
    list.forEach ({it->println(it)})
    list.forEach ({println(it)})
    list.forEach (){ println(it) }
    list.forEach {
        println(it)
    }

}
