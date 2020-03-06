package com.eeepay.zzq.rxhttpdemo.parse;

import java.io.Serializable;

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2020/3/5-17:17
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class ResultCallBack<T> implements Serializable {
    public int code;// 标识码
    public String message;//错误提示语
    public T data;//泛型T 数据
    public int count;//数据数量
    public boolean success;//是否成功； true 代表成功； false 代表不成功


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", count=" + count +
                ", success=" + success +
                '}';
    }
}
