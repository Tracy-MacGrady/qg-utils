package com.qgclient.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 屏幕相关 工具类
 */
public class WindowUtil {
    private static DisplayMetrics mDisplayMetrics;
    private static WindowUtil utils = null;

    private WindowUtil() {
    }

    public static WindowUtil getInstance(Context mContext) {
        if (utils == null)
            utils = new WindowUtil();
        if (mContext != null) {
            mDisplayMetrics = new DisplayMetrics();
            WindowManager mWindowManager = ((WindowManager) mContext
                    .getSystemService(Context.WINDOW_SERVICE));
            mWindowManager.getDefaultDisplay().getMetrics(mDisplayMetrics);
        }
        return utils;
    }

    /**
     * 获取屏幕密度 // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
     */
    public float getDensity() {
        return mDisplayMetrics.density;
    }

    /**
     * 获取屏幕宽度
     */
    public int getmScreenWidth() {
        return mDisplayMetrics.widthPixels;
    }

    /**
     * 获取屏幕高度
     */
    public int getmScreenHeight() {
        return mDisplayMetrics.heightPixels;
    }

    /**
     * dip变像素
     *
     * @param dpValue
     * @return
     */
    public int DipToPixels(float dpValue) {
        final float scale = getDensity();
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 像素变dip
     *
     * @param pxValue
     * @return
     */
    public int PixelsToDip(float pxValue) {
        final float scale = getDensity();
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 sp
     */
    public static int px2sp(float pxValue, Context context) {

        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);

    }

    /**
     */
    public static int sp2px(float spValue, Context context) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static double getInches(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager winManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
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

    /**
     * 获取屏幕
     */
    public static double getScreen(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay()
                .getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        int dens = dm.densityDpi;
        double wi = (double) width / (double) dens;
        double hi = (double) height / (double) dens;
        double x = Math.pow(wi, 2);
        double y = Math.pow(hi, 2);
        return Math.sqrt(x + y);
    }
}
