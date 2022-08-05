package com.example.myapplication2.chain;

public class CIntercepter implements Intercepter {
    @Override
    public String intercept(Chain chain) {
        System.out.println("CIntercepter");
        chain.process();
        return "C";
    }
}
