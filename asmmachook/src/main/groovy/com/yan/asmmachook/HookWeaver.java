package com.yan.asmmachook;

import com.quinn.hunter.transform.asm.BaseWeaver;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

public final class HookWeaver extends BaseWeaver {

  private HookExtension hookExtension;

  public HookWeaver(HookExtension hookExtension) {
    this.hookExtension = hookExtension;
  }

  @Override
  public boolean isWeavableClass(String fullQualifiedClassName) {
    return super.isWeavableClass(fullQualifiedClassName);
  }

  @Override
  protected ClassVisitor wrapClassWriter(ClassWriter classWriter) {
    return new HookClassAdapter(classWriter, hookExtension);
  }
}
