package com.example.myapplication2.generic;

import java.util.ArrayList;
import java.util.List;

public class TestGeneric {
    public static void main(String[] args) {
//        对于无界通配符<?> extends 的通配符限定泛型，我们无法向里面添加元素(只可以添加null)，只能读取其中的元素。
//        对于无界通配符<?> super 的通配符限定泛型，我们可以读取其中的元素，但读取出来的元素会变为 Object 类型。

        List<? extends Object> list = new ArrayList<>();
//        list.add(123);error
        list.get(0);//无界通配符？ extend只能取，不能添加


        List<? super Object> list2 = new ArrayList<>();
        list2.add(123);//无界通配符？ super
        list2.get(0);
    }


}

