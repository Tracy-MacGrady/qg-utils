package com.qgclient.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

public class NetUtils {
    public static boolean checkNet(Context context) {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                // 获取网络连接管理的对象
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    // 判断当前网络是否已经连接
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            DebugUtils.exception(e);
        }
        return false;
    }

    public static boolean getWifi(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // //mobile 3G Data Network
        // State mobile =
        // connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        // DebugUtils.debug("mobile net:"+mobile.toString());
        // wifi
        NetworkInfo networkInfo = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);// .getState();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        // DebugUtils.debug("wifi net:"+wifi.toString());
        return false;
    }

    public static final int NETWORK_TYPE_NONE = 0;
    public static final int NETWORK_TYPE_2G = NETWORK_TYPE_NONE + 1;
    public static final int NETWORK_TYPE_3G = NETWORK_TYPE_2G + 1;
    public static final int NETWORK_TYPE_MOBILE = NETWORK_TYPE_3G + 1;
    public static final int NETWORK_TYPE_4G = NETWORK_TYPE_MOBILE + 1;
    public static final int NETWORK_TYPE_WIFI = NETWORK_TYPE_MOBILE + 1;
    public static final int NETWORK_TYPE_OTHER = NETWORK_TYPE_WIFI + 1;

    /**
     * 判断网络类型
     */
    public static String getNetWorkType(Context context) {
        ConnectivityManager connectivityMgr = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityMgr.getActiveNetworkInfo();
        if (networkInfo == null) {
            return "无网络";
        }

        if (ConnectivityManager.TYPE_MOBILE == networkInfo.getType()) {
            TelephonyManager telMgr = (TelephonyManager) context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);

            int mobileType = telMgr.getNetworkType();
            switch (mobileType) {
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN:
                    return "2G";
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                case TelephonyManager.NETWORK_TYPE_EHRPD:
                case TelephonyManager.NETWORK_TYPE_HSPAP:
                    return "3G";
                case TelephonyManager.NETWORK_TYPE_LTE:
                    return "4G";
                default:
                    return "手机网络";
            }
        } else if (ConnectivityManager.TYPE_WIFI == networkInfo.getType()) {
            return "wifi";
        } else {
            return "其他网络";
        }
    }
}
