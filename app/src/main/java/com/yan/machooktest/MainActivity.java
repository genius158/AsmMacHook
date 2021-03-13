package com.yan.machooktest;

import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.yan.machook.IMacHook;
import com.yan.machook.MacHook;

import java.net.InetAddress;
import java.net.NetworkInterface;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WifiInfo wifiInfo = ((WifiManager) getApplicationContext().getSystemService("wifi")).getConnectionInfo();
        wifiInfo.getMacAddress();

        TelephonyManager var2 = (TelephonyManager) getApplicationContext().getSystemService("phone");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String s = var2.getMeid();
            String s2 = var2.getDeviceId();
        }
      Log.e("getLocalMacAddress","  "+Test2.getLocalMacAddressFromIp()) ;
        MacHook.setiMacHook(new IMacHook() {
            @Override
            public String getTestMac(WifiInfo wifiInfo) {
                return wifiInfo.getMacAddress();
            }

            @Override
            public String getTestDeviceId(TelephonyManager telephonyManager) {
                return null;
            }

            @Override
            public String getTestDeviceId(TelephonyManager telephonyManager, int i) {
                return null;
            }

            @Override
            public String getTestSubscriberId(TelephonyManager telephonyManager) {
                return null;
            }

            @Override
            public String getTestSubscriberId(TelephonyManager telephonyManager, int i) {
                return null;
            }

            @Override
            public String getTestImei(TelephonyManager telephonyManager) {
                return null;
            }

            @Override
            public String getTestImei(TelephonyManager telephonyManager, int i) {
                return null;
            }

            @Override
            public String getTestMeid(TelephonyManager telephonyManager) {
                return null;
            }

            @Override
            public String getTestMeid(TelephonyManager telephonyManager, int i) {
                return null;
            }

            @Override
            public String getTestSimSerialNumber(TelephonyManager telephonyManager) {
                return null;
            }

            @Override
            public String getTestSimSerialNumber(TelephonyManager telephonyManager, int i) {
                return null;
            }

            @Override
            public String getSecureString(ContentResolver contentResolver, String type) {
                return null;
            }

            @Override
            public byte[] getTestHardwareAddress(NetworkInterface networkInterface) {
                return new byte[0];
            }

            @Override
            public String getBluetoothTestAddress(BluetoothAdapter bluetoothAdapter) {
                return null;
            }

            @Override
            public String getTestInetHostAddress(InetAddress address) {
                return null;
            }
        });
    }
}