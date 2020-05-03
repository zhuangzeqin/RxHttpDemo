package com.eeepay.zzq.rxhttpdemo.bean;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2020/5/3-21:18
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
@RequiredArgsConstructor(staticName = "testmenthod")// 会生成一个包含常量，和标识了NotNull的变量的构造方法。生成的构造方法是private，如何想要对外提供使用可以使用staticName选项生成一个static方法。
@Data//@ToString, @EqualsAndHashCode, 所有属性的@Getter, 所有non-final属性的@Setter和@RequiredArgsConstructor的组合，通常情况下，我们使用这个注解就足够了。
public class TestLombok {
    @NonNull
    private int id;
    @NonNull
    private String name;
}
