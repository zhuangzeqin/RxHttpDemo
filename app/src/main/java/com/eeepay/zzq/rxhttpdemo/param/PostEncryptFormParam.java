package com.eeepay.zzq.rxhttpdemo.param;

import android.nfc.Tag;
import android.util.Log;

import java.util.List;

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
    /**
     * @param url 请求路径
     *            Method#POST  Method#PUT  Method#DELETE  Method#PATCH
     */
    public PostEncryptFormParam(String url) {
        super(url, Method.POST);
    }

    /**
     * 自定义api
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
        //这里拿到你添加的所有参数
        List<KeyValuePair> keyValuePairs = getKeyValuePairs();
        for (KeyValuePair keyValuePair : keyValuePairs) {
            Log.d("PostEncryptFormParam", "KEY: "+keyValuePair.getKey());
            Log.d("PostEncryptFormParam", "value: "+keyValuePair.getValue());
        }
        String encryptStr = "加密后的字符串";  //根据上面拿到的参数，自行实现加密逻辑
//        addHeader("encryptStr", encryptStr);
        return super.getRequestBody();
    }
}
