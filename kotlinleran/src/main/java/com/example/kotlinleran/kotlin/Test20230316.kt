package com.example.kotlinleran.kotlin

class Test20230316 {
}

fun main() {

    var person = MPerson("hua", 18)
    println(person.name)
}

class MPerson {
    var name: String? = null
    var age: Int = 0

    init {
        println("init")
    }

    constructor():this("a") {
        println("constructor")
    }

    constructor(name: String):this(name,0) {
        this.name = name
        println("constructor $name")
    }

    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
        println("constructor $name   $age")
    }

}