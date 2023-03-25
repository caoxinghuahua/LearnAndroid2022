package com.example.myapplication2.annotion;

import androidx.annotation.DrawableRes;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class TestAnnotion {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        testAnnoation();
//        setDrawable(111);
    }

    /**
     * https://blog.csdn.net/qq_35091353/article/details/116424104
     * */
    public static void testAnnoation() throws ClassNotFoundException {
        Class classAn = Class.forName("com.example.myapplication2.annotion.MyAnnoation");
        Boolean flag = classAn.isAnnotationPresent(Description.class);
        if (flag) {
            Description description = (Description) classAn.getAnnotation(Description.class);
            System.out.println("description name:" + description.value());
        }
        Method[] methods = classAn.getMethods();
        Set<Method> set = new HashSet<Method>();
        for (Method method : methods) {

            if (method.isAnnotationPresent(Name.class)) {
                set.add(method);
            }
        }
        for (Method m : set) {
            Name name = m.getAnnotation(Name.class);
            System.out.println(name.f1());
            System.out.println("创建的社区:" + name.f2());
        }
    }

    public static void setDrawable(@DrawableRes int id){

    }
}
