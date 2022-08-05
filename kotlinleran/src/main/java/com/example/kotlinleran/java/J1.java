package com.example.kotlinleran.java;

import com.example.kotlinleran.kotlin.K1;
import com.example.kotlinleran.kotlin.K1Kt;

public class J1 {
    public static String in="javassssss";
    public J1(){
        //java call kotlin
        new K1().show2();//kolin类内的方法
        K1Kt.show();//kotlin类外的方法
    }
    public void test(){
        System.out.println("test java");
    }

}
