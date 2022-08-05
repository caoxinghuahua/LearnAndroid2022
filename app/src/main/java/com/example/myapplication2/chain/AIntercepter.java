package com.example.myapplication2.chain;

public class AIntercepter implements Intercepter{
    @Override
    public String intercept(Chain chain) {
        System.out.println("AIntercepter");
        chain.process();
        return "A";
    }
}
