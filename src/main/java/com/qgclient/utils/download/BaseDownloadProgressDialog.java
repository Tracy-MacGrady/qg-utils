package com.qgclient.utils.download;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by 张学林 on 2017/7/11.
 */
public abstract class BaseDownloadProgressDialog extends Dialog {
    public BaseDownloadProgressDialog(Context context) {
        this(context, 0);
    }

    public BaseDownloadProgressDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public abstract void setMax(int max);

    public abstract void setProgress(final int progress);
}
