package com.example.kotlinleran.java;

import com.example.kotlinleran.kotlin.Single;

import org.jetbrains.annotations.Nullable;

public class Construct {
    public static void main(String[] args) {
       PersonS personS=new  PersonS.Builder().setName("aaa").seAge(18).Build();
       System.out.println("name:"+personS.getName()+"---age:"+personS.getAge());
        String a=null;
        Single.SingleInstance.INSTANCE.test(a);
    }


}
