package com.yan.machooktest;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import androidx.core.app.ActivityCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeviceInfo {
    public static final String TAG = "DeviceInfo";
    public static final String DEVICE_INFO_IMEI = "IMEI";
    public static final String DEVICE_INFO_MAC_ADDRESS = "MAC_ADDRESS";
    private static String mImei;
    private static String mImsi;
    private static String mMac;
    private static String mLocalIp;
    private static String mRemoteIp;
    private static float sPixelDensity = -1.0F;
    private static String mOpenUDID;

    public DeviceInfo() {
    }

    public static String getPhoneIMEI(Context context) {
        mImei = getIMEI(context);
        return mImei;
    }

    private static String getIMEI(Context context) {
        String mImei = "";

        try {
            if (ActivityCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE") == 0) {
            }
        } catch (Exception var3) {
        }

        if (mImei.trim().length() <= 1) {
            mImei = "XYI" + UUID.randomUUID().toString();
        }

        return mImei;
    }

    public static String getLocalMacAddress(Context context) {
        mMac = getMacAddr(context);
        return mMac;
    }

    private static String getMacAddr(Context context) {
        String mMac = "";

        try {
            WifiManager wifi = (WifiManager) context.getSystemService("wifi");
            WifiInfo info = null;
            if (wifi != null) {
                info = wifi.getConnectionInfo();
            }

            if (info != null) {
                mMac = info.getMacAddress();
            }
        } catch (Exception var4) {
        }

        if (null == mMac || mMac.trim().length() <= 1) {
            mMac = "XYM" + UUID.randomUUID().toString();
        }

        return mMac;
    }

    public static String getMacAddrV2() {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();

            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface) networkInterfaces.nextElement();
                byte[] hardwareAddress = networkInterface.getHardwareAddress();
                if (hardwareAddress != null && hardwareAddress.length != 0) {
                    StringBuilder stringBuffer = new StringBuilder();
                    byte[] var4 = hardwareAddress;
                    int var5 = hardwareAddress.length;

                    for (int var6 = 0; var6 < var5; ++var6) {
                        byte hardwareAddres = var4[var6];
                        stringBuffer.append(String.format("%02X:", hardwareAddres));
                    }

                    if (stringBuffer.length() > 0) {
                        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                    }

                    return stringBuffer.toString();
                }
            }
        } catch (SocketException var8) {
        } catch (Exception var9) {
        }

        return "";
    }

    public static String getLocalIpAddress() {
        if (mLocalIp == null) {
            try {
                Enumeration en = NetworkInterface.getNetworkInterfaces();

                while (en.hasMoreElements()) {
                    NetworkInterface intf = (NetworkInterface) en.nextElement();
                    Enumeration enumIpAddr = intf.getInetAddresses();

                    while (enumIpAddr.hasMoreElements()) {
                        InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            mLocalIp = inetAddress.getHostAddress().toString();
                        }
                    }
                }
            } catch (SocketException var4) {
            }
        }

        return mLocalIp;
    }

    public static String GetNetIp(String ipaddr) {
        if (mRemoteIp == null) {
            if (TextUtils.isEmpty(ipaddr)) {
                ipaddr = "http://checkip.dyndns.org/";
            }

            URL infoUrl = null;
            InputStream inStream = null;

            try {
                infoUrl = new URL(ipaddr);
                URLConnection connection = infoUrl.openConnection();
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                int responseCode = httpConnection.getResponseCode();
                if (responseCode == 200) {
                    inStream = httpConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
                    StringBuilder strber = new StringBuilder();
                    String line = null;

                    while ((line = reader.readLine()) != null) {
                        strber.append(line + "\n");
                    }

                    inStream.close();
                    String html = strber.toString();
                    Pattern pattern = Pattern.compile("(\\d{1,3})[.](\\d{1,3})[.](\\d{1,3})[.](\\d{1,3})", 2);

                    for (Matcher matcher = pattern.matcher(html); matcher.find(); mRemoteIp = matcher.group(0)) {
                    }
                }
            } catch (MalformedURLException var12) {
                var12.printStackTrace();
            } catch (IOException var13) {
                var13.printStackTrace();
            } catch (Exception var14) {
                var14.printStackTrace();
            }
        }

        return mRemoteIp;
    }

    public static String getModule() {
        return Build.MODEL;
    }

    public static String getSDK() {
        return String.valueOf(VERSION.SDK_INT);
    }

    public static String getSDKVersion() {
        return VERSION.RELEASE;
    }

    public static String getSubscriberId(Context context) {
        if (mImsi == null) {
            try {
                TelephonyManager tm = (TelephonyManager) context.getSystemService("phone");
                mImsi = tm.getSubscriberId();
                if (null == mImsi || mImsi.trim().length() == 0) {
                    mImsi = "000000";
                }
            } catch (Exception var2) {
                var2.printStackTrace();
            }
        }

        return mImsi;
    }

    public static String getPhoneNumber(Context context) {
        String strPhoneNumber = "0";
        return strPhoneNumber;
    }


    public static String getAndroidId(Context context) {
        String androidId = "" + Secure.getString(context.getContentResolver(), "android_id");
        return androidId;
    }
}
