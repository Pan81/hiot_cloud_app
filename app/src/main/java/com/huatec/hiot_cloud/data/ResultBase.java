package com.huatec.hiot_cloud.data;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResultBase<T> implements Serializable {
/*
返回对象
 */

    T data;
    /*
返回描述
 */
    String msg;
    /*
返回状态
 */
    int status;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
