package com.example.asmlib.test;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

public class TreeApiTest {
    public static void main(String[] args) {
        Class clazz = User.class;
        //获取处理class的路径
        String clazzFilePath = getClassFilePath(clazz);
        try {
            ClassReader classReader = new ClassReader(new FileInputStream(clazzFilePath));
//            ClassNode classNode = new ClassNode();
//            classReader.accept(classNode, Opcodes.ASM9);
//            List<FieldNode> fieldNodes = classNode.fields;
//            for (FieldNode fieldNode : fieldNodes) {
//                System.out.println("user 类的属性：" + fieldNode.name + "--" + fieldNode.desc);
//            }
//            List<MethodNode> methodNodes = classNode.methods;
//            for (MethodNode methodNode : methodNodes) {
//                System.out.println("user 类的方法：" + methodNode.name + "--" + methodNode.desc);
//            }

            classReader.accept(new ClassVisitor(Opcodes.ASM9) {
                @Override
                public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {

                    System.out.println("visitFiled:" + name);

                    return super.visitField(access, name, descriptor, signature, value);
                }

                @Override
                public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                    System.out.println("visitMethod:" + name);

                    return super.visitMethod(access, name, descriptor, signature, exceptions);
                }
            }, 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getClassFilePath(Class clazz) {
        // file:/Users/zhy/hongyang/repo/BlogDemo/app/build/intermediates/javac/debug/classes/
        String buildDir = clazz.getProtectionDomain().getCodeSource().getLocation().getFile();
        String fileName = clazz.getSimpleName() + ".class";
        File file = new File(buildDir + clazz.getPackage().getName().replaceAll("[.]", "/") + "/", fileName);
        return file.getAbsolutePath();
    }

    public static void testSocket() {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("127.0.0.1", 10000));
            socket.getInputStream();
            socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
