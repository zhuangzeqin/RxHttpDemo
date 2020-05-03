package com.eeepay.zzq.rxhttpdemo.bean;

import android.util.Log;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2020/3/25-14:26
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
@Builder(toBuilder = true)//@Builder声明实体，表示可以进行Builder方式初始化
@Getter
@ToString
public class User {
    private String name;
    private int age;
    private BigDecimal price;
    @Builder.ObtainVia(field = "id")
    private final String id = UUID.randomUUID().toString();
    @Builder.ObtainVia(field = "insertTime")
    private final long insertTime = System.currentTimeMillis();
    @Singular(value = "testhobbies")
    private List<String> hobbies;

    public void start() {
        Log.d("user", "调用了start方法");
    }
}
