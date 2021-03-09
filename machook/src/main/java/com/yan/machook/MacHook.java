package com.yan.machook;

import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.net.wifi.WifiInfo;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * @author Bevan (Contact me: https://github.com/genius158)
 * @since 2021/3/6
 */
public class MacHook {
    private static IMacHook iMacHook;

    public static void setiMacHook(IMacHook iMacHook) {
        MacHook.iMacHook = iMacHook;
    }

    public static String getTestMac(WifiInfo wifiInfo) {
        Log.e("MacHook", "getTestMac getTestMac getTestMac");
        if (iMacHook != null) {
            return iMacHook.getTestMac(wifiInfo);
        }
        return "";
    }

    public static String getTestDeviceId(TelephonyManager telephonyManager) {
        Log.e("MacHook", "getTestDeviceId getTestDeviceId getTestDeviceId");
        if (iMacHook != null) {
            return iMacHook.getTestDeviceId(telephonyManager);
        }
        return "";
    }

    public static String getTestDeviceId(TelephonyManager telephonyManager, int i) {
        Log.e("MacHook", "getTestDeviceId getTestDeviceId getTestDeviceId");
        if (iMacHook != null) {
            return iMacHook.getTestDeviceId(telephonyManager, i);
        }
        return "";
    }


    public static String getTestSubscriberId(TelephonyManager telephonyManager) {
        Log.e("MacHook", "getTestSubscriberId getTestSubscriberId getTestSubscriberId");
        if (iMacHook != null) {
            return iMacHook.getTestSubscriberId(telephonyManager);
        }
        return "";
    }

    public static String getTestSubscriberId(TelephonyManager telephonyManager, int i) {
        Log.e("MacHook", "getTestSubscriberId getTestSubscriberId getTestSubscriberId");
        if (iMacHook != null) {
            return iMacHook.getTestSubscriberId(telephonyManager, i);
        }
        return "";
    }

    public static String getTestImei(TelephonyManager telephonyManager) {
        Log.e("MacHook", "getTestImei getTestImei getTestImei");
        if (iMacHook != null) {
            return iMacHook.getTestImei(telephonyManager);
        }
        return "";
    }

    public static String getTestImei(TelephonyManager telephonyManager, int i) {
        Log.e("MacHook", "getTestImei getTestImei getTestImei");
        if (iMacHook != null) {
            return iMacHook.getTestImei(telephonyManager, i);
        }
        return "";
    }

    public static String getTestMeid(TelephonyManager telephonyManager) {
        Log.e("MacHook", "getTestMeid getTestMeid getTestMeid");
        if (iMacHook != null) {
            return iMacHook.getTestImei(telephonyManager);
        }
        return "";
    }

    public static String getTestMeid(TelephonyManager telephonyManager, int i) {
        Log.e("MacHook", "getTestMeid getTestMeid getTestMeid");
        if (iMacHook != null) {
            return iMacHook.getTestImei(telephonyManager, i);
        }
        return "";
    }

    public static String getTestSimSerialNumber(TelephonyManager telephonyManager) {
        Log.e("MacHook", "getTestSimSerialNumber getTestSimSerialNumber getTestSimSerialNumber");
        if (iMacHook != null) {
            return iMacHook.getTestSimSerialNumber(telephonyManager);
        }
        return "";
    }

    public static String getTestSimSerialNumber(TelephonyManager telephonyManager, int i) {
        Log.e("MacHook", "getTestSimSerialNumber getTestSimSerialNumber getTestSimSerialNumber");
        if (iMacHook != null) {
            return iMacHook.getTestSimSerialNumber(telephonyManager, i);
        }
        return "";
    }

    public static String getSecureString(ContentResolver contentResolver, String type) {
        if (!"android_id".equals(type)) return Settings.Secure.getString(contentResolver, type);

        Log.e("MacHook", "getTestAndroidId getTestAndroidId getTestAndroidId");
        if (iMacHook != null) {
            return iMacHook.getSecureString(contentResolver, type);
        }
        return "";
    }

    public static byte[] getTestHardwareAddress(NetworkInterface networkInterface) {
        Log.e("MacHook", "getTestHardwareAddress getTestHardwareAddress getTestHardwareAddress");
        if (iMacHook != null) {
            return iMacHook.getTestHardwareAddress(networkInterface);
        }
        return "".getBytes();
    }

    public static String getBluetoothTestAddress(BluetoothAdapter bluetoothAdapter) {
        Log.e("MacHook", "getBluetoothTestAddress getBluetoothTestAddress getBluetoothTestAddress");
        if (iMacHook != null) {
            return iMacHook.getBluetoothTestAddress(bluetoothAdapter);
        }
        return "";
    }

    /**
     * socket 创建 也用到
     * 还是 原路返回
     * Returns the IP address string in textual presentation.
     *
     * @param address 调用对象
     * @return
     */
    public static String getTestInetHostAddress(InetAddress address) {
        Log.e("MacHook", "getTestInetHostAddress getTestInetHostAddress getTestInetHostAddress");
        return address.getHostAddress();
    }

    public static Object macInvoke(Method method, Object object, Object... params) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (object instanceof WifiInfo) {
            if ("getMacAddress".equals(method.getName())) {
                if (params.length == 0) return getTestMac((WifiInfo) object);
            }
        }

        if (object instanceof TelephonyManager) {
            if ("getMeid".equals(method.getName()) && params.length == 0) {
                return getTestMeid((TelephonyManager) object);
            }
            if ("getMeid".equals(method.getName()) && params.length == 1 && params[0] instanceof Integer) {
                return getTestMeid((TelephonyManager) object, (int) params[0]);
            }
            if ("getDeviceId".equals(method.getName()) && params.length == 0) {
                return getTestDeviceId((TelephonyManager) object);
            }
            if ("getDeviceId".equals(method.getName()) && params.length == 1 && params[0] instanceof Integer) {
                return getTestDeviceId((TelephonyManager) object, (int) params[0]);
            }
            if ("getSubscriberId".equals(method.getName()) && params.length == 0) {
                return getTestSubscriberId((TelephonyManager) object);
            }
            if ("getSubscriberId".equals(method.getName()) && params.length == 1 && params[0] instanceof Integer) {
                return getTestSubscriberId((TelephonyManager) object, (int) params[0]);
            }
            if ("getImei".equals(method.getName()) && params.length == 0) {
                return getTestImei((TelephonyManager) object);
            }
            if ("getImei".equals(method.getName()) && params.length == 1 && params[0] instanceof Integer) {
                return getTestImei((TelephonyManager) object, (int) params[0]);
            }
            if ("getSimSerialNumber".equals(method.getName()) && params.length == 0) {
                return getTestSimSerialNumber((TelephonyManager) object);
            }
            if ("getSimSerialNumber".equals(method.getName()) && params.length == 1 && params[0] instanceof Integer) {
                return getTestSimSerialNumber((TelephonyManager) object, (int) params[0]);
            }
        }

        if (object instanceof NetworkInterface) {
            if ("getHardwareAddress".equals(method.getName()) && params.length == 0) {
                return getTestHardwareAddress((NetworkInterface) object);
            }
        }

        if (object instanceof BluetoothAdapter) {
            if ("getAddress".equals(method.getName()) && params.length == 0) {
                return getBluetoothTestAddress((BluetoothAdapter) object);
            }
        }

        return method.invoke(object, params);
    }
}
