package com.example.myapplication2.reflect;

public class Test1 {
    private String name = "test1";

    public Test1() {

    }

    private void show() {
        System.out.println("name:" + name);
    }

    public void setName(String name){
        this.name=name;
        show();
    }
}
