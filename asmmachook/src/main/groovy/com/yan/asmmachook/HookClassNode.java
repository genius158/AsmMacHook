package com.yan.asmmachook;


import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.ArrayList;
import java.util.ListIterator;


/**
 * @author Bevan (Contact me: https://github.com/genius158)
 * @since 2021/3/12
 */
public class HookClassNode {
    private ClassNode classNode;

    public HookClassNode(ClassNode classNode) {
        this.classNode = classNode;
        for (MethodNode node : classNode.methods) {
            tryHook(node, Opcodes.INVOKEVIRTUAL, "android/net/wifi/WifiInfo", "getMacAddress", "()Ljava/lang/String;"
                    , Opcodes.INVOKESTATIC, "getTestMac");

            tryHook(node, Opcodes.INVOKEVIRTUAL, "android/telephony/TelephonyManager", "getMeid", "(I)Ljava/lang/String;"
                    , Opcodes.INVOKESTATIC, "getTestMeid");
            tryHook(node, Opcodes.INVOKEVIRTUAL, "android/telephony/TelephonyManager", "getMeid", "()Ljava/lang/String;"
                    , Opcodes.INVOKESTATIC, "getTestMeid");

            tryHook(node, Opcodes.INVOKEVIRTUAL, "android/telephony/TelephonyManager", "getDeviceId", "(I)Ljava/lang/String;"
                    , Opcodes.INVOKESTATIC, "getTestDeviceId");
            tryHook(node, Opcodes.INVOKEVIRTUAL, "android/telephony/TelephonyManager", "getDeviceId", "()Ljava/lang/String;"
                    , Opcodes.INVOKESTATIC, "getTestDeviceId");


            tryHook(node, Opcodes.INVOKEVIRTUAL, "android/telephony/TelephonyManager", "getSubscriberId", "(I)Ljava/lang/String;"
                    , Opcodes.INVOKESTATIC, "getTestSubscriberId");
            tryHook(node, Opcodes.INVOKEVIRTUAL, "android/telephony/TelephonyManager", "getSubscriberId", "()Ljava/lang/String;"
                    , Opcodes.INVOKESTATIC, "getTestSubscriberId");


            tryHook(node, Opcodes.INVOKEVIRTUAL, "android/telephony/TelephonyManager", "getImei", "(I)Ljava/lang/String;"
                    , Opcodes.INVOKESTATIC, "getTestImei");
            tryHook(node, Opcodes.INVOKEVIRTUAL, "android/telephony/TelephonyManager", "getImei", "()Ljava/lang/String;"
                    , Opcodes.INVOKESTATIC, "getTestImei");


            tryHook(node, Opcodes.INVOKEVIRTUAL, "android/telephony/TelephonyManager", "getSimSerialNumber", "(I)Ljava/lang/String;"
                    , Opcodes.INVOKESTATIC, "getTestSimSerialNumber");
            tryHook(node, Opcodes.INVOKEVIRTUAL, "android/telephony/TelephonyManager", "getSimSerialNumber", "()Ljava/lang/String;"
                    , Opcodes.INVOKESTATIC, "getTestSimSerialNumber");


            tryHook(node, Opcodes.INVOKEVIRTUAL, "java/net/NetworkInterface", "getHardwareAddress", "()[B"
                    , Opcodes.INVOKESTATIC, "getTestHardwareAddress");

            tryHook(node, Opcodes.INVOKEVIRTUAL, "android/bluetooth/BluetoothAdapter", "getAddress", "()Ljava/lang/String;"
                    , Opcodes.INVOKESTATIC, "getBluetoothTestAddress");


            tryHook(node, Opcodes.INVOKEVIRTUAL, "java/net/InetAddress", "getHostAddress", "()Ljava/lang/String;"
                    , Opcodes.INVOKESTATIC, "getTestInetHostAddress");


            tryHook(node, Opcodes.INVOKEVIRTUAL, "java/net/InetAddress", "getHostAddress", "()Ljava/lang/String;"
                    , Opcodes.INVOKESTATIC, "getTestInetHostAddress");

            tryHookAndroidId(node);
            
            tryHookMethodInvoke(node);
        }
    }

    private void tryHookMethodInvoke(MethodNode node) {
    }

    private void tryHookAndroidId(MethodNode node) {
        MethodInsnNode targetMethodNode = findTargetNode(node, Opcodes.INVOKESTATIC, "android/provider/Settings$Secure", "getString", "(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;");

        if (targetMethodNode != null) {
            ArrayList<LdcInsnNode> ldcInsnNodes = getLdcInsNodes(targetMethodNode, 1);
            if (!ldcInsnNodes.isEmpty() && "android_id".equals(ldcInsnNodes.get(0).cst)) {
                tryHook(node, targetMethodNode.getOpcode(), targetMethodNode.owner, targetMethodNode.name, targetMethodNode.desc, targetMethodNode.getOpcode(), "getSecureString");
            }
        }
    }


    private void tryHook(MethodNode node,
                         int oldOpcode,
                         String oldClassPath,
                         String oldMethod,
                         String oldMethodDes,
                         int newOpcode,
                         String newMethod
    ) {
        MethodInsnNode targetMethodNode = findTargetNode(node, oldOpcode, oldClassPath, oldMethod, oldMethodDes);
        boolean needHook = targetMethodNode != null;
        if (!needHook) return;

        if (needHook) {
            String des = targetMethodNode.desc;
            if (targetMethodNode.getOpcode() == Opcodes.INVOKEVIRTUAL && newOpcode == Opcodes.INVOKESTATIC) {
                des = des.replace("(", "(L" + targetMethodNode.owner + ";");
            }
            node.instructions.set(targetMethodNode, new MethodInsnNode(newOpcode,
                    HookExtension.HOOK_CLASS_PATH, newMethod, des, false));
        }
    }

    private MethodInsnNode findTargetNode(MethodNode node,
                                          int opcode,
                                          String classPath,
                                          String method,
                                          String methodDes) {
        ListIterator<AbstractInsnNode> absNodeIterator = node.instructions.iterator();

        MethodInsnNode targetMethodNode = null;
        while (absNodeIterator.hasNext()) {
            AbstractInsnNode absNode = absNodeIterator.next();
            if (absNode instanceof MethodInsnNode) {
                MethodInsnNode methodInsnNode = (MethodInsnNode) absNode;
                if (opcode == methodInsnNode.getOpcode()
                        && classPath.equals(methodInsnNode.owner)
                        && method.equals(methodInsnNode.name)
                        && methodDes.equals(methodInsnNode.desc)
                ) {
                    targetMethodNode = methodInsnNode;
                }
            }
        }
        return targetMethodNode;
    }

    /**
     * @param count 相对执行的方法只上 count个参数  方法参数倒序
     */
    private ArrayList<LdcInsnNode> getLdcInsNodes(MethodInsnNode node, int count) {
        ArrayList<LdcInsnNode> ldcInsNodes = new ArrayList<>();
        AbstractInsnNode per = node.getPrevious();
        while (per != null) {
            AbstractInsnNode cur = per;
            per = per.getPrevious();

            if (cur instanceof LdcInsnNode) {
                ldcInsNodes.add((LdcInsnNode) cur);
                if ((--count) <= 0) return ldcInsNodes;
            }
        }
        return ldcInsNodes;
    }


}
