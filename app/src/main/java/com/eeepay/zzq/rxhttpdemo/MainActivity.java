package com.eeepay.zzq.rxhttpdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.eeepay.zzq.rxhttpdemo.bean.Ariticle;
import com.eeepay.zzq.rxhttpdemo.bean.LoginInfo;
import com.eeepay.zzq.rxhttpdemo.bean.TestLombok;
import com.eeepay.zzq.rxhttpdemo.bean.User;
import com.eeepay.zzq.rxhttpdemo.enc.EncRSA;
import com.eeepay.zzq.rxhttpdemo.utils.UserData;
import com.eeepay.zzq.rxhttpdemo.utils.Utils;
import com.rxjava.rxlife.RxLife;

import org.apache.commons.text.StringEscapeUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import rxhttp.wrapper.callback.Function;
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

    private Button btn_test, btn_test2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.init(this);
        RxHttpManager.init();//初始化操作
        //设置debug模式，默认为false，设置为true后，发请求，过滤"RxHttp"能看到请求日志
//        RxHttp.setDebug(true);
//非必须,只能初始化一次，第二次将抛出异常
//        RxHttp.init(OkHttpClient okHttpClient)
//或者，调试模式下会有日志输出
//        RxHttp.init(OkHttpClient okHttpClient, boolean debug)
        btn_test = (Button) this.findViewById(R.id.btn_test);
        btn_test2 = (Button) this.findViewById(R.id.btn_test2);
        btn_test.setOnClickListener(this);
        btn_test2.setOnClickListener(this);
        User user = User.builder().age(31).name("zhuangzeqin").
                price(new BigDecimal(2546389)).testhobbies("123").testhobbies("456").build();//        Log.d("user",user.toString());
        Log.d("user", user.toString());
        user.start();
//        TestLombok testLombok = new TestLombok(50,"edcddd");
//        testLombok.setId(36);
//        testLombok.setName("abc");
//        Log.d("user",testLombok.toString());
        TestLombok yangbaohong = TestLombok.testmenthod(36, "yangbaohong");
        Log.d("user", yangbaohong.toString());
    }

    private void getTestInfo1() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.clear();
        hashMap.put("timestamp", "1462377600");
        hashMap.put("type", 1);
        hashMap.put("client", "ceshi");
        RxHttp.postEncryptForm("calendar/vacations").test(88, 78).
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
//                getTestInfo1();
//                Presenter.with(this).build().reqestTestModel();
//                setDomainUrl();
//                getAriticle();
                testLogin();
                break;
            case R.id.btn_test2:
                tojson();
//                testGetRequest();
                break;
            default:

                break;
        }
    }


    private void tojson() {
        String str1 = "{\"resourceId\":\"dfead70e4ec5c11e43514000ced0cdcaf\",\"properties\":{\"process_id\":\"process4\",\"name\":\"\",\"documentation\":\"\",\"processformtemplate\":\"\"}}";
        System.out.println("tmp1:" + str1);
//        String tmp = StringEscapeUtils.unescapeJava(str1);
        String tmp = StringEscapeUtils.unescapeJson(str1);
        System.out.println("tmp2:" + tmp);

//        String tmp = StringEscapeUtils.unescapeJson(jsonData);

    }

    private void testLogin() {
        String encpassword = "";
        try {
            encpassword = EncRSA.EncPass("abc888888");//RSA 加密密码
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        //1 通过自定义参数注解@Param进行加密 postJson请求，需要将所有的参数，也就是json字符串加密后再发送出去
        //2 通拦截器 拦截所有的参数进行加密
        RxHttp.postEncryptJson("agentApi2/login").setDomainTosdb_testIfAbsent().
                add("userName", "13888888888").
                add("password", encpassword).
                add("agentOem", "200010")
                .asResultCallBack(LoginInfo.DataBean.class)
                .as(RxLife.asOnMain(this))
                .subscribe(response -> {
                    //保存用户信息到sp里
                    UserData userData = UserData.getInstance();
                    userData.setUserName(response.getUserName());//用户名 前海移联直营
                    userData.setPassword(response.getPassword());//密码
                    userData.setAgentOem(response.getAgentOem());//组织ID 200010
                    userData.setUserId(response.getUserId());//用户id 1000000000000002897
                    userData.setAgentNo(response.getAgentNo());//代理商编号 1446
                    userData.setAgentNode(response.getAgentNode());//代理商节点 0-1446-
                    userData.setAgentName(response.getAgentName());//代理商名称 E前海移联直营read
                    userData.setLoginToken(response.getLoginToken());//登录的token 57005982-4283-43c8-a776-96f974d3dc4c
                    userData.setAgentLevel(response.getAgentLevel());//代理商级别 1
                    userData.setParentId(response.getParentId());//父id
                    userData.setOneLevelId(response.getOneLevelId());//一级等级id
                    userData.setMobilePhone(response.getMobilePhone());//手机号
                    userData.setManage(response.getManage());//角色管理 1
                    userData.setLockTime(response.getLockTime());//锁住时间
                    userData.setWrongPasswordCount(response.getWrongPasswordCount());//密码错误次数 0
                    userData.setOneAgentNo(response.getOneAgentNo());//一级代理商编号
                    userData.setTeamId(response.getTeamId());//组织iD
                    userData.setLogin(true);//是否登录过
                    Toast.makeText(this, userData.toString(), Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    Log.d("RxHttp", "登录失败" + throwable.getLocalizedMessage());
                });
    }

    private void testGetRequest() {
        RxHttp.getEncrypt("agentApi2/terminalApplyRecord/countTerminalApplyRecord/%s", UserData.getInstance().getAgentNo()).setDomainTosdb_testIfAbsent()
                .asString()
                .as(RxLife.asOnMain(this))
                .subscribe(s -> {
                    Log.d("RxHttp", "回调成功" + s);
                }, throwable -> {
                    Log.d("RxHttp", "回调失败" + throwable.getMessage());
                });
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
                .subscribe(dataBean -> {
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
