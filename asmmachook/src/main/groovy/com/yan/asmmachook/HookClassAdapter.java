package com.yan.asmmachook;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static com.yan.asmmachook.HookExtension.HOOK_CLASS;
import static com.yan.asmmachook.HookExtension.HOOK_INTERFACE_PATH;

public final class HookClassAdapter extends ClassVisitor {

    private String className = "";
    private String superClassName = "";
    private HookExtension hookExtension;

    HookClassAdapter(final ClassVisitor cv, HookExtension hookExtension) {
        super(Opcodes.ASM6, cv);
        this.hookExtension = hookExtension;
    }

    private boolean needIntercept = false;

    @Override
    public void visit(int version, int access, String name, String signature, String superName,
                      String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);

        if (name != null) {
            this.className = name.replace("/", ".");
        }
        if (superName != null) {
            this.superClassName = superName.replace("/", ".");
        }


        // 过滤自定义接口
        needIntercept = false;
        for (String face : interfaces) {
            if (HOOK_INTERFACE_PATH.equals(face.replace("/", "."))) {
                needIntercept = true;
                break;
            }
        }
        if (HOOK_CLASS.equals(className)) {
            needIntercept = true;
        }
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name,
                                     final String desc, final String signature, final String[] exceptions) {
        if (needIntercept) {
            return super.visitMethod(access, name, desc, signature, exceptions);
        }

        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        return mv == null ? null
                : new HookMethodAdapter(className, superClassName, name, access, desc, mv, hookExtension);
    }
}