package com.huatec.hiot_cloud.test.networktest;

import java.io.Serializable;

public class loginResultDTO implements Serializable {
    private String tokenValue;

    private String uuid;

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
