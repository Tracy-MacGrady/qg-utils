package com.qgclient.utils.download;

import android.content.Context;
import android.content.DialogInterface;

import com.qgclient.utils.entity.InitSysInfo;


/**
 * Created by 李健健 on 2016/11/16.
 */
public class DownLoadApkUtil {
    private static DownLoadApkUtil util;

    private DownLoadApkUtil() {
    }

    public static DownLoadApkUtil getInstance() {
        if (util == null) util = new DownLoadApkUtil();
        return util;
    }

    public void showDialog(final Context context, final InitSysInfo.VersionInfo data, final DownLoadApkUtilInterface listener, BaseDownLoadDialog dialog) {
        if (context == null) return;
        dialog.setVersionInfo(data);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (listener != null) listener.onCancle();
            }
        });
        dialog.show();
    }

    public interface DownLoadApkUtilInterface {
        void onCancle();
    }
}
