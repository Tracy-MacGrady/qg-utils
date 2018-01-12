package com.qgclient.utils.http;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qgclient.utils.AppUtils;
import com.qgclient.utils.DebugUtils;
import com.qgclient.utils.NetUtils;
import com.qgclient.utils.NetworkUtils;
import com.qgclient.utils.R;
import com.qgclient.utils.ToastUtil;
import com.qgclient.utils.userauth.AuthCookie;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2018/1/9.
 */

public class QGHttpClient {
    private static QGHttpClient instance;
    private RequestQueue requestQueue;
    private DefaultRetryPolicy retryPolicy;

    private QGHttpClient() {
    }

    public static QGHttpClient getInstance() {
        if (instance == null) {
            synchronized (QGHttpClient.class) {
                instance = new QGHttpClient();
            }
        }
        return instance;
    }

    /**
     * 初始化
     *
     * @param requestQueue1
     */
    public void initHttpClient(RequestQueue requestQueue1) {
        this.requestQueue = requestQueue1;
        this.retryPolicy = new DefaultRetryPolicy(QGHttpConstant.getInstance().getDefault_timeout_ms(), QGHttpConstant.getInstance().getDefault_max_retries(), QGHttpConstant.getInstance().getDefault_backoff_mult());
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public void get(Context context, String requestUrl, HashMap<String, String> params, QGHttpHandler<?> handler) {
        get(context, requestUrl, params, false, handler);
    }

    public void get(Context context, String requestUrl, HashMap<String, String> params, boolean isGetCookie, QGHttpHandler<?> handler) {
        if (context == null || TextUtils.isEmpty(requestUrl)) {
            if (handler != null) handler.onFailure("参数错误！", "-100001");
        } else {
            String URL = getUrlWithQueryString(true, requestUrl, params);
            DebugUtils.error(URL);
            if (!NetworkUtils.hasNetWork(context)) {
                // TODO: 2018/1/9 获取缓存数据 如果缓存数据为空则提示网络连接状态
                Cache.Entry cacheEntry = requestQueue.getCache().get(URL);
                if (handler != null) {
                    if (cacheEntry != null) handler.onResponse(new String(cacheEntry.data));
                    else handler.onFailure(context.getString(R.string.check_network), "-100001");
                }
            } else {
                MyStringRequest stringRequest = new MyStringRequest(URL, handler, handler);
                stringRequest.setContext(context);
                stringRequest.setGetCookie(isGetCookie);
                stringRequest.setRetryPolicy(retryPolicy);
                stringRequest.setTag(context);
                requestQueue.add(stringRequest);
            }
        }
    }

    public void post(Context context, String requestUrl, HashMap<String, String> params, QGHttpHandler<?> handler) {
        post(context, requestUrl, params, false, handler);
    }

    public void post(Context context, String requestUrl, HashMap<String, String> params, boolean isGetCookie, QGHttpHandler<?> handler) {
        if (context == null || TextUtils.isEmpty(requestUrl)) {
            if (handler != null) handler.onFailure("参数错误！", "-100001");
        } else {
            DebugUtils.error(requestUrl);
            if (!NetworkUtils.hasNetWork(context)) {
                if (handler != null)
                    handler.onFailure(context.getString(R.string.check_network), "-100001");
            } else {
                MyStringRequest stringRequest = new MyStringRequest(Request.Method.POST, requestUrl, handler, handler);
                stringRequest.setContext(context);
                stringRequest.setGetCookie(isGetCookie);
                stringRequest.setRetryPolicy(retryPolicy);
                stringRequest.setTag(context);
                requestQueue.add(stringRequest);
            }
        }
    }


    public String getUrlWithQueryString(boolean shouldEncodeUrl, String url, HashMap<String, String> params) {
        if (shouldEncodeUrl)
            url = url.replace(" ", "%20");
        if (params != null) {
            String paramString = getParamsList(params).trim();
            if (!paramString.equals("") && !paramString.equals("?")) {
                url += url.contains("?") ? "&" : "?";
                url += paramString;
            }
        }
        return url;
    }

    public String getParamsList(HashMap<String, String> params) {
        List<BasicNameValuePair> lparams = new LinkedList<BasicNameValuePair>();
        for (ConcurrentHashMap.Entry<String, String> entry : params.entrySet()) {
            lparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return URLEncodedUtils.format(lparams, HTTP.UTF_8);
    }
}
