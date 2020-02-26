package com.eeepay.zzq.rxhttpdemo.mvp;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;

import com.rxjava.rxlife.BaseScope;
import com.rxjava.rxlife.RxLife;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2020/2/26-12:31
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class TestModel extends BaseScope {
    public TestModel(LifecycleOwner owner) {
        super(owner);
    }

    public void testModel()
    {
        Observable.interval(1, 1, TimeUnit.SECONDS)
                .as(RxLife.as(this)) //这里的this 为Scope接口对象
                .subscribe(aLong -> {
                    Log.e("LJX", "accept aLong=" + aLong);
                });
    }
}
