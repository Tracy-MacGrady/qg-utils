package com.qgclient.utils.listener;

import com.qgclient.utils.DebugUtils;
import com.qgclient.utils.interfaces.NetWorkStatusInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tracy-MacGrady on 2017/10/20.
 */

public class NetWorkStatusListener {
    private long lastSendToAllConnect = 0;
    private static NetWorkStatusListener instance;
    private List<NetWorkStatusInterface> listenerList = new ArrayList<>();

    public static NetWorkStatusListener getInstance() {
        if (instance == null) synchronized (NetWorkStatusListener.class) {
            if (instance == null) instance = new NetWorkStatusListener();
        }
        return instance;
    }

    public void addListener(NetWorkStatusInterface li) {
        this.listenerList.add(li);
    }

    public void removeListener(NetWorkStatusInterface li) {
        this.listenerList.remove(li);
    }

    public synchronized void sendToAllListenerConnect() {
        DebugUtils.error("this is listenerList.size()" + listenerList.size());
        if (System.currentTimeMillis() - lastSendToAllConnect < 1000) return;
        lastSendToAllConnect = System.currentTimeMillis();
        for (int i = 0, size = listenerList.size(); i < size; i++) {
            listenerList.get(i).onNetConnect();
        }
    }

    public void sendToAllListenerLost() {
        for (int i = 0, size = listenerList.size(); i < size; i++) {
            listenerList.get(i).onNetLost();
        }
    }
}
