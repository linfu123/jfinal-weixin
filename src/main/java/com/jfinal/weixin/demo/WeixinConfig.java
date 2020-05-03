/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.demo;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.template.Engine;
import com.jfinal.template.source.ClassPathSourceFactory;
import com.jfinal.weixin.model._MappingKit;
import com.jfinal.weixin.report.ReportController;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;


public class WeixinConfig extends JFinalConfig {
    // 本地开发模式
    private boolean isLocalDev = true;

    /**
     * 如果生产环境配置文件存在，则优先加载该配置，否则加载开发环境配置文件
     * @param pro 生产环境配置文件
     * @param dev 开发环境配置文件
     */
    public void loadProp(String pro, String dev) {
        try {
            PropKit.use(pro);
        }
        catch (Exception e) {
            PropKit.use(dev);
            isLocalDev = true;
        }
    }

    @Override
    public void configConstant(Constants me) {
        loadProp("a_little_config_pro.txt", "a_little_config.txt");
        me.setDevMode(PropKit.getBoolean("devMode", false));
       // me.setBaseDownloadPath(PathKit.getWebRootPath() + "/file/");
        // ApiConfigKit 设为开发模式可以在开发阶段输出请求交互的 xml 与 json 数据
        ApiConfigKit.setDevMode(me.getDevMode());
    }

    @Override
    public void configRoute(Routes me) {
        /**
         * jfinal 3.6 新添加的配置项，如果有控制器继承了 MsgController 就必须
         * 要添加下面的配置，该配置才能将超类 MsgController 中的 index() 方法
         * 映射为 action
         *
         * 使用 jfinal 3.6 之前的版本不必理会这项配置
         */
        me.setMappingSuperClass(true);

        me.add("/msg", WeixinMsgController.class);
        me.add("/api", WeixinApiController.class);
        me.add("/report",ReportController.class);
//        me.add("/pay", WeixinPayController.class);
       // me.add("/wxa/user", WxaUserApiController.class);
       // me.add("/wxa/information",WxaInfomationController.class);
//        me.add("/subscribemsg", SubscribeMsgController.class);
        me.add("/demo", DemoController.class);
    }

    @Override
    public void configPlugin(Plugins me) {
    	  DruidPlugin druidPlugin = new DruidPlugin (PropKit.get("jdbcUrl").trim(), PropKit.get("user").trim(), PropKit.get("password").trim());
//        // 这里是做映射
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        arp.setShowSql(true);
        arp.setShowSql(isLocalDev);
		arp.setDialect(new MysqlDialect());
//		arp.setBaseSqlTemplatePath(PathKit.getRootClassPath());//该方法会把资源目录定义到test-class目录下
		arp.getEngine().setSourceFactory(new ClassPathSourceFactory());
		arp.addSqlTemplate("jxt.sql");//复杂sql写在这个文件中 以namespace和sqlname获取对应sql，sql条件可用质量写
		//获取sql可以使用dao.getSqlPara或Db.getSqlPara获取
		arp.getSqlKit().getEngine().addSharedObject("sk", new com.jfinal.kit.StrKit());//添加共享sql引擎
        //注意，如果有数据库的话，这里还是需要添加其他的配置的
        //数据库映射统一使用GeneratorModel生成，无需而外配置
        _MappingKit.mapping(arp);
//        //添加到插件列表中
        me.add(druidPlugin);
        me.add(arp);

         EhCachePlugin ecp = new EhCachePlugin();
         me.add(ecp);

//        // 使用redis分布accessToken
//         RedisPlugin redisPlugin = new RedisPlugin("weixin", "127.0.0.1");
//        // redisPlugin.setSerializer(JdkSerializer.me); // 需要使用fst高性能序列化的用户请删除这一行（Fst jar依赖请查看WIKI）
//         me.add(redisPlugin);
    }

