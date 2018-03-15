package com.zzpdream.hotel.entity;

/**
 * Created by Administrator on 2018/3/15.
 */
public class ReturnData<T> {
    public int code;
    public String msg;
    public T data;

    public ReturnData(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
