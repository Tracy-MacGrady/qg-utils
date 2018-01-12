package com.qgclient.utils.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Parcelable;
import android.util.Log;

import com.qgclient.utils.DebugUtils;
import com.qgclient.utils.NetUtils;
import com.qgclient.utils.listener.NetWorkStatusListener;

/**
 * Created by 李健健 on 2017/5/25.
 */

public class NetWorkReceiver extends BroadcastReceiver {
    private String getConnectionType(int type) {
        String connType = "";
        if (type == ConnectivityManager.TYPE_MOBILE) {
            connType = "3G网络数据";
        } else if (type == ConnectivityManager.TYPE_WIFI) {
            connType = "WIFI网络";
        }
        return connType;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        DebugUtils.error("NetWorkTAG", "===" + intent.getAction());
        // 监听网络连接，包括wifi和移动数据的打开和关闭,以及连接上可用的连接都会接到监听
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            //获取联网状态的NetworkInfo对象
            NetworkInfo info = intent
                    .getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (info != null) {
                //如果当前的网络连接成功并且网络连接可用
                if (NetworkInfo.State.CONNECTED == info.getState() && info.isAvailable()) {
                    NetWorkStatusListener.getInstance().sendToAllListenerConnect();
                    if (info.getType() == ConnectivityManager.TYPE_WIFI
                            || info.getType() == ConnectivityManager.TYPE_MOBILE) {
                        DebugUtils.error("NetWorkTAG", getConnectionType(info.getType()) + "连上");
                    }
                } else {
                    NetWorkStatusListener.getInstance().sendToAllListenerLost();
                    DebugUtils.error("NetWorkTAG", getConnectionType(info.getType()) + "断开");
                }
            }
        }
    }

}
