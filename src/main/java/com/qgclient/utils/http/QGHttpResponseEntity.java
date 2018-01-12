package com.qgclient.utils.http;

import android.text.TextUtils;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2018/1/9.
 */

public class QGHttpResponseEntity {
    private String result;
    private String code;
    private Object data;
    private String desc;
    private int needkicklogout;

    public String getResult() {
        return TextUtils.isEmpty(result) ? "" : result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCode() {
        return TextUtils.isEmpty(code) ? "" : code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDesc() {
        return TextUtils.isEmpty(desc) ? "" : desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getNeedkicklogout() {
        return needkicklogout;
    }

    public void setNeedkicklogout(int needkicklogout) {
        this.needkicklogout = needkicklogout;
    }
}
