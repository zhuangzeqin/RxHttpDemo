package com.eeepay.zzq.rxhttpdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.eeepay.zzq.rxhttpdemo.bean.Ariticle;
import com.rxjava.rxlife.RxLife;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import rxhttp.wrapper.param.Method;
import rxhttp.wrapper.param.RxHttp;
import rxhttp.wrapper.parse.SimpleParser;

/**
 * 描述：RxHttp是基于OkHttp的二次封装，并与RxJava做到无缝衔接，一条链就能发送任意请求
 * 1. 30秒即可上手，学习成本极低
 * <p>
 * 2. 史上最优雅的处理网络缓存
 * <p>
 * 3. 史上最优雅的处理多个BaseUrl及动态BaseUrl
 * <p>
 * 4. 史上最优雅的对错误统一处理，且不打破Lambda表达式
 * <p>
 * 5. 史上最优雅的实现文件上传/下载及进度的监听，且支持断点下载
 * <p>
 * 6. 支持Gson、Xml、ProtoBuf、FastJson等第三方数据解析工具
 * <p>
 * 7. 支持Get、Post、Put、Delete等任意请求方式，可自定义请求方式
 * <p>
 * 8. 支持在Activity/Fragment/View/ViewModel/任意类中，自动关闭请求
 * <p>
 * 9. 支持统一加解密，且可对单个请求设置是否加解密
 * <p>
 * 10. 支持添加公共参数/头部，且可对单个请求设置是否添加公共参数/头部
 * 作者：zhuangzeqin
 * 时间: 2020/2/26-10:55
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置debug模式，默认为false，设置为true后，发请求，过滤"RxHttp"能看到请求日志
        RxHttp.setDebug(true);
//非必须,只能初始化一次，第二次将抛出异常
//        RxHttp.init(OkHttpClient okHttpClient)
//或者，调试模式下会有日志输出
//        RxHttp.init(OkHttpClient okHttpClient, boolean debug)


        btn_test = (Button) this.findViewById(R.id.btn_test);
        btn_test.setOnClickListener(v -> {

        });
    }

    private void getTestInfo1() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.clear();
        hashMap.put("timestamp", "1462377600");
        hashMap.put("type", 1);
        hashMap.put("client", "ceshi");
        RxHttp.postEncryptForm("calendar/vacations").test(88,78).
                add("token", "A2E0C3CDEA081D3BFC34F8FE23A15886").//通过add 方式添加参数
                addAll(hashMap).//也可以通过map 添加参数
                asString().
                observeOn(AndroidSchedulers.mainThread()).//AndroidSchedulers.mainThread() && Schedulers.io() 线程切换
                doOnSubscribe(disposable -> {
            Log.d("main", "请求开始，当前在主线程回调");
        }).doFinally(() -> {
            Log.d("main", "请求结束，当前在主线程回调");
        }).
                as(RxLife.asOnMain(this))
                .subscribe(s -> {
                    Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
                    Log.d("main", s);
                    btn_test.setText(s);
                }, throwable -> {
                    Log.d("main", throwable.getMessage());
                });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_test:
                getTestInfo1();
//                Presenter.with(this).build().reqestTestModel();
//                setDomainUrl();
//                getAriticle();
                break;
            default:

                break;
        }
    }

    /**
     * 查看某个公众号历史数据
     */
    private void getAriticle() {
        //多个占位符n$标识占位符的顺序如何请求(单个占位符%d即可)
//        RxHttp.get("wxarticle/list/%1$d/%2$d/json", 480, 1).setDomainTozzqIfAbsent()
//                .asObject(Ariticle.class)//确定返回数据类型 实体bean ariticle
//                .as(RxLife.asOnMain(this))
//                .subscribe(ariticle -> {
//                    Log.d("RxHttp", "回调成功" + ariticle.getData().getDatas().get(0).getShareUser());
//                }, throwable -> {
//                    Log.d("RxHttp", "回调失败" + throwable.getMessage());
//                });

        RxHttp.get("wxarticle/list/%1$d/%2$d/json", 480, 1).setDomainTozzqIfAbsent()
                .asResult(Ariticle.DataBean.class)//确定返回数据类型 实体bean ariticle
                .as(RxLife.asOnMain(this))
                .subscribe(dataBean  -> {
                    Log.d("RxHttp", "回调成功" + dataBean.getDatas().get(0).getLink());
                }, throwable -> {
                    Log.d("RxHttp", "回调失败" + throwable.getMessage());
                });
    }

    /**
     * 同步请求； 比如我们的token 失效之后 需要同步获取最新的token 值；然后将获取到token 值继续请求刚才的接口数据
     */
    private void reqExecute() {
        try {
            // 获取到最新的token，这里需要同步请求token,千万不能异步 根据自己的业务修改
            String token = RxHttp.postForm("url").execute(SimpleParser.get(String.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //设置公共参数，请求头
        setPubParam();

    }
    //设置公共参数，请求头
    //我们需要调用RxHttp.setOnParamAssembly(Function)方法，并传入一个Function接口对象，每次发起请求，都会回调该接口。
    private void setPubParam() {
        RxHttp.setOnParamAssembly(param -> {//此方法在子线程中执行，即请求发起线程
            Method method = param.getMethod();
            if (method.isGet()) {     //可根据请求类型添加不同的参数
                //get 请求
            } else if (method.isPost()) {
                //post 请求
            }
            param.add("token", "147258369");//添加公共参数
            param.addHeader("device", "android");//添加公共请求头
            return param;
        });

//        如果希望某个请求不回调该接口，即不添加公共参数/请求头，则可以调用setAssemblyEnabled(boolean)方法，并传入false即可，如下：
        RxHttp.postForm("url").setAssemblyEnabled(false)//设置是否添加公共参数/头部，默认为true
                .asString()
                .as(RxLife.asOnMain(this))
                .subscribe(s -> {
                    Log.d("RxHttp", "回调成功" + s);
                }, throwable -> {
                    Log.d("RxHttp", "回调失败" + throwable.getMessage());
                });
    }

    //设置数据解密/解码器
    private void setDecoderCode() {
        /**
         * 有些时候，请求会返回一大串的密文，此时就需要将密文转化为明文，直接来看代码，如下：
         *
         */
        RxHttp.setResultDecoder(new Function<String, String>() {
            //每次请求成功，都会回调这里，并传入请求返回的密文
            @Override
            public String apply(String s) throws Exception {
                String plaintext = "decode(s)";   //将密文解密成明文，解密逻辑自己实现
                //接口会在每次请求成功的时候被回调，并传入请求返回的密文，只需要将密文解密后返回即可。
                return plaintext;    //返回明文
            }
        });
        //如果一些特殊的接口不需要解密的话； 设置setDecoderEnabled 为false 即可
        RxHttp.get("url")
                .setDecoderEnabled(false)//设置本次请求不需要解密，默认为true
                .asString()
                .as(RxLife.asOnMain(this))
                .subscribe(s -> {
                    Log.d("RxHttp", "回调成功" + s);
                }, throwable -> {
                    Log.d("RxHttp", "回调失败" + throwable.getMessage());
                });
    }

    /**
     * 指定请求/回调线程（注意点）
     * //1 指定请求所在线程，需要在第二部曲前任意位置调用，第二部曲后调用无效
     * //2 指定回调所在线程，需要在第二部曲后调用
     */
    private void setSchedulers() {
        //1 获取disposable 手动关闭请求
        Disposable disposable = RxHttp.postForm("url").
                subscribeOnCurrent()//指定在当前线程执行请求，即同步执行，
                .asString().//第二部曲
                observeOn(AndroidSchedulers.mainThread())//指定在主线程回调
                .subscribe(s -> {
                    Log.d("RxHttp", "回调成功" + s);
                }, throwable -> {
                    Log.d("RxHttp", "回调失败" + throwable.getMessage());
                });
        //手动关闭请求
        if (!disposable.isDisposed()) {
            disposable.dispose(); //没有结束，则关闭请求
        }
        //自动关闭；自动关闭请求，需要引入本人开源的另一个库RxLife
        //as(RxLife.as(this)) //页面销毁、自动关闭请求
        //as(RxLife.asOnMain(this)) //页面销毁、自动关闭请求 并且在主线程回调观察者

    }

    /**
     * 动态域名设置
     */
    private void setDomainUrl() {
        //获取公众号列表
        RxHttp.get("wxarticle/chapters/json").
                setDomainTozzqIfAbsent()//有时为了方便也是可以直接写死对方的ip地址进行调试
                .asString()
                .as(RxLife.asOnMain(this))
                .subscribe(s -> {
                    Log.d("RxHttp", "回调成功" + s);
                }, throwable -> {
                    Log.d("RxHttp", "回调失败" + throwable.getMessage());
                });
    }


}
