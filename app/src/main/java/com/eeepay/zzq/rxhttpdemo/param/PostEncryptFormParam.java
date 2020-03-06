package com.eeepay.zzq.rxhttpdemo.param;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

import com.eeepay.zzq.rxhttpdemo.adapter.NullToEmptyAdapterFactory;
import com.eeepay.zzq.rxhttpdemo.bean.AppDeviceInfo;
import com.eeepay.zzq.rxhttpdemo.utils.Md5;
import com.eeepay.zzq.rxhttpdemo.utils.UserData;
import com.eeepay.zzq.rxhttpdemo.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.RequestBody;
import rxhttp.wrapper.annotation.Param;
import rxhttp.wrapper.entity.KeyValuePair;
import rxhttp.wrapper.param.FormParam;
import rxhttp.wrapper.param.Method;

/**
 * 描述：postForm请求，需要将所有添加的参数，拼接在一起，随后加密，最后将加密的字符串添加到请求头中
 * 作者：zzq
 * 时间：2020/2/27 10:58
 * 邮箱：1546374673@qq.com
 */
@Param(methodName = "postEncryptForm")
public class PostEncryptFormParam extends FormParam {
    //Gson 处理Null 值的问题
    private static final Gson GSON = new GsonBuilder().registerTypeAdapterFactory(new NullToEmptyAdapterFactory()).create();
    //再拼接的字符串后加上key=46940880d9f79f27bb7f85ca67102bfdylkj@@agentapi2#$$^&pretty
    private static final String KEY_VALUE = "key=46940880d9f79f27bb7f85ca67102bfdylkj@@agentapi2#$$^&pretty";

    /**
     * @param url 请求路径
     *            Method#POST  Method#PUT  Method#DELETE  Method#PATCH
     */
    public PostEncryptFormParam(String url) {
        super(url, Method.POST);
    }

    /**
     * 自定义api
     *
     * @param a
     * @param b
     * @return
     */
    public PostEncryptFormParam test(long a, float b) {
        //这里的业务逻辑自行实现
        return this;
    }

    /**
     * 自定义apid
     *
     * @param s
     * @param b
     * @return
     */
    public PostEncryptFormParam test1(String s, double b) {
        //这里的业务逻辑自行实现
        return this;
    }


    @Override
    public RequestBody getRequestBody() {
        TreeMap<String, Object> rootMap = new TreeMap<>();
        String app_info = "";
        //这里拿到你添加的所有参数
        List<KeyValuePair> keyValuePairs = getKeyValuePairs();
        for (KeyValuePair keyValuePair : keyValuePairs) {
            Log.d("PostEncryptFormParam", "KEY: " + keyValuePair.getKey());
            Log.d("PostEncryptFormParam", "value: " + keyValuePair.getValue());
            rootMap.put(keyValuePair.getKey(), keyValuePair.getValue());
        }
        app_info = signInfo(rootMap);
//        String encryptStr = "加密后的字符串";  //根据上面拿到的参数，自行实现加密逻辑
        addHeader("Content-Type", "application/json; charset=UTF-8");
        addHeader("Connection", "keep-alive");
        addHeader("Accept", "*/*");
        addHeader("app-info", app_info);
        return super.getRequestBody();
    }

    /**
     * zhuangzeqin 2020年3月5日16:25:25
     * 签名构建信息
     *
     * @param rootMap
     * @return
     */
    private static String signInfo(TreeMap<String, Object> rootMap) {
        String timestamp = System.currentTimeMillis() + "";//时间戳
        rootMap.put("timestamp", timestamp);
        String loginToken = UserData.getInstance().getLoginToken();//登录的token
        rootMap.put("loginToken", loginToken);
        //1 遍历map key拼接  key=value&
        //2 value 空的不拼接
        String data = "";
        Iterator iter = rootMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            // 获取key
            String key = (String) entry.getKey();
            // 获取value
            Object value1 = entry.getValue();
            if (value1 instanceof String) {
                String value = (String) entry.getValue();
                if (!TextUtils.isEmpty(value)) {
                    data = data + key + "=" + value + "&";
                }
            } else if (value1 instanceof Boolean) {
                Boolean value = (Boolean) entry.getValue();
                if (value != null) {
                    data = data + key + "=" + value + "&";
                }
            } else if (value1 instanceof Integer) {
                Integer value = (Integer) entry.getValue();
                if (value != null) {
                    data = data + key + "=" + value + "&";
                }
            } else if (value1 instanceof Long) {
                Long value = (Long) entry.getValue();
                if (value != null) {
                    data = data + key + "=" + value + "&";
                }
            } else if (value1 instanceof Double) {
                Double value = (Double) entry.getValue();
                if (value != null) {
                    data = data + key + "=" + value + "&";
                }
            } else if (value1 instanceof Float) {
                Float value = (Float) entry.getValue();
                if (value != null) {
                    data = data + key + "=" + value + "&";
                }
            }
        }
        //再拼接的字符串后加上key=46940880d9f79f27bb7f85ca67102bfdylkj@@agentapi2#$$^&pretty
        data = data + KEY_VALUE;
        //最后对拼接完成的字符串进行md5签名,然后以sign为key放到请求body中
        String sign = Md5.encode(data);
        //重新组装 装换成json字符串
        String app_info = getAppDeviceInfo(sign, timestamp);
        return app_info;
    }

    /**
     * 公共参数转换为json 字符串
     *
     * @return
     */
    private static String getAppDeviceInfo(String sign, String timestamp) {
        try {
            AppDeviceInfo appDeviceInfo = new AppDeviceInfo();
            String appName = "盛代宝";//Utils.getApp().getResources().getString(R.string.lib_app_name);// app的项目名比如盛代宝后面有可能是OEM
            appDeviceInfo.setAppName(appName);
            appDeviceInfo.setName(android.os.Build.MODEL); // 型号 Mi 5s plus
            appDeviceInfo.setSystemName("android");// android / ios
            appDeviceInfo.setSystemVersion(android.os.Build.VERSION.RELEASE);//系统版本 8.0.0
            appDeviceInfo.setDeviceId(getDeviceId());//设备id
            appDeviceInfo.setAppVersion(getVersionName());//app版本
            appDeviceInfo.setAppBuild(Utils.getUUID());//app构建版本
            String appNo = "2";// app的标志
            appDeviceInfo.setAppNo(appNo);
            appDeviceInfo.setAppChannel(appNo);//app渠道---
            String loginToken = UserData.getInstance().getLoginToken();
//        String loginToken = PreferenceUtils.getStringParam(ConfigPathConstants.SP_LOGINTOKEN, "unknow");
            appDeviceInfo.setLoginToken(loginToken);//登陆token
            appDeviceInfo.setTimestamp(timestamp);//当前的时间戳
            appDeviceInfo.setSign(sign);
            appDeviceInfo.setJpushDevice("123456");//获取极光推送的注册的id
            String jsonData = GSON.toJson(appDeviceInfo);//公共参数转换为json 字符串
            //最好编码一下； 防止乱码
            String encode = URLEncoder.encode(jsonData, "UTF-8");//App传递给后台时候编码
            return encode;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 返回安卓设备ID
     */
    @SuppressLint("HardwareIds")
    private static String getDeviceId() {
        String id = "NO Search";
        id = android.provider.Settings.Secure.getString(
                Utils.getApp().getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);
        return id;
    }

    // 获得软件版本名
    private static String getVersionName() {
        String versionname = "unknow";
        try {
            versionname = Utils.getApp().getPackageManager().getPackageInfo(
                    Utils.getApp().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionname;
    }
}
