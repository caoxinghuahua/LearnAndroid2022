package com.example.myapplication2.chain;

import java.util.List;

public class Chain {

    public List<Intercepter> interceptors;
    public int index;

    public Chain(int index, List<Intercepter> intercepters) {
        this.index = index;
        this.interceptors = intercepters;
    }

    public String process() {
        if (index >= interceptors.size()) throw new AssertionError();

        System.out.println("process start");
        Chain chain = new Chain(index + 1, interceptors);
        Intercepter intercepter = interceptors.get(index);


        return intercepter.intercept(chain);
    }
}
