package com.qgclient.utils;

import android.util.Log;

public class DebugUtils {
    public static final String TAG = "DebugUtils====";
    public static final boolean ISTRACE = true;

    public static void debug(String msg) {
        if (ISTRACE) {
            debug(TAG, msg);
        }
    }

    public static void debug(String tag, String msg) {
        if (ISTRACE) {
            Log.d(tag, msg == null ? "null" : msg);
        }
    }

    public static void syso(String msg) {
        if (ISTRACE) {
            System.out.println(msg);
        }
    }

    public static void error(String tag, String msg) {
        if (ISTRACE) {
            Log.e(tag, msg == null ? "null" : msg);
        }
    }

    public static void error(String msg) {
        if (ISTRACE) {
            error(TAG, msg);
        }
    }


    public static void exception(Exception e) {
        if (ISTRACE)
            e.printStackTrace();
    }

    public static void exception(Error e) {
        if (ISTRACE)
            e.printStackTrace();
    }

    public static void exception(String tag, Exception e) {
        error(tag, "异常:");
        error(tag, "Caused by:" + e.toString());
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            error(tag, stackTraceElement.toString());
        }
    }
}
