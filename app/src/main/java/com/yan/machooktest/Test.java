package com.yan.machooktest;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.yan.machook.MacHook;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Bevan (Contact me: https://github.com/genius158)
 * @since 2021/3/6
 */
public class Test {
    String d = "";

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint({"MissingPermission", "NewApi"})
    public void Test1(Context var1) {
        TelephonyManager var2 = (TelephonyManager) var1.getApplicationContext().getSystemService("phone");
        this.b(var2.getTypeAllocationCode(1));
        this.b(var2.getManufacturerCode(1));
        this.b(var2.getTypeAllocationCode(1));
        this.b(var2.getDeviceId(1));
//        this.b(MacHook.getTestDeviceId());
//        this.b(MacHook.getTestImei());
        this.a(var2.getImei(1));
        this.a(var2.getImei());
    }

//    @RequiresApi(api = Build.VERSION_CODES.M)
//    @SuppressLint({"MissingPermission", "NewApi"})
//    public static void Test4(Context var1) {
//        TelephonyManager var2 = (TelephonyManager) var1.getApplicationContext().getSystemService("phone");
//        try {
//            Class ctm = Class.forName("android.telephony.TelephonyManager");
//            Method method = ctm.getDeclaredMethod("getDeviceId",int.class);
//            method.invoke(var2,2);
//        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
//    }

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
            if (MacHook.methodInvokeHookCheck(method.getDeclaringClass())){
                MacHook.macInvoke(method,var2);
            }else {
                method.invoke(var2);
            }
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("MissingPermission")
    public void Test3(Context var1) {
        TelephonyManager var2 = (TelephonyManager) var1.getApplicationContext().getSystemService("phone");
        var2.getDeviceId();
        this.b(var2.getDeviceId());
        this.a(var2.getSubscriberId());
        WifiManager var3 = (WifiManager) var1.getApplicationContext().getSystemService("wifi");
        WifiInfo var4 = var3.getConnectionInfo();
        this.d = var4.getMacAddress();
    }

    public void b(String id) {
        id.getBytes();

    }

    public void a(String id) {

    }


    @SuppressLint({"MissingPermission", "HardwareIds"})
    public void Test2(Context var1) {
        TelephonyManager var2 = (TelephonyManager) var1.getApplicationContext().getSystemService("phone");
//        this.b(MacHook.getTestDeviceId());
//        this.a(MacHook.getTestSubscriberId());
        WifiManager var3 = (WifiManager) var1.getApplicationContext().getSystemService("wifi");
        WifiInfo var4 = var3.getConnectionInfo();
//        this.d = MacHook.getTestMac();
    }

    /**
     * 需要权限 <uses-permission android:name="android.permission.BLUETOOTH"/>
     **/


    public static String mac() {
        return BluetoothAdapter.getDefaultAdapter().getAddress();
    }

}
