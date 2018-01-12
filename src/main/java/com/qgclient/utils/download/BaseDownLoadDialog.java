package com.qgclient.utils.download;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import com.qgclient.utils.entity.InitSysInfo;

/**
 * Created by 李健健 on 2017/7/13.
 */

public abstract class BaseDownLoadDialog extends Dialog {

    public BaseDownLoadDialog(Context context) {
        super(context);
    }

    public BaseDownLoadDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public BaseDownLoadDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public abstract void setVersionInfo(InitSysInfo.VersionInfo data);

    @Override
    public void setOnCancelListener(OnCancelListener listener) {
        super.setOnCancelListener(listener);
    }
}
