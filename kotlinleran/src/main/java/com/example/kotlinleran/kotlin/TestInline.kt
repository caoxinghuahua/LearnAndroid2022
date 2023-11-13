package com.example.kotlinleran.kotlin




// https://www.jianshu.com/p/d30139e4e309
fun main() {
    helloWorld(
        {
            println("pre")
//            return
        },
        {
            println("middle")
        },
        {
            println("post")

        }
    )
}


inline fun helloWorld(preFun: () -> Unit,crossinline middleFun:()->Unit,noinline postFun: () -> Unit): () -> Unit {
    preFun()
    //内联函数里的函数类型的参数不允许这种间接调用，需要用crossinline,不能调用return
    test {
        middleFun()
    }
    println("hello")
    //当作返回值时不能內连,需要用noinline
    return postFun
}

fun  test(aa:()->Unit){
    println("test crossinline")
    aa()
}


//