package com.yan.machooktest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

import androidx.annotation.RequiresApi;

import com.yan.machook.MacHook;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Bevan (Contact me: https://github.com/genius158)
 * @since 2021/3/6
 */
public class Test3 {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint({"MissingPermission", "NewApi"})
    public void Test5(Context var1) {
        TelephonyManager var2 = (TelephonyManager) var1.getApplicationContext().getSystemService("phone");
        try {
            Class ctm = Class.forName("android.telephony.TelephonyManager");
            Method method = ctm.getDeclaredMethod("getDeviceId");
            method.invoke(var2);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint({"MissingPermission", "NewApi"})
    public void Test6(Context var1) {
        TelephonyManager var2 = (TelephonyManager) var1.getApplicationContext().getSystemService("phone");
        try {
            Class ctm = Class.forName("android.telephony.TelephonyManager");
            Method method = ctm.getDeclaredMethod("getDeviceId");
            if (MacHook.methodInvokeHookCheck(method.getDeclaringClass())) {
                MacHook.macInvoke(method, var2);
            } else {
                method.invoke(var2);
            }

        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
