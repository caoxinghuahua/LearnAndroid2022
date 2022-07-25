package com.example.myapplication2.reflect;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map;

public class Main {
    Map<String, String> map;

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, NoSuchMethodException, ClassNotFoundException {
        testReflect();
        testFanxin();

//        List<String> list=new ArrayList[1];
    }

    /**
     * https://blog.csdn.net/qq_44715943/article/details/120587716
     */
    public static void testReflect() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Class class1 = Class.forName("com.example.myapplication2.reflect.Test1");
        Field name = class1.getDeclaredField("name");
        name.setAccessible(true);//反射修改私有属性访问
        Object obj = class1.newInstance();
        name.set(obj, "hua 111");//反射修改私有属性
        Method show = class1.getDeclaredMethod("show", new Class[]{});
        show.setAccessible(true);
        System.out.println(name.get(obj));

        show.invoke(obj, new Object[]{});//反射调用私有方法

        Method setName = class1.getDeclaredMethod("setName", String.class);
        setName.invoke(obj, "11111111");
    }

    /**
     * 反射获取泛型类型
     */
    public static void testFanxin() throws NoSuchFieldException {
        // TypeVariable
        Class cls = Student.class;
        Field kField = cls.getDeclaredField("key");
        Field valueField = cls.getDeclaredField("value");
        TypeVariable kType = (TypeVariable) kField.getGenericType();
        TypeVariable valueType = (TypeVariable) valueField.getGenericType();
        System.out.println("kType:" + kType.getName());
        System.out.println("valueType:" + valueType.getName());
        // getGenericDeclaration 方法
        System.out.println(kType.getGenericDeclaration());
        System.out.println(valueType.getGenericDeclaration());
        //getBounds
        System.out.println("K 的上界:"); // 有两个
        for (Type type : kType.getBounds()) {
            System.out.println(type);
        }
        System.out.println("V 的上界:"); // 有一个
        for (Type type : valueType.getBounds()) {
            System.out.println(type);
        }


        //ParameterizedType
        Field f = Main.class.getDeclaredField("map");
        System.out.println(f.getGenericType());
        ParameterizedType kp = (ParameterizedType) f.getGenericType();
        System.out.println(kp.getRawType());
        for (Type type : kp.getActualTypeArguments()) {
            System.out.println(type);
        }
    }


    /**
     * 泛型擦除带来的问题
     * https://www.jianshu.com/p/ac28fddbddd6
     */
    public void testReflectError() {
        //泛型不能显示的用到类型操作中
        //
    }
}
