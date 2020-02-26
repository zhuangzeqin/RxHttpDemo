package com.eeepay.zzq.rxhttpdemo.mvp;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2020/2/26-12:42
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class BasePresenter implements LifecycleOwner {
    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return null;
    }

    private Object mTag;//设置请求的tag
    private ResultCallBack mResultCallBack;//监听的返回接口

    private BasePresenter(Builder builder)
    {
        this.mTag = builder.tag;
        this.mResultCallBack = builder.resultCallBack;
    }
    /**
     * 获取Builder 实例
     * @return
     */
    public static Builder with()
    {
        return new Builder();
    }

    public static class Builder {
        private Object tag;//设置请求的tag
        private ResultCallBack resultCallBack;//监听的返回接口

        public Builder() {
        }

        public Builder setTag(Object tag) {
            this.tag = tag;
            return this;
        }

        public Builder setResultCallBack(@NonNull ResultCallBack resultCallBack) {
            this.resultCallBack = resultCallBack;
            return this;
        }

        /**
         * 静态内部类调用外部类的构造函数，来构造外部类
         *Builder类中的成员函数返回Builder对象自身的另一个作用就是让它支持链式调用，使代码可读性大大增强。
         * @return
         */
        public BasePresenter build() {
            return new BasePresenter(this);
        }
    }
    /**
     * 开始请求数据
     */
    public void start()
    {
        if (mResultCallBack == null)
            throw new IllegalStateException("===ResultCallBack is null,you can must implement.===");
    }
    /**
     * 将结果返回给外部调用者使用
     **/
    public interface ResultCallBack {//外围实现这个接口的时候

        void onSucceed(Object tag, String data);//成功

        void onFailure(Object tag, String msg);//失败
    }
}
