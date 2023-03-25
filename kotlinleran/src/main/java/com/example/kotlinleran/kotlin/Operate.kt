package com.example.kotlinleran.kotlin

//https://blog.csdn.net/zhaoyanjun6/article/details/120496977
//运算符重载
fun main() {
    val point=Point(1,2)
    println("${10+point}")
    val point2=Point(5,6)
    val point3=point.plus(point2)
    point3.show()
}



class Point(val x:Int,val y:Int){

    fun show(){
        println("x=$x,y=$y")
    }
    operator fun plus(other:Point):Point{
        return Point(this.x+other.x,this.y+other.y)
    }
}

operator fun Int.plus(point: Point):Int{
    return this+point.x+point.y
}