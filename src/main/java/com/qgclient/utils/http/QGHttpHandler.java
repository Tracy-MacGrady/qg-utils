package com.qgclient.utils.http;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.qgclient.utils.DebugUtils;
import com.qgclient.utils.ToastUtil;
import com.qgclient.utils.base.BaseApplication;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2018/1/9.
 */

public abstract class QGHttpHandler<T> implements Response.Listener<String>, Response.ErrorListener {
    private Context context;

    public QGHttpHandler(Context context) {
        this.context = context;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        onFinish();
    }

    @Override
    public void onResponse(String response) {
        try {
            Gson gson = new Gson();
            String responseVal = response.trim();
            DebugUtils.error(responseVal);
            if (!TextUtils.isEmpty(responseVal)) {
                QGHttpResponseEntity responseEntity = gson.fromJson(responseVal, QGHttpResponseEntity.class);
                if (responseEntity.getNeedkicklogout() == 1) {
                    BaseApplication.getInstance().logout();
                    return;
                }
                if (responseEntity != null) {
                    if (responseEntity.getCode().equals("10000") || responseEntity.getCode().equals("E0000000") || responseEntity.getResult().equals("E_SUCCESS")) {
                        if (responseEntity.getData() != null) {
                            String data = gson.toJson(responseEntity.getData());
                            if (!TextUtils.isEmpty(data)) {
                                T t = gson.fromJson(data, getType());
                                if (t != null) onSuccess(t);
                            }
                        }
                    }
                } else {
                    if (responseEntity.getCode().equals("100007")) {
                        BaseApplication.getInstance().logout();
                    } else onFailure(responseEntity.getDesc(), responseEntity.getCode());
                }
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            onFailure("", "");
        } finally {
            onFinish();
        }
    }

    public abstract void onSuccess(T t);

    public void onFailure(String errorMsg, String errorCode) {
        if (!TextUtils.isEmpty(errorMsg)) ToastUtil.show(context, errorMsg);
    }

    public void onFinish() {

    }

    /**
     * 获取当前的类型
     *
     * @return
     */
    private Type getType() {
        Type type = String.class;
        Type mySuperClass = this.getClass().getGenericSuperclass();
        if (mySuperClass instanceof ParameterizedType)
            type = ((ParameterizedType) mySuperClass).getActualTypeArguments()[0];
        return type;
    }
}
