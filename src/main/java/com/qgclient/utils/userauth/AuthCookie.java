package com.qgclient.utils.userauth;

import java.util.List;

/**
 * Created by 李健健 on 2017/7/11.
 */

public class AuthCookie {
    private static List<String> cookies;

    public static List<String> getCookie() {
        return cookies;
    }

    public static void setCookie(List<String> cookieVal) {
        cookies = cookieVal;
    }
}
