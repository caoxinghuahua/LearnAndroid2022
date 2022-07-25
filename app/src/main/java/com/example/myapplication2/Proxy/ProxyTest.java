package com.example.myapplication2.Proxy;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.security.Key;
import java.util.Map;

public class ProxyTest {
    public static void main(String[] args) {
        ApiImpl api = new ApiImpl();
        Api apiTest = (Api) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(), new Class[]{Api.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return method.invoke(api, args);
            }
        });
        apiTest.print("动态代理");

        Person person = new Person();


        Map<Thread, StackTraceElement[]> map = Thread.currentThread().getAllStackTraces();

        StackTraceElement[] stackTraceElements;
        for (Thread key : map.keySet()) {
            System.out.println("name:" + key.getName() + "\n" + "StackTraceElement:");
            stackTraceElements = map.get(key);
            StringBuilder builder = new StringBuilder();
            for (int i = 0; stackTraceElements != null && i < stackTraceElements.length; i++) {
                builder.append(stackTraceElements[i]).append("\n");
            }
            System.out.println("stackTraceElements:" + builder.toString());

        }


    }

    static class ApiImpl implements Api {

        @Override
        public void print(String name) {
            System.out.println("name:" + name);
        }
    }


    public static class Person implements Serializable {
        private One one;
        private Two two;

        public Person() {
            this.one = new One();
            this.two = new Two();
        }
    }

    public static class One implements Serializable {

    }

    public static class Two {

    }


}
