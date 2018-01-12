package com.qgclient.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 李健健 on 2017/7/10.
 */

public class ToastUtil {
    private static Toast toast;

    public static void showCustomView(Context context, View view, int timeLength) {
        if (toast != null) toast.cancel();
        toast = new Toast(context);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(timeLength);
        toast.show();
    }

    public static void show(Context context, String value, int timeLength) {
        if (toast != null) toast.cancel();
        toast = new Toast(context);
        View view = View.inflate(context, R.layout.toast_layout, null);
        TextView textView = view.findViewById(R.id.textview);
        textView.setText(value);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(timeLength);
        toast.show();
    }

    public static void show(Context context, String value) {
        if (toast != null) toast.cancel();
        toast = new Toast(context);
        View view = View.inflate(context, R.layout.toast_layout, null);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(value);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void cancel() {
        if (toast != null) toast.cancel();
    }

    public static void show(Context context, @StringRes int resId) {
        String string = context.getResources().getString(resId);
        show(context, string);
    }
}
