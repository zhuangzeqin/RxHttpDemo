package com.eeepay.zzq.rxhttpdemo.parse;

/**
 * 描述：后台统一的数据格式封装
 * 作者：zhuangzeqin
 * 时间: 2020/2/26-17:41
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class Result<T> {
    private int errorCode;
    private String errorMsg;
    private T data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
