package com.qgclient.utils.base;

import android.app.ActivityManager;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.android.volley.toolbox.Volley;
import com.qgclient.utils.http.QGHttpClient;

/**
 * Created by Administrator on 2018/1/9.
 */

public abstract class BaseApplication extends MultiDexApplication {
    public static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (isMainProcess()) {
            instance = this;
            QGHttpClient.getInstance().initHttpClient(Volley.newRequestQueue(this));
        }
    }

    public abstract void logout();

    /**
     * 获取当前进程名
     */
    private String getCurrentProcessName() {
        int pid = android.os.Process.myPid();
        String processName = "";
        ActivityManager manager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo process : manager.getRunningAppProcesses()) {
            if (process.pid == pid) {
                processName = process.processName;
            }
        }
        return processName;
    }

    /**
     * 包名判断是否为主进程
     *
     * @param
     * @return
     */
    public boolean isMainProcess() {
        return getApplicationContext().getPackageName().equals(getCurrentProcessName());
    }
}
