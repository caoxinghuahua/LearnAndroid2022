package com.example.myapplication2.generic;

import java.util.ArrayList;
import java.util.List;

public class TestGeneric {
    public static void main(String[] args) {
//        对于无界通配符<?> extends 的通配符限定泛型，我们无法向里面添加元素(只可以添加null)，只能读取其中的元素。
//        对于无界通配符<?> super 的通配符限定泛型，我们可以读取其中的元素，但读取出来的元素会变为 Object 类型。

        List<? extends Object> list = new ArrayList<>();
//        list.add(123);error
        list.add(null);
        list.get(0);//无界通配符？ extend只能取，不能添加


        List<? super Object> list2 = new ArrayList<>();
        list2.add(123);//无界通配符？ super
        list2.get(0);
//        List<String>  list3=new ArrayList<>();
//        list3.add("ss");
//        list3.get(0);
//        new Fuction().reduce(list3);
    }



    class  A<T>{

    }
    interface B<T>{
        void test(T t);
        T testB();
    }


}


//https://blog.csdn.net/csdn_aiyang/article/details/107154081?spm=1001.2101.3001.6650.1&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7Edefault-1-107154081-blog-91480970.pc_relevant_multi_platform_whitelistv1_exp2&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7Edefault-1-107154081-blog-91480970.pc_relevant_multi_platform_whitelistv1_exp2&utm_relevant_index=2
class Fuction{
    public   <T> List<T> reduce(List<T> list){

        return list;
    }
}