package com.eeepay.zzq.rxhttpdemo.adapter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

/**
 * 描述：json 对null 值的处理
 * Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullToEmptyAdapterFactory()).create();
 * 作者：zhuangzeqin
 * 时间: 2018/7/24-15:59
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class NullToEmptyAdapterFactory<T> implements TypeAdapterFactory {
    @Override
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<T> rawType = (Class<T>) type.getRawType();
        if (rawType == String.class) {
            return (TypeAdapter<T>) new StringNullAdapter();
        } else if (rawType == Boolean.class) {
            return (TypeAdapter<T>) new BooleanNullAdapter();

        } else if (rawType == Double.class) {
            return (TypeAdapter<T>) new DoubleNullAdapter();
        } else if (rawType == Long.class) {
            return (TypeAdapter<T>) new LongNullAdapter();
        } else if (rawType == Integer.class) {
            return (TypeAdapter<T>) new IntegerNullAdapter();
        }
        return null;
    }

}
