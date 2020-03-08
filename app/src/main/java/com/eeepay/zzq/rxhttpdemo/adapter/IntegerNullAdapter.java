package com.eeepay.zzq.rxhttpdemo.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * 描述：integer 对null 值的处理
 * 作者：zhuangzeqin
 * 时间: 2018/7/24-15:54
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class IntegerNullAdapter extends TypeAdapter<Integer> {
    @Override
    public void write(JsonWriter writer, Integer value) throws IOException {
        if(value==null) {
            writer.nullValue();
            return;
        }
        writer.value(value);
    }

    @Override
    public Integer read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return 0;
        }
        return reader.nextInt();
    }
}
