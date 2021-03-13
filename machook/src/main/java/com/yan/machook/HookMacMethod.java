package com.yan.machook;

import java.lang.reflect.Method;

/**
 * @author Bevan (Contact me: https://github.com/genius158)
 * @since 2021/3/12
 */
public class HookMacMethod {
    private Method method;

    public HookMacMethod(Method method) {
        this.method = method;
    }

    public Object invoke(Object obj, Object... args) {
        return "";
    }

}
