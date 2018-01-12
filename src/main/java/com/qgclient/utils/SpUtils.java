package com.qgclient.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.util.Map;

/**
 * SharedPreferences 工具类
 */
public class SpUtils {
    public static final String KEY_NAME = "pref_app";

    public static <T> boolean saveToLocal(Context mContext, String key, T t) {
        boolean ret = false;
        ret = saveToLocal(mContext, KEY_NAME, key, t);
        return ret;
    }

    public static <T> boolean saveToLocal(Context mContext, String name,
                                          String key, T t) {
        boolean ret = false;
        // TODO
        SharedPreferences sp;
        if (name == null)
            sp = getDefaultSharedPreferences(mContext);
        else
            sp = mContext.getSharedPreferences(name, Context.MODE_PRIVATE);

        if (t instanceof Boolean)
            ret = sp.edit().putBoolean(key, (Boolean) t).commit();
        if (t instanceof String)
            ret = sp.edit().putString(key, (String) t).commit();
        if (t instanceof Integer)
            ret = sp.edit().putInt(key, (Integer) t).commit();
        if (t instanceof Float)
            ret = sp.edit().putFloat(key, (Float) t).commit();
        if (t instanceof Long)
            ret = sp.edit().putLong(key, (Long) t).commit();

        return ret;
    }

    public static <T> T getFromLocal(Context mContext, String key,
                                     T defaultValue) {
        return getFromLocal(mContext, KEY_NAME, key, defaultValue);
    }

    /**
     * 从本地取回数据
     *
     * @param mContext
     * @param name         SharedPreferences名字 null为getDefaultSharedPreferences;
     * @param key
     * @param defaultValue 默认值
     * @return
     */
    @SuppressWarnings({"unchecked", "deprecation"})
    public static <T> T getFromLocal(Context mContext, String name, String key,
                                     T defaultValue) {
        SharedPreferences sp;
        if (TextUtils.isEmpty(name))
            sp = getDefaultSharedPreferences(mContext);
        else
            sp = mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
        Map<String, ?> map = sp.getAll();
        if (map == null)
            return defaultValue;

        if (map.get(key) == null)
            return defaultValue;

        return (T) map.get(key);

    }

    public static boolean deleteLocalValue(Context context, String name,
                                           String key) {
        SharedPreferences sp;
        if (name == null) {
            sp = getDefaultSharedPreferences(context);
        } else {
            sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        }
        return sp.edit().remove(key).commit();
    }

    public static boolean clearSp(String name, Context mContext) {
        SharedPreferences sp;
        if (name == null)
            sp = getDefaultSharedPreferences(mContext);
        else
            sp = mContext.getSharedPreferences(name,
                    Context.MODE_PRIVATE);
        return sp.edit().clear().commit();
    }

    private static SharedPreferences getDefaultSharedPreferences(
            Context mContext) {
        return PreferenceManager.getDefaultSharedPreferences(mContext);
    }
}
