package com.qgclient.utils.userauth;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qgclient.utils.http.QGHttpConstant;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class UserAuthAbstract<T> {

    /**
     * 验证用户信息
     */
    protected abstract boolean authUser(Context context);

    /**
     * 登录
     * 100
     *
     * @param context
     * @param userInfo
     */
    protected abstract void login(Context context, T userInfo);


    /**
     * 强制用户身份失效
     *
     * @param context
     */
    protected abstract void invinvalidateUserIdentity(Context context);

    /**
     * 检查cookie
     *
     * @param context
     * @param authToken
     * @return
     */
    protected boolean checkAuthTokenValid(Context context, final T userinfo, String authToken) {
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        ArrayList<String> cookieStrList = new Gson().fromJson(authToken, type);
        if (cookieStrList != null) {
            List<MyCookie> cookies = cookieString2Cookie(cookieStrList);
            if (cookies != null && cookies.size() > 0) {
                //检查cookie的有效时间
                for (MyCookie cookie : cookies) {
                    if (QGHttpConstant.getInstance().getCookieKeyAL().equals(cookie.getName())) {
                        if (!cookie.getExpires().before(new Date())) {
                            loadUserInfo(userinfo, cookieStrList);
                            return true;
                        }
                        return false;
                    }
                }
            }
        }
        return false;
    }

    protected List<MyCookie> cookieString2Cookie(ArrayList<String> strList) {
        List<MyCookie> cookies = new ArrayList<MyCookie>();
        for (String val : strList) {
            MyCookie myCookie = new MyCookie();
            String[] keyValues = val.split(";");
            for (int i = 0; i < keyValues.length; i++) {
                String[] values = keyValues[i].trim().split("=");
                if (i == 0 && values.length > 1) {
                    myCookie.setName(values[0].trim());
                    myCookie.setValue(values[1].trim());
                }
                if (values.length > 1 && values[0].trim().equals("expires")) {
                    myCookie.setExpires(new Date(values[1].trim()));
                }
            }
            cookies.add(myCookie);
        }
        return cookies;
    }

    /**
     * 加载用户信息
     *
     * @param userInfo
     */
    protected abstract void loadUserInfo(T userInfo, ArrayList<String> cookieVal);

    /**
     * 重新启动应用
     */
    protected void restartApplication(Context context) {
        if (context != null) {
            final Intent intent = context.getPackageManager()
                    .getLaunchIntentForPackage(context.getPackageName());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        }
    }

    /**
     * 修改用户信息
     */
    protected abstract void updateUserInfo(Context context);
}
