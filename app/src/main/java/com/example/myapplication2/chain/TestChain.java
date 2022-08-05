package com.example.myapplication2.chain;

import java.util.ArrayList;
import java.util.List;

public class TestChain {
    public static void main(String[] args) {
        List<Intercepter> intercepters = new ArrayList<>();
        intercepters.add(new AIntercepter());
        intercepters.add(new BIntercepter());
        intercepters.add(new CIntercepter());

        Chain chain = new Chain(0, intercepters);
        try {
            chain.process();
        } catch (Exception e) {

        }


    }
}
