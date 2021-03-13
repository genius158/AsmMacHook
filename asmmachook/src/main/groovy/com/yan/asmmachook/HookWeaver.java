package com.yan.asmmachook;

import com.android.build.api.transform.QualifiedContent;
import com.quinn.hunter.amtransform.asm.BaseWeaver;

import org.objectweb.asm.tree.ClassNode;

public final class HookWeaver extends BaseWeaver {

    private HookExtension hookExtension;

    public HookWeaver(HookExtension hookExtension) {
        this.hookExtension = hookExtension;
    }

    @Override
    public boolean isWeavableClass(QualifiedContent input, String fullQualifiedClassName) {
        return super.isWeavableClass(input, fullQualifiedClassName);
    }

    @Override
    protected void visitClassNode(ClassNode classNode) {
        new HookClassNode(classNode);
    }
}
