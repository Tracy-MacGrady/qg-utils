package com.qgclient.utils.http;

/**
 * Created by Administrator on 2018/1/9.
 */

public class QGHttpConstant {
    private static QGHttpConstant instance = new QGHttpConstant();
    //HTTP REQUEST OUT TIME
    // The default socket timeout in milliseconds
    private int default_timeout_ms = 50000;
    // The default number of retries
    private int default_max_retries = 2;
    // The default backoff multiplier
    private float default_backoff_mult = 1f;
    //COOKIE KEY
    private String cookieKeyUS = "vpsus";
    private String cookieKeyUI = "vpsui";
    private String cookieKeyAL = "vpsal";
    //DOMAIN
    private String appname;
    private String referer;

    private QGHttpConstant() {
    }

    public static QGHttpConstant getInstance() {
        return instance;
    }

    public void initCookieKey(String us, String ui, String al) {
        this.cookieKeyUS = us;
        this.cookieKeyUI = ui;
        this.cookieKeyAL = al;
    }

    public int getDefault_timeout_ms() {
        return default_timeout_ms;
    }

    public int getDefault_max_retries() {
        return default_max_retries;
    }

    public float getDefault_backoff_mult() {
        return default_backoff_mult;
    }

    public String getCookieKeyUS() {
        return cookieKeyUS;
    }

    public String getCookieKeyUI() {
        return cookieKeyUI;
    }

    public String getCookieKeyAL() {
        return cookieKeyAL;
    }

    public void setDefault_timeout_ms(int default_timeout_ms) {
        this.default_timeout_ms = default_timeout_ms;
    }

    public void setDefault_max_retries(int default_max_retries) {
        this.default_max_retries = default_max_retries;
    }

    public void setDefault_backoff_mult(float default_backoff_mult) {
        this.default_backoff_mult = default_backoff_mult;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getReferer() {
        return referer;
    }
}
