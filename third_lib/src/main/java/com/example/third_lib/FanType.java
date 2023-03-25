package com.example.third_lib;

public class FanType implements MyInterFace<String> {
    @Override
    public void show(String s) {
        System.out.println("s=" + s);
    }
}


interface MyInterFace<T> {
    void show(T s);
}