package com.eeepay.zzq.rxhttpdemo.bean;

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2020/3/25-14:26
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class User {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    private String name;
    private int age;

}
