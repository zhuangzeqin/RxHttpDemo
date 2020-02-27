package com.eeepay.zzq.rxhttpdemo.param;

import java.util.Map;

import okhttp3.RequestBody;
import rxhttp.wrapper.annotation.Param;
import rxhttp.wrapper.param.JsonParam;
import rxhttp.wrapper.param.Method;
/**
 * 描述：postJson请求，需要将所有的参数，也就是json字符串加密后再发送出去
 * 作者：zzq
 * 时间：2020/2/27 11:11
 * 邮箱：1546374673@qq.com
 */
@Param(methodName = "postEncryptJson")
public class PostEncryptJsonParam  extends JsonParam {

    /**
     * @param url    请求路径
     * Method#POST  Method#PUT  Method#DELETE  Method#PATCH
     */
    public PostEncryptJsonParam(String url) {
        super(url, Method.POST);
    }

    @Override
    public RequestBody getRequestBody() {
        //这里拿到你添加的所有参数
        Map<String, Object> params = getParams();
        String encryptStr = "加密后的字符串";  //根据上面拿到的参数，自行实现解密逻辑
        return RequestBody.create(MEDIA_TYPE_JSON, encryptStr);  //发送加密后的字符串
    }
}
