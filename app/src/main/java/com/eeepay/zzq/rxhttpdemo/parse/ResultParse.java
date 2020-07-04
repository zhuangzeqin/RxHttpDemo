package com.eeepay.zzq.rxhttpdemo.parse;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Response;
import rxhttp.wrapper.annotation.Parser;
import rxhttp.wrapper.entity.ParameterizedTypeImpl;
import rxhttp.wrapper.parse.AbstractParser;

//通过@Parser注解，为解析器取别名为Result，此时就会在RxHttp类生成asResult(Class<T>)方法
@Parser(name = "Result")
public class ResultParse<T> extends AbstractParser<T> {
    protected ResultParse() {
        super();
    }

    public ResultParse(Type type) {
        super(type);
    }

    @Override
    public T onParse(Response response) throws IOException {
        final Type type = ParameterizedTypeImpl.get(Result.class, mType); //获取泛型类型
        Result<T> data = convert(response, type);
        T t = data.getData();//获取data 字段
        if (data.getErrorCode() == 0 && t != null) {
            return t;
        } else {
            if (t == null && mType == String.class) {
                /*
                 * 考虑到有些时候服务端会返回：{"errorCode":0,"errorMsg":"关注成功"}  类似没有data的数据
                 * 此时code正确，但是data字段为空，直接返回data的话，会报空指针错误，
                 * 所以，判断泛型为String类型时，重新赋值，并确保赋值不为null
                 */
                t = (T) data.getErrorMsg();
                return t;
            } else {
                return null;
            }
        }
    }
}
