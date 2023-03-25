package com.example.myapplication2.test;


import androidx.annotation.NonNull;

public class Test2 implements Cloneable {
    public static void main(String[] args) {

        System.out.println("class learn");


    }

    /**
     * 验证静态导包
     */
    public static void print() {
        System.out.println("static");
    }

    @NonNull
    @Override
    protected Test2 clone() throws CloneNotSupportedException {
        return (Test2) super.clone();
    }
}