    @Override
    public void configInterceptor(Interceptors me) {
        // 设置默认的 appId 规则，默认值为appId，可采用url挂参数 ?appId=xxx 切换多公众号
        // ApiInterceptor.setAppIdParser(new AppIdParser.DefaultParameterAppIdParser("appId")); 默认无需设置
        // MsgInterceptor.setAppIdParser(new AppIdParser.DefaultParameterAppIdParser("appId")); 默认无需设置
    }

	@Override
	public void configEngine(Engine engine) {
	}
	
    @Override
    public void configHandler(Handlers me) {
    }

    public void afterJFinalStart() {
        // 1.5 之后支持redis存储access_token、js_ticket，需要先启动RedisPlugin
//        ApiConfigKit.setAccessTokenCache(new RedisAccessTokenCache());
        // 1.6新增的2种初始化
//        ApiConfigKit.setAccessTokenCache(new RedisAccessTokenCache(Redis.use("weixin")));
//        ApiConfigKit.setAccessTokenCache(new RedisAccessTokenCache("weixin"));

        ApiConfig ac = new ApiConfig();
        // 配置微信 API 相关参数
        ac.setToken(PropKit.get("token"));
        ac.setAppId(PropKit.get("appId"));
        ac.setAppSecret(PropKit.get("appSecret"));
//
//        /**
//         *  是否对消息进行加密，对应于微信平台的消息加解密方式：
//         *  1：true进行加密且必须配置 encodingAesKey
//         *  2：false采用明文模式，同时也支持混合模式
//         */
        ac.setEncryptMessage(PropKit.getBoolean("encryptMessage", false));
        ac.setEncodingAesKey(PropKit.get("encodingAesKey", "setting it in config file"));

        /**
         * 多个公众号时，重复调用ApiConfigKit.putApiConfig(ac)依次添加即可，第一个添加的是默认。
         */
        ApiConfigKit.putApiConfig(ac);
//        String str = "{\n" +
//                "    \"button\": [\n" +
//                "        {\n" +
//                "            \"name\": \"进入理财\",\n" +
//                "            \"url\": \"http://m.bajie8.com/bajie/enter\",\n" +
//                "            \"type\": \"view\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "             \"name\": \"信息登记\",\n" +
//                "            \"url\": \"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx309f5291ebecfa38&redirect_uri=http://uz2px4.natappfree.cc/jsp/form4.html&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect\",\n" +
//                "\t          \"type\": \"view\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "\t    \"name\": \"模板\",\n" +
//                "\t    \"key\": \"112\",\n" +
//                "\t    \"type\": \"click\"\n" +
//                "        }\n" +
//                "    ]\n" +
//                "}";
//        ApiResult apiResult=MenuApi.createMenu(str);
//        System.out.println(apiResult.getJson());

        /**
         * 1.9 新增LocalTestTokenCache用于本地和线上同时使用一套appId时避免本地将线上AccessToken冲掉
         *
         * 设计初衷：https://www.oschina.net/question/2702126_2237352
         *
         * 注意：
         * 1. 上线时应保证此处isLocalDev为false，或者注释掉该不分代码！
         *
         * 2. 为了安全起见，此处可以自己添加密钥之类的参数，例如：
         * http://localhost/weixin/api/getToken?secret=xxxx
         * 然后在WeixinApiController#getToken()方法中判断secret
         *
         * @see WeixinApiController#getToken()
         */
//        if (isLocalDev) {
//            String onLineTokenUrl = "http://localhost/weixin/api/getToken";
//            ApiConfigKit.setAccessTokenCache(new LocalTestTokenCache(onLineTokenUrl));
//        }
       // WxaConfig wc = new WxaConfig();
        //wc.setAppId(PropKit.get("appId").trim());
        //wc.setAppSecret(PropKit.get("appSecret").trim());
        //WxaConfigKit.setWxaConfig(wc);
        //设置消息模板
//        TemplateMsgApi.setIndustry("1", "2");
    }
    
   

    public static void main(String[] args) {
        JFinal.start("src/main/webapp", 80, "/", 5);
    }
}
