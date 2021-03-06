package com.qgclient.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络连接工具类
 */
public class NetworkUtils {

    public static final int NETWORK_NONE = -1;
    public static final int NETWORK_WIFI = 1;
    public static final int NETWORK_MOBILE = 0;
    public static final int NETWORK_MOBILE_CMNET = 2;
    public static final int NETWORK_MOBILE_CMWAP = 3;
    public static final int NETWORK_3G = 4;

    public static int getAPNType(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isAvailable()) {
            return NETWORK_NONE;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            // if (networkInfo.getExtraInfo().equals("cmnet") ||
            // networkInfo.getExtraInfo().equals("3gnet")) {
            // return NETWORK_MOBILE_CMNET;
            // } else {
            // return NETWORK_MOBILE_CMWAP;
            // }
            return NETWORK_MOBILE;
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            return NETWORK_WIFI;
        }
        return NETWORK_NONE;
    }

    public static boolean isWifi(Context context) {
        if (getAPNType(context) == NETWORK_WIFI) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isMobile(Context context) {
        if (getAPNType(context) == NETWORK_MOBILE) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean hasNetWork(Context context) {
        if (context == null) return false;
        return isMobile(context) || isWifi(context) || NetUtils.checkNet(context);
    }

}
