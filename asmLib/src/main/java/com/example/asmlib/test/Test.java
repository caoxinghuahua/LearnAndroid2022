package com.example.asmlib.test;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.FileOutputStream;

public class Test {
    public static void main(String[] args) {
//        testUser();
        startHook();
    }

    private static void testUser() {
        User user = new User();
        user.name = "sqssas";
        user.print();
    }

    //Child 的 class文件路径
    public static final String LOCAL_PATH = "/Users/hua/AndroidStudioProjects/MyApplication2/asmLib/build/intermediates/javac/debug/classes/com/example/asmlib/test";

    private static void startHook() {
        try {
            //1.首先创建ClassReader,读取目标类User的内容
            ClassReader cr = new ClassReader(User.class.getName());
            //2.然后创建ClassWriter对象，
            ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
            ClassVisitor cv = new UserClassVisitor(Opcodes.ASM9, cw);
            cr.accept(cv, Opcodes.ASM9);
            // 获取生成的class文件对应的二进制流
            byte[] code = cw.toByteArray();
            //将二进制流写到out/下
            FileOutputStream fos = new FileOutputStream(LOCAL_PATH + "/User1.class");
            fos.write(code);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
