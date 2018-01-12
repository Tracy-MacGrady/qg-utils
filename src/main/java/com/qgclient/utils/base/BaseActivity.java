package com.qgclient.utils.base;

import android.app.Activity;
import android.os.Bundle;

import com.qgclient.utils.ActivityUtil;
import com.qgclient.utils.ProgressUtil;
import com.qgclient.utils.ToastUtil;
import com.qgclient.utils.http.QGHttpClient;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2018/1/10.
 */

public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtil.getInstance().pushActivity(this);
        MobclickAgent.onPageStart(this.getClass().getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ToastUtil.cancel();
        ProgressUtil.getInstance().dismissProgressDialog();
        QGHttpClient.getInstance().getRequestQueue().cancelAll(this);
        QGHttpClient.getInstance().getRequestQueue().getCache().clear();
        ActivityUtil.getInstance().removeActivity(this);
        MobclickAgent.onPageEnd(this.getClass().getName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
