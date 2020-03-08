package com.eeepay.zzq.rxhttpdemo.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * 描述：String 对null 值的处理
 * 作者：zhuangzeqin
 * 时间: 2018/7/24-15:54
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class StringNullAdapter extends TypeAdapter<String> {
    @Override
    public void write(JsonWriter writer, String value) throws IOException {
        // TODO Auto-generated method stub
        if (value == null) {
            writer.nullValue();
            return;
        }
        writer.value(value);
    }

    @Override
    public String read(JsonReader reader) throws IOException {
        // TODO Auto-generated method stub
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return "";
        }
        return reader.nextString();
    }
}
