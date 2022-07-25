package com.example.myapplication2.test;

public class ParentClass {
    protected int id=100;
    public void show(){
        System.out.println("this is parent:"+id);
    }
}
class ChildClass extends ParentClass{
    protected int id=1000;
    @Override
    public void show() {
        System.out.println("this is ChildClass:"+id);
    }
}