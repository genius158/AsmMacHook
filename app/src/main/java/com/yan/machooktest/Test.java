package com.yan.machooktest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import androidx.annotation.RequiresApi;

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

}
