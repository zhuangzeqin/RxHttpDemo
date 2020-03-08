package com.eeepay.zzq.rxhttpdemo.bean;

import java.io.Serializable;

/**
 * 描述：公共参数, 放在请求头 user-agent json字符串
 * 作者：zhuangzeqin
 * 时间: 2019/5/15-13:49
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class AppDeviceInfo implements Serializable {
    private String name;             // Mi 5s plus
    private String systemName;       // android / ios
    private String systemVersion;    // 8.0.0
    private String deviceId;         // 设备id
    private String appNo;            // app类型
    private String appVersion;       // app版本
    private String appBuild;        // app构建版本
    private String appChannel;      // app渠道
    private String loginToken;      // 登陆token
    private String timestamp;         //当前时间戳
    private String appName;             //app 项目名---比如盛代宝
    private String sign;                //签名
    private String jpushDevice;      // 极光推送设备id

    public String getJpushDevice() {
        return jpushDevice;
    }

    public void setJpushDevice(String jpushDevice) {
        this.jpushDevice = jpushDevice;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }


    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }


    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public String getAppNo() {
        return appNo;
    }

    public void setAppNo(String appNo) {
        this.appNo = appNo;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppBuild() {
        return appBuild;
    }

    public void setAppBuild(String appBuild) {
        this.appBuild = appBuild;
    }

    public String getAppChannel() {
        return appChannel;
    }

    public void setAppChannel(String appChannel) {
        this.appChannel = appChannel;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    @Override
    public String toString() {
        return "AppDeviceInfo{" +
                "name='" + name + '\'' +
                ", systemName='" + systemName + '\'' +
                ", systemVersion='" + systemVersion + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", appBuild='" + appBuild + '\'' +
                ", appChannel='" + appChannel + '\'' +
                ", loginToken='" + loginToken + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", appName='" + appName + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
