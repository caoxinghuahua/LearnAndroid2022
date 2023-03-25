package com.example.kotlinleran.java;

import com.example.kotlinleran.kotlin.K1;
import com.example.kotlinleran.kotlin.K1Kt;
import com.example.kotlinleran.kotlin.Single;

public class J1 {
    public static String in = "javassssss";

    public J1() {
        //java call kotlin
        new K1().show2();//kolin类内的方法
        K1Kt.show();//kotlin类外的方法
        Single.SingleInstance.INSTANCE.show();
    }

    public void test() {
        System.out.println("test java");
    }

}


class Person {
    private String name;
    private int age;

    public Person(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
    }

    private static class Builder {
        private String name;
        private int age;

        private Builder setName(String name) {
            this.name = name;
            return this;
        }

        private Builder seAge(int age) {
            this.age = age;
            return this;
        }

        private Person Build() {
            Person person = new Person(this);
            return person;
        }
    }
}