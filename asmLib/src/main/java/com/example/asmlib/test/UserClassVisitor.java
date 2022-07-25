package com.example.asmlib.test;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class UserClassVisitor extends ClassVisitor {
    public UserClassVisitor(int api) {
        super(api);
    }

    public UserClassVisitor(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {

        MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
        if (name.equals("print")) {
            return new UserMethod(Opcodes.ASM9, methodVisitor, access, name, descriptor);
        }
        return methodVisitor;


    }
}
