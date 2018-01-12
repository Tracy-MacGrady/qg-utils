package com.qgclient.utils.userauth;

import java.util.Date;

/**
 * Created by lijianjian on 2016/7/5.
 */
public class MyCookie {
    private String name;
    private String value;
    private Date expires;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
