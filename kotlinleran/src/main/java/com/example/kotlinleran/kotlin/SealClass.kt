package com.example.kotlinleran.kotlin

class SealClass {


    fun show(mm: MM){
        when(mm){
            is MM.A->{
                println("A")
            }
            is MM.B ->{
                println("A")
            }
        }
    }

}
//https://www.cnblogs.com/javaktolin/p/15601483.html
//对子类的限定，多个实例，相当枚举的扩展
sealed class MM{
    object A : MM()
    object B : MM()
}

fun main() {
}