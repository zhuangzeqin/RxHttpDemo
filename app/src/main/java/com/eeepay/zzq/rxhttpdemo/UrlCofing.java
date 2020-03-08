package com.eeepay.zzq.rxhttpdemo;

import rxhttp.wrapper.annotation.DefaultDomain;
import rxhttp.wrapper.annotation.Domain;

/**
 * 描述：RxHttp共有3种指定域名的方式，按优先级排名分别是：手动输入域名 > 指定非默认域名 > 使用默认域名。
 * 作者：zhuangzeqin
 * 时间: 2020/2/26-11:11
 * 邮箱：zzq@eeepay.cn
 * 备注: 现实开发中，也会有动态域名切换的需求，如域名被封、或者需要根据服务端下发的域名去配置，这对于RxHttp来说简直就是 so easy !!! 我们只需要对BaseUrl重新赋值，此时发请求便会立即生效
 */
public class UrlCofing {
    @DefaultDomain
    public static String baseUlr = "http://co-api.51wnl.com/";//默认的域名地址
    @Domain(name = "zzq")/** ------注释说明--通过@Domain()注解标注非默认域名，就会在RxHttp类中生成setDomainToXxxIfAbsent()方法，其中Xxx就是注解中取的别名。------ **/
    public static String wanandroid = "https://www.wanandroid.com/";//动态域名地址；非默认域名，并取别名为BaseUrlWanandroid 有时我们需要跟不同的后台人员调试，这时候就可以用到动态域名
    //以后测试环境打包的APP地址用这个阿里云外网域名地址
    @Domain(name = "sdb_test")
    public static final String AGENTAPI2_BASEURL = "http://agentapi2.sqianbao.cn/";


}
