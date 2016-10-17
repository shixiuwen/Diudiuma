package com.shixia.diudiuma.common_utils;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

/**
 *
 */
public class NetUtils {
    public enum NetType {
        WIFI, CMNET, CMWAP, NONE
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] allNetworks = connectivityManager.getAllNetworkInfo();
        if (null != allNetworks) {
            for (int i = 0; i < allNetworks.length; i++) {
                if (allNetworks[i].getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isOpenGPS(Context mContext) {
        LocationManager lm = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * 网络是否打开
     */
    public static boolean isOpenNetWork(Context mContext) {
        ConnectivityManager cm = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo == null) {
            return false;
        }
        NetworkInfo.State state = networkInfo.getState();
        if (state == NetworkInfo.State.CONNECTED /*|| state == State.CONNECTING*/) {
            return true;
        }
        if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        } else if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    public static boolean isNetworkConnected(Context context) {
        if (null != context) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (null != activeNetworkInfo) {
                return activeNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public static boolean isWifiConnected(Context context) {
        if (null != context) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(connectivityManager.TYPE_WIFI);
            if (null != networkInfo) {
                boolean available = networkInfo.isAvailable();
                return available;
            }
        }
        return false;
    }

    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mobileNetworkInfo = connectivityManager.getNetworkInfo(connectivityManager.TYPE_MOBILE);
            if (null != mobileNetworkInfo) {
                return mobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public static int getConnectedType(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }

    public static NetType getAPNType(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null) {
            return NetType.NONE;
        }
        int nType = networkInfo.getType();

        if (nType == ConnectivityManager.TYPE_MOBILE) {
            if (networkInfo.getExtraInfo().toLowerCase(Locale.getDefault()).equals("cmnet")) {
                return NetType.CMNET;
            } else {
                return NetType.CMWAP;
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            return NetType.WIFI;
        }
        return NetType.NONE;
    }

    /**
     * WIFI网络开关
     *
     * @return 设置是否success
     */
    public static boolean toggleWiFi(boolean enabled, Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        WifiManager wm = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        return wm.setWifiEnabled(enabled);

    }

    public static void openMobileData(Context context, boolean enabled)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        Method method = manager.getClass()
                .getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
        method.setAccessible(true);
        method.invoke(manager, enabled);
    }
}
