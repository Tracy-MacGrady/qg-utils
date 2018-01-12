package com.qgclient.utils;

import android.content.Context;
import android.view.View;

import com.qgclient.utils.dialog.QGProgressDialog;

/**
 * Created by Administrator on 2018/1/10.
 */

public class ProgressUtil {
    private static ProgressUtil instance;
    private QGProgressDialog qgProgressDialog;

    public static ProgressUtil getInstance() {
        if (instance == null) {
            synchronized (ProgressUtil.class) {
                if (instance == null) instance = new ProgressUtil();
            }
        }
        return instance;
    }

    public void setMyProgressContentView(Context context, View view) {
        if (qgProgressDialog != null) {
            qgProgressDialog.dismiss();
            qgProgressDialog = null;
        }
        qgProgressDialog = new QGProgressDialog(context, view);
    }

    public void showProgressDialog(Context context) {
        if (qgProgressDialog == null) qgProgressDialog = new QGProgressDialog(context, null);
        qgProgressDialog.show();
    }

    public void dismissProgressDialog() {
        if (qgProgressDialog != null) {
            if (qgProgressDialog.isShowing()) qgProgressDialog.dismiss();
            qgProgressDialog = null;
        }
    }
}
