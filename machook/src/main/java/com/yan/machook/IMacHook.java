package com.yan.machook;


import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.net.wifi.WifiInfo;
import android.telephony.TelephonyManager;

import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * @author Bevan (Contact me: https://github.com/genius158)
 * @since 2021/3/6
 */
public interface IMacHook {


    String getTestMac(WifiInfo wifiInfo);

    String getTestDeviceId(TelephonyManager telephonyManager);

    String getTestDeviceId(TelephonyManager telephonyManager, int i);


    String getTestSubscriberId(TelephonyManager telephonyManager);

    String getTestSubscriberId(TelephonyManager telephonyManager, int i);

    String getTestImei(TelephonyManager telephonyManager);

    String getTestImei(TelephonyManager telephonyManager, int i);

    String getTestMeid(TelephonyManager telephonyManager);

    String getTestMeid(TelephonyManager telephonyManager, int i);

    String getTestSimSerialNumber(TelephonyManager telephonyManager);

    String getTestSimSerialNumber(TelephonyManager telephonyManager, int i);

    String getSecureString(ContentResolver contentResolver, String type);

    byte[] getTestHardwareAddress(NetworkInterface networkInterface);

    String getBluetoothTestAddress(BluetoothAdapter bluetoothAdapter);

    /**
     * socket 创建 也用到
     * 还是 原路返回
     * Returns the IP address string in textual presentation.
     *
     * @param address 调用对象
     * @return
     */
    String getTestInetHostAddress(InetAddress address);
}
