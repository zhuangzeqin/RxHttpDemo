package com.eeepay.zzq.rxhttpdemo.parse;

import java.io.IOException;
import java.lang.reflect.Type;

import rxhttp.wrapper.annotation.Parser;
import rxhttp.wrapper.entity.ParameterizedTypeImpl;
import rxhttp.wrapper.parse.AbstractParser;

/**
 * 描述：通过@Parser注解，为解析器取别名为Response，此时就会在RxHttp类生成asResult(Class<T>)方法
 * 作者：zhuangzeqin
 * 时间: 2020/2/26-17:42
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
@Parser(name ="Result")
public class ResponseParser<T> extends AbstractParser<T> {
    //省略构造方法
    @Override
    public T onParse(okhttp3.Response response) throws IOException {
        final Type type = ParameterizedTypeImpl.get(Result.class, mType); //获取泛型类型
        Result<T> data = convert(response, type);
        T t = data.getData(); //获取data字段
//        if (data.getErrorCode() != 0 || t == null) {//这里假设code不等于0，代表数据不正确，抛出异常
////            throw new ParseException(String.valueOf(data.getCode()), data.getMsg(), response);
//        }
        return t;
    }
}
