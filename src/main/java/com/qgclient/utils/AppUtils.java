package com.qgclient.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 *
 */
public class AppUtils {
    /**
     * 获取版本编号
     *
     * @return
     */
    public static int getVersionCode(Context context) {
        PackageInfo info = null;
        int versionCode = 0;
        try {
            PackageManager manager = context.getPackageManager();
            info = manager.getPackageInfo(context.getPackageName(), 0);
            versionCode = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取版本编号
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        PackageInfo info = null;
        String versionName = "";
        try {
            PackageManager manager = context.getPackageManager();
            info = manager.getPackageInfo(context.getPackageName(), 0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public static int getApiLevel() {

        int level = Integer.valueOf(android.os.Build.VERSION.SDK);
        return level;
    }

    public static void exitApp(Context context) {

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        context.startActivity(intent);
    }

    public static void openKeyboard(Activity activity) {

        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void hiddenKeyboard(Activity activity) {

        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        try {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断设备是否为pad
     *
     * @param context
     * @return
     */
    public static boolean isPad(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * 获取应用的UserAgent
     *
     * @param context 应用的context
     * @param appname 应用的名称
     * @return 应用的ua字符串，context为空的时候返回""
     */
    public static String getUAStr(Context context, String appname) {

        PackageManager manager = null;
        if (context != null) {
            manager = context.getPackageManager();
        }
        LinkedHashMap<String, String> map = AppUtils.getUAMap(context);
        String UAStr = "";
        try {
            PackageInfo info;
            String version;
            if (manager != null) {
                info = manager.getPackageInfo(context.getPackageName(), 0);
                version = info.versionName;
            } else {
                version = null;
            }
            UAStr += appname + "/" + version + "/adr (";
            for (Map.Entry<String, String> entry : map.entrySet()) {
                UAStr += entry.getValue() + ",";
            }
            UAStr = UAStr.substring(0, UAStr.length() - 1) + ")";
        } catch (NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return UAStr;
    }

    @SuppressWarnings("unused")
    public static LinkedHashMap<String, String> getUAMap(Context context) {

        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("MOBILE_TYPE", null);
        map.put("IMEI", null);
        map.put("IMSI", null);
        map.put("SDK_VERSION", null);
        map.put("DM", null + "*" + null);
        map.put("INCHES", null);// 屏幕尺寸
        map.put("DENSITY", null);// 屏幕密度（每寸像素：120/160/240/320）
        map.put("NET_TYPE", null); // 网络类型
        map.put("CHANNAL", null); // 渠道名
        map.put("LANGUAGE", null); // 语言
        try {
            if (context != null) {
                map.put("MOBILE_TYPE", android.os.Build.MODEL);
                map.put("SDK_VERSION", android.os.Build.VERSION.RELEASE);
                map.put("NET_TYPE", NetUtils.getNetWorkType(context)); // 网络类型
                map.put("CHANNAL", AppUtils.getMetaDate(context, "UMENG_CHANNEL")); // 渠道名
                map.put("LANGUAGE", Locale.getDefault().getLanguage()); // 语言类型
                TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                DisplayMetrics dm = new DisplayMetrics();
                WindowManager winManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                winManager.getDefaultDisplay().getMetrics(dm);
                // SDK1.5是不支持这个参数的
                String deviceid = manager.getDeviceId();
                if (deviceid == null || deviceid.trim().equals("")) {
                    deviceid = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                }
                map.put("IMEI", deviceid);
                map.put("IMSI", manager.getSubscriberId());
                map.put("DM", dm.widthPixels + "*" + dm.heightPixels);
                map.put("INCHES", getScreenInches(context) + "");// 屏幕尺寸
                map.put("DENSITY", dm.densityDpi + "");// 屏幕密度（每寸像素：120/160/240/320）
            }
        } catch (Exception e) {
        } finally {
            return map;
        }
    }

    public static String getMetaDate(Context context, String data_name) {
        ApplicationInfo appInfo;
        String value = "";
        try {
            appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            value = appInfo.metaData.getString(data_name);
        } catch (NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return value;
    }

    public static double getScreenInches(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager winManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        winManager.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        int dens = dm.densityDpi;
        double wi = (double) width / (double) dens;
        double hi = (double) height / (double) dens;
        double x = Math.pow(wi, 2);
        double y = Math.pow(hi, 2);
        double screenInches = Math.sqrt(x + y);

        return screenInches;
    }
}
