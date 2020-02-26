package com.eeepay.zzq.rxhttpdemo.mvp;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

/**
 * User: ljx
 * Date: 2019-05-26
 * Time: 15:20
 */
public class Presenter {
    private TestModel testModel;
    private Object mTag;//设置请求的tag
    private LifecycleOwner owner;
    private ResultCallBack mResultCallBack;//监听的返回接口

    private Presenter(Builder builder) {
        this.mTag = builder.tag;
        this.owner = builder.owner;
        this.mResultCallBack = builder.resultCallBack;
    }

    /**
     * 获取Builder 实例
     *
     * @return
     */
    public static Builder with(LifecycleOwner owner) {
        return new Builder(owner);
    }

    public static class Builder {
        private Object tag;//设置请求的tag
        private ResultCallBack resultCallBack;//监听的返回接口
        private LifecycleOwner owner;

        public Builder(LifecycleOwner owner) {
            this.owner = owner;
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
         * Builder类中的成员函数返回Builder对象自身的另一个作用就是让它支持链式调用，使代码可读性大大增强。
         *
         * @return
         */
        public Presenter build() {
            return new Presenter(this);
        }
    }

    /**
     * 开始请求数据
     */
    public void start() {
//        if (mResultCallBack == null)
//            throw new IllegalStateException("===ResultCallBack is null,you can must implement.===");
        testModel = new TestModel(owner);
        testModel.testModel();
    }
    /**
     * 开始请求数据
     */
    public void reqestTestModel() {
//        if (mResultCallBack == null)
//            throw new IllegalStateException("===ResultCallBack is null,you can must implement.===");
        testModel = new TestModel(owner);
        testModel.testModel();
    }

    /**
     * 将结果返回给外部调用者使用
     **/
    public interface ResultCallBack {//外围实现这个接口的时候

        void onSucceed(Object tag, String data);//成功

        void onFailure(Object tag, String msg);//失败
    }

}
