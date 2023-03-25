package com.example.kotlinleran.java;

public class Construct {
    public static void main(String[] args) {
       PersonS personS=new  PersonS.Builder().setName("aaa").seAge(18).Build();
       System.out.println("name:"+personS.getName()+"---age:"+personS.getAge());

    }


}
