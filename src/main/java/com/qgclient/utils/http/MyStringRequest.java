package com.qgclient.utils.http;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qgclient.utils.AppUtils;
import com.qgclient.utils.userauth.AuthCookie;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/9.
 */

public class MyStringRequest extends StringRequest {
    private boolean isGetCookie = false;
    private Context context;

    public MyStringRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    public MyStringRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("FROM", "mobile");
        headers.put("User-Agent", AppUtils.getUAStr(context, QGHttpConstant.getInstance().getAppname()));
        headers.put("Referer", QGHttpConstant.getInstance().getReferer());
        String cookie = "";
        if (AuthCookie.getCookie() != null) {
            for (int i = 0; i < AuthCookie.getCookie().size(); i++) {
                cookie += AuthCookie.getCookie().get(i).toString() + ";";
            }
        }
        headers.put("Cookie", cookie);
        return headers;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        if (isGetCookie()) {
            Map<String, String> headers = response.headers;
            Type type = new TypeToken<List<String>>() {
            }.getType();
            String cookies = headers.get("Set-Cookie");
            if (!TextUtils.isEmpty(cookies)) {
                List<String> cookieList = new Gson().fromJson(cookies, type);
                if (cookieList != null && cookieList.size() > 0) {
                    for (int i = 0, size = cookieList.size(); i < size; i++) {
                        String val = cookieList.get(i);
                        if (val.contains(QGHttpConstant.getInstance().getCookieKeyUS() + "=deleted;")
                                || val.contains(QGHttpConstant.getInstance().getCookieKeyUI() + "=deleted;")
                                || val.contains(QGHttpConstant.getInstance().getCookieKeyAL() + "=deleted;")) {
                            cookieList.remove(i);
                            i--;
                            size--;
                        }
                    }
                    AuthCookie.setCookie(cookieList);
                }
            }
        }
        return super.parseNetworkResponse(response);
    }

    public void setGetCookie(boolean getCookie) {
        isGetCookie = getCookie;
    }

    public boolean isGetCookie() {
        return isGetCookie;
    }
}
