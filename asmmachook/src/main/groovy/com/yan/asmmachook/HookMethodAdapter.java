package com.yan.asmmachook;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.LocalVariablesSorter;

public final class HookMethodAdapter extends LocalVariablesSorter implements Opcodes {

    private String classNamePath;
    private String className;
    private String superClassName;
    private String methodDes;
    private String methodName;
    private HookExtension hookExtension;

    public HookMethodAdapter(String className, String superClassName, String methodName, int access,
                             String desc,
                             MethodVisitor mv, HookExtension hookExtension) {
        super(Opcodes.ASM5, access, desc, mv);
        this.classNamePath = className.replace(".", "/");
        this.className = className;
        this.superClassName = superClassName;
        this.methodName = methodName;
        this.methodDes = desc;
        this.hookExtension = hookExtension;
    }


    @Override
    public void visitLdcInsn(Object value) {

        // 华为里面反射访问了 高通的 TelephonyManager
        if ("android.telephony.MSimTelephonyManager".equals(value)) {
            // 这里，为了抛出 ClassNotFoundException
            value = "test.test.MSimTelephonyManager";
        }

        // adb shell 获取 mac hook
        if ("cat /sys/class/net/wlan0/address ".equals(value)) {
            // 直接打印unknown
            value = "echo unknown ";
        }

        super.visitLdcInsn(value);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
         if ("android/net/wifi/WifiInfo".equals(owner) && ("getMacAddress").equals(name) && "()Ljava/lang/String;".equals(desc)) {
            mv.visitMethodInsn(INVOKESTATIC, HookExtension.HOOK_CLASS_PATH, "getTestMac", "(L" + owner + ";)Ljava/lang/String;", false);
            return;
        }

        if (("android/telephony/TelephonyManager").equals(owner) && ("getMeid").equals(name) && "(I)Ljava/lang/String;".equals(desc)) {
            mv.visitMethodInsn(INVOKESTATIC, HookExtension.HOOK_CLASS_PATH, "getTestMeid", "(L" + owner + ";I)Ljava/lang/String;", false);
            return;
        }

        if (("android/telephony/TelephonyManager").equals(owner) && ("getMeid").equals(name) && "()Ljava/lang/String;".equals(desc)) {
            mv.visitMethodInsn(INVOKESTATIC, HookExtension.HOOK_CLASS_PATH, "getTestMeid", "(L" + owner + ";)Ljava/lang/String;", false);
            return;
        }

        if (("android/telephony/TelephonyManager").equals(owner) && ("getDeviceId").equals(name) && "(I)Ljava/lang/String;".equals(desc)) {
            mv.visitMethodInsn(INVOKESTATIC, HookExtension.HOOK_CLASS_PATH, "getTestDeviceId", "(L" + owner + ";I)Ljava/lang/String;", false);
            return;
        }

        if (("android/telephony/TelephonyManager").equals(owner) && ("getDeviceId").equals(name) && "()Ljava/lang/String;".equals(desc)) {
            mv.visitMethodInsn(INVOKESTATIC, HookExtension.HOOK_CLASS_PATH, "getTestDeviceId", "(L" + owner + ";)Ljava/lang/String;", false);
            return;
        }

        if ("android/telephony/TelephonyManager".equals(owner) && "getSubscriberId".equals(name) && "(I)Ljava/lang/String;".equals(desc)) {
            mv.visitMethodInsn(INVOKESTATIC, HookExtension.HOOK_CLASS_PATH, "getTestSubscriberId", "(L" + owner + ";I)Ljava/lang/String;", false);
            return;
        }

        if ("android/telephony/TelephonyManager".equals(owner) && "getSubscriberId".equals(name) && "()Ljava/lang/String;".equals(desc)) {
            mv.visitMethodInsn(INVOKESTATIC, HookExtension.HOOK_CLASS_PATH, "getTestSubscriberId", "(L" + owner + ";)Ljava/lang/String;", false);
            return;
        }

        if ("android/telephony/TelephonyManager".equals(owner) && ("getImei").equals(name) && "(I)Ljava/lang/String;".equals(desc)) {
            mv.visitMethodInsn(INVOKESTATIC, HookExtension.HOOK_CLASS_PATH, "getTestImei", "(L" + owner + ";I)Ljava/lang/String;", false);
            return;
        }

        if ("android/telephony/TelephonyManager".equals(owner) && ("getImei").equals(name) && "()Ljava/lang/String;".equals(desc)) {
            mv.visitMethodInsn(INVOKESTATIC, HookExtension.HOOK_CLASS_PATH, "getTestImei", "(L" + owner + ";)Ljava/lang/String;", false);
            return;
        }

        if ("android/telephony/TelephonyManager".equals(owner) && "getSimSerialNumber".equals(name) && "(I)Ljava/lang/String;".equals(desc)) {
            mv.visitMethodInsn(INVOKESTATIC, HookExtension.HOOK_CLASS_PATH, "getTestSimSerialNumber", "(L" + owner + ";I)Ljava/lang/String;", false);
            return;
        }

        if ("android/telephony/TelephonyManager".equals(owner) && "getSimSerialNumber".equals(name) && "()Ljava/lang/String;".equals(desc)) {
            mv.visitMethodInsn(INVOKESTATIC, HookExtension.HOOK_CLASS_PATH, "getTestSimSerialNumber", "(L" + owner + ";)Ljava/lang/String;", false);
            return;
        }


        //java.net.NetworkInterface.getHardwareAddress
        if ("java/net/NetworkInterface".equals(owner) && "getHardwareAddress".equals(name) && "()[B".equals(desc)) {
            mv.visitMethodInsn(INVOKESTATIC, HookExtension.HOOK_CLASS_PATH, "getTestHardwareAddress", "(L" + owner + ";)[B", false);
            return;
        }

        //android.bluetooth.BluetoothAdapter.getAddress
        if ("android/bluetooth/BluetoothAdapter".equals(owner) && "getAddress".equals(name) && "()Ljava/lang/String;".equals(desc)) {
            mv.visitMethodInsn(INVOKESTATIC, HookExtension.HOOK_CLASS_PATH, "getBluetoothTestAddress", "(L" + owner + ";)Ljava/lang/String;", false);
            return;
        }

        //        methodVisitor.visitMethodInsn(INVOKESTATIC, "android/provider/Settings$Secure", "getString", "(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;", false);
        // "android_id".equals(androidIdVisitRecord) &&
        if ("android/provider/Settings$Secure".equals(owner)
                && "getString".equals(name) && "(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;".equals(desc)) {
            mv.visitMethodInsn(INVOKESTATIC, HookExtension.HOOK_CLASS_PATH, "getSecureString", desc, false);
            return;
        }


        //methodVisitor.visitMethodInsn(INVOKESTATIC, "com/quvideo/mobile/platform/machook/MacHook", "getTestInetHostAddress", "(Ljava/lang/String;)Ljava/lang/String;", false);
        if ("java/net/InetAddress".equals(owner) && "getHostAddress".equals(name) && "()Ljava/lang/String;".equals(desc)) {
            mv.visitMethodInsn(INVOKESTATIC, HookExtension.HOOK_CLASS_PATH, "getTestInetHostAddress", "(L" + owner + ";)Ljava/lang/String;", false);
            return;
        }


        super.visitMethodInsn(opcode, owner, name, desc, itf);
    }

}
