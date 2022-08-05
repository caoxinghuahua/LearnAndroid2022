package com.example.myapplication2.chain;

public class BIntercepter implements Intercepter {
    @Override
    public String intercept(Chain chain) {
        System.out.println("BIntercepter");
        chain.process();
        return "B" ;
    }
}
