package com.eeepay.zzq.rxhttpdemo.parse;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;

import okhttp3.Response;
import rxhttp.wrapper.annotation.Parser;
import rxhttp.wrapper.entity.ParameterizedTypeImpl;
import rxhttp.wrapper.exception.ParseException;
import rxhttp.wrapper.parse.AbstractParser;

//通过@Parser注解，为解析器取别名为Result，此时就会在RxHttp类生成asResult(Class<T>)方法
@Parser(name = "ResultCallBack")
public class ResultCallBackParse<T> extends AbstractParser<T> {
    public ResultCallBackParse() {
        super();
    }

    public ResultCallBackParse(Class<T> type) {
        super(type);
    }

    @Override
    public T onParse(Response response) throws IOException {
        final Type type = ParameterizedTypeImpl.get(ResultCallBack.class, mType); //获取泛型类型
        ResultCallBack<T> data = convert(response, type);
        int code = data.code;//code 标识码 401
        String message = data.message;//错误提示语
        int count = data.count;//数据数量总数
        T t = data.getData();//获取data 字段
        if (HttpURLConnection.HTTP_UNAUTHORIZED == code) {
            // 标识码 401 被登录拦截了下来了
            Log.d("ResultCallBackParse", "<----401 被登录拦截了下来了,需要调转到登录页面---->");
            throw new ParseException(String.valueOf(code), message, response);
            // 需要调转到登录页面，把参数跟被登录拦截下来的路径传递给登录页面，登录成功后再进行跳转被拦截的页面
//            ARouter.getInstance().build(LOGIN_PATH).withFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).navigation();
        }
        //不成功的情况
        if (!data.isSuccess()) {
            if (!TextUtils.isEmpty(message)) {//不为空的；提示错误信息
                throw new ParseException(String.valueOf(code), "温馨提示："+message, response);
            }
        }
        /*
         * 考虑到有些时候服务端会返回：{"errorCode":0,"errorMsg":"关注成功"}  类似没有data的数据
         * 此时code正确，但是data字段为空，直接返回data的话，会报空指针错误，
         * 所以，判断泛型为String类型时，重新赋值，并确保赋值不为null
         */
        if (t == null && mType == String.class) {
            t = (T) message;
            return t;
        }
        return t;
    }
}
